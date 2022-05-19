package ru.maksonic.elmaks.data.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.maksonic.elmaks.core.CloudObject
import ru.maksonic.elmaks.core.di.IoDispatcher
import ru.maksonic.elmaks.core.store.ResourceProvider
import ru.maksonic.elmaks.data.R
import ru.maksonic.elmaks.data.common.FakeDataException
import ru.maksonic.elmaks.data.common.JsonConverter

/**
 * @Author maksonic on 19.05.2022
 */
interface BaseCloudDataSource<C : CloudObject> {
    fun fetchCloudList(): DataList<C>

    abstract class Base<C : CloudObject>(
        private val jsonConverter: JsonConverter,
        private val rp: ResourceProvider,
        @IoDispatcher private val dispatcher: CoroutineDispatcher
    ) : BaseCloudDataSource<C> {
        private companion object {
            private const val FAKE_DELAY = 3000L
        }

        override fun fetchCloudList() = flow<Result<List<C>>> {
         //   delay(FAKE_DELAY)
            val conversion = jsonConverter.convertAssertJsonToString(jsonFileName)

            conversion.onSuccess { rawString ->
                val cloudList = cloudList(rawString)
                if (cloudList.isEmpty()) {
                    emit(
                        Result.failure(
                            FakeDataException(
                                rp.getString(R.string.error_failed_cloud_fetching)
                            )
                        )
                    )
                } else {
                    emit(Result.success(cloudList))
                }
            }
            conversion.onFailure { throwable ->
                emit(Result.failure(throwable))
            }
        }.flowOn(dispatcher)

        protected abstract val jsonFileName: String
        protected abstract fun cloudList(rawString: String): List<C>

    }
}
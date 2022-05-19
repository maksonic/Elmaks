package ru.maksonic.elmaks.feature.main.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import ru.maksonic.elmaks.core.ui.theme.ElmaksTheme
import ru.maksonic.elmaks.feature.main.model.Model
import ru.maksonic.elmaks.feature.main.model.Msg
import ru.maksonic.elmaks.feature.main.update.MainViewModel
import ru.maksonic.elmaks.feature.main.widget.CityItem
import ru.maksonic.elmaks.feature.main.widget.MainHeader

/**
 * @Author maksonic on 18.05.2022
 */
@Composable
internal fun SuccessCitiesViewState(model: Model, sendMsg: Message) {
    val cities = if (model.inputSearchCity.value.text.isEmpty()) model.citiesList
    else model.searchedList

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = model.isRefreshing),
        onRefresh = { sendMsg(Msg.Ui.OnSwipeRefreshList) },
        indicator = { state, trigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = trigger,
                scale = true,
                contentColor = ElmaksTheme.color.primary,
                backgroundColor = ElmaksTheme.color.surface,
            )
        }
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            item {
                MainHeader(model)
            }
            when {
                cities.isNotEmpty() -> {
                    items(cities) { city ->
                        CityItem(
                            city = city,
                            selectCity = { sendMsg(Msg.Ui.OnCityClicked(city.kladrId)) })
                    }
                }
                cities.isEmpty() -> item { EmptySearchResultViewState() }
            }
        }
    }
}

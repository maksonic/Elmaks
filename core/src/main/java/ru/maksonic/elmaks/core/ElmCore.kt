package ru.maksonic.elmaks.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * @author maksonic on 12.05.2022
 */
interface StateModel
interface Message
interface Command

interface ElmProgram<M : Message, C : Command> {
    suspend fun execute(cmd: C, consumer: (M) -> Unit)
}

interface Sandbox<T : StateModel, M : Message, C : Command> {
    fun sendMsg(msg: M)
    fun update(msg: M, model: T): Pair<T, Set<C>>
}

abstract class ElmRuntime<T : StateModel, M : Message, C : Command>(
    private val initialModel: T,
    private val initialCmd: Set<C> = emptySet(),
    private val subscriptions: List<ElmProgram<M, C>>
) : ViewModel(), Sandbox<T, M, C> {

    private val _model: MutableStateFlow<T> = MutableStateFlow(initialModel)
    val featureModel: StateFlow<T>
        get() = _model

    init {
        viewModelScope.launch {
            executePrograms(initialCmd)
        }
    }

    override fun sendMsg(msg: M) {
        val (updatedModel, commands) = update(msg, _model.value)
        _model.value = updatedModel

        executePrograms(commands)
    }

    private fun executePrograms(commands: Set<C>) {
        commands.forEach { cmd ->
            viewModelScope.launch {
                subscriptions.forEach { subscription ->
                    subscription.execute(cmd, ::sendMsg)
                }
            }
        }
    }
}
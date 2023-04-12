package com.michona.fitify.domain.repository

import android.content.Context
import com.google.gson.JsonParser
import com.michona.fitify.domain.WhileUiSubscribed
import com.michona.fitify.domain.data.InstructionMap
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import java.io.Closeable
import java.io.InputStreamReader

/**
 * Loads the Instructions JSON into memory (into [instructions] SharedFlow).
 * It's exposed as a [StateFlow] to support the reactive approach, i.e whenever is ready the UI can respond to it.
 * */
class InstructionRepository(private val context: Context, private val dispatcher: CoroutineDispatcher) : Closeable {

    private val job = SupervisorJob()
    private val scope = CoroutineScope(dispatcher + job)

    private val parser by lazy { JsonParser() }

    private val _instructions = MutableSharedFlow<InstructionMap>(replay = 0)
    val instructions: StateFlow<InstructionMap> = _instructions.stateIn(scope, WhileUiSubscribed, mapOf())

    /**
     * Reads the [INSTRUCTIONS_FILE] and loads the [InstructionMap] into [_instructions], only if not already loaded.
     * */
    suspend fun loadInstructions(force: Boolean = false) {
        /* no need to load if already loaded and is not forceful. */
        if (instructions.value.isNotEmpty() && !force) return

        withContext(dispatcher) {
            context.assets.open(INSTRUCTIONS_FILE).use {
                val jsonObject = parser.parse(InputStreamReader(it, "UTF-8")).asJsonObject
                _instructions.emit(jsonObject.keySet().associateWith { jsonObject.get(it).asString })
            }
        }
    }

    companion object {
        private const val INSTRUCTIONS_FILE = "instructions.json"
    }

    override fun close() {
        job.complete()
        scope.cancel()
    }
}

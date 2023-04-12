package com.michona.fitify

import android.app.Application
import android.content.Context
import com.michona.fitify.domain.local.localModule
import com.michona.fitify.domain.remote.networkModule
import com.michona.fitify.rules.MainCoroutineRule
import io.mockk.mockkClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule

class KoinModulesTest {

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        mockkClass(clazz)
    }

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Test
    fun `test DI modules`() {
        koinApplication {
            modules(appModule, networkModule, localModule, dispatchersKoinModule)
            checkModules {
                withInstance<Context>()
                withInstance<Application>()
            }
        }
    }
}

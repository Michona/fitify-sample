package com.michona.fitify.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michona.fitify.navigation.INavigator
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    fun navigateWith(navigator: INavigator, intent: suspend INavigator.() -> Unit) {
        viewModelScope.launch {
            intent.invoke(navigator)
        }
    }
}

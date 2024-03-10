package fi.paradox.p4

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    // Interná MutableLiveData, ktorú môžeme aktualizovať
    private val _myLiveData = MutableLiveData<String>()
    // Inicializácia StateFlow s predvolenou hodnotou
    private val _myStateFlow = MutableStateFlow("Initial Value")
    val myStateFlow: StateFlow<String> = _myStateFlow

    // Externá LiveData, ktorú môže pozorovať UI (aktivita/fragment)
    val myLiveData: LiveData<String> = _myLiveData

    // Funkcia na aktualizáciu dát v LiveData
    fun updateDataLiveData(newData: String) {
        _myLiveData.value = newData
    }

    // Funkcia na aktualizáciu hodnoty v StateFlow
    fun updateDataCoroutine(newValue: String) {
        viewModelScope.launch {
            _myStateFlow.value = newValue
        }
    }

    // Prípadne ďalšie funkcie pre manipuláciu s dátami alebo logiku biznis pravidiel
}
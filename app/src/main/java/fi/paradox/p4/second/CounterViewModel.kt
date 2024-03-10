package fi.paradox.p4.second

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CounterViewModel : ViewModel() {
    // Inicializácia MutableStateFlow s počiatočnou hodnotou 0
    val counter = MutableStateFlow(0)

    fun incrementCounter() {
        // Spustenie asynchrónnej úlohy v viewModelScope
        viewModelScope.launch(Dispatchers.IO) {
            // Simulácia dlhšej operácie, napríklad siete alebo výpočtu
//            delay(1000)  // 1 sekundové oneskorenie

            // Získanie aktuálnej hodnoty, inkrementácia a emitovanie novej hodnoty
            val currentValue = counter.value
            counter.emit(currentValue + 1)
        }
    }
}
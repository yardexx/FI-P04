package fi.paradox.p4.second

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import fi.paradox.p4.R
import fi.paradox.p4.second.fragment.Fragment1
import fi.paradox.p4.second.fragment.Fragment2

class MainActivityFragment : BaseActivity() {
    private lateinit var viewModel: CounterViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_fragment)
        viewModel = ViewModelProvider(this)[CounterViewModel::class.java]

        // Načítanie predvoleného fragmentu pri štarte
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, Fragment1())
            commit()
        }

        // Nastavenie listenerov pre tlačidlá
        findViewById<Button>(R.id.button_fragment1).setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, Fragment1())
                commit()
            }
        }

        findViewById<Button>(R.id.button_fragment2).setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, Fragment2())
                commit()
            }
        }

        // Pozorovanie zmeny counter a aktualizácia TextView
        lifecycleScope.launchWhenStarted {
            viewModel.counter.collect { count ->
                findViewById<TextView>(R.id.counter).text = count.toString()
            }
        }
    }
}
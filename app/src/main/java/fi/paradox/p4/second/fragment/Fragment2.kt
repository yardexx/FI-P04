package fi.paradox.p4.second.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fi.paradox.p4.MainViewModel
import fi.paradox.p4.R
import fi.paradox.p4.second.CounterViewModel

class Fragment2 : BaseFragment() {
    private lateinit var viewModel: CounterViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<FrameLayout>(R.id.fragment_parent)?.let {
            it.setBackgroundColor(Color.GRAY)
        }
        view.findViewById<TextView>(R.id.item)?.let {
            it.setText(this@Fragment2.toString())
        }
    }
}

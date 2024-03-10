package fi.paradox.p4.second.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fi.paradox.p4.LIFECYCLE_TAG
import fi.paradox.p4.ON_ACTIVITY_CREATED
import fi.paradox.p4.ON_ATTACH
import fi.paradox.p4.ON_CREATE
import fi.paradox.p4.ON_CREATE_VIEW
import fi.paradox.p4.ON_DESTROY
import fi.paradox.p4.ON_DESTROY_VIEW
import fi.paradox.p4.ON_DETACH
import fi.paradox.p4.ON_PAUSE
import fi.paradox.p4.ON_RESUME
import fi.paradox.p4.ON_START
import fi.paradox.p4.ON_STOP

open class BaseFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i(LIFECYCLE_TAG, "${this@BaseFragment}$ON_ATTACH")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(LIFECYCLE_TAG, "${this@BaseFragment}$ON_CREATE")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i(LIFECYCLE_TAG, "${this@BaseFragment} $ON_CREATE_VIEW")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i(LIFECYCLE_TAG, "${this@BaseFragment} $ON_ACTIVITY_CREATED")
    }

    override fun onStart() {
        super.onStart()
        Log.i(LIFECYCLE_TAG, "${this@BaseFragment} $ON_START")
    }

    override fun onResume() {
        super.onResume()
        Log.i(LIFECYCLE_TAG, "${this@BaseFragment} $ON_RESUME")
    }

    override fun onPause() {
        super.onPause()
        Log.i(LIFECYCLE_TAG, "${this@BaseFragment} $ON_PAUSE")
    }

    override fun onStop() {
        super.onStop()
        Log.i(LIFECYCLE_TAG, "${this@BaseFragment} $ON_STOP")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(LIFECYCLE_TAG, "${this@BaseFragment} $ON_DESTROY_VIEW")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(LIFECYCLE_TAG, "${this@BaseFragment} $ON_DESTROY")
    }

    override fun onDetach() {
        super.onDetach()
        Log.i(LIFECYCLE_TAG, "${this@BaseFragment} $ON_DETACH")
    }
}

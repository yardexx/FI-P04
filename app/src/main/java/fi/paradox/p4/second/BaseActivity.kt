package fi.paradox.p4.second

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import fi.paradox.p4.LIFECYCLE_TAG
import fi.paradox.p4.ON_CONFIGURATION_CHANGE
import fi.paradox.p4.ON_CREATE
import fi.paradox.p4.ON_DESTROY
import fi.paradox.p4.ON_PAUSE
import fi.paradox.p4.ON_RESUME
import fi.paradox.p4.ON_START
import fi.paradox.p4.ON_STOP

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(LIFECYCLE_TAG, ON_CREATE)
    }

    override fun onStart() {
        super.onStart()
        Log.i(LIFECYCLE_TAG, ON_START)
    }

    override fun onResume() {
        super.onResume()
        Log.i(LIFECYCLE_TAG, ON_RESUME)
    }

    override fun onPause() {
        super.onPause()
        Log.i(LIFECYCLE_TAG, ON_PAUSE)
    }

    override fun onStop() {
        super.onStop()
        Log.i(LIFECYCLE_TAG, ON_STOP)
    }

    override fun onDestroy() {
        Log.i(LIFECYCLE_TAG, ON_DESTROY)
        super.onDestroy()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.i(LIFECYCLE_TAG, ON_CONFIGURATION_CHANGE)
    }
}
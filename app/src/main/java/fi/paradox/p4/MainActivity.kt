package fi.paradox.p4

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope

class MainActivity : AppCompatActivity() {
    private lateinit var rotateAnimation: Animation
    private lateinit var viewModel: MainViewModel

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null

    private val sensorListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            // Logovanie údajov z akcelerometra
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]
            Log.i(ACCELEROMETER_TAG, "x: $x, y: $y, z: $z")
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Inicializácia ViewModelu
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        Log.i(LIFECYCLE_TAG, ON_CREATE)
        loadAnimation()
        loadAccelerometer()
        saveDataToSharedPref()
    }

    override fun onStart() {
        super.onStart()
        Log.i(LIFECYCLE_TAG, ON_START)
        initAccelerometer()
        // Inicializácia observerov v onStart()
        viewModel.myLiveData.observe(this, Observer { data ->
            // Aktualizujte UI na základe zmeny dát
            updateUI(data)
        })
        lifecycleScope.launchWhenStarted {
            viewModel.myStateFlow.collect { data ->
                // Aktualizujte UI na základe zmeny dát
                updateUI(data)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.i(LIFECYCLE_TAG, ON_RESUME)
        fadeInAnimation()
        startRotateAnimation()
    }

    override fun onPause() {
        super.onPause()
        Log.i(LIFECYCLE_TAG, ON_PAUSE)
        // Zastavenie animácie
        findViewById<ImageView>(R.id.image_view_id).clearAnimation()
    }

    override fun onStop() {
        super.onStop()
        Log.i(LIFECYCLE_TAG, ON_STOP)
        // Zrušenie registrácie listenera senzora
        sensorManager.unregisterListener(sensorListener)
    }

    override fun onDestroy() {
        clearSecretData()
        Log.i(LIFECYCLE_TAG, ON_DESTROY)
        super.onDestroy()
    }
    private fun loadAccelerometer(){
        // Inicializácia senzorového manažéra a akcelerometra
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }
    private fun initAccelerometer(){
        // Registrácia listenera senzora s predvolenou frekvenciou aktualizácií
        accelerometer?.also { accel ->
            sensorManager.registerListener(sensorListener, accel, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }
    private fun loadAnimation(){
        // Načítanie animácie
        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate).apply {
            // Nastavenie AnimationListenera
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                    Log.i(ANIMATION_TAG, "Animácia začala")
                }

                override fun onAnimationEnd(animation: Animation?) {
                    Log.i(ANIMATION_TAG, "Animácia skončila")
                }

                override fun onAnimationRepeat(animation: Animation?) {
                    Log.i(ANIMATION_TAG, "Animácia sa opakuje")
                }
            })
        }
    }
    private fun startRotateAnimation(){
        // Aplikovanie animácie na ImageView
        val imageView = findViewById<ImageView>(R.id.image_view_id)
        imageView.startAnimation(rotateAnimation)
    }
    private fun fadeInAnimation() {
        // Načítanie animácie
        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        fadeInAnimation.duration = 2000 // Nastavte na 2000 milisekúnd (2 sekundy)
        // Aplikovanie animácie na TextView
        val textView = findViewById<TextView>(R.id.text_view_id)
        textView.startAnimation(fadeInAnimation)
    }

    private fun updateUI(data: String) {
        // Logika pre aktualizáciu UI
        // viewbinding/compose
    }
    private fun saveDataToSharedPref(){
        // Uloženie dát do SharedPreferences
        val sharedPreferences = getSharedPreferences(SHARED_PREF_SECRET_KEY, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString(SHARED_PREF_SECRET_KEY, "Toto je dôležita a tajna hodnota ktoru si nemôžem dovoliť mať nikde uloženu pokial aplikacianebude bežať")
            apply()
        }
    }
    private fun clearSecretData(){
//         Vymazanie dát z SharedPreferences
        val sharedPreferences = getSharedPreferences(SHARED_PREF_SECRET_KEY, Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().commit()
        Log.i(LIFECYCLE_TAG, "Mazanie dát prebehlo")
    }
}
package com.yanghyeonjin.hellokotlin

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.yanghyeonjin.hellokotlin.databinding.ActivityShakeDetectBinding
import render.animations.Attention
import render.animations.Render
import kotlin.math.sqrt

class ShakeDetectActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var binding: ActivityShakeDetectBinding

    private lateinit var sensorManager: SensorManager
    private var accel: Float = 0.0f
    private var accelCurrent: Float = 0.0f
    private var accelLast: Float = 0.0f


    companion object {
        const val TAG: String = "로그"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShakeDetectBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        this.sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        accel = 10f
        accelCurrent = SensorManager.GRAVITY_EARTH
        accelLast = SensorManager.GRAVITY_EARTH
    }


    override fun onResume() {
        super.onResume()
        Log.e(TAG, "ShakeActivity - onResume() called")

        // 센서 매니저에 리스너 설정
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "ShakeActivity - onPause() called")

        // 센서 매니저 해제
        sensorManager.unregisterListener(this)
    }



    override fun onSensorChanged(event: SensorEvent?) {
        val x: Float = event?.values?.get(0) as Float
        val y: Float = event.values?.get(1) as Float
        val z: Float = event.values?.get(2) as Float

        accelLast = accelCurrent
        accelCurrent = sqrt((x * x + y * y + z * z).toDouble()).toFloat()

        val delta: Float = accelCurrent - accelLast
        accel = accel * 0.9f + delta

        if (accel > 20) {
            Log.e(TAG, "ShakeActivity - 흔들었음 (accel: $accel)")
            binding.ivFace.setImageResource(R.drawable.ic_smile_big)

            // Create Render Class
            val render = Render(this)

            // Set Animation
            render.setAnimation(Attention().Wobble(binding.ivFace))
            render.start()

            // 1초 뒤에 기존 얼굴로 돌아가도록
            Handler().postDelayed({
                binding.ivFace.setImageResource(R.drawable.ic_smile)
            }, 2000)

        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        Log.e(TAG, "ShakeActivity - onAccuracyChanged() called")
    }
}
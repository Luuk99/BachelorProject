package e.www.sensortest

import android.hardware.Sensor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    // Constants for the sensors
    val accelerometerSensor = Sensor.TYPE_ACCELEROMETER
    val ambientTempSensor = Sensor.TYPE_AMBIENT_TEMPERATURE
    val gravitySensor = Sensor.TYPE_GRAVITY
    val gyroscopeSensor = Sensor.TYPE_GYROSCOPE
    val lightSensor = Sensor.TYPE_LIGHT
    val linearAccelerationSensor = Sensor.TYPE_LINEAR_ACCELERATION
    val magneticFieldSensor = Sensor.TYPE_MAGNETIC_FIELD
    val pressureSensor = Sensor.TYPE_PRESSURE
    val proximitySensor = Sensor.TYPE_PROXIMITY
    val relativeHumiditySensor = Sensor.TYPE_RELATIVE_HUMIDITY
    val rotationVectorSensor = Sensor.TYPE_ROTATION_VECTOR

    // Sensor listener variable
    private lateinit var sensorListener: CustomSensorListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up the sensor listener
        sensorListener = CustomSensorListener(this, accelerometerSensor)
    }

    override fun onStart() {
        // Start the sensor listener
        sensorListener.start()
        super.onStart()
    }

    override fun onStop() {
        // Stop the sensor listener
        sensorListener.stop()
        super.onStop()
    }
}

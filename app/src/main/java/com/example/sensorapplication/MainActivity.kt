package com.example.myapplication
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
class MainActivity : ComponentActivity(), SensorEventListener {
    lateinit var acc: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
        var sm:SensorManager= getSystemService(SENSOR_SERVICE) as SensorManager
        acc = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) as Sensor
        sm.registerListener(this,acc,SensorManager.SENSOR_DELAY_NORMAL)

    }
    override fun onSensorChanged(event: SensorEvent?) {
        when(event!!.sensor){
            acc->{
                valueStr.value = event.values[0].toString()+","+event.values[1].toString()+","+event.values[2].toString()
                //    Toast.makeText(applicationContext,str,Toast.LENGTH_SHORT).show()
            }
        }
        // TODO("Not yet implemented")
    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // TODO("Not yet implemented")
    }
}
var valueStr = mutableStateOf("")
@Composable
fun Greeting(name: String) {
    Text(text = "Hello ${valueStr.value}", fontSize = 50.sp)
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}

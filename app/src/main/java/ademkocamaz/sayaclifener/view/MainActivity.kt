package ademkocamaz.sayaclifener.view

import ademkocamaz.sayaclifener.R
import android.content.Context
import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
/*import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds*/
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var isFlashLightOn: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //MobileAds.initialize(this)

        /*var adRequest = AdRequest.Builder().build()
        main_adView.loadAd(adRequest)*/

        val chronometer = main_chronometer

        isFlashLightOn = false
        main_imageView.setImageResource(R.drawable.ic_baseline_flash_off_24)

        main_imageView.setOnClickListener { view ->
            if (isFlashLightOn) {
                isFlashLightOn = false
                flashLight()
                main_imageView.setImageResource(R.drawable.ic_baseline_flash_off_24)
                chronometer.stop()
                /*adRequest = AdRequest.Builder().build()
                main_adView.loadAd(adRequest)*/
            } else {
                isFlashLightOn = true
                flashLight()
                main_imageView.setImageResource(R.drawable.ic_baseline_flash_on_24)
                chronometer.base = SystemClock.elapsedRealtime()
                chronometer.start()
                /*adRequest = AdRequest.Builder().build()
                main_adView.loadAd(adRequest)*/
            }

        }
    }

    fun flashLight() {
        try {
            val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
            val cameraId = cameraManager.cameraIdList[0]
            if (isFlashLightOn) {
                cameraManager.setTorchMode(cameraId, true)
            } else {
                cameraManager.setTorchMode(cameraId, false)
            }
        } catch (exception: Exception) {
            Toast.makeText(this,exception.localizedMessage,Toast.LENGTH_LONG).show()
        }

    }


}
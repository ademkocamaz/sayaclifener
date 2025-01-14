package ademkocamaz.sayaclifener.view

import ademkocamaz.sayaclifener.R
import ademkocamaz.sayaclifener.databinding.ActivityMainBinding
import android.content.Context
import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity : AppCompatActivity() {

    private var isFlashLightOn: Boolean = false
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        val chronometer = binding.mainChronometer

        isFlashLightOn = false
        binding.mainImageView.setImageResource(R.drawable.ic_baseline_flash_off_24)

        binding.mainImageView.setOnClickListener {
            if (isFlashLightOn) {
                isFlashLightOn = false
                flashLight()
                binding.mainImageView.setImageResource(R.drawable.ic_baseline_flash_off_24)
                chronometer.stop()
            } else {
                isFlashLightOn = true
                flashLight()
                binding.mainImageView.setImageResource(R.drawable.ic_baseline_flash_on_24)
                chronometer.base = SystemClock.elapsedRealtime()
                chronometer.start()
            }

        }
        binding.mainAdView.loadAd(AdRequest.Builder().build())
        InterstitialAd.load(
            this,
            "ca-app-pub-5764318432941968/6832382131",
            AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    interstitialAd.show(this@MainActivity)
                }
            })
        setContentView(binding.root)
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
            Toast.makeText(this, exception.localizedMessage, Toast.LENGTH_LONG).show()
        }

    }


}
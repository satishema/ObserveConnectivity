package com.android.observeconnectivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import com.android.observeconnectivity.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Created by Satish V on 27/09/22.
 * Company : HighOnSwift pvt Ltd
 * Email Id : iamsatishema@gmail.com
 */

class MainActivity : AppCompatActivity() {

    private lateinit var connectivityObserver: ConnectivityObserver

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        connectivityObserver = NetworkConnectivityObserver(applicationContext)

        GlobalScope.launch(Dispatchers.IO) {
            connectivityObserver.observe().collectLatest {
                runOnUiThread {
                    binding.tvStatus.text = HtmlCompat.fromHtml(
                        "Network Status : <b>$it</b>",
                        HtmlCompat.FROM_HTML_MODE_LEGACY
                    )
                }
            }
        }
    }
}
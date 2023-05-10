package com.gamil.moahear.razavimathexam1401_1402

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gamil.moahear.razavimathexam1401_1402.databinding.ActivityMainBinding
import com.gamil.moahear.razavimathexam1401_1402.utils.NetWorkChecker
import com.gamil.moahear.razavimathexam1401_1402.utils.NetworkType

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val broadcastReceiver by lazy {
        object:BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                checkNetworkStatus()
            }

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerReceiver(broadcastReceiver, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
        binding.apply {

        }
    }

    private fun checkNetworkStatus() {
        if (NetWorkChecker(this).networkType==NetworkType.NOTHING){
            Toast.makeText(this, "لطفا به اینترنت متصل شوید", Toast.LENGTH_LONG).show()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }
}
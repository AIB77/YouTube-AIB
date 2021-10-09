package com.example.kotlinyoutube

import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class MainActivity : AppCompatActivity() {

    private lateinit var TheYouTube: YouTubePlayerView
    private lateinit var player: YouTubePlayer

    private var currentVideo = 0
    private var time = 0f

    private val Thevideos: Array<Array<String>> = arrayOf(
        arrayOf("How To Learn Android App Development ", "_TgrWBZ40cs"),
        arrayOf("What Is Cyber Security: How It Works?", "inWWhr5tnEA"),
        arrayOf("IoT - Internet of Things | What is IoT?", "6mBO2vqLv38"),
        arrayOf("What Is Deep Learning? ", "6M5VXKLf4D4"),
        arrayOf(" What Is Machine Learning?", "ukzFI9rgwfU"),
        arrayOf("What Is Cloud Computing?", "M988_fsOSWo"),
        arrayOf("What Is Blockchain?", "yubzJw0uiE4"),
        arrayOf(" What Is Lean Six Sigma?", "s2HCrhNVfak"),
        arrayOf("Data Science For Beginners ", "X3paOmcrTjQ"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        TheYouTube = findViewById(R.id.youtube)

        TheYouTube.addYouTubePlayerListener(object: AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                player = youTubePlayer
                player.loadVideo(Thevideos[currentVideo][1], time)
                RV()
            }
        })

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            TheYouTube.enterFullScreen()
        } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
            TheYouTube.exitFullScreen()
        }
    }

    private fun RV(){
        val recyclerView: RecyclerView = findViewById(R.id.videolistRV)
        recyclerView.adapter = VideoAdapter(Thevideos, player)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }


}
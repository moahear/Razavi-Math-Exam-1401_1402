package com.gamil.moahear.razavimathexam1401_1402

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.gamil.moahear.razavimathexam1401_1402.databinding.FragmentPlayVideoBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class PlayVideoFragment : Fragment() {
    private lateinit var binding: FragmentPlayVideoBinding
    private val videoUrlArgs: PlayVideoFragmentArgs by navArgs()
    private val exoPlayer by lazy {
        ExoPlayer.Builder(binding.root.context).build()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.let {
            it.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        binding = FragmentPlayVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val mediaItem=MediaItem.fromUri("https://s9.uupload.ir/files/moahear/1.mp4".toUri())
        val mediaItem1 = MediaItem.fromUri(videoUrlArgs.questionVideoUrl1.toUri())
        val mediaItem2 = MediaItem.fromUri(videoUrlArgs.questionVideoUrl2.toUri())
        exoPlayer.addMediaItem(mediaItem1)
        exoPlayer.addMediaItem(mediaItem2)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true

        exoPlayer.play()
        binding.apply {
            playerView.player = exoPlayer

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer.release()
        activity?.let {
            it.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
        }
    }
}
package com.gamil.moahear.razavimathexam1401_1402

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gamil.moahear.razavimathexam1401_1402.databinding.FragmentHomeBinding
import com.gamil.moahear.razavimathexam1401_1402.utils.Constants

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var questions: ArrayList<Question>
    private val questionAdapter by lazy {
        QuestionAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        questions = arrayListOf()
        questions.add(
            Question(
                0,
                R.drawable.q1,
                "https://s9.uupload.ir/files/moahear/1.mp4",
                "https://s9.uupload.ir/files/moahear/2_1.mp4"
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        questionAdapter.submitList(Constants.QUESTIONS)
        binding.apply {
            rvQuestions.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            rvQuestions.adapter = questionAdapter
        }
        questionAdapter.onQuestionClick { videoUrl1, videoUrl2 ->
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToPlayVideoFragment()
                    .setQuestionVideoUrl1(videoUrl1).setQuestionVideoUrl2(videoUrl2)
            )
        }
    }

}
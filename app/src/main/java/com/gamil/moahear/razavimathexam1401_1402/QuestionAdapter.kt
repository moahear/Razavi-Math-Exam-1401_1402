package com.gamil.moahear.razavimathexam1401_1402

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gamil.moahear.razavimathexam1401_1402.databinding.ItemQuestionBinding

class QuestionAdapter: RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {
    private val questions=ArrayList<Question>()
    inner class QuestionViewHolder(private val binding:ItemQuestionBinding):RecyclerView.ViewHolder(binding.root){
        fun bindQuestion(question: Question) {
            binding.apply {
            Glide.with(imgItemQuestion.context).load(question.questionImage).into(imgItemQuestion)
              //radioButton1.setCompoundDrawables(null,null,)
               // btnPlayVideo.isEnabled = rdgContainer.checkedRadioButtonId != -1
                btnPlayVideo.setOnClickListener {
                   setOnQuestionClickListener?.let {
                       it(question.videoAnswerAddress1,question.videoAnswerAddress2)
                   }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
       val binding=ItemQuestionBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return QuestionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bindQuestion(questions[position])
    }
    private var setOnQuestionClickListener:((String,String)->Unit)?=null
    fun onQuestionClick(listener:(String,String)->Unit){
        setOnQuestionClickListener=listener
    }
    private class QuestionDiffUtilCallback(val oldList:List<Question>,val newList:List<Question>):DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition]===newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition]===newList[newItemPosition]
        }
    }

    fun submitList(newList:List<Question>) {
       val diffResult= DiffUtil.calculateDiff(QuestionDiffUtilCallback(questions,newList))
        diffResult.dispatchUpdatesTo(this)
        questions.clear()
        questions.addAll(newList)
    }
}
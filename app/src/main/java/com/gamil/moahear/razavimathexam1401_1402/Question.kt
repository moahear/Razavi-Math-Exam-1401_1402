package com.gamil.moahear.razavimathexam1401_1402

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question(
    val id: Int,
    val questionImage: Int,
    val videoAnswerAddress1: String,
    val videoAnswerAddress2: String,
):Parcelable

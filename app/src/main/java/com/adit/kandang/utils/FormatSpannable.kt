package com.adit.kandang.utils

import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan

object FormatSpannable {
    fun formatSpannableBoldBlack(sentence: String, args: String): SpannableStringBuilder {
        val wordStart = sentence.indexOf(args)
        val wordEnd = wordStart + args.length

        val spannable = SpannableStringBuilder(sentence)

        spannable.setSpan(
            ForegroundColorSpan(Color.BLACK),
            wordStart,
            wordEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannable.setSpan(
            StyleSpan(Typeface.BOLD),
            wordStart,
            wordEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannable

    }
}
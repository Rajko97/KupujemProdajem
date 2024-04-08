package com.quable.kupujemprodajem.common

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import coil.load
import coil.size.Scale
import com.quable.kupujemprodajem.R
import com.quable.kupujemprodajem.common.Constants.Companion.IMAGES_BASE_URL
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@BindingAdapter("android:visibility")
fun View.setVisibility(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("android:bindCurrency")
fun TextView.addCurrency(currency: String?) {
    val newText = when (currency) {
        "eur" -> "$text €"
        "rsd" -> "$text din"
        else -> "DOGOVOR"
    }
    text = newText
}

@BindingAdapter("android:setPassedDays")
fun TextView.setPassedDays(dateTimeText: String) {
    fun getTextBasedOnDate(dateTime: LocalDateTime): String {
        val today = LocalDate.now()
        val yesterday = today.minusDays(1)

        val date = dateTime.toLocalDate()

        val daysDifference = ChronoUnit.DAYS.between(date, today)

        return when {
            date == today -> ", danas"
            date == yesterday -> ", juče"
            daysDifference % 10 == 1L -> ", pre $daysDifference dan"
            else -> ", pre $daysDifference dana"
        }
    }

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val dateTime = LocalDateTime.parse(dateTimeText, formatter)

    text = getTextBasedOnDate(dateTime)
}

@BindingAdapter("android:setCategoryText", "android:setGroupText", requireAll = false)
fun TextView.setCategoryText(category: String, group: String) {
    fun createSpannableString(category: String, group: String): Spannable {
        val spannableStringBuilder = SpannableStringBuilder()

        // Append category text
        spannableStringBuilder.append(category)

        // Append separator with black color span
        val blackStart = spannableStringBuilder.length
        spannableStringBuilder.append(" > ")
        val blackColorSpan = ForegroundColorSpan(Color.BLACK)
        spannableStringBuilder.setSpan(
            blackColorSpan,
            blackStart,
            spannableStringBuilder.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE,
        )

        // Append group text
        spannableStringBuilder.append(group)

        return spannableStringBuilder
    }

    text = createSpannableString(category, group)
}

@BindingAdapter("android:setHtml")
fun TextView.setHtml(text: String) {
    setText(HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY))
}

@BindingAdapter("android:setImageAsync")
fun ImageView.setImageAsync(uri: String?) {
    uri?.let {
        this.load("$IMAGES_BASE_URL$it") {
            placeholder(R.drawable.ic_launcher_foreground)
            scale(Scale.FIT)
        }
    }
}

@BindingAdapter("android:setFavoritesIcon")
fun ImageView.setImageAsync(isFavorite: Boolean) {
    val drawableId = if (isFavorite) R.drawable.ic_star_filled else R.drawable.ic_star_blank
    this.setImageDrawable(ResourcesCompat.getDrawable(resources, drawableId, context.theme))
}

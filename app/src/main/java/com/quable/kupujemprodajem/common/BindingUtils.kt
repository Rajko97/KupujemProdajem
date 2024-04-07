package com.quable.kupujemprodajem.common

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import coil.load
import coil.size.Scale
import com.quable.kupujemprodajem.R
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@BindingAdapter("android:bindCurrency")
fun TextView.addCurrency(currency: String) {
    post {
        when (currency) {
            "eur" -> this.append(" €")
            "rsd" -> this.append(" din")
        }
    }
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
            (daysDifference % 10).toInt() == 1 -> ", pre $daysDifference dan"
            else -> ", pre $daysDifference dana"
        }
    }

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val dateTime = LocalDateTime.parse(dateTimeText, formatter)

    text = getTextBasedOnDate(dateTime)
}

@BindingAdapter("android:setImageAsync")
fun ImageView.setImageAsync(uri: String?) {
    uri?.let {
        this.load("https://images.kupujemprodajem.com$it") {
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

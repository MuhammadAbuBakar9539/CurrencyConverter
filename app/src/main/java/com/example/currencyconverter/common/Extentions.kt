package com.example.currencyconverter.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

fun ViewGroup.inflate(layoutId: Int): View {
    return LayoutInflater.from(context).inflate(layoutId, this, false)
}

fun CircleImageView.loadImage(imgUrl: String) {
    Picasso.get().load(imgUrl).into(this)
}
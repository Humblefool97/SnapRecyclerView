package com.bytestore.snaprecyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val textView = view.findViewById<TextView>(R.id.textView)

    fun bind(item: String?) {
        textView.text = item ?: "-"
    }
}
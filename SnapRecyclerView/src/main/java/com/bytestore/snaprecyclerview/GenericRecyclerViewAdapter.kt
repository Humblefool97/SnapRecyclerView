package com.bytestore.snaprecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.Nullable
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * A generic [PagingDataAdapter] built for [RecyclerView]
 */
abstract class GenericRecyclerViewAdapter<T : Any, VH : RecyclerView.ViewHolder>(
    @IdRes val layout: Int,
    diffUtil: DiffUtil.ItemCallback<T>
) : PagingDataAdapter<T, VH>(diffUtil) {

    abstract fun getViewHolder(view: View, viewType: Int): VH

    abstract fun onBind(viewHolder: VH, @Nullable item: T?, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return getViewHolder(
            LayoutInflater.from(parent.context).inflate(layout, parent, false),
            viewType
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        onBind(holder, position = position, item = getItem(position))
    }

    fun getCount() = itemCount
}
package com.bytestore.snaprecyclerview

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        with(recyclerView) {
            adapter = pagingAdapter
            layoutManager = LinearLayoutManager(context)
        }
        lifecycleScope.launch {
            val list = listOf<String>("Sunday","Monday")
            pagingAdapter.submitData(PagingData.from(list))
        }
    }

    private val pagingAdapter = object : GenericRecyclerViewAdapter<String, ItemViewHolder>(
        R.layout.item_list,
        diffUtil
    ) {
        override fun getViewHolder(view: View, viewType: Int): ItemViewHolder {
            return ItemViewHolder(view)
        }

        override fun onBind(viewHolder: ItemViewHolder, item: String?, position: Int) {
            viewHolder.bind(item)
        }

    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem
        }
    }
}
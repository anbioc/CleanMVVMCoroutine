package com.aba.search.presentation.list

import android.view.ViewGroup
import com.aba.core.util.ImageLoader
import javax.inject.Inject

class SearchListAdapterImpl @Inject constructor(
    private val callback: SearchListAdapter.Callback,
    private val imageLoader: ImageLoader
) : SearchListAdapter() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobItemViewHolder =
        JobItemViewHolder.create(
            parent, callback, imageLoader
        )

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: JobItemViewHolder, position: Int) =
        holder.bind(itemList[position])
}
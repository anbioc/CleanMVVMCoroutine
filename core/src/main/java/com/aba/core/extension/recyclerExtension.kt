package com.aba.core.extension

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setupLinearList(
    recyclerAdapter: RecyclerView.Adapter<*>,
    hasFixedSize: Boolean
){
    adapter = recyclerAdapter
    setHasFixedSize(hasFixedSize)
    layoutManager = LinearLayoutManager(context)
}
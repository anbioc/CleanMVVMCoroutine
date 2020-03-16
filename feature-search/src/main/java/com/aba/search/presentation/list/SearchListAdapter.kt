package com.aba.search.presentation.list

import androidx.recyclerview.widget.RecyclerView
import com.aba.core.domain.data.JobDomainModel

abstract class SearchListAdapter : RecyclerView.Adapter<JobItemViewHolder>() {

    var itemList: List<JobDomainModel> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    interface Callback {
        fun onJobItemClicked(jobItem: JobDomainModel)
    }
}
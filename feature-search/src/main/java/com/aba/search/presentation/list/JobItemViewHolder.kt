package com.aba.search.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aba.core.domain.data.JobDomainModel
import com.aba.core.extension.fromHtml
import com.aba.core.util.ImageLoader
import com.aba.feature_search.R
import kotlinx.android.extensions.LayoutContainer

class JobItemViewHolder(
    override val containerView: View,
    private val callback: SearchListAdapter.Callback,
    private val imageLoader: ImageLoader
) :
    LayoutContainer, RecyclerView.ViewHolder(containerView) {

    companion object {
        fun create(
            parent: ViewGroup,
            callback: SearchListAdapter.Callback,
            imageLoader: ImageLoader
        ) = JobItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_job, parent, false
                ), callback, imageLoader
        )
    }

    fun bind(jobItem: JobDomainModel) {
        containerView.findViewById<TextView>(R.id.jobItemTitle).text = jobItem.title.fromHtml()
        containerView.findViewById<TextView>(R.id.jobItemDescription).text = jobItem.description.fromHtml()
        initListeners(jobItem)
        imageLoader.loadImage(
            containerView.findViewById(R.id.jobItemImageView),
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground,
            jobItem.companyUrl
        )
    }

    private fun initListeners(jobItem: JobDomainModel) {
        itemView.setOnClickListener {
            callback.onJobItemClicked(jobItem)
        }
    }


}
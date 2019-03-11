package com.espresslabs.kotlinyoutube

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.video_row.view.*

class MainAdapter(val homeFeed: HomeFeed): RecyclerView.Adapter<HomeViewHolder>() {

    // numberOfItems
    override fun getItemCount(): Int {
        return homeFeed.videos.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.video_row, parent, false)
        return HomeViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val video = homeFeed.videos[position]
        holder.view.textView_video_title.text = video.name
    }

}

class HomeViewHolder(val view: View): RecyclerView.ViewHolder(view)

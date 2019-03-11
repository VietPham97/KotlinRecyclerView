package com.espresslabs.kotlinyoutube

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.video_row.view.*

class MainAdapter: RecyclerView.Adapter<HomeViewHolder>() {

    val videoTitles = listOf("First Title", "Second", "3rd", "More Title")

    // numberOfItems
    override fun getItemCount(): Int {
        return videoTitles.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.video_row, parent, false)
        return HomeViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val title = videoTitles[position]
        holder.view.textView_video_title.text = title
    }

}

class HomeViewHolder(val view: View): RecyclerView.ViewHolder(view)

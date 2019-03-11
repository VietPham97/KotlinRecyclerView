package com.espresslabs.kotlinyoutube

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
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
        holder.view.textView_channel_name.text = "${video.channel.name}  •  20K Views\n4 days ago"

        val thumbnailImageView = holder.view.imageView_video_thumbnail
        Picasso.get().load(video.imageUrl).into(thumbnailImageView)

        val channelProfileImageView = holder.view.imageView_channel_profile
        Picasso.get().load(video.channel.profileImageUrl).into(channelProfileImageView)

        holder.video = video
    }

}

class HomeViewHolder(val view: View, var video: Video? = null): RecyclerView.ViewHolder(view) {

    companion object {
        const val VIDEO_TITLE_KEY: String = "VIDEO_TITLE"
        const val VIDEO_ID_KEY: String = "VIDEO_ID"
    }

    init {
        view.setOnClickListener {
            val intent = Intent(view.context, DetailsActivity::class.java)
            intent.putExtra(VIDEO_ID_KEY, video?.id)
            intent.putExtra(VIDEO_TITLE_KEY, video?.name)
            view.context.startActivity(intent)
        }
    }

}

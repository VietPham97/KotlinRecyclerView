package com.espresslabs.kotlinyoutube

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.lesson_row.view.*
import okhttp3.*
import java.io.IOException

class DetailsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setUpToolbar()
        setUpRecyclerView()
        getCourseDetail()
    }

    private fun getCourseDetail() {
        val videoId = intent.getIntExtra(HomeViewHolder.VIDEO_ID_KEY, -1)
        val courseDetailUrl = "https://api.letsbuildthatapp.com/youtube/course_detail?id=$videoId"

        val client = OkHttpClient()
        val request = Request.Builder().url(courseDetailUrl).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to fetch course detail ${e.localizedMessage}")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                val gson = GsonBuilder().create()
                val lessons = gson.fromJson(body, Array<Lesson>::class.java)

                runOnUiThread {
                    my_recycler_view.adapter = CourseDetailAdapter(lessons)
                }
            }

        })
    }

    private fun setUpToolbar() {
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // we'll change the nav bar title...
        val navBarTitle = intent.getStringExtra(HomeViewHolder.VIDEO_TITLE_KEY)
        supportActionBar?.title = navBarTitle
    }

    private fun setUpRecyclerView() {
        my_recycler_view.layoutManager = LinearLayoutManager(this)
    }

    private class CourseDetailAdapter(val lessons: Array<Lesson>): RecyclerView.Adapter<LessonViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val lessonView = layoutInflater.inflate(R.layout.lesson_row, parent, false)

            return LessonViewHolder(lessonView)
        }

        override fun getItemCount(): Int {
            return lessons.size
        }

        override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
            val lesson = lessons[position]

            holder.lessonView.textView_lesson_title.text = lesson.name
            holder.lessonView.textView_duration.text = lesson.duration

            val lessonImageView = holder.lessonView.imageView_lesson_thumbnail
            Picasso.get().load(lesson.imageUrl).into(lessonImageView)

            holder.lesson = lesson
        }

    }

    class LessonViewHolder(
        val lessonView: View,
        var lesson: Lesson? = null
    ) : RecyclerView.ViewHolder(lessonView) {

        companion object {
            const val LESSON_URL_KEY: String = "LESSON_URL"
        }

        init {
            lessonView.setOnClickListener {
                val intent = Intent(lessonView.context, OnlineLessonActivity::class.java)

                intent.putExtra(LESSON_URL_KEY, lesson?.link)

                lessonView.context.startActivity(intent)
            }
        }

    }

}
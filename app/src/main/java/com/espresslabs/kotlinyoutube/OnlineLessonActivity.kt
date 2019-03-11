package com.espresslabs.kotlinyoutube

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_online_lesson.*

class OnlineLessonActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_online_lesson)

        setUpToolbar()
        setUpWebView()
    }

    private fun setUpWebView() {
        val lessonLink = intent.getStringExtra(DetailsActivity.LessonViewHolder.LESSON_URL_KEY)

        my_web_view.settings.javaScriptEnabled = true
        my_web_view.settings.loadWithOverviewMode = true
        my_web_view.settings.useWideViewPort = true

        my_web_view.loadUrl(lessonLink)
    }

    private fun setUpToolbar() {
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}

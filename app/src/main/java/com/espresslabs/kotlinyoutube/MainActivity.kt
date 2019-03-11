package com.espresslabs.kotlinyoutube

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(my_toolbar)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
//        my_recycler_view.setBackgroundColor(Color.BLUE)
        my_recycler_view.layoutManager = LinearLayoutManager(this)
        my_recycler_view.adapter = MainAdapter()
    }

}

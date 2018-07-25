package com.example.kubik.testviewpagertask

import android.os.Bundle
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int) = FragmentViewPager.newInstance(position)

            override fun getCount() = 3
        }

        viewPager.setPageTransformer(true) { view, position ->
           // textView.alpha = 1.0f - Math.abs(position)

            textView.text = StringBuffer("This page's number is: ").append(position).toString()

            Log.d("myLogs", "position:  " + position.toString())
            Log.d("myLogs", "current item:  " + viewPager.currentItem.toString())
        }

        tabLayout.setupWithViewPager(viewPager, true)
    }
}
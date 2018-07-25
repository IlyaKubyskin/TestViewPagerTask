package com.example.kubik.testviewpagertask

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var pagerAdapter: FragmentStatePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pagerAdapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return FragmentViewPager.newInstance(position)
            }

            override fun getCount(): Int {
                return 3
            }
        }

        viewPager.adapter = pagerAdapter
        viewPager.setPageTransformer(true, transformer)
        tabLayout.setupWithViewPager(viewPager, true)
    }

    private val transformer = ViewPager.PageTransformer { view, position ->
        val tv = view.findViewById<TextView>(R.id.textView)

        tv.translationX = 2 * tv.width * -position
        tv.alpha = 1.0f - Math.abs(2 * position)
    }
}
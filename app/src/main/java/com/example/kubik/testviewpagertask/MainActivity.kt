package com.example.kubik.testviewpagertask

import android.os.Bundle
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var prevPositionOffset = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int) = FragmentViewPager.newInstance(position)

            override fun getCount() = 3
        }

        setText(viewPager.currentItem)

        tabLayout.setupWithViewPager(viewPager, true)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                val currentItem = viewPager.currentItem
                if (currentItem == position) {
                    if (prevPositionOffset < 0.5f && positionOffset >= 0.5f) {
                        setText(currentItem + 1)
                    } else if (prevPositionOffset >= 0.5f && positionOffset < 0.5f) {
                        setText(currentItem)
                    }
                } else {
                    if (prevPositionOffset < 0.5f && positionOffset >= 0.5f) {
                        setText(currentItem)
                    } else if (prevPositionOffset >= 0.5f && positionOffset < 0.5f) {
                        setText(currentItem - 1)
                    }
                }
                prevPositionOffset = positionOffset
            }

            override fun onPageSelected(position: Int) {
            }
        })
    }

    private fun setText(text: Int) = textView.apply {
        this.text = StringBuffer("This page's number is: ").append(text).toString()
    }

    private fun setAlpha(alpha: Float) = textView.apply {
        this.alpha = alpha
    }
}
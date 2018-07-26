package com.example.kubik.testviewpagertask

import android.os.Bundle
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
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

      /*  viewPager.setPageTransformer(true) { view, position ->
           // textView.alpha = 1.0f - Math.abs(position)

            setText(position.toString() + "   :  " + viewPager.currentItem)
        }*/

        setText(viewPager.currentItem.toString())

        tabLayout.setupWithViewPager(viewPager, true)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                setText(positionOffset.toString() + "    :   " + position.toString())

            }

            override fun onPageSelected(position: Int) {

            }
        })
    }

    private fun setText(text: String) {
        textView.text = StringBuffer("This page's number is: ").append(text).toString()
    }
}
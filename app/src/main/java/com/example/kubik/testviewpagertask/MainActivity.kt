package com.example.kubik.testviewpagertask

import android.os.Bundle
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.abs

class MainActivity : AppCompatActivity() {

    private var prevPositionOffset = 0f
    private var prevPosition = 0
    private val textList = listOf("Frag_1", "Frag_2", "Frag_3", "Frag_4", "Frag_5", "Frag_6")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int) = FragmentViewPager.newInstance(position)

            override fun getCount() = textList.size
        }

        setText(textList[viewPager.currentItem])

        tabLayout.setupWithViewPager(viewPager, true)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

                val currentItem = viewPager.currentItem

                if (positionOffset in 0f..0.5f) {
                    setAlpha(abs(1f - positionOffset * 2))
                } else if (positionOffset in 0.5f..1f) {
                    setAlpha(abs(positionOffset * 2 - 1f))
                }

                if (currentItem == position) {
                    if (prevPositionOffset < 0.5f && positionOffset >= 0.5f && currentItem != textList.size) {
                        setText(textList[currentItem + 1])
                    } else if (prevPositionOffset >= 0.5f && positionOffset < 0.5f) {
                        setText(textList[currentItem])
                    }
                } else {
                    if (prevPositionOffset < 0.5f && positionOffset >= 0.5f) {
                        setText(textList[currentItem])
                    } else if (prevPositionOffset >= 0.5f && positionOffset < 0.5f && currentItem != 0 && currentItem != prevPosition) {
                        setText(textList[currentItem - 1])
                    }
                }

                prevPositionOffset = positionOffset
                prevPosition = position
            }

            override fun onPageSelected(position: Int) {
                setText(textList[position])
                setAlpha(1f)
            }
        })
    }

    private fun setText(text: String) = textView.apply {
        this.text = text
    }

    private fun setAlpha(alpha: Float) = textView.apply {
        this.alpha = alpha
    }
}
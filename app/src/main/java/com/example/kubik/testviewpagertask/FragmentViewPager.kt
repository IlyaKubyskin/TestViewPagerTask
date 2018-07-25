package com.example.kubik.testviewpagertask

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_layout.*

class FragmentViewPager : Fragment() {

    var page: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        page = arguments?.getInt(PAGE) ?: 0
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (page) {
            0 -> imageViewFragment.setImageResource(R.drawable.flowers1)
            1 -> imageViewFragment.setImageResource(R.drawable.flowers2)
            2 -> imageViewFragment.setImageResource(R.drawable.flowers3)
        }

        textView.text = StringBuffer("This page's number is: ").append(page).toString()
    }

    companion object {
        private const val PAGE = "page"

        fun newInstance(page: Int):FragmentViewPager {
            val fragment = FragmentViewPager()
            fragment.arguments = Bundle().apply {
                putInt(PAGE, page)
            }
            return fragment
        }
    }
}

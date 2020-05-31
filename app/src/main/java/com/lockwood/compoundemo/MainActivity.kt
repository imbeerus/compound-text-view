package com.lockwood.compoundemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.lockwood.compoundemo.fragment.recycler.RecyclerFragment
import com.lockwood.compoundemo.fragment.showcase.ShowcaseFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val tabTitles = arrayOf("Showcase", "Recycler")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() = with(pager) {
        adapter = object : FragmentStateAdapter(this@MainActivity) {

            override fun getItemCount(): Int = ITEM_COUNT

            override fun createFragment(p0: Int): Fragment = if (p0 == 0) {
                ShowcaseFragment.newInstance()
            } else {
                RecyclerFragment.newInstance()
            }
        }

        TabLayoutMediator(tabLayout, this) { tab, position ->
            tab.text = tabTitles[position]
            setCurrentItem(tab.position, true)
        }.attach()
    }

    companion object {

        private const val ITEM_COUNT = 2
    }
}


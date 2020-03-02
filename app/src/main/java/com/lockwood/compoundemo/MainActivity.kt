package com.lockwood.compoundemo

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.lockwood.compoundemo.fragment.RecyclerFragment
import com.lockwood.compoundemo.fragment.ShowcaseFragment
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

fun Context.drawable(@DrawableRes res: Int): Drawable? = AppCompatResources.getDrawable(this, res)

val Fragment.ctx
    get() = requireContext()

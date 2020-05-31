package com.lockwood.compoundemo.fragment.recycler

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lockwood.compoundemo.R
import com.lockwood.compoundemo.adapter.AvatarAdapter
import com.lockwood.compoundemo.extensions.addItemDecoration
import kotlinx.android.synthetic.main.fragment_recycler.*

class RecyclerFragment : Fragment(R.layout.fragment_recycler) {

    companion object {

        private val IMAGES_TO_SHOW
            get() = arrayOf(
                "https://i.imgur.com/VPyuS0V.png",
                "https://i.imgur.com/MLV7ggh.png",
                "https://i.imgur.com/E0bRUgX.png",
                "https://i.imgur.com/vQJvcLD.png",
                "https://i.imgur.com/ln8CLl2.png",
                "https://i.imgur.com/0AsBGcT.png",
                "https://i.imgur.com/brcXPxE.png",
                "https://i.imgur.com/6dW5XvJ.png",
                "https://i.imgur.com/st7IlwN.png"
            )

        private const val ITEM_COUNT = 300

        fun newInstance(): RecyclerFragment {
            return RecyclerFragment()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val imagesToShow = Array(ITEM_COUNT) { IMAGES_TO_SHOW.random() }

        with(recyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = AvatarAdapter(imagesToShow, requireContext())
            addItemDecoration()
            setHasFixedSize(true)
        }
    }

}
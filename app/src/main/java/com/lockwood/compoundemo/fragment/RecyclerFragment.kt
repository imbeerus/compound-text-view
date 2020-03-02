package com.lockwood.compoundemo.fragment

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.Coil
import coil.api.load
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import com.lockwood.compound.CompoundTextView
import com.lockwood.compoundemo.R
import com.lockwood.compoundemo.ctx
import com.lockwood.compoundemo.drawable
import com.lockwood.compoundemo.glide.GlideApp
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.fragment_recycler.*

class RecyclerFragment : Fragment(R.layout.fragment_recycler) {

    private val images
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

    private val imagesToShow
        get() = Array(ITEM_COUNT) { images.random() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(ctx)
            adapter = AvatarAdapter(imagesToShow)
            addItemDecoration(DividerItemDecoration(ctx, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
        }
    }

    class AvatarAdapter(private val data: Array<String>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun getItemCount() = data.size

        override fun getItemViewType(position: Int) = when {
            position <= 100 -> TYPE_GLIDE
            position <= 200 -> TYPE_PICASSO
            else -> TYPE_COIL
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ) = CompoundViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_compound,
                parent,
                false
            ) as CompoundTextView
        )

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val url = data[position]
            val type = getItemViewType(position)
            val title = when (type) {
                TYPE_GLIDE -> "GLIDE"
                TYPE_PICASSO -> "PICASSO"
                else -> "COIL"
            }
//          because we change (resize for eg) Drawable out of CompoundTextView (via image loader)
//          it's important to use isUseCustomTransformation=true in xml
            with((holder as CompoundViewHolder).compoundView) {
                text = "I'm from $title"
                loadImage(url, type)
            }
        }

        private fun CompoundTextView.loadImage(url: String, type: Int) {
            val placeholder = context.drawable(R.drawable.ic_placeholder)!!
            val size = drawableCustomSize
            when (type) {
                TYPE_GLIDE -> {
                    GlideApp.with(context)
                        .load(url)
                        .override(size)
                        .placeholder(placeholder)
                        .into(GlideCompoundTarget(this))
                        .waitForLayout()
                }
                TYPE_PICASSO -> {
                    Picasso.get()
                        .load(url)
                        .placeholder(placeholder)
                        .resize(size, size)
                        .into(PicassoCompoundTarget(this))
                }
                else -> {
                    Coil.load(context, url) {
                        size(size)
                        placeholder(placeholder)
                        target(
                            onStart = { setDrawables(start = it) },
                            onSuccess = { setDrawables(start = it) }
                        )
                    }
                }
            }

        }

        companion object {

            private const val TYPE_GLIDE = 0
            private const val TYPE_PICASSO = 1
            private const val TYPE_COIL = 2
        }

        class CompoundViewHolder(val compoundView: CompoundTextView) :
            RecyclerView.ViewHolder(compoundView)

    }

    class GlideCompoundTarget(view: CompoundTextView) :
        CustomViewTarget<CompoundTextView, Drawable>(view) {

        override fun onResourceCleared(placeholder: Drawable?) =
            view.setDrawables(start = placeholder)

        override fun onLoadFailed(errorDrawable: Drawable?) =
            view.setDrawables(start = errorDrawable)

        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) =
            view.setDrawables(start = resource)

    }

    class PicassoCompoundTarget(private val view: CompoundTextView) : Target {

        override fun onPrepareLoad(placeHolderDrawable: Drawable?) =
            view.setDrawables(start = placeHolderDrawable)

        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) =
            view.setDrawables(start = errorDrawable)

        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) =
            view.setDrawables(start = BitmapDrawable(view.context.resources, bitmap))

    }

    companion object {

        private const val ITEM_COUNT = 300

        fun newInstance() = RecyclerFragment()

    }
}
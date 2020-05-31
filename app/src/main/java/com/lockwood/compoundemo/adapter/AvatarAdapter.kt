package com.lockwood.compoundemo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.LoadRequest
import com.lockwood.compound.CompoundTextView
import com.lockwood.compoundemo.R
import com.lockwood.compoundemo.adapter.holder.CompoundViewHolder
import com.lockwood.compoundemo.extensions.drawable
import com.lockwood.compoundemo.glide.GlideApp
import com.lockwood.compoundemo.target.CoilCompoundTarget
import com.lockwood.compoundemo.target.GlideCompoundTarget
import com.lockwood.compoundemo.target.PicassoCompoundTarget
import com.squareup.picasso.Picasso

class AvatarAdapter(
    private val data: Array<String>,
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {

        private const val TYPE_GLIDE = 0
        private const val TYPE_PICASSO = 1
        private const val TYPE_COIL = 2

    }

    private val glideImageLoader = GlideApp.with(context)
    private val picassoImageLoader = Picasso.get()
    private val coilImageLoader = ImageLoader.Builder(context).build()

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int) = when {
        position <= 100 -> TYPE_GLIDE
        position <= 200 -> TYPE_PICASSO
        else -> TYPE_COIL
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CompoundViewHolder {
        return CompoundViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_compound,
                parent,
                false
            ) as CompoundTextView
        )
    }

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
        val compoundView = (holder as CompoundViewHolder).compoundView
        with(compoundView) {
            text = "I'm from $title"
            loadImage(url, type)
        }
    }

    private fun CompoundTextView.loadImage(url: String, type: Int) {
        val placeholderDrawable = context.drawable(R.drawable.ic_placeholder)
        val placeholder = requireNotNull(placeholderDrawable)
        val size = drawableCustomSize

        when (type) {
            TYPE_GLIDE -> {
                glideImageLoader
                    .load(url)
                    .override(size)
                    .placeholder(placeholder)
                    .into(GlideCompoundTarget(this))
                    .waitForLayout()
            }
            TYPE_PICASSO -> {
                picassoImageLoader
                    .load(url)
                    .placeholder(placeholder)
                    .resize(size, size)
                    .into(PicassoCompoundTarget(this))
            }
            else -> {
                coilImageLoader.execute(
                    LoadRequest.Builder(context)
                        .data(url)
                        .size(size)
                        .placeholder(placeholder)
                        .target(CoilCompoundTarget(this))
                        .build()
                )
            }
        }
    }

}

package com.lockwood.compound.transofrmation

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

/**
 * A class for performing tint transformation on a drawable
 *
 * @param colorRes to tint drawable
 */
class TintTransformation(
    @ColorRes private val colorRes: Int
) : DrawableTransformation {

    override fun performTransformation(source: Drawable, context: Context) = try {
        val colorInt = context.color(colorRes)
        source.tint(colorInt)
    } catch (e: Exception) {
        Log.e(TAG, e.message.toString())
        source
    }

    private fun Drawable.tint(@ColorInt tint: Int): Drawable {
        val wrappedDrawable = DrawableCompat.wrap(this)
        val mutableDrawable = wrappedDrawable.mutate()
        DrawableCompat.setTint(mutableDrawable, tint)
        return mutableDrawable
    }

    private fun Context.color(@ColorRes res: Int) = ContextCompat.getColor(this, res)

    companion object {

        val TAG = TintTransformation::class.java.simpleName
    }

}
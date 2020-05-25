package com.lockwood.compound.transofrmation

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.annotation.ColorRes
import com.lockwood.compound.extenions.color
import com.lockwood.compound.extenions.tint

/**
 * A class for performing tint transformation on a drawable
 *
 * @param colorRes to tint drawable
 */
class TintTransformation(
    @ColorRes private val colorRes: Int
) : DrawableTransformation {

    companion object {

        val TAG = TintTransformation::class.java.simpleName
    }

    override fun performTransformation(source: Drawable, context: Context): Drawable {
        val validRes = colorRes > 0

        return if (validRes) {
            try {
                val colorInt = context.color(colorRes)
                source.tint(colorInt)
            } catch (e: Exception) {
                Log.e(TAG, e.message.toString())
                source
            }
        } else {
            source
        }
    }

}
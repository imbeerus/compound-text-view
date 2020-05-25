package com.lockwood.compound.extenions

import android.content.Context
import com.lockwood.compound.R

/**
 * Is device in right to left configuration
 */
internal val Context.isRtl
    get() = resources.getBoolean(R.bool.is_right_to_left)
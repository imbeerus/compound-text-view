package com.lockwood.compoundemo.extensions

import android.widget.ArrayAdapter
import androidx.annotation.ArrayRes
import androidx.appcompat.widget.AppCompatSpinner

fun AppCompatSpinner.setSimpleAdapter(@ArrayRes resId: Int) {
    ArrayAdapter.createFromResource(
        context,
        resId,
        android.R.layout.simple_spinner_item
    ).also {
        it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter = it
    }
}
package com.lockwood.compoundemo.fragment.showcase.listener

import android.widget.CompoundButton
import androidx.appcompat.widget.AppCompatCheckBox
import com.lockwood.compound.CompoundTextView
import com.lockwood.compound.Position
import com.lockwood.compoundemo.R
import com.lockwood.compoundemo.extensions.drawable

class PositionCheckedListener(
    private val compoundView: CompoundTextView,
    private val checkBoxesByPosition: List<AppCompatCheckBox>
) : CompoundButton.OnCheckedChangeListener {

    override fun onCheckedChanged(
        buttonView: CompoundButton?,
        isChecked: Boolean
    ) {
        val drawables = checkBoxesByPosition.mapIndexed { i, view ->
            if (!view.isChecked) {
                null
            } else {
                val res = when (i) {
                    Position.END -> R.drawable.ic_arrow_back
                    Position.TOP -> R.drawable.ic_arrow_downward
                    Position.BOTTOM -> R.drawable.ic_arrow_upward
                    else -> R.drawable.ic_arrow_forward
                }
                view.context.drawable(res)
            }
        }

        compoundView.setDrawables(
            drawables[0],
            drawables[1],
            drawables[2],
            drawables[3]
        )
    }

}
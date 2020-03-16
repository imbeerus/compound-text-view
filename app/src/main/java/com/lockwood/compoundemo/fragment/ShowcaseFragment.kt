package com.lockwood.compoundemo.fragment

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.annotation.ArrayRes
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatSpinner
import androidx.fragment.app.Fragment
import com.lockwood.compound.CompoundViewClickListener
import com.lockwood.compound.Position
import com.lockwood.compoundemo.R
import com.lockwood.compoundemo.ctx
import com.lockwood.compoundemo.drawable
import kotlinx.android.synthetic.main.fragment_showcase.*

class ShowcaseFragment : Fragment(R.layout.fragment_showcase), AdapterView.OnItemSelectedListener {

    private lateinit var root: View

    private var toast: Toast? = null

    private val drawablesPositions = arrayOf(R.id.start, R.id.top, R.id.end, R.id.bottom)
    private val checkBoxesByPosition
        get() = drawablesPositions.map { root.findViewById<AppCompatCheckBox>(it) }

    private val compoundViewTouchListener = object : CompoundViewClickListener {

        override fun onViewClick(view: View, event: MotionEvent) =
            toast("onViewClick")

        override fun onStartDrawableClick(view: View, drawable: Drawable, event: MotionEvent) =
            toast("onStartDrawableClick")

        override fun onTopDrawableClick(view: View, drawable: Drawable, event: MotionEvent) =
            toast("onTopDrawableClick")

        override fun onEndDrawableClick(view: View, drawable: Drawable, event: MotionEvent) =
            toast("onEndDrawableClick")

        override fun onBottomDrawableClick(view: View, drawable: Drawable, event: MotionEvent) =
            toast("onBottomDrawableClick")

    }

    private val positionCheckedListener = CompoundButton.OnCheckedChangeListener { _, _ ->
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
                ctx.drawable(res)
            }
        }

        compound.setDrawables(
            drawables[0],
            drawables[1],
            drawables[2],
            drawables[3]
        )
    }

    private val attributesSpinners = arrayOf(R.id.gravity, R.id.padding)
    private val attributesViews
        get() = attributesSpinners.map { root.findViewById<AppCompatSpinner>(it) }
    private val attributesItems = arrayOf(R.array.gravity, R.array.padding)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        root = view
        checkBoxesByPosition.forEach { it.setOnCheckedChangeListener(positionCheckedListener) }
        attributesViews.forEachIndexed { i, spinner ->
            spinner.setSimpleAdapter(attributesItems[i])
            spinner.onItemSelectedListener = this@ShowcaseFragment
        }
        compound.setCompoundDrawablesTouchListener(compoundViewTouchListener)
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        when (parent.id) {
            R.id.gravity -> updateGravity(position)
            R.id.padding -> updatePadding(position, view)
            else -> {
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) = Unit

    private fun updateGravity(position: Int) {
        val gravity = when (position) {
            1 -> Gravity.START
            2 -> Gravity.TOP
            3 -> Gravity.BOTTOM
            4 -> Gravity.END
            5 -> Gravity.CENTER
            6 -> Gravity.CENTER_HORIZONTAL
            7 -> Gravity.CENTER_VERTICAL
            else -> Gravity.NO_GRAVITY
        }
        compound.drawableGravity = gravity
    }

    private fun updatePadding(position: Int, view: View?) {
        if (view != null) {
            val padding = when (position) {
                in 1..5 -> (view as TextView).text.toString().toInt()
                else -> 0
            }
            compound.drawablePadding = ctx.dpToPixel(padding)
        }
    }

    private fun Context.dpToPixel(dp: Int): Int {
        val scale = (resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        return (dp.toFloat() * scale).toInt()
    }

    private fun AppCompatSpinner.setSimpleAdapter(@ArrayRes resId: Int) {
        ArrayAdapter.createFromResource(
            context,
            resId,
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            adapter = it
        }
    }

    private fun toast(str: String) {
        toast?.cancel()
        toast = Toast.makeText(context, str, Toast.LENGTH_SHORT)
        toast!!.show()
    }

    companion object {

        fun newInstance() = ShowcaseFragment()
    }

}
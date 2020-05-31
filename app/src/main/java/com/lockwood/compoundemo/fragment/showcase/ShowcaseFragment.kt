package com.lockwood.compoundemo.fragment.showcase

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatSpinner
import androidx.fragment.app.Fragment
import com.lockwood.compoundemo.R
import com.lockwood.compoundemo.extensions.dpToPixel
import com.lockwood.compoundemo.extensions.setSimpleAdapter
import com.lockwood.compoundemo.fragment.showcase.listener.PositionCheckedListener
import com.lockwood.compoundemo.fragment.showcase.listener.ToastCompoundViewClickListener
import kotlinx.android.synthetic.main.fragment_showcase.*

class ShowcaseFragment : Fragment(R.layout.fragment_showcase), AdapterView.OnItemSelectedListener {

    companion object {

        fun newInstance(): ShowcaseFragment {
            return ShowcaseFragment()
        }

    }

    private lateinit var root: View

    private val drawablesPositions = arrayOf(R.id.start, R.id.top, R.id.end, R.id.bottom)

    private val attributesSpinners = arrayOf(R.id.gravity, R.id.padding)

    private val attributesItems = arrayOf(R.array.gravity, R.array.padding)

    private val checkBoxesByPosition
        get() = drawablesPositions.map { root.findViewById<AppCompatCheckBox>(it) }

    private val attributesViews
        get() = attributesSpinners.map { root.findViewById<AppCompatSpinner>(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        root = view

        val positionCheckedListener =
            PositionCheckedListener(
                compound,
                checkBoxesByPosition
            )
        checkBoxesByPosition.forEach { it.setOnCheckedChangeListener(positionCheckedListener) }

        val toastCompoundViewClickListener =
            ToastCompoundViewClickListener(
                requireContext()
            )
        compound.setCompoundDrawablesTouchListener(toastCompoundViewClickListener)

        attributesViews.forEachIndexed { i, spinner ->
            with(spinner) {
                setSimpleAdapter(attributesItems[i])
                onItemSelectedListener = this@ShowcaseFragment
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        when (parent.id) {
            R.id.gravity -> updateGravity(position)
            R.id.padding -> updatePadding(position, view)
            else -> {
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {}

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

            compound.drawablePadding = requireContext().dpToPixel(padding)
        }
    }

}
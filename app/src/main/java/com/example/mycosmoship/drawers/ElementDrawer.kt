package com.example.mycosmoship.drawers

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.mycosmoship.CELL_SIZE
import com.example.mycosmoship.R
import com.example.mycosmoship.enums.Material
import com.example.mycosmoship.models.Coordinate
import com.example.mycosmoship.models.Element

class ElementDrawer(val container: FrameLayout) {

    var currentMaterial = Material.EMPTY
    private val elementsOnContainer = mutableListOf<Element>()

    fun onTouchContainer(x:Float, y:Float){
        val topMargin = y.toInt() - (y.toInt() % CELL_SIZE)
        val leftMargin = x.toInt() - (x.toInt() % CELL_SIZE)
        val coordinate = Coordinate(topMargin, leftMargin)
        drawView(coordinate)
    }


     fun drawView(coordinate: Coordinate){
        val view = ImageView(container.context)
        val layoutParams = FrameLayout.LayoutParams(CELL_SIZE, CELL_SIZE)
        when(currentMaterial){
            Material.EMPTY -> {

            }
            Material.BRICK -> view.setImageResource(R.drawable.brick)
            Material.CONCRETE -> view.setImageResource(R.drawable.concrete)
            Material.GRASS -> view.setImageResource(R.drawable.grass)
        }
        layoutParams.topMargin = coordinate.top
        layoutParams.leftMargin = coordinate.left
        val viewId = View.generateViewId()
        view.id = viewId
        view.layoutParams = layoutParams
        container.addView(view)
        elementsOnContainer.add(Element(viewId, currentMaterial, coordinate))
    }
}
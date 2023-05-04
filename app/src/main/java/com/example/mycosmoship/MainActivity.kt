package com.example.mycosmoship

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.*
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.FrameLayout
import com.example.mycosmoship.drawers.ElementDrawer
import com.example.mycosmoship.drawers.GridDrawer
import com.example.mycosmoship.enums.Direction
import com.example.mycosmoship.enums.Material
import com.example.mycosmoship.models.Coordinate
import kotlinx.android.synthetic.main.activity_main.*


const val CELL_SIZE = 50
const val VERTICAL_CELL_AMOUNT = 24
const val HORIZONTAL_CELL_AMOUNT = 13
const val VERTICAL_MAX_SIZE = CELL_SIZE * VERTICAL_CELL_AMOUNT
const val HORIZONTAL_MAX_SIZE = CELL_SIZE * HORIZONTAL_CELL_AMOUNT


class MainActivity : AppCompatActivity() {

    private var editMode = false

    private val gridDrawer by lazy {
        GridDrawer(container)
    }

    private val elementDrawer by lazy {
        ElementDrawer(container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        container.layoutParams = FrameLayout.LayoutParams(VERTICAL_MAX_SIZE, HORIZONTAL_MAX_SIZE)
        editor_clear.setOnClickListener{ elementDrawer.currentMaterial = Material.EMPTY}
        editor_brick.setOnClickListener{ elementDrawer.currentMaterial = Material.BRICK}
        editor_concrete.setOnClickListener{ elementDrawer.currentMaterial = Material.CONCRETE}
        editor_grass.setOnClickListener{ elementDrawer.currentMaterial = Material.GRASS}
        container.setOnTouchListener { _, event ->
            elementDrawer.onTouchContainer(event.x, event.y)
            return@setOnTouchListener true
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_settings -> {
                switchEditMode()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun switchEditMode(){
        if (editMode){
            gridDrawer.removeGrid()
            material_container.visibility = GONE
        }else{
            gridDrawer.drawGrid()
            material_container.visibility = VISIBLE
        }
        editMode = !editMode
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when(keyCode){
            KEYCODE_DPAD_UP -> move(Direction.UP)
                KEYCODE_DPAD_DOWN ->move (Direction.BOTTOM)
            KEYCODE_DPAD_LEFT ->move (Direction.LEFT)
            KEYCODE_DPAD_RIGHT ->move(Direction.RIGHT)
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun move(direction: Direction){
        val layoutParams = ship.layoutParams as FrameLayout.LayoutParams
        when (direction) {

            Direction.UP -> {
                ship.rotation = 0f
                if (layoutParams.topMargin > 0){
                    (ship.layoutParams as FrameLayout.LayoutParams).topMargin += -CELL_SIZE
                }
            }
            Direction.BOTTOM -> {
                ship.rotation = 180f
                if (layoutParams.topMargin + ship.height < HORIZONTAL_MAX_SIZE) {
                    (ship.layoutParams as FrameLayout.LayoutParams).topMargin += CELL_SIZE
                }
            }
            Direction.RIGHT -> {
                ship.rotation = 90f
                if (layoutParams.leftMargin + ship.width < VERTICAL_MAX_SIZE) {
                    (ship.layoutParams as FrameLayout.LayoutParams).leftMargin += CELL_SIZE}
            }
            Direction.LEFT -> {
                ship.rotation = 270f
                    if (layoutParams.leftMargin > 0){
                    (ship.layoutParams as FrameLayout.LayoutParams).leftMargin += -CELL_SIZE
                }
            }
        }
        container.removeView(ship)
        container.addView(ship)

    }

}
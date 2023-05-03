package com.example.mycosmoship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.*
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.recyclerview.widget.ItemTouchHelper.UP
import kotlinx.android.synthetic.main.activity_main.*


const val CELL_SIZE = 50
const val VERTICAL_CELL_AMOUNT = 38
const val HORIZONTAL_CELL_AMOUNT = 25
const val VERTICAL_MAX_SIZE = CELL_SIZE * VERTICAL_CELL_AMOUNT
const val HORIZONTAL_MAX_SIZE = CELL_SIZE * HORIZONTAL_CELL_AMOUNT


class MainActivity : AppCompatActivity() {
    private val gridDrawer by lazy {
        GridDrawer(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        container.layoutParams = FrameLayout.LayoutParams(VERTICAL_MAX_SIZE, HORIZONTAL_MAX_SIZE)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_settings -> {
                gridDrawer.drawGrid()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
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
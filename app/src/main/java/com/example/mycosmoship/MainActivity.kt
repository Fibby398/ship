package com.example.mycosmoship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.*
import android.widget.FrameLayout
import androidx.recyclerview.widget.ItemTouchHelper.UP
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        when (direction) {
            Direction.UP -> {
                ship.rotation = 0f
                (ship.layoutParams as FrameLayout.LayoutParams).topMargin += -50
            }
            Direction.BOTTOM -> {
                ship.rotation = 180f
                (ship.layoutParams as FrameLayout.LayoutParams).topMargin += 50

            }
            Direction.LEFT -> {
                ship.rotation = 270f
                (ship.layoutParams as FrameLayout.LayoutParams).leftMargin += -50

            }
            Direction.RIGHT -> {
                ship.rotation = 90f
                (ship.layoutParams as FrameLayout.LayoutParams).leftMargin += 50

            }
        }
        container.removeView(ship)
        container.addView(ship)

    }

}
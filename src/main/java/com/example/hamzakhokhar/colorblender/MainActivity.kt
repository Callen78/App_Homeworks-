package com.example.hamzakhokhar.colorblender

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.graphics.ColorUtils
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import com.example.hamzakhokhar.colorblender.R.id.colorA
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable


class MainActivity : AppCompatActivity() {
    var blending: Float = 0f
    var colorOne: Int = 0
    var colorTwo: Int = 0
    var Name: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                blending = progress / 100f
                setBlendColor(blending)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }
//to s
//et surface view color
    fun setBlendColor(blendPercent: Float) {
        var color = ColorUtils.blendARGB(colorOne, colorTwo, blendPercent)
        surfaceView.setBackgroundColor(color)

    }
//intent to open companion app
    fun otherapp(view : View, requestCode: Int, extras: String){
        val launchColorPicker = packageManager.getLaunchIntentForPackage("com.example.hamzakhokhar.colorpicker")  as Intent
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.putExtra(extras,requestCode)
//        intent.putExtra(extras,2)
//        intent.putExtra(extras,3)
    startActivityForResult(intent, requestCode)

    }

    fun firstColor (view : View){
        Name = "firstColor"
        otherapp(view, 1, Name)
    }

    fun secondColor(view : View){
        Name = "secondColor"
        otherapp(view, 2, Name)

    }

    fun thirdColor(view: View){
        Name = "textColor"
        otherapp(view, 3, Name)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data?.extras == null) {

        }
        if (requestCode == 1 && resultCode == Activity.RESULT_OK){
            colorOne = data!!.getIntExtra("Color", 0)
            colorA.setBackgroundColor(colorOne)

            surfaceView.setBackgroundColor(colorOne)
        }
        if (requestCode == 2 && resultCode == Activity.RESULT_OK){
            colorTwo = data!!.getIntExtra("Color", 0)
            colorB.setBackgroundColor(colorTwo)
            surfaceView.setBackgroundColor(colorTwo)
        }
        if (requestCode == 3 && resultCode == Activity.RESULT_OK) {
            var color = data!!.getIntExtra("Color", 0)
            textcolor.setTextColor(color)
        }
    }
}

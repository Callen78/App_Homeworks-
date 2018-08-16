package com.example.ceocarlallen.newcolorpicker

import android.app.ProgressDialog.show
import android.content.ClipData
import android.content.DialogInterface
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony.Mms.Part.FILENAME
import android.support.annotation.StringDef
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.Menu
import android.widget.SeekBar
import android.util.Log
import android.view.View
import android.widget.EditText
import android.view.MenuItem
import android.view.SurfaceView
import android.widget.*
import com.example.ceocarlallen.newcolorpicker.R.color.red
import com.example.ceocarlallen.newcolorpicker.R.id.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.io.File
import java.util.*

class MainActivity : AppCompatActivity() {

    //variable to hold filename
    val FILENAME = "saved_colors.txt"

    //created an array List to hold colors
    var colorList = arrayListOf<String>()

    //on Create function
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //added to use the toolbar
        setSupportActionBar(theToolbar)

        //writes to a file
        val file = File(filesDir, FILENAME)
        if (!file.exists()) {
            val output = file.printWriter()
            output.print("Save")
            output.close()
        }


        //created red seek bar object variable
        val redSeek = findViewById<SeekBar>(R.id.seekBar)

        //blue seek bar
        val blueSeek = findViewById<SeekBar>(R.id.blueSeekBar)

        //green seek bar
        val greenSeek = findViewById<SeekBar>(R.id.greenSeekBar)

        //Surface View object
        val colorView = findViewById<SurfaceView>(R.id.colorView)



        //Holds the colors
        var redC = 0
        var blueC = 0
        var greenC = 0


        //red Seek Bar properties
        redSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar?, progress: Int, fromUser: Boolean) {
                redC = progress

                redProgressNum.setText(progress.toString())

                Log.d("red", greenC.toString());
                setSurfaceViewColor(redC, greenC, blueC)
                redSeek.max = 255

            }

            override fun onStartTrackingTouch(SeekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(SeekBar: SeekBar?) {
            }
        })


        //blue Seek Bar properties
        blueSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar?, progress: Int, user: Boolean) {
                blueSeek.max = 255

                // keeps track of the progress bar
                blueC = progress

                Log.d("blue", blueC.toString());
                //
                blueProgressNum.setText(progress.toString())
                setSurfaceViewColor(redC, greenC, blueC)

            }

            //on start tracking method
            override fun onStartTrackingTouch(SeekBar: SeekBar?) {
            }

            //on stop tracking method
            override fun onStopTrackingTouch(SeekBar: SeekBar?) {
            }
        })


        //green Seek Bar properties
        greenSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, user: Boolean) {

                // keeps track of the progress of the progress
                greenC = progress

                //get progress from the seekBar
                greenProgressNum.setText(progress.toString())

                //surface view
                setSurfaceViewColor(redC, greenC, blueC)
                Log.d("red", redC.toString());
                //sets the green seekBar
                greenSeek.max = 255

            }

            override fun onStartTrackingTouch(SeekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(SeekBar: SeekBar?) {

            }
        })


        redProgressNum.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                try {
                    //Update Seekbar value after entering a number
                    seekBar.setProgress(Integer.parseInt(redProgressNum.editableText.toString()))
                    redProgressNum.setSelection(redProgressNum .editableText.toString().length)
                } catch (ex: Exception) {

                }


            }
        })

        blueProgressNum.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                try {
                    //Update Seekbar value after entering a number
                    blueSeekBar.setProgress(Integer.parseInt(blueProgressNum.editableText.toString()))
                    blueProgressNum.setSelection(blueProgressNum.editableText.toString().length)
                } catch (ex: Exception) {

                }


            }
        })

        greenProgressNum.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                try {
                    //Update Seekbar value after entering a number
                    greenSeekBar.setProgress(Integer.parseInt(greenProgressNum.editableText.toString()))
                    greenProgressNum.setSelection(greenProgressNum.editableText.toString().length)
                } catch (ex: Exception) {

                }


            }
        })
    }

    private fun setSurfaceViewColor(r: Int, g: Int, b: Int) {
        colorView.setBackgroundColor(Color.rgb(r, g, b))
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val builder = AlertDialog.Builder(this)

        return when (item.itemId) {
            R.id.save -> {
                var name: EditText? = null

                with(builder) {
                    setTitle("Save Color")
                    setMessage("Enter Color Name Below to Save")

                    name = EditText(context)
                    name!!.hint = "Color Name"
                    name!!.inputType = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS

                    setPositiveButton("OK") { dialog, whichButton ->
                        colorList.add("${name!!.text}," + seekBar.progress + "," + 	blueSeekBar.progress + "," + greenSeekBar.progress)
                        dialog.dismiss()
                    }

                    setNegativeButton("Cancel") { dialog, whichButton -> 				dialog.dismiss() }
                }

                builder.create()
                builder.setView(name)
                builder.show()
                return true
            }

            R.id.settings -> {
                with(builder) {
                    setTitle("Recall Color")


                    setItems(colorList.toTypedArray(), 						DialogInterface.OnClickListener({ dialog, which ->
                        var selectedColor = colorList.get(which)
                        var data = selectedColor.split(",")
                        redProgressNum.setText(data[1])
                        blueProgressNum.setText(data[2])
                        greenProgressNum.setText(data[3])
                    }))

                    setPositiveButton("OK") { dialog, whichButton -> dialog.dismiss() }

                    setNegativeButton("Cancel") { dialog, whichButton -> 				dialog.dismiss() }
                }

                builder.create()
                builder.show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }

            }
    override fun onPause() {
        super.onPause()

        val file = File(filesDir, FILENAME)


        val output = file.printWriter()

        for (color in colorList) {
            output.println("$color")
        }

        output.flush()
        output.close()

    }


    override fun onResume() {
        super.onResume()

        val file = File(filesDir, FILENAME)
        val input = Scanner(file)

        while (input.hasNextLine()) {
            colorList.add(input.nextLine())
        }

        input.close()

    }
}














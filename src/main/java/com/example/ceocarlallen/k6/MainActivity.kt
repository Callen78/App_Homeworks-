package com.example.ceocarlallen.k6

import android.support.v7.app.AppCompatActivity
import android.app.Activity;
import android.content.Context
import android.os.Bundle;
import android.text.InputType.TYPE_CLASS_TEXT
import android.view.View;
import android.widget.*
import android.widget.Toast.makeText
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.lang.Integer.parseInt
import android.widget.RadioGroup




class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //var male = true

        // created an variable with a string which will display if condition is true
        var answer = "male"

        //created an variable to find a radio group button
        val radioGroup = findViewById<RadioGroup>(R.id.gender) as RadioGroup

        radioGroup.setOnCheckedChangeListener { group, checkedId ->

            //conditional statement
            //answer equals when something changes inside the radio group do this..
            //this statement is equal to a switch statement in java.
            answer =  when (checkedId) {
                R.id.male -> "male"
                R.id.female  ->"female"
                else -> "child"

            }

        }


        // This portion displays an toast message that displays the name that the user
        // types into the edit text field
        val userN = findViewById<EditText>(R.id.username) as EditText
        //sets the setOnClicker
        createUserButton.setOnClickListener{
            val name:String = userN.text.toString()
            Toast.makeText(this, "User " + name + ""+ answer + " created.", Toast.LENGTH_LONG).show()
        }



/*

         var male = true

        var radioGroup = findViewById<RadioGroup>(R.id.gender)

        radioGroup.setOnCheckedChangeListener { radioGroup, i ->

            fun onCheckedChangeListner(radioGroup: RadioGroup, int: Int){
                if(male){
                    male = true
                }
                else
                    false
            }
        }
*/




        /*var username = findViewById(R.id.username) as EditText

        Toast.makeText(this, username.text, Toast.LENGTH_SHORT).show()
        */


        /* Creates the pop up toast message when the button is clicked

        val createUserButton = findViewById(R.id.createUserButton) as Button
        // set on-click listener
        createUserButton.setOnClickListener {
            Toast.makeText(this@MainActivity, "You clicked me." , Toast.LENGTH_SHORT).show()
        }
        */


    }
}

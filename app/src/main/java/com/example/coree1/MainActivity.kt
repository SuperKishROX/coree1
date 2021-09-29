package com.example.coree1

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView


import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    override fun onStart() {
        super.onStart()
        Log.i("LIFECYCLE" , "onStart")
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("LIFECYCLE" , "onCreate")

        val score = findViewById<Button>(R.id.score)
        val steal = findViewById<Button>(R.id.steal)
        val reset = findViewById<Button>(R.id.reset)
        val result = findViewById<TextView>(R.id.result)

        val sharePref = this.getSharedPreferences("pref", MODE_PRIVATE);

        // 0 is the default value given in case the value is not found
        var increment_number = sharePref.getInt("num",0)

        // Setting the textview to last stored value so that on rotation we can see that stored value
        result.setText(Integer.toString(increment_number))
        check(increment_number,result)

        score.setOnClickListener {
            increment_number ++
            result.text = increment_number.toString()
            sharePref.edit().putInt("num",increment_number).apply()
            check(increment_number,result)


            if (increment_number >14) { increment_number = 0
                sharePref.edit().putInt("num",increment_number).apply()}


        }
        steal.setOnClickListener {
            if (increment_number in 1..16) {
                increment_number --
                result.text = increment_number.toString()
                sharePref.edit().putInt("num",increment_number).apply()
                check(increment_number,result)
            }

        }
        reset.setOnClickListener {
            increment_number = 0
            result.text = increment_number.toString()
            sharePref.edit().putInt("num",increment_number).apply()
            check(increment_number,result)
        }

    }

    fun check(increment_number:Int, result:TextView){
        Log.i("increment number", Integer.toString(increment_number))
        when {
            increment_number >= 10 -> {
                result.setTextColor(Color.GREEN)
            }
            increment_number >= 5 -> {
                result.setTextColor(Color.BLUE)

            }
            increment_number >= 5 -> {
                result.setTextColor(Color.BLUE)
            }
            else -> {
                result.setTextColor(Color.BLACK)
            }
        }
        if (increment_number == 15){
            Log.i("Playing Track", "Kono Dio Da")
            val mediaPlayer = MediaPlayer.create(this, R.raw.kono_dio_da) //audio
            mediaPlayer.start()
        }



    }






}
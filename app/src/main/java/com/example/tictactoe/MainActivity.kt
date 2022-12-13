package com.example.tictactoe

import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isInvisible
import androidx.core.view.isVisible


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var restart: Button = findViewById(R.id.Restart)
        restart.setOnClickListener {
            restartActivity(MainActivity())
        }

        //anim1
        val layout = findViewById<ConstraintLayout>(R.id.view1)
        var player1:TextView=findViewById(R.id.player1)
        val animationDrawable = layout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(300)
        animationDrawable.setExitFadeDuration(400)
        animationDrawable.start()
        //visiblity
        if(layout.isInvisible){
            layout.setVisibility(View.VISIBLE);
            player1.setVisibility(View.VISIBLE)
        }

        //anim2
        val layout2 = findViewById<ConstraintLayout>(R.id.view2)
        val animationDrawable2 = layout2.background as AnimationDrawable
        animationDrawable2.setEnterFadeDuration(15)
        animationDrawable2.setExitFadeDuration(300)
        animationDrawable2.start()

    }
    fun restartActivity(activity: MainActivity){

        val mIntent = intent
        finish()
        startActivity(mIntent)
    }

    fun buClick(view:View){
        val buSelected = view as Button
        var cellID=0
        when(buSelected.id){
            R.id.bu1 -> cellID=1
            R.id.bu2 -> cellID=2
            R.id.bu3 -> cellID=3
            R.id.bu4 -> cellID=4
            R.id.bu5 -> cellID=5
            R.id.bu6 -> cellID=6
            R.id.bu7 -> cellID=7
            R.id.bu8 -> cellID=8
            R.id.bu9 -> cellID=9
        }
        playGame(cellID,buSelected)
    }


    var player1=ArrayList<Int>()
    var player2=ArrayList<Int>()

    var activePlayer=1

    private fun playGame(cellID: Int, buSelected: Button) {
        if (activePlayer==1){
            buSelected.text="X"
            buSelected.setBackgroundColor(Color.parseColor("#aefff8"))
            player1.add(cellID)
            activePlayer=2

            val layout2 = findViewById<ConstraintLayout>(R.id.view2)
            var player2:TextView=findViewById(R.id.player2)
            val layout = findViewById<ConstraintLayout>(R.id.view1)
            var player1:TextView=findViewById(R.id.player1)

            if (layout2.isInvisible){
                layout2.setVisibility(View.VISIBLE);
                player2.setVisibility(View.VISIBLE)
            }
            if(layout.isVisible){
                layout.setVisibility(View.INVISIBLE);
                player1.setVisibility(View.INVISIBLE)
            }

        }
        else{
            buSelected.text="O"
            buSelected.setBackgroundColor(Color.parseColor("#A7E5A2"))
            player2.add(cellID)
            activePlayer=1

            val layout = findViewById<ConstraintLayout>(R.id.view1)
            var player1:TextView=findViewById(R.id.player1)
            val layout2 = findViewById<ConstraintLayout>(R.id.view2)
            var player2:TextView=findViewById(R.id.player2)

            if(layout.isInvisible){
                layout.setVisibility(View.VISIBLE);
                player1.setVisibility(View.VISIBLE)
            }
            if (layout2.isVisible){
                layout2.setVisibility(View.INVISIBLE);
                player2.setVisibility(View.INVISIBLE);
            }

        }
        buSelected.isEnabled=false
        checkWinner()

    }

    private fun checkWinner() {
        var winner = -1
        //row 1 check
        if(player1.contains(1)&&player1.contains(2)&&player1.contains(3)){
            winner=1
        }
        if(player2.contains(1)&&player2.contains(2)&&player2.contains(3)){
            winner=2
        }

        //row2
        if(player1.contains(4)&&player1.contains(5)&&player1.contains(6)){
            winner=1
        }
        if(player2.contains(4)&&player2.contains(5)&&player2.contains(6)){
            winner=2
        }

        //row 3
        if(player1.contains(7)&&player1.contains(8)&&player1.contains(9)){
            winner=1
        }
        if(player2.contains(7)&&player2.contains(8)&&player2.contains(9)){
            winner=2
        }

        //col1
        if(player1.contains(1)&&player1.contains(4)&&player1.contains(7)){
            winner=1
        }
        if(player2.contains(1)&&player2.contains(4)&&player2.contains(7)){
            winner=2
        }

        //col2
        if(player1.contains(2)&&player1.contains(5)&&player1.contains(8)){
            winner=1
        }
        if(player2.contains(2)&&player2.contains(5)&&player2.contains(8)){
            winner=2
        }
        //col3
        if(player1.contains(3)&&player1.contains(6)&&player1.contains(9)){
            winner=1
        }
        if(player2.contains(3)&&player2.contains(6)&&player2.contains(9)){
            winner=2
        }

        //diagonal 1
        if(player1.contains(1)&&player1.contains(5)&&player1.contains(9)){
            winner=1
        }
        if(player2.contains(1)&&player2.contains(5)&&player2.contains(9)){
            winner=2
        }

        //diagonal2
        if(player1.contains(3)&&player1.contains(5)&&player1.contains(7)){
            winner=1
        }
        if(player2.contains(3)&&player2.contains(5)&&player2.contains(7)){
            winner=2
        }

        //draw
        if (player1.size==4 &&player2.size==5 && winner==-1){
            winner=3
        }
        if (player1.size==5 &&player2.size==4 && winner==-1){
            winner=3
        }

        if(winner!=-1){
            if (winner==1){
                Toast.makeText(this, "Player 1 is Winner", Toast.LENGTH_SHORT).show()

                val handler = Handler()
                handler.postDelayed(Runnable { // Do something after 5s = 5000ms
                restartActivity(MainActivity())
                }, 1200)

            }
            if(winner==2){
                Toast.makeText(this, "Player 2 is Winner", Toast.LENGTH_SHORT).show()

                val handler = Handler()
                handler.postDelayed(Runnable { // Do something after 5s = 5000ms
                    restartActivity(MainActivity())
                }, 1200)

            }
            if(winner==3){
                Toast.makeText(this,  " Draw ", Toast.LENGTH_SHORT).show()

                val handler = Handler()
                handler.postDelayed(Runnable { // Do something after 5s = 5000ms
                    restartActivity(MainActivity())
                }, 1200)

            }
        }
    }

}

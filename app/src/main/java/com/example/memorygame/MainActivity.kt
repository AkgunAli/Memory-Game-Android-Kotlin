package com.example.memorygame

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.support.annotation.RequiresApi
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.memorygame.R.drawable.*

import kotlinx.android.synthetic.main.content_main.*
import java.util.Timer
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {


    private lateinit var timer: CountDownTimer
    private var secondsRemaining: Long = 60



    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val images: MutableList<Int> =
            mutableListOf(ari, kedi, tavuk, balik, fil, ordek, ari, kedi, tavuk, balik, fil, ordek)

        val buttons = arrayOf(button1, button2, button3, button4, button5, button6, button7, button8,
            button9, button10, button11, button12)

        val cardBack = code
        var clicked = 0
        var turnOver = false
        var lastClicked = -1
        var sayac =0



        val text = findViewById<TextView>(R.id.view_timer2) as TextView

        timer = object : CountDownTimer(secondsRemaining * 1000, 1000) {
            override fun onFinish(){

                text.setText("Zaman Doldu")

                sureson(View(this@MainActivity))


            }
            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished / 1000
                text.setText("Zaman : "+secondsRemaining.toString())
            }
        }.start()




        images.shuffle()
        for(i in 0..11){
            buttons[i].setBackgroundResource(cardBack)
            buttons[i].text = "cardBack"
            buttons[i].textSize = 0.0F
            buttons[i].setOnClickListener {
                if (buttons[i].text == "cardBack" && !turnOver) {
                    buttons[i].setBackgroundResource(images[i])
                    buttons[i].setText(images[i])
                    if (clicked == 0) {
                        lastClicked = i

                    }
                    clicked++
                } else if (buttons[i].text !in "cardBack") {
                    buttons[i].setBackgroundResource(cardBack)
                    buttons[i].text = "cardBack"
                    clicked--

                }

                if (clicked == 2) {
                    turnOver = true
                    if (buttons[i].text == buttons[lastClicked].text) {
                        buttons[i].isClickable = false
                        buttons[lastClicked].isClickable = false
                        sayac ++
                        turnOver = false
                        clicked = 0

                    }

                    if (sayac ==6)

                    {
                        timer.cancel()

                        dialog(View(this))

                    }



                }



                if (buttons[i].text != buttons[lastClicked].text){

                    Handler().postDelayed({

                        buttons[lastClicked].setBackgroundResource(cardBack)
                        buttons[lastClicked].text = "cardBack"
                        buttons[i].setBackgroundResource(cardBack)
                        buttons[i].text = "cardBack"
                        turnOver = false
                        clicked = 0


                    }, 600)

                }





                else if (clicked == 0) {

                    turnOver = false



                }
            }
        }

    }








    fun dialog(view: View)
    {
        // AlertDialog nesnemizi üretiyoruz
        val alert = AlertDialog.Builder(this)

        // Başlık
        alert.setTitle("Oyun Bitti!")
        //Mesaj
        alert.setMessage("Süre : " + secondsRemaining
                             + "\n"+
                            "Puan : " + secondsRemaining*15

        )
        //Herhangi bir boşluğa basınca kapanmaması için true olursa kapanır
        //Geri tuşununu da pasif hale getiriyoruz
        alert.setCancelable(false);
        //AlertDialog'un iconunu belirliyoruz

        alert.setPositiveButton("Sonraki Seviye") { dialogInterface: DialogInterface, i: Int ->
            val intent = Intent(this, Main3Activity::class.java)
            startActivity(intent)
        }

        alert.setNegativeButton("Tekrar Oyna") {dialogInterface: DialogInterface, i: Int ->
            // Hayır butonuna tıklayınca olacaklar


            finish();
            startActivity(getIntent());
 }
        alert.show()
    }







    fun sureson(view: View)
    {
        val alert = AlertDialog.Builder(this)

        alert.setTitle("Süre Doldu!")
        alert.setMessage("Süre : " + secondsRemaining
                + "\n"+
                "Puan : " + secondsRemaining*15

        )

        alert.setCancelable(false);

        alert.setPositiveButton("Tekrar Oyna") { dialogInterface: DialogInterface, i: Int ->
            finish();
            startActivity(getIntent());
        }

        alert.setNegativeButton("Anamenü") {dialogInterface: DialogInterface, i: Int ->
            // Hayır butonuna tıklayınca olacaklar

            val intent = Intent(this, Main2Activity::class.java)
            startActivity(intent)
}
        alert.show()
    }










    override fun onBackPressed() {
        // do something
    }










}














package com.example.memorygame

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.CountDownTimer
import android.os.Handler
import android.support.annotation.RequiresApi
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.memorygame.R.drawable.*
import kotlinx.android.synthetic.main.content_main2.*
import kotlinx.android.synthetic.main.content_main2.button1
import kotlinx.android.synthetic.main.content_main2.button10
import kotlinx.android.synthetic.main.content_main2.button11
import kotlinx.android.synthetic.main.content_main2.button12
import kotlinx.android.synthetic.main.content_main2.button2
import kotlinx.android.synthetic.main.content_main2.button3
import kotlinx.android.synthetic.main.content_main2.button4
import kotlinx.android.synthetic.main.content_main2.button5
import kotlinx.android.synthetic.main.content_main2.button6
import kotlinx.android.synthetic.main.content_main2.button7
import kotlinx.android.synthetic.main.content_main2.button8
import kotlinx.android.synthetic.main.content_main2.button9
import java.util.Timer
import kotlin.concurrent.schedule
class Main3Activity : AppCompatActivity() {
    private lateinit var timer: CountDownTimer
    private var secondsRemaining: Long = 120



    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val images: MutableList<Int> =
            mutableListOf(camel, coala, fox, lion, monkey, wolf, ari, ordek, kedi, tavuk, balik,fil,camel, coala, fox, lion, monkey, wolf, ari, ordek, kedi, tavuk, balik,fil)

        val buttons = arrayOf(button1, button2, button3, button4, button5, button6, button7, button8,
            button9, button10, button11, button12,button13,button14,button15,button16,button17,button18,button19,button20,button21,button22,button23,button24)

        val cardBack = code
        var clicked = 0
        var turnOver = false
        var lastClicked = -1
        var sayac =0


        val text = findViewById<TextView>(R.id.view_timer2) as TextView

        timer = object : CountDownTimer(secondsRemaining * 1000, 1000) {
            override fun onFinish(){

                text.setText("Zaman Doldu")

                sureson(View(this@Main3Activity))


            }
            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished / 1000
                text.setText("Zaman : "+secondsRemaining.toString())
            }
        }.start()




        images.shuffle()
        for(i in 0..23){
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

                    if (sayac ==12)

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
            startActivity(intent) }
        alert.show()
    }

    override fun onBackPressed() {
        // do something
    }





}





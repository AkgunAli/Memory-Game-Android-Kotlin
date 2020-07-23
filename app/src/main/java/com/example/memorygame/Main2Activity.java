package com.example.memorygame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Main2Activity extends Activity {
Button btn1,btn2;
ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        btn1 = findViewById(R.id.giris);
        btn2 = findViewById(R.id.cikis);

        img = findViewById(R.id.resimm);


        img.setImageResource(R.drawable.resim);


    }

    @Override
    public void onBackPressed() {


    }

    public void giris(View target)

    {

        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
        Main2Activity.this.startActivity(intent);
        Main2Activity.this.finish();

    }
    public void cikis(View target)

    {
     alert();

    }

public  void alert () {
    AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
builder.setTitle("Uyarı");
builder.setMessage("Çıkış Yapmak İstediğinize Eminmisiniz ?");
builder.setNegativeButton("İPTAL", new DialogInterface.OnClickListener(){
        public void onClick(DialogInterface dialog, int id) {



        }
    });


 builder.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
            finish();
            System.exit(0);


        }
    });


builder.show();

}





}

package com.example.mytimer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
private  TextView textViewTimer,textViewNumber;
private int secundTime;
private boolean isWork = false;
private TextView textViewPrint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewNumber = findViewById(R.id.texttt);
        textViewTimer = findViewById(R.id.textViewTimer);
        textViewPrint = findViewById(R.id.textPrint);
        if (savedInstanceState != null){
            secundTime = savedInstanceState.getInt("s");
            isWork = savedInstanceState.getBoolean("i");
        }
        Timer();
    }

    @Override
    public void onSaveInstanceState( Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("s",secundTime);
        outState.putBoolean("i",isWork);
    }

    public void clickStart(View view) {
        isWork = true;
    }

    public void clickpause(View view) {
        isWork = false;
    }

    public void clickRestart(View view) {
        isWork = false;
        secundTime = 0;

    }
   public void Timer(){
       Handler handler = new Handler();
       handler.post(new Runnable() {
           @Override
           public void run() {
               int hours = secundTime/3600;
               int minutes = (secundTime%3600)/60;
               int secunds = secundTime%60;
               String printTime = String.format(Locale.getDefault(),"%d:%02d:%02d",hours,minutes,secunds);
               textViewTimer.setText(printTime);

               if (isWork){
                   secundTime++;
               }
               handler.postDelayed(this,1000);
               if (secundTime==15){
                   textViewPrint.setText("One more time");

                   Toast.makeText(MainActivity.this, "It was 15 s", Toast.LENGTH_SHORT).show();
               }
               if (secundTime==18){
                   textViewPrint.setText("");
               }
           }
       });

   }


    public void clickNumber(View view) {
        isWork = true;
        String s = String.format(Locale.getDefault(),"Number: %s",secundTime);
        textViewNumber.setText(s);
    }
}
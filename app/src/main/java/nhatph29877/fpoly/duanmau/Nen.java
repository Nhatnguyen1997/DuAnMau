package nhatph29877.fpoly.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class Nen extends AppCompatActivity {
    ProgressBar pb;
    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nen);
        pb = findViewById(R.id.progress);
        thanhcho();
    }
    public void thanhcho(){
        pb = findViewById(R.id.progress);
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                counter++;
                pb.setProgress(counter);
                if(counter == 40){
                    t.cancel();
                    startActivity(new Intent(Nen.this,Login.class));
                }
            }
        };
        t.schedule(tt,0,200);

    }
    }

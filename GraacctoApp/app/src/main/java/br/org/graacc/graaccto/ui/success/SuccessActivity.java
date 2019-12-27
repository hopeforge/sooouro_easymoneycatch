package br.org.graacc.graaccto.ui.success;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import br.org.graacc.graaccto.R;

public class SuccessActivity extends AppCompatActivity {

    private TextView tv_timeout;
    private int startCountdown = 5;
    private int currentCountdown;
    private Handler countdownHandler;
    protected Timer countdownTimer;
    private static final int DELAY = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        findViews();
        countDown();
    }

    private void findViews() {
        tv_timeout = findViewById(R.id.tv_timeout);
    }

    private void countDown() {
        countdownHandler = new Handler();
        countdownTimer = new Timer();
        currentCountdown = startCountdown;
        for (int i = 0; i <= startCountdown; i++) {
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    countdownHandler.post(runnable);
                }
            };
            countdownTimer.schedule(task, i * DELAY);
        }
    }

    public void finish(View view) {
        finish();
    }

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (currentCountdown != 0) {
                tv_timeout.setText("Fechando... ".concat(String.valueOf(currentCountdown)));
                currentCountdown--;
            } else {
                finish();
            }
        }
    };

}

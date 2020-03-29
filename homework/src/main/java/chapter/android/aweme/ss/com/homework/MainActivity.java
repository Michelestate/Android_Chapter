package chapter.android.aweme.ss.com.homework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Main";

    private static final String ON_CREATE = "onCreate";
    private static final String ON_START = "onStart";
    private static final String ON_RESUME = "onResume";
    private static final String ON_PAUSE = "onPause";
    private static final String ON_STOP = "onStop";
    private static final String ON_RESTART = "onRestart";
    private static final String ON_DESTROY = "onDestroy";

    private TextView mainLifecycleDisplay;

    private void logAndAppend1(String lifecycleEvent) {
        Log.d(TAG, "Lifecycle Event: " + lifecycleEvent);
        mainLifecycleDisplay.append(lifecycleEvent + "\n");

//        Intent i1 = getIntent();
//        mainLifecycleDisplay.append(i1.getStringExtra("data1"));
//        Intent i2 = getIntent();
//        mainLifecycleDisplay.append(i2.getStringExtra("data2"));

    }

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mainLifecycleDisplay =(TextView)findViewById(R.id.tv_loglifecycle);

            logAndAppend1(ON_CREATE);
            findViewById(R.id.btn_exercises1).setOnClickListener(this);
            findViewById(R.id.btn_exercises2).setOnClickListener(this);
            findViewById(R.id.btn_exercises3).setOnClickListener(this);

        Button button = findViewById(R.id.button);
        Button button_new = findViewById(R.id.button_new);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (Exercises1.instance == 1) {
                    mainLifecycleDisplay.append("Exercises1:onStop\n");
                }else if(Exercises1.instance == 2 && (Exercises1.instance1 + Exercises1.instance2 == 2)){
                    mainLifecycleDisplay.append("Exercises1:onStop\n");
                    mainLifecycleDisplay.append("Exercises1:onDestroy\n");
                }
                Exercises1.instance = 0;
                Exercises1.instance1 = 0;
                Exercises1.instance2 = 0;
            }
        });

        button_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainLifecycleDisplay.setText("Lifecycle renew\n");
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logAndAppend1(ON_RESTART);
    }

    @Override
    protected void onStart() {
        super.onStart();
        logAndAppend1(ON_START);
    }

    @Override
    protected void onResume() {
        super.onResume();
        logAndAppend1(ON_RESUME);
    }


    @Override
    protected void onPause() {
        super.onPause();
        logAndAppend1(ON_PAUSE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        logAndAppend1(ON_STOP);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logAndAppend1(ON_DESTROY);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_exercises1:
                startActivity(new Intent(this, Exercises1.class));
                break;
            case R.id.btn_exercises2:
                startActivity(new Intent(this, Exercises2.class));
                break;
            case R.id.btn_exercises3:
                startActivity(new Intent(this, Exercises3.class));
                break;
        }
    }
}

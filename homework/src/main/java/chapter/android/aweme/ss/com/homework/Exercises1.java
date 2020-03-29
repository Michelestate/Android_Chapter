package chapter.android.aweme.ss.com.homework;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 作业1：
 * Logcat在屏幕旋转的时候 #onStop() #onDestroy()会展示出来
 * 但我们的 mLifecycleDisplay 由于生命周期的原因(Tips:执行#onStop()之后，UI界面我们是看不到的)并没有展示
 * 在原有@see Exercises1 基础上如何补全它，让其跟logcat的展示一样?
 * <p>
 * Tips：思考用比Activity的生命周期要长的来存储？  （比如：application、static变量）
 */
public class Exercises1 extends AppCompatActivity {

    public static int instance = 0;
    public static int instance1 = 0;
    public static int instance2 = 0;
    private static final String TAG = "Exercise1";

    private static final String ON_CREATE = "onCreate";
    private static final String ON_START = "onStart";
    private static final String ON_RESUME = "onResume";
    private static final String ON_PAUSE = "onPause";
    private static final String ON_STOP = "onStop";
    private static final String ON_RESTART = "onRestart";
    private static final String ON_DESTROY = "onDestroy";

    private TextView ex1LifecycleDisplay;
    private void logAndAppend(String lifecycleEvent) {
        Log.d(TAG, "Lifecycle Event: " + lifecycleEvent);
        ex1LifecycleDisplay.append(lifecycleEvent + "\n");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise1);
        ex1LifecycleDisplay = findViewById(R.id.ex1_loglifecycle);
        logAndAppend(ON_CREATE);
        instance = 0;
        instance1 = 0;
        instance2 = 0;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logAndAppend(ON_RESTART);
        instance = 0;
        instance1 = 0;
        instance2 = 0;
    }

    @Override
    protected void onStart() {
        super.onStart();
        logAndAppend(ON_START);
        instance = 0;
        instance1 = 0;
        instance2 = 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
        logAndAppend(ON_RESUME);
        instance = 0;
        instance1 = 0;
        instance2 = 0;
    }


    @Override
    protected void onPause() {
        super.onPause();
        logAndAppend(ON_PAUSE);
        instance = 0;
        instance1 = 0;
        instance2 = 0;
    }

    @Override
    protected void onStop() {
//        super.onStop();
        super.onStop();
        logAndAppend(ON_STOP);
        instance = 1;
        instance1 = 1;
//        Intent i1 = new Intent(Exercises1.this, MainActivity.class);
//        i1.putExtra("data1", "Exercise1.onStop\n");
//        startActivity(i1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logAndAppend(ON_DESTROY);
        instance = 2;
        instance2 = 1;
//        Intent i2 = new Intent(Exercises1.this, MainActivity.class);
//        i2.putExtra("data2", "Exercise1.onDestroy\n");
//        startActivity(i2);
    }

}

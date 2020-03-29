package chapter.android.aweme.ss.com.homework;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class message extends AppCompatActivity {

    private static final String TAG = "message";


//    Log.d(TAG, "Lifecycle Event:");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        TextView view_Display = findViewById(R.id.tv_content_info);

        Intent i = getIntent();
        int index = i.getIntExtra("index",0);//defaultValue默认返回值
        view_Display.append("这是第"+index+"条item\n");
    }
}

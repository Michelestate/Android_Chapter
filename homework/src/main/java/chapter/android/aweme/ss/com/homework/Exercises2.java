package chapter.android.aweme.ss.com.homework;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * 作业2：一个抖音笔试题：统计页面所有view的个数
 * Tips：ViewGroup有两个API
 * {@link android.view.ViewGroup #getChildAt(int) #getChildCount()}
 * 用一个TextView展示出来
 */
public class Exercises2 extends AppCompatActivity {

    private static final String TAG = "Exercise2";
    @SuppressLint("ResourceType")

    public int getAllChildViewCount(View view) {
        //todo 补全你的代码
        int viewCount = 0;

        if (view == null) {
            return 0;
        }
        Log.d(TAG, "已进入程序\n");
        if (view instanceof ViewGroup) {
            Log.d(TAG, "view instanceof ViewGroup\n");
            ViewGroup viewGroup = (ViewGroup) view;
            LinkedList<ViewGroup> queue = new LinkedList<ViewGroup>();
            queue.add(viewGroup);
            while (!queue.isEmpty()) {
                ViewGroup current = queue.removeFirst();
                viewCount++;
                for (int i = 0; i < current.getChildCount(); i++) {
                    if (current.getChildAt(i) instanceof ViewGroup) {
                        queue.add((ViewGroup) current.getChildAt(i));
                    } else {
                        viewCount++;
                    }
                }
            }
        } else {
            Log.d(TAG, "else viewCount++\n");
            viewCount++;
        }
        return viewCount;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise2);

        Button button5 = findViewById(R.id.button5);
        final TextView vi = findViewById(R.id.textView);
        final View view2 = (View)findViewById(R.id.exercise2_ConstraintLayout);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int viewCount;
                viewCount = getAllChildViewCount(view2);
                vi.setText("本页面所有view的个数是：" + viewCount + "\n");
                vi.append("包括页面中线性布局LineLayout和输出结果的textView!\n");
            }
        });
    }
}
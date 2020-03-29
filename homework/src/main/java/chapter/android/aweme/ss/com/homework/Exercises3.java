package chapter.android.aweme.ss.com.homework;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * 大作业:实现一个抖音消息页面,
 * 1、所需的data数据放在assets下面的data.xml这里，使用PullParser这个工具类进行xml解析即可
 * <p>如何读取assets目录下的资源，可以参考如下代码</p>
 * <pre class="prettyprint">
 *
 *         @Override
 *     protected void onCreate(@Nullable Bundle savedInstanceState) {
 *         super.onCreate(savedInstanceState);
 *         setContentView(R.layout.activity_xml);
 *         //load data from assets/data.xml
 *         try {
 *             InputStream assetInput = getAssets().open("data.xml");
 *             List<Message> messages = PullParser.pull2xml(assetInput);
 *             for (Message message : messages) {
 *
 *             }
 *         } catch (Exception exception) {
 *             exception.printStackTrace();
 *         }
 *     }
 * </pre>
 * 2、所需UI资源已放在res/drawable-xxhdpi下面
 *
 * 3、作业中的会用到圆形的ImageView,可以参考 widget/CircleImageView.java
 */

public class Exercises3 extends AppCompatActivity implements UserAdapter.ListItemClickListener {
    private static final String TAG = "Exercise3";
//    private static final int NUM_LIST_ITEMS = 100;
    private List<User> mUserList = new ArrayList<>();
//    private Toast mToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG,"Exercise3:onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        RecyclerView mUsersListView;
        UserAdapter muAdapter;

        initUsers();
//        Log.d(TAG,"离开initUsers函数");
        mUsersListView = findViewById(R.id.rv_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mUsersListView.setLayoutManager(layoutManager);
        /*
         * Use this setting to improve performance if you know that changes in content do not
         * change the child layout size in the RecyclerView
         */
        mUsersListView.setHasFixedSize(true);

        /*
         * The UsersAdapter is responsible for displaying each item in the list.
         */
        muAdapter = new UserAdapter(mUserList, this);

        mUsersListView.setAdapter(muAdapter);
        mUsersListView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            // 最后一个完全可见项的位置
            private int lastCompletelyVisibleItemPosition;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (visibleItemCount > 0 && lastCompletelyVisibleItemPosition >= totalItemCount - 1) {
                        Toast.makeText(Exercises3.this, "已滑动到底部!,触发loadMore", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    lastCompletelyVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
                }
                Log.d(TAG, "onScrolled: lastVisiblePosition=" + lastCompletelyVisibleItemPosition);
            }
        });
    }

    private void initUsers() {
        try {
//            Log.d(TAG,"进入initUsers函数");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // 使用工厂来实例化一个构造Document的对象
            DocumentBuilder builder = factory.newDocumentBuilder();
            // 利用DocumentBuilder来构造一个Document对象
            Document document = builder.parse(getAssets().open("data.xml"));
            // 获取Document文档的根节点对象
            Element element = document.getDocumentElement();
            // 通过根节点，获取到根节点下面的所有二级子节点
            NodeList nodeList = element.getElementsByTagName("message");
//            Log.d(TAG,"2");

            // 遍历子节点，获取内部的每一个节点
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element message = (Element)nodeList.item(i);
                String title = message.getElementsByTagName("title").item(0).getTextContent();
//                Log.d(TAG,"1");
//                Log.d(TAG,title);
                String description = message.getElementsByTagName("hashtag").item(0).getTextContent();
//                Log.d(TAG,description);
                String time = message.getElementsByTagName("time").item(0).getTextContent();
                String image = message.getElementsByTagName("icon").item(0).getTextContent();

                int picture;
                switch (image) {
                    case "TYPE_ROBOT":
                        picture = R.drawable.session_robot;
                        break;
                    case "TYPE_GAME":
                        picture = R.drawable.icon_micro_game_comment;
                        break;
                    case "TYPE_SYSTEM":
                        picture = R.drawable.session_system_notice;
                        break;
                    case "TYPE_STRANGER":
                        picture = R.drawable.session_stranger;
                        break;
                    default:
                        picture = R.drawable.icon_girl;
                        break;
                }

                User user1 = new User(title, description, time, picture);
                mUserList.add(user1);
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Log.d(TAG, "onListItemClick: ");
        Intent i = new Intent(Exercises3.this, message.class);
        i.putExtra("index", clickedItemIndex);
        startActivity(i);
    }
}
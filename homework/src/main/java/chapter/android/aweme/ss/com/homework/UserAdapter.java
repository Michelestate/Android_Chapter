package chapter.android.aweme.ss.com.homework;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import chapter.android.aweme.ss.com.homework.widget.CircleImageView;

/**
 * 适配器
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private static final String TAG = "UserAdapter";
    //private int mNumberItems;
    //private static int viewHolderCount;
    private List<User> mUserList ;
    private final ListItemClickListener mOnClickListener;

    public UserAdapter(List<User> UserList, ListItemClickListener listener) {
        this.mUserList = UserList;
        this.mOnClickListener = listener;
        //viewHolderCount = 0;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.im_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        ViewHolder viewHolder = new ViewHolder(view);

        Log.d(TAG, "onCreateViewHolder: ViewHolder created");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Log.d(TAG, "onBindViewHolder: #" + position);
        User user = mUserList.get(position);
        viewHolder.listItemTitleView.setText(user.getTitle());
        viewHolder.listItemdescView.setText(user.getDescription());
        viewHolder.listItemTimeView.setText(user.getTime());
        viewHolder.viewHolderImage.setImageResource(user.getImageId());
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView viewHolderImage;
        private final TextView listItemTitleView;
        private final TextView listItemdescView;
        private final TextView listItemTimeView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            listItemTitleView = (TextView) itemView.findViewById(R.id.tv_title);
            listItemdescView = (TextView) itemView.findViewById(R.id.tv_description);
            listItemTimeView = (TextView) itemView.findViewById(R.id.tv_time);
            viewHolderImage = (CircleImageView) itemView.findViewById(R.id.iv_avatar);
            itemView.setOnClickListener(this);
        }


//            viewHolderIndex.setText(String.format("ViewHolder index: %s", getAdapterPosition()));
//            int backgroundColorForViewHolder = ColorUtils.
//            getViewHolderBackgroundColorFromInstance(itemView.getContext(), getAdapterPosition() % 10);
//            itemView.setBackgroundColor(backgroundColorForViewHolder);


        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            if (mOnClickListener != null) {
                mOnClickListener.onListItemClick(clickedPosition);
            }
        }
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}
package com.example.vikrant.demoapp27;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.vikrant.demoapp27.Util.RoundImageView;
import com.example.vikrant.demoapp27.database.UserList;

import java.util.List;

/**
 * Created by sit78 on 03-01-2018.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserRowHolder> {

    private Context context;
    private List<UserList> mUserLists;

    public UserAdapter(Context context) {
        this.context = context;
    }

    @Override
    public UserRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_list_row_holder, parent, false);
        UserRowHolder userRowHolder = new UserRowHolder(view);
        return userRowHolder;
    }

    @Override
    public void onBindViewHolder(UserRowHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    void setList(List<UserList> list) {
        mUserLists = list;
        notifyDataSetChanged();
    }


    public class UserRowHolder extends RecyclerView.ViewHolder {

        private Button btnInsta;
        private TextView txtName, txtUserName, txtFollower, txtFollowing;
        private RoundImageView profileImage;

        public UserRowHolder(View itemView) {
            super(itemView);

            btnInsta = itemView.findViewById(R.id.btnInsta);
            txtName = itemView.findViewById(R.id.txtName);
            txtFollower = itemView.findViewById(R.id.txtFollower);
            txtFollowing = itemView.findViewById(R.id.txtFollowing);
            txtUserName = itemView.findViewById(R.id.txtUserName);
            profileImage = itemView.findViewById(R.id.profileImage);
        }
    }
}

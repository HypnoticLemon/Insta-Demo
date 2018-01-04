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
import com.squareup.picasso.Picasso;

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

        holder.txtName.setText(mUserLists.get(position).getName());
        holder.txtFollower.setText(mUserLists.get(position).getFollows());
        holder.txtFollowing.setText(mUserLists.get(position).getFollowed_by());
        holder.txtUserName.setText(mUserLists.get(position).getUser_name());

        Picasso.with(context)
                .load(mUserLists.get(position).getProfile_pic())
                .centerCrop()
                .fit()
                .into(holder.profileImage);


    }

    @Override
    public int getItemCount() {
        return (mUserLists != null ? mUserLists.size() : 0);
    }


    void setList(List<UserList> list) {
        mUserLists = list;
        notifyDataSetChanged();
    }


    public class UserRowHolder extends RecyclerView.ViewHolder {

        private TextView txtName, txtUserName, txtFollower, txtFollowing;
        private RoundImageView profileImage;

        public UserRowHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtFollower = itemView.findViewById(R.id.txtFollower);
            txtFollowing = itemView.findViewById(R.id.txtFollowing);
            txtUserName = itemView.findViewById(R.id.txtUserName);
            profileImage = itemView.findViewById(R.id.profileImage);
        }
    }
}

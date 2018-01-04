package com.example.vikrant.demoapp27;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.vikrant.demoapp27.database.UserList;

import java.util.List;

public class LoginHistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerViewUserList;
    private UserViewModel userViewModel;
    private String TAG = LoginHistoryActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_history);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Login History");

        objectInitialization();
        eventListener();
    }

    private void eventListener() {

    }

    private void objectInitialization() {
        recyclerViewUserList = findViewById(R.id.recyclerViewUserList);

        final UserAdapter userAdapter = new UserAdapter(this);
        recyclerViewUserList.setAdapter(userAdapter);
        recyclerViewUserList.setLayoutManager(new LinearLayoutManager(this));

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getAllUser().observe(this, new Observer<List<UserList>>() {
            @Override
            public void onChanged(@Nullable List<UserList> userLists) {
                userAdapter.setList(userLists);
                if (userLists != null && userLists.size() > 0) {
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}

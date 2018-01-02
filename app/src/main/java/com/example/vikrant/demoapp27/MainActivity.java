package com.example.vikrant.demoapp27;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vikrant.demoapp27.Util.Constants;
import com.example.vikrant.demoapp27.Util.InstagramApp;
import com.example.vikrant.demoapp27.Util.RoundImageView;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnInsta;
    private InstagramApp mApp;
    private HashMap<String, String> userInfoHashmap = new HashMap<String, String>();
    private TextView txtName, txtUserName, txtFollower, txtFollowing;
    private RoundImageView profileImage;
    private RelativeLayout relativeDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        objectInitialization();
        eventListener();


        mApp = new InstagramApp(this, Constants.CLIENT_ID,
                Constants.CLIENT_SECRET, Constants.CALLBACK_URL);
        mApp.setListener(new InstagramApp.OAuthAuthenticationListener() {

            @Override
            public void onSuccess() {
                btnInsta.setText("Disconnect");
                relativeDetails.setVisibility(View.VISIBLE);
                mApp.fetchUserName(handler);
            }

            @Override
            public void onFail(String error) {
                relativeDetails.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT)
                        .show();
            }
        });


        if (mApp.hasAccessToken()) {
            btnInsta.setText("Disconnect");
            relativeDetails.setVisibility(View.VISIBLE);
            mApp.fetchUserName(handler);
        }

    }

    private void objectInitialization() {
        btnInsta = findViewById(R.id.btnInsta);
        txtName = findViewById(R.id.txtName);
        txtFollower = findViewById(R.id.txtFollower);
        txtFollowing = findViewById(R.id.txtFollowing);
        txtUserName = findViewById(R.id.txtUserName);
        profileImage = findViewById(R.id.profileImage);
        relativeDetails = findViewById(R.id.relativeDetails);
        relativeDetails.setVisibility(View.GONE);
    }

    private void eventListener() {
        btnInsta.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnInsta:
                sessionToggle();
                break;
        }
    }

    private void sessionToggle() {
        if (mApp.hasAccessToken()) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(
                    MainActivity.this);
            builder.setMessage("Disconnect from Instagram?")
                    .setCancelable(false)
                    .setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    mApp.resetAccessToken();
                                    relativeDetails.setVisibility(View.GONE);
                                    // btnConnect.setVisibility(View.VISIBLE);
                                    relativeDetails.setVisibility(View.GONE);
                                    btnInsta.setText("Connect");
                                    // tvSummary.setText("Not connected");
                                }
                            })
                    .setNegativeButton("No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    dialog.cancel();
                                }
                            });
            final AlertDialog alert = builder.create();
            alert.show();
        } else {
            mApp.authorize();
        }
    }


    private Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == InstagramApp.WHAT_FINALIZE) {
                userInfoHashmap = mApp.getUserInfo();

                txtName.setText(userInfoHashmap.get("full_name"));
                txtFollower.setText(userInfoHashmap.get("follows"));
                txtFollowing.setText(userInfoHashmap.get("followed_by"));
                txtUserName.setText(userInfoHashmap.get("username"));

                Picasso.with(MainActivity.this)
                        .load(userInfoHashmap.get("profile_picture"))
                        .centerCrop()
                        .fit()
                        .into(profileImage);

            } else if (msg.what == InstagramApp.WHAT_FINALIZE) {
                Toast.makeText(MainActivity.this, "Check your network.",
                        Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    });
}
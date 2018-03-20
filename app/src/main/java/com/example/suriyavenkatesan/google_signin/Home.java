package com.example.suriyavenkatesan.google_signin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
    private Button glogout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAuth=FirebaseAuth.getInstance();
        glogout=(Button)findViewById(R.id.btn_gl);

        final ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        glogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    mAuth.signOut();
                    startActivity(new Intent(getApplication(), MainActivity.class));
                }
                else {
                    AlertDialog alert=new AlertDialog.Builder(Home.this).create();
                    alert.setTitle("Notification");
                    alert.setMessage("Please Turn on the Internet.");
                    alert.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alert.show();

                }
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mAuth.signOut();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

}

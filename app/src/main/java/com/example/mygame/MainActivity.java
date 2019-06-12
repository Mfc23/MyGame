package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView header;
    private TextView alterOp;
    private TextView userInfo;
    private TextView passWord;

    private Button topBut;
    private Button botBut;

    private int mode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up text views
        header   = findViewById(R.id.headerMain);
        alterOp  = findViewById(R.id.botText);
        userInfo = findViewById(R.id.userEnter);
        passWord = findViewById(R.id.passEnter);

        //set up buttons
        topBut     = findViewById(R.id.LogBut);
        botBut = findViewById(R.id.newBut);

        topBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mode == 1)
                    checkLogin();
                if(mode == 2)
                    createLogin();
            }
        });

        botBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mode == 1)
                    NewAccount();
                if(mode == 2)
                    setLog();
            }
        });
    }

    public void checkLogin(){

    }

    public void createLogin(){

    }

    //switches the mode for new accounts
    public void NewAccount(){
        header.setText("Create New Account info");
        alterOp.setText("Already have an account?");
        topBut.setText("Create new!");
        botBut.setText("Login");
        mode = 2;
    }

    //resets to let users login
    public void setLog(){
        header.setText("Enter Login info");
        alterOp.setText("To start a new game");
        topBut.setText("LOGIN");
        botBut.setText("NEW SAVE");
        mode = 1;
    }
}

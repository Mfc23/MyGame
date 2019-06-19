package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String[]> users;

    private TextView header;
    private TextView alterOp;
    private TextView userInfo;
    private TextView passWord;

    private Button topBut;
    private Button botBut;

    private int mode = 1;

    private SQLControl helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //loads all users for the app
        loadUsers();

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


    //goes through the users to see if the name exists
    private boolean findUser(String checkedName){
        String[] names;

        for(int i = 0; i < users.size() - 1; i++)
        {
            names = users.get(i);

            if(checkedName.equals(names[1]))
                return true;
        }

        return false;
    }


    //loads the array list or makes a new one
    private void loadUsers(){
        //loads the data from shared prefrences
        SharedPreferences sharedPreferences = getSharedPreferences("Data", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Data List", null);
        Type type = new TypeToken<ArrayList<String[]>>() {}.getType();
        users = gson.fromJson(json,type);

        //if no data is found, makes a new arraylist
        if (users == null){
            users = new ArrayList<>();
        }
    }

    //saves the new incoming data
    private void saveUser(String[] save){
        //adds the new information to the array list
        users.add(save);

        //saves the data
        SharedPreferences sharedPreferences = getSharedPreferences("Data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(users);
        editor.putString("Data List", json);
        editor.apply();
    }
}

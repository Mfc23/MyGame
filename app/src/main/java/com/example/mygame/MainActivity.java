package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

    //checks if the user exists and if the password matches
    //if so, moves to the next stage
    public void checkLogin(){
        //needed strings
        String userName = userInfo.getText().toString();
        String enteredPass = passWord.getText().toString();
        String realPass = findUser(userName);

        //possible error message
        String toast = "";

        //checks if password exists
        if (realPass.equals(""))
            toast = "Username not found!";
        else if (realPass.equals(enteredPass))
            toNext();
        else
            toast = "Password was not correct!";

        //toasts error message
        Toast.makeText(this, toast, Toast.LENGTH_SHORT);
    }

    public void createLogin(){

    }

    public void toNext(){

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
    private String findUser(String checkedName){
        String[] names;

        for(int i = 0; i < users.size() - 1; i++)
        {
            names = users.get(i);

            //If user exists returns their password
            if(checkedName.equals(names[1]))
                return names[2];
        }

        //if the user does not exist, returns ""
        return "";
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

package com.hogo.rahul.databaselr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hogo.rahul.databaselr.Database.Database;

public class Dashboard extends AppCompatActivity {

    EditText tvName, tvEmail, tvPassword;
    Button btnSumbmit;
    String Name, Email, password;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        database = new Database(this);

        tvName = (EditText) findViewById(R.id.tv_name);
        tvEmail = (EditText) findViewById(R.id.tv_email);
        tvPassword = (EditText) findViewById(R.id.tv_passworrd);
        btnSumbmit = (Button) findViewById(R.id.btn_submit);


        btnSumbmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Name = tvName.getText().toString();
                Email = tvEmail.getText().toString();
                password = tvPassword.getText().toString();

                if (Name.equals("") && (Email.equals("")) && (password.equals(""))) {
                    Toast.makeText(Dashboard.this, "please fill Name", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(getApplicationContext(), "Done All the fields", Toast.LENGTH_SHORT).show();


                    boolean checkexit = database.insertToCartTable(Name, Email, password);

                    if (checkexit == false) {

                        Toast.makeText(getApplicationContext(), "This Product Is Already Exit", Toast.LENGTH_SHORT).show();

                    }
                    if (checkexit == true) {
                        Toast.makeText(getApplicationContext(), "Product Added Successfully", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


}

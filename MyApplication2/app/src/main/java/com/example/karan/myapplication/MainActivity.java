package com.example.karan.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String usertype;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
final helper db=new helper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button submit=(Button) findViewById(R.id.button);
        Button login=(Button)findViewById(R.id.button2);
        final RadioButton seller=(RadioButton)findViewById(R.id.radioButton2);
        final RadioButton buyer=(RadioButton)findViewById(R.id.radioButton3);
        final EditText username=(EditText)findViewById(R.id.editText);
        final EditText password=(EditText)findViewById(R.id.editText2);
        seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usertype ="seller";
            }
        });
        buyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usertype ="buyer";
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usertype1=db.Login(username.getText().toString(),password.getText().toString());
                if(usertype1.equals("FFFF"))
                {
                    Toast.makeText(getApplicationContext(),"Invalid details",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"LogIn Successfull",Toast.LENGTH_SHORT).show();
                    if(usertype1.equals("seller")) {
                        Intent next = new Intent(MainActivity.this, seller.class);
                        startActivity(next);
                    }
                    else
                    {
                        Intent next=new Intent(MainActivity.this,buyer.class);
                        startActivity(next);
                    }

                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result=db.insertData(username.getText().toString(),password.getText().toString(),usertype);
                if(result==true)
                {
                    Toast.makeText(getApplicationContext(),"Data Inserted",Toast.LENGTH_SHORT).show();
                    if(db.Login(username.getText().toString(),password.getText().toString()).equals("buyer"))
                    {
                        Intent next=new Intent(MainActivity.this,buyer.class);
                        startActivity(next);

                    }
                }
                else
                    Toast.makeText(getApplicationContext(),"Data Not Inserted",Toast.LENGTH_SHORT).show();

            }
        });

    }
}

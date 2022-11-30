package com.example.karan.am;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyHelper MyDb=new MyHelper(this);
    public String UID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Button lg,nr;
        final EditText un,pass;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                      // Code here executes on main thread after user presses
                lg=(Button)findViewById(R.id.button);
                nr=(Button)findViewById(R.id.button2);
                un=(EditText)findViewById(R.id.editText2);
                pass=(EditText)findViewById(R.id.editText);
        ImageButton quit=(ImageButton)findViewById(R.id.quit);

                nr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                Intent rga=new Intent(MainActivity.this,RegestrationActivity.class);
                startActivity(rga);
                    }
                });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                System.exit(0);
            }
        });

                lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usertype = MyDb.SignIn(un.getText().toString(), pass.getText().toString());
                if (usertype.equals("FFFF")) {
                    Toast.makeText(getApplicationContext(), "Invalid Usename or Password,Try again", Toast.LENGTH_SHORT).show();
                    un.setText("");
                    pass.setText("");

                } else {

                    UID=un.getText().toString();
                    if(usertype.equals("buyer")) {

                        //Bundle bundle=new Bundle();
                        //bundle.putString("username",UID);

                        Intent buyer;
                        buyer = new Intent(MainActivity.this, Buyer.class);
                        buyer.putExtra("username",UID);
                        startActivity(buyer);
                    }
                    else {
                        Intent seller=new Intent(MainActivity.this,CreateAcD.class);
                        seller.putExtra("username",UID);
                        startActivity(seller);
                    }
                }
            }

        });
    }
}

package com.example.karan.am;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Details extends AppCompatActivity {
MyHelper db=new MyHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView UID=(TextView)findViewById(R.id.textView29);
        TextView NAME=(TextView)findViewById(R.id.editText10);
        TextView ADDRESS=(TextView)findViewById(R.id.editText4);
        TextView EMAIL=(TextView)findViewById(R.id.editText5);
        TextView PHONE=(TextView)findViewById(R.id.editText9);
        TextView WASTETYPE=(TextView)findViewById(R.id.textView42);
        TextView QUANTITY=(TextView)findViewById(R.id.textView44);
        TextView COST=(TextView)findViewById(R.id.textView46);
        final TextView Billing_detail=(TextView)findViewById(R.id.textView47);

        Button bill=(Button)findViewById(R.id.button9);
        ImageButton buy=(ImageButton)findViewById(R.id.imageButton8);
        ImageButton logout=(ImageButton)findViewById(R.id.imageButton6);
        ImageButton back=(ImageButton)findViewById(R.id.imageButton7);

        String Name = null;
        String Phone=null;
        String Email=null;
        String Address=null;
        final String user,wastetype,quantity,cost;
        Intent intent=getIntent();
        wastetype=intent.getStringExtra("WASTETYPE");
        quantity=intent.getStringExtra("QUANTITY");
        cost=intent.getStringExtra("COST");
        user=intent.getStringExtra("UID");

        Cursor cursor1=db.getDetail(user);
        while(cursor1.moveToNext()) {

            Name = cursor1.getString(cursor1.getColumnIndex("NAME"));

            Phone = cursor1.getString(cursor1.getColumnIndex("PHONE"));

            Email = cursor1.getString(cursor1.getColumnIndex("EMAIL"));

            Address = cursor1.getString(cursor1.getColumnIndex("ADDRESS"));
        }


        UID.setText(intent.getStringExtra("UID"));
        NAME.setText(Name);
        ADDRESS.setText(Address);
        EMAIL.setText(Email);
        PHONE.setText(Phone);
        WASTETYPE.setText(wastetype);
        QUANTITY.setText(quantity);
        final int Rate;
        Rate =Integer.parseInt(cost);
        COST.setText(cost);

        bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount=Rate*Integer.parseInt(quantity);
                Billing_detail.setText("Amount to be Paid :"+amount);

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1=new Intent(Details.this,MainActivity.class);

                startActivity(intent1);
                Toast.makeText(getApplicationContext(),"Logged out",Toast.LENGTH_SHORT).show();

            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean res=db.buy(user,wastetype,quantity,cost);
                if(res)
                {
                    Toast.makeText(getApplicationContext(),"Order Placed",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Order Not Placed",Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back=new Intent(Details.this,Buyer.class);
                back.putExtra("username",user);
                startActivity(back);
            }
        });




    }
}

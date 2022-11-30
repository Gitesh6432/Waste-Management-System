package com.example.karan.am;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Buyer extends AppCompatActivity {
    SellerListAdapter adapter;
MyHelper db=new MyHelper(this);

    static String waste=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer);

        Intent intent=getIntent();
        final String UID=intent.getStringExtra("username");
        TextView userType=(TextView)findViewById(R.id.title_buyer);
        userType.setText("Hello "+UID);
        ImageButton logout=(ImageButton)findViewById(R.id.logout_buyer);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1=new Intent(Buyer.this,MainActivity.class);

                startActivity(intent1);
                Toast.makeText(getApplicationContext(),"Logged out",Toast.LENGTH_SHORT).show();

            }
        });



        final ListView listView=(ListView)findViewById(R.id.ListV);
        Cursor cursor;
        String wastetype;
         String user;
         String quantity;
         String cost;
        cursor=db.fetchData(waste);
        ArrayList<Seller> sellers=new ArrayList<>();
        Seller temp;
        if(cursor!=null)
        {
            while(cursor.moveToNext())
            {
                wastetype=cursor.getString(1);
                user=cursor.getString(0);
                quantity=cursor.getString(2);
                cost=cursor.getString(3);
                temp=new Seller(wastetype,user,quantity,cost);
                sellers.add(temp);
            }
        }



        adapter = new SellerListAdapter(this, R.layout.adapter_view_layout,sellers);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Seller seller= (Seller) listView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),"Fetching details",Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(Buyer.this,Details.class);
                intent1.putExtra("UID",seller.getUser());
                intent1.putExtra("WASTETYPE",seller.getWastetype());
                intent1.putExtra("QUANTITY",seller.getQuantity());
                intent1.putExtra("COST",seller.getCost());
                startActivity(intent1);
           }
        });









    }
}

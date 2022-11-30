package com.example.karan.am;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SellerListAdapter extends ArrayAdapter<Seller>{

    private static final String TAG="SellerListAdapter";
    private Context mcontext;
    int mResource;

    public SellerListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Seller> objects) {
        super(context, resource, objects);
        mcontext=context;
        mResource=resource;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView,ViewGroup parent) {
        String wastetype=getItem(position).getWastetype();
        String user=getItem(position).getUser();
        String quantity=getItem(position).getQuantity();
        String cost=getItem(position).getCost();

        Seller seller=new Seller(wastetype,user,quantity,cost);
        LayoutInflater inflater=LayoutInflater.from(mcontext);
        convertView=inflater.inflate(mResource,parent,false);

        TextView tvwastetype=(TextView)convertView.findViewById(R.id.textView16);
        TextView tvuser=(TextView)convertView.findViewById(R.id.textView17);
        TextView tvquantity=(TextView)convertView.findViewById(R.id.textView19);
        TextView tvcost=(TextView)convertView.findViewById(R.id.textView20);

        tvwastetype.setText(wastetype);
        tvuser.setText(user);
        tvquantity.setText(quantity+" Kg");
        tvcost.setText("$ "+cost);

        return convertView;
    }
}

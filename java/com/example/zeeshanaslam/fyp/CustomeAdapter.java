package com.example.zeeshanaslam.fyp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import net.igenius.customcheckbox.CustomCheckBox;

import java.util.ArrayList;

public class CustomeAdapter extends RecyclerView.Adapter<CustomeAdapter.MyViewHolder> {

    //private ArrayList<ItemModel> dataSet;
    private String [] pricce;
    private  String[]name;

    //private  String[]id;
    private Context context;
    public  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CustomCheckBox customCheckBox;
        TextView name;
        TextView price;
        TextView count;
        Context context;
        Button addBtn,subBtn;

        public MyViewHolder(@NonNull View itemView, final Context context) {
            super(itemView);
            this.customCheckBox = itemView.findViewById(R.id.checkItem);
            this.name =itemView.findViewById(R.id.itemName);
            this.price = itemView.findViewById(R.id.price);
            this.count = itemView.findViewById(R.id.count);
            this.addBtn = itemView.findViewById(R.id.addBtn);
            this.subBtn = itemView.findViewById(R.id.subBtn);
            this.context=context;

            itemView.setOnClickListener(this);
            subBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int b=getAdapterPosition();
                    Intent intent = new Intent("ADD_PAYMENT");
                    intent.putExtra("index", b+"");
                    intent.putExtra("msg", "sub");
                    intent.putExtra("name",name.getText().toString());
                    if(Integer.parseInt(count.getText().toString())>0)
                    {
                        count.setText(((Integer.parseInt(count.getText().toString()))-1)+"");
                        if(Integer.parseInt(count.getText().toString())==0)
                        {
                            customCheckBox.setChecked(false);
                        }
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                    }

                }
            });

            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int b=getAdapterPosition();

                    //String t=total.getText().toString();

                    Intent intent = new Intent("ADD_PAYMENT");

                    customCheckBox.setChecked(true);
                    intent.putExtra("index", b+"");
                    intent.putExtra("msg", "add");

                    intent.putExtra("name",name.getText().toString());
                    count.setText(1+((Integer.parseInt(count.getText().toString())))+"");
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                }
            });

            customCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int b=getAdapterPosition();

                    //String t=total.getText().toString();

                    Intent intent = new Intent("ADD_PAYMENT");


                    if(customCheckBox.isChecked())
                    {
                        customCheckBox.setChecked(false);
                        intent.putExtra("index", b+"");
                        intent.putExtra("msg", "sub");

                        intent.putExtra("name",name.getText().toString());
                        count.setText(((Integer.parseInt(count.getText().toString()))-1)+"");
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                    }
                    else
                    {
                        customCheckBox.setChecked(true);
                        intent.putExtra("index", b+"");
                        intent.putExtra("msg", "add");
                        intent.putExtra("name",name.getText().toString());
                        count.setText(1+((Integer.parseInt(count.getText().toString())))+"");
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);




                    }
                }
            });
        }

        @Override
        public void onClick(View v) {



            int b=getAdapterPosition();
            String az;
            if(b==0)
            {
                Toast.makeText(context,"Found",Toast.LENGTH_SHORT).show();

            }
        }
    }

    public CustomeAdapter(String []name,String [] price,Context context) {

        //this.dataSet = dataSet;
        this.name=name;
        this.pricce=price;
        //this.id=id;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_layout, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view,context);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.name.setText(name[i]);
        myViewHolder.price.setText(pricce[i]);

    }

    @Override
    public int getItemCount() {
        return name.length;
    }
}

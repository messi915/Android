package com.example.zeeshanaslam.fyp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProgrammingAdapter extends RecyclerView.Adapter<ProgrammingAdapter.ProgrammingViewHolder> {

    private  int [] images;
    private  String[]data;
    private Context context;
    public ProgrammingAdapter(String []data,int [] img,Context context)
    {
        this.data=data;
        images=img;
        this.context=context;

    }

    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.list_item_layout,parent,false);

        return new ProgrammingViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingViewHolder programmingViewHolder, int i) {


        String title=data[i];
        int image_id = images[i];
        programmingViewHolder.txtTitle.setText(title);
        programmingViewHolder.imgIcon.setImageResource(image_id);

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgIcon;
        TextView txtTitle;
        Context context;
        public ProgrammingViewHolder(@NonNull View itemView,Context context) {
            super(itemView);
            imgIcon=(ImageView)itemView.findViewById(R.id.imgicon);
            txtTitle=(TextView)itemView.findViewById(R.id.textTitle);
            this.context=context;
            itemView.setOnClickListener(this);
            itemView.setOnTouchListener(new View.OnTouchListener(){
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction() == MotionEvent.ACTION_DOWN)
                    {
                        //v.setBackgroundColor(Color.parseColor("#62CDF1"));
                    }
                    if (event.getAction() == MotionEvent.ACTION_UP )
                    {
                        //v.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        //v.setBackground(R.drawable.border);
                    }
                    return false;
                }
            });

        }

        @Override
        public void onClick(View v) {
            int r=R.drawable.border2;
            int r2=R.drawable.border;
            v.setBackgroundResource(r);
            //v.setBackground(R.drawable.border);
            //v.setBackgroundColor(Color.parseColor("#62CDF11"));
            Intent intent=new Intent(context,service_detail.class);

            int b=getAdapterPosition();
            SharedPreferences data=context.getSharedPreferences("APP DATA",0);
            SharedPreferences.Editor editor=data.edit();
            editor.putString("MainServiceSelected",txtTitle.getText().toString());
            editor.commit();

            //Toast.makeText(context,txtTitle.getText().toString(),Toast.LENGTH_SHORT).show();

            String az;
            if(b==0)
            {
                intent.putExtra("image_id","0");


            }
            else if(b==1)
                intent.putExtra("image_id","1");
            else if(b==2)
                intent.putExtra("image_id","2");
            else if(b==3)
                intent.putExtra("image_id","3");
            else if(b==4)
                intent.putExtra("image_id","4");
            else if(b==5)
                intent.putExtra("image_id","5");
            else if(b==6)
                intent.putExtra("image_id","6");
            else
                intent.putExtra("image_id","7");

           // v.setBackgroundColor(Color.parseColor("#FFFFFF"));
            v.setBackgroundResource(r2);
            context.startActivity(intent);
        }
    }
}

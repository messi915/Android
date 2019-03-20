package com.example.zeeshanaslam.fyp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ServiceDetail extends RecyclerView.Adapter<ServiceDetail.ServiceViewHolder> {

    private  int [] images;
    private  String[]data;
    private Context context;
    private int pre_id;
    public ServiceDetail(String []data,int [] img,int pre_id,  Context context)
    {
        this.data=data;
        images=img;
        this.context=context;
        this.pre_id=pre_id;
    }
    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.list_service_detail,parent,false);

        return new ServiceViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder programmingViewHolder, int i) {

            String title=data[i];
        int image_id = images[i];
        programmingViewHolder.txtTitle.setText(title);
        programmingViewHolder.imgIcon.setImageResource(image_id);

    }


    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgIcon;
        TextView txtTitle;
        Context context;
        public ServiceViewHolder(@NonNull View itemView,Context context) {
            super(itemView);
            imgIcon=(ImageView)itemView.findViewById(R.id.imgiconn);
            txtTitle=(TextView)itemView.findViewById(R.id.service_detail_item);
            this.context=context;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context,CardDisplay.class);

            int b=getAdapterPosition();
            String az;


            intent.putExtra("pre_id",pre_id+"");
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

            context.startActivity(intent);
        }
    }
}

package com.example.zeeshanaslam.fyp.Employee;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zeeshanaslam.fyp.R;
import com.example.zeeshanaslam.fyp.view_requests.RequestDetails;

import java.util.ArrayList;

public class EmployeeRequestedService extends RecyclerView.Adapter<EmployeeRequestedService.ServiceViewHolder> {

    private  ArrayList<String> rupees;
    private ArrayList<String> data;
    private Context context;
    public EmployeeRequestedService(ArrayList<String> data, ArrayList<String> img, Context context)
    {
        this.data=data;
        rupees=img;
        this.context=context;
    }
    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.view_request_recycler,parent,false);

        return new ServiceViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder programmingViewHolder, int i) {

            String title=data.get(i);
        String image_id = rupees.get(i);
        programmingViewHolder.txtTitle.setText(title);
        programmingViewHolder.rupees.setText(image_id);

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView rupees;
        TextView txtTitle;
        Context context;
        public ServiceViewHolder(@NonNull View itemView,Context context) {
            super(itemView);
            rupees=(TextView) itemView.findViewById(R.id.rupees);
            txtTitle=(TextView)itemView.findViewById(R.id.service_detail_item);
            this.context=context;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context,Employee_RequestDetails.class);

            int b=getAdapterPosition();
            intent.putExtra("rupees",rupees.getText().toString());
            intent.putExtra("title",txtTitle.getText().toString());

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

            context.startActivity(intent);
        }
    }
}

package com.example.contactapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.viewHolder> {
    private List<Contactdetail> contactList;
    private Context context;
     public RecylerViewAdapter(List<Contactdetail> contactList, Context context){
            this.contactList = contactList;
            this.context = context;
        }
        @Override
        @NonNull
        public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
           View view = LayoutInflater.from(context).inflate(R.layout.single_contact_view, null);
            viewHolder contactViewHolder = new viewHolder(view);
         return contactViewHolder;

    }

    @Override
    public void onBindViewHolder(viewHolder viewholder , int i) {
           Contactdetail contactdetail = contactList.get(i);
           viewholder.tvContactName.setText(contactdetail.getContactName());
           viewholder.tvPhoneNumber.setText(contactdetail.getContactNumber());
           
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView ivContactImage;
        TextView tvContactName;
        TextView tvPhoneNumber;



        public viewHolder(@NonNull View itemView) {
            super(itemView);
            ivContactImage = (ImageView) itemView.findViewById(R.id.ivContactImage);
            tvContactName = (TextView) itemView.findViewById(R.id.tvContactName);
            tvPhoneNumber = (TextView) itemView.findViewById(R.id.tvPhoneNumber);
        }
    }
}

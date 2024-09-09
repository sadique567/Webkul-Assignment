package com.org.assignmentpractice;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserDetailsAdaptor extends RecyclerView.Adapter<UserDetailsAdaptor.MyHolder> {
    private ArrayList<UserDetailsModel> listData;
    private Context context;
    private ItemClickListener clickListener;

    public UserDetailsAdaptor(Context context , ArrayList<UserDetailsModel> listData) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View item =layoutInflater.inflate(R.layout.user_details_card , parent , false);
        return new MyHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        int id = listData.get(position).idUserSearch;

        holder.name.setText(listData.get(position).getNameUser());
        holder.email.setText(listData.get(position).getEmailUser());
        holder.phone.setText(listData.get(position).getPhoneUser());
        holder.address.setText("Street: "+listData.get(position).getAddressModels().getStreet() +",\n "
                +"Suite: "+listData.get(position).getAddressModels().getSuite()+",\n"
                +"City: "+listData.get(position).getAddressModels().getCity() +",\n"
                +"ZipCode: "+listData.get(position).getAddressModels().getZipcode()+",\n"
                +"lat: "+listData.get(position).getAddressModels().getGeoAddessModel().getLatitudeAdd() +",\n"
                +"lan: "+listData.get(position).getAddressModels().getGeoAddessModel().getLongitude());
        holder.website.setText(listData.get(position).websiteUser);
        holder.company_details.setText("Name: "+listData.get(position).getCompanyModel().getNameCo()+",\n"
                +"catchPhrase: "+listData.get(position).getCompanyModel().getCatchPharesCo()+",\n"
                +"bs: "+listData.get(position).getCompanyModel().getBsCo()+",\n"
        );
        holder.userDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , Screen2Activity.class);
                    intent.putExtra("id" , id);
                view.getContext().startActivity(intent);
                //Toast.makeText(context, "userCard"+id, Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
//public void setClickListener(ItemClickListener itemClickListener){
//this.clickListener = itemClickListener;
//}


    //create another class to fetch data
    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name , email , phone , website , address , company_details;
        CardView userDetails;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            this .name = itemView.findViewById(R.id.name_user);
            this.email = itemView.findViewById(R.id.email_user);
            this.phone = itemView.findViewById(R.id.phone_user);
            this.website = itemView.findViewById(R.id.website_cmpy);
            this.address = itemView.findViewById(R.id.address_cmpy);
            this.company_details = itemView.findViewById(R.id.company_details);
            this.userDetails = itemView.findViewById(R.id.userdetails_card);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null){
                clickListener.onClick(view , getAdapterPosition());
            }
        }
    }
}

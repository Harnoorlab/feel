package com.example.feelhut.notificationrecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feelhut.R;


public class notification_adapter extends RecyclerView.Adapter<notification_adapter.notificationviewholder> {

    private String[] data;
    public notification_adapter(String[] data){
        this.data= data;
    }
    @NonNull
    @Override
    public notificationviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.notification_recycler, parent,false);

        return new notificationviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull notificationviewholder holder, int position) {
String ourtext = data[position];
holder.notificationtext.setText(ourtext);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class notificationviewholder extends RecyclerView.ViewHolder{
    ImageView profilepic;
    TextView notificationtext;
        public notificationviewholder (View itemView){
            super(itemView);
            profilepic = itemView.findViewById(R.id.notification_profile_pic);
            notificationtext = itemView.findViewById(R.id.notification_text);
        }
    }
}

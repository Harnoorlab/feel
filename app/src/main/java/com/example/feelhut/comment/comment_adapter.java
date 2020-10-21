package com.example.feelhut.comment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feelhut.R;
import com.example.feelhut.home_recyclerview.home_adapter;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class comment_adapter extends FirestoreRecyclerAdapter<comment_list, comment_adapter.comment_holder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public comment_adapter(@NonNull FirestoreRecyclerOptions<comment_list> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull comment_holder holder, int position, @NonNull comment_list model) {
        holder.commment_on_post.setText(model.getComments());
    }

    @NonNull
    @Override
    public comment_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_recycler_dsign, parent, false);
        return new comment_holder(v);
    }

    public class comment_holder extends RecyclerView.ViewHolder{
      TextView commment_on_post,comment_time;
        public comment_holder(@NonNull View itemView) {
            super(itemView);
            commment_on_post = itemView.findViewById(R.id.commenttextview);
        }
    }
}

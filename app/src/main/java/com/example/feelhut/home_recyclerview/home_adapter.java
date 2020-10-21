package com.example.feelhut.home_recyclerview;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feelhut.comment.Comments;
import com.example.feelhut.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class home_adapter extends FirestoreRecyclerAdapter<posttextlist,home_adapter.homeholder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public home_adapter(@NonNull FirestoreRecyclerOptions<posttextlist> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull homeholder holder, int position, @NonNull posttextlist model) {
        holder.postdescription.setText(model.getPost());
         

    }

    @NonNull
    @Override
    public homeholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_recycler_design, parent, false);
        return new homeholder(v);

    }

    public class homeholder extends RecyclerView.ViewHolder{
    TextView postdescription,time;
    TextView viewallcomments;
        public homeholder(@NonNull final View itemView) {
            super(itemView);
    postdescription = itemView.findViewById(R.id.postrecyclerviewtextView3);
viewallcomments = itemView.findViewById(R.id.textView4);
time = itemView.findViewById(R.id.timetext);
viewallcomments.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(itemView.getContext(), Comments.class);
        itemView.getContext().startActivity(intent);
        Toast.makeText(itemView.getContext(), "Well Done", Toast.LENGTH_SHORT).show();
    }
});
    }
    }
}


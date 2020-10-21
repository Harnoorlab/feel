package com.example.feelhut.comment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.feelhut.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.List;

public class Comments extends AppCompatActivity {
    ImageView profileimage;
    EditText addcommenttext;
    TextView postcommenttext;
    FirebaseFirestore db;
    //  DocumentReference documentReference;
    RecyclerView comment_recycler_view;
    FirebaseDatabase firebaseDatabase;
    comment_adapter adaptercomment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        profileimage = findViewById(R.id.Addcommentprofileimage);
        addcommenttext = findViewById(R.id.addcommenttext);
        postcommenttext = findViewById(R.id.postcomment);
        //  db = FirebaseFirestore.getInstance();
        // documentReference = db.collection("Comment").document();
        comment_recycler_view = findViewById(R.id.commentsrecycler);

        FirebaseFirestore.setLoggingEnabled(true);

        db = FirebaseFirestore.getInstance();
        Query query = db.collection("CommentDetails").orderBy("mTimestamp").limit(50);

        FirestoreRecyclerOptions<comment_list> options = new FirestoreRecyclerOptions.Builder<comment_list>()
                .setQuery(query, comment_list.class)
                .build();

        // Setting up the recycle adapter in onCreate
        adaptercomment = new comment_adapter(options);

        comment_recycler_view.setHasFixedSize(true);
        comment_recycler_view.setLayoutManager(new LinearLayoutManager(getParent()));
        comment_recycler_view.setAdapter(adaptercomment);
        postcommenttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addcommenttext.equals("")) {
                    Toast.makeText(Comments.this, "Comment can't be empty", Toast.LENGTH_SHORT).show();
                } else {
                    String commentText = addcommenttext.getText().toString();
                    CollectionReference commentColRef = FirebaseFirestore.getInstance().collection("CommentDetails");
                    commentColRef.add(new comment_list(commentText));
                    Toast.makeText(Comments.this, "Commented", Toast.LENGTH_SHORT).show();
                    addcommenttext.setText("");



                }

            }


        });

    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            adaptercomment.startListening();
        }catch (NullPointerException e){}
        }

    @Override
    public void onStop() {
        super.onStop();
       try {
           adaptercomment.stopListening();
       }catch (NullPointerException e){}
    }
}







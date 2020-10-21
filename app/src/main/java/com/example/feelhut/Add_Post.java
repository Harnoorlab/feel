package com.example.feelhut;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.feelhut.fragments.HomeFragment;
import com.example.feelhut.home_recyclerview.posttextlist;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Add_Post extends AppCompatActivity {
     EditText posttext;
     FirebaseAuth auth;
 //    FirebaseUser currentuser;
  //   FirebaseDatabase postdatabase;
   //  DatabaseReference postdatabaseref, postdatabaseuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__post);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        try {
            actionBar.setTitle("Write Your Post");

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        }catch (NullPointerException e){}
        posttext = findViewById(R.id.PostTextTextMultiLine);
     //   auth = FirebaseAuth.getInstance();
      /*  currentuser = auth.getCurrentUser();
try {
    postdatabaseuser = FirebaseDatabase.getInstance().getReference().child("Users").child(currentuser.getUid());

    postdatabaseref = FirebaseDatabase.getInstance().getReference().child("Users Details");
}catch (NullPointerException e)
        {}
    */}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addpostmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.POST:
    savepost();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void savepost() {
        String Post = posttext.getText().toString();
        if (Post.length()<10){
            Toast.makeText(this, "Please enter at least 10 words ", Toast.LENGTH_SHORT).show();

        }
        else{
          //  final   String postText = posttext.getText().toString();
           // final   String username = posttext.getText().toString();

try {
   // final  DatabaseReference ref= postdatabaseref.push();




          //  ref.child("Post").setValue(postText);
           // ref.child("Name").setValue(postText);
}catch (NullPointerException e){}
            CollectionReference postref = FirebaseFirestore.getInstance()
                    .collection("PostDetails");//should same
            postref.add(new posttextlist(Post));
            Toast.makeText(this, "Successfully Posted", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }

    }

}
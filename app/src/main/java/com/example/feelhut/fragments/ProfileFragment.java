package com.example.feelhut.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.feelhut.LogIn_SignUp;
import com.example.feelhut.R;
import com.example.feelhut.editprofile;
import com.example.feelhut.loginsignupfragment.LogIn_Fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
Button logout,deleteaccount,editprofilebutton;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView name,emailaddress;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_profile, container, false);


    logout = view.findViewById(R.id.logout);
    name = view.findViewById(R.id.nameinprofile);
    emailaddress = view.findViewById(R.id.emailinprofile);
    editprofilebutton = view.findViewById(R.id.edit_text_button);
    firebaseDatabase = FirebaseDatabase.getInstance();
    databaseReference = firebaseDatabase.getReference("Usereesss");
    databaseReference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

       for (DataSnapshot ds: snapshot.getChildren()) {
           try {
               final String strtext = getArguments().getString("Users Details");

               if (ds.child("Email").getValue().equals(strtext)) {
                   name.setText(ds.child("Name").getValue(String.class));
                   emailaddress.setText(ds.child("Email").getValue(String.class));
               }
           } catch (NullPointerException e) {
           }
       }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });
    try {
        editprofilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), editprofile.class));
            }
        });
    }catch (NullPointerException e){}
    logout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

           FirebaseAuth firebaseauth= FirebaseAuth.getInstance();
           firebaseauth.signOut();
            Intent i = new Intent(getContext(),
                    LogIn_SignUp.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
try {
    startActivity(i);
}catch (NullPointerException e){}
        }
    });
    return view;
    }
}
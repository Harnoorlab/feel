package com.example.feelhut.loginsignupfragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.feelhut.MainActivity;
import com.example.feelhut.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
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
TextView alreadyhaveaccount;
    EditText emailaddress, name, password,confirmpassword;
    Button signupbutton;
    FirebaseAuth auth;
    DatabaseReference ref;
    ProgressDialog pd;
    FirebaseUser users;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
name = view.findViewById(R.id.name);
password = view.findViewById(R.id.passwordtext);
emailaddress = view.findViewById(R.id.emailtext);
confirmpassword= view.findViewById(R.id.confirmpassword);
        pd = new ProgressDialog(getActivity());
signupbutton = view.findViewById(R.id.signupbutton);

        ref = FirebaseDatabase.getInstance().getReference().child("Users Details");
auth = FirebaseAuth.getInstance();
        pd = new ProgressDialog(getActivity());
   signupbutton.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
         pd.setMessage("Please Wait");
           pd.show();
           if (users==null) {

               final String myemail = emailaddress.getText().toString();
               final String mypassword = password.getText().toString();
               final String myname = name.getText().toString();
               String myConfirmPassword = confirmpassword.getText().toString();
               try {
                   if (TextUtils.isEmpty(myemail) && TextUtils.isEmpty(myname) && TextUtils.isEmpty(mypassword)) {
                       Toast.makeText(getActivity(), "Please Enter Above Details", Toast.LENGTH_SHORT).show();
                   } else if (TextUtils.isEmpty(myemail) && TextUtils.isEmpty(myname)) {
                       Toast.makeText(getActivity(), "Please Enter Name And Email", Toast.LENGTH_SHORT).show();
                   } else if (TextUtils.isEmpty(myemail) && TextUtils.isEmpty(mypassword)) {
                       Toast.makeText(getActivity(), "Please Enter Email And Password", Toast.LENGTH_SHORT).show();
                   } else if (TextUtils.isEmpty(myname) && TextUtils.isEmpty(mypassword)) {
                       Toast.makeText(getActivity(), "Please Enter Name And Password", Toast.LENGTH_SHORT).show();
                   } else if (TextUtils.isEmpty(myemail)) {
                       Toast.makeText(getActivity(), "Please Enter Email", Toast.LENGTH_SHORT).show();
                   } else if (myname.isEmpty()) {
                       Toast.makeText(getActivity(), "Please Enter Your Name", Toast.LENGTH_SHORT).show();
                   } else if (mypassword.length() < 8) {
                       Toast.makeText(getActivity(), "Password must not less then 8", Toast.LENGTH_SHORT).show();
                   } else if (!mypassword.equals(myConfirmPassword)) {
                       Toast.makeText(getActivity(), "Password doesn't match", Toast.LENGTH_SHORT).show();
                   } else {
                       auth.createUserWithEmailAndPassword(myemail, mypassword)
                               .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                   @Override
                                   public void onComplete(@NonNull Task<AuthResult> task) {
                                       if (task.isSuccessful()) {
                                           startActivity(new Intent(getActivity(), MainActivity.class));
                                           Toast.makeText(getActivity(), "Thank You :) Enjoy " + myname + "!!", Toast.LENGTH_SHORT).show();
                                           String userid;
                                           userid = auth.getCurrentUser().getUid();
                                           DatabaseReference currentuser = ref.child(userid);
                                           currentuser.child("Name").setValue(myname);
                                           currentuser.child("Email").setValue(myemail);
                                           currentuser.child("Password").setValue(mypassword);
                                           pd.dismiss();
                                       } else {
                                           Toast.makeText(getActivity(), "Something Went Wrong. Please Try Again", Toast.LENGTH_SHORT).show();
                                           pd.dismiss();
                                       }
                                   }
                               });
                   }

               } catch (NullPointerException e) {
               }
           }else
               Toast.makeText(getActivity(), "Account already exist, Please LogIn ", Toast.LENGTH_SHORT).show();
           pd.dismiss();}

   });
        Bundle bundle=new Bundle();
        bundle.putString("Users Details", "From Activity");
        //set Fragmentclass Arguments
        SignUpFragment fragobj=new SignUpFragment();
        fragobj.setArguments(bundle);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        alreadyhaveaccount=view.findViewById(R.id.alreadyhaveaccount);
        alreadyhaveaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new LogIn_Fragment());
            }

            private void changeFragment(Fragment myfragment) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.LoginSignupFragment, myfragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}
package com.example.feelhut.loginsignupfragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.feelhut.MainActivity;
import com.example.feelhut.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LogIn_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogIn_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LogIn_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LogIn_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LogIn_Fragment newInstance(String param1, String param2) {
        LogIn_Fragment fragment = new LogIn_Fragment();
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
TextView donthaveaccount;
    EditText email,password;
    Button login;
    FirebaseAuth auth;
    ProgressDialog pd;
    FirebaseUser user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_log_in_, container, false);

    email=view.findViewById(R.id.emailtext);
    password =view.findViewById(R.id.passwordtext);
    login=view.findViewById(R.id.loginbutton);
    auth = FirebaseAuth.getInstance();
    pd = new ProgressDialog(getActivity());
    login.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            String myemail = email.getText().toString();
            String mypassword = password.getText().toString();
            if (user!=null) {
                if (myemail.isEmpty() && mypassword.isEmpty()) {
                    Toast.makeText(getActivity(), "Please Enter Email And Password", Toast.LENGTH_SHORT).show();
                } else if (myemail.isEmpty()) {
                    Toast.makeText(getActivity(), "Please Enter Email", Toast.LENGTH_SHORT).show();
                } else if (mypassword.isEmpty()) {
                    Toast.makeText(getActivity(), "Please Enter Password", Toast.LENGTH_SHORT).show();
                } else {
                    pd.setMessage("Please Wait");
                    pd.show();
                    auth.signInWithEmailAndPassword(myemail, mypassword)
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {


                                    if (task.isSuccessful()) {
                                        pd.dismiss();
                                        startActivity(new Intent(getActivity(), MainActivity.class));
                                        Toast.makeText(getActivity(), "Welcome Back :)", Toast.LENGTH_SHORT).show();

                                    } else {
                                        pd.dismiss();
                                        Toast.makeText(getActivity(), "Something Went Wrong. Please Try Again", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }else {
                Toast.makeText(getActivity(), "Account Doesn't Exist", Toast.LENGTH_SHORT).show();
            }
        }
    });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    donthaveaccount=view.findViewById(R.id.donthaveaccount);
    donthaveaccount.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setFragment(new SignUpFragment());
        }

        private void setFragment(Fragment mefragment) {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.LoginSignupFragment, mefragment);
            transaction.commit();

        }
    });
    }
}
package com.example.feelhut;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.feelhut.loginsignupfragment.SignUpFragment;

public class LogIn_SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in__sign_up);
        setFragment(new SignUpFragment());
    }

    private void setFragment(Fragment myfragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.LoginSignupFragment, myfragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
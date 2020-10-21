package com.example.feelhut;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.feelhut.home_recyclerview.posttextlist;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.Document;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.example.feelhut.R.id.*;

public class Calendar_date_text extends AppCompatActivity {
  EditText calendartextview;
    CalendarView calender;
  FirebaseFirestore db;
ListenerRegistration calendarlistener;

DocumentReference documentReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_date_text);
         calendartextview = findViewById(calendaredittext);
        calender = findViewById(R.id.calendarindiary);


//setsupportactiobar nu remove kita taa feelhut di jagah date aa gyi



        calendartextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendartextview.setCursorVisible(true);
                calendartextview.requestFocus();
            }
        });

      Toolbar  toolbar=findViewById(R.id.toolbar);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");


        toolbar.setTitle(name);
        setSupportActionBar(toolbar);

}
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.calendar_date_text_menu, menu);
            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         final String ourcalendartext;

         final String calendarpost = "CalendarPost";
         FirebaseFirestore db ;
            try {
             db=   FirebaseFirestore.getInstance();


    switch (item.getItemId()) {
        case caledartextpost:
            ourcalendartext = calendartextview.getText().toString();


            calendartextview.setCursorVisible(false);
            Map<String, String> user = new HashMap<>();
            user.put(calendarpost, ourcalendartext);
            db.collection("Calendar").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(Calendar_date_text.this, "Saved", Toast.LENGTH_SHORT).show();
                }
            });
            db.collection("Calendar").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {



                        }
                     else {

                    }
                }
            });

            break;
    }


}catch (NullPointerException e){}
            return super.onOptionsItemSelected(item);
    }
}
package com.example.feelhut.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.feelhut.Add_Post;
import com.example.feelhut.R;
import com.example.feelhut.home_recyclerview.home_adapter;
import com.example.feelhut.home_recyclerview.posttextlist;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
RecyclerView postsrecyclerview;
    FirebaseFirestore fbfs = FirebaseFirestore.getInstance();
    CollectionReference postref = fbfs.collection("PostDetails");
    home_adapter adapterhome;
    TextView viewallcomments;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Query query = postref;

        FirestoreRecyclerOptions<posttextlist> options = new FirestoreRecyclerOptions.Builder<posttextlist>()
                .setQuery(query, posttextlist.class)
                .build();
        adapterhome = new home_adapter(options);
        postsrecyclerview = view.findViewById(R.id.home_fragment_recycler_view);
        postsrecyclerview.setHasFixedSize(true);
        postsrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        postsrecyclerview.setAdapter(adapterhome);





       setHasOptionsMenu(true);
        return view;

    }
    @Override
    public void onStart() {
        super.onStart();
        adapterhome.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterhome.stopListening();


    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();

        // Add the new menu items
        inflater.inflate(R.menu.homemenu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.createpost:
                startActivity(new Intent(getContext(), Add_Post.class));
        }
        return super.onOptionsItemSelected(item);
    }
}

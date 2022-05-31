package kr.ac.cnu.computer.savagr;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VetFragmentDiagnosisComment extends Fragment {
    TextView plants_name, growth;
    View view;
    private static final String TAG = "DiagnosisComment";
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_user_step1, container, false);

        plants_name = view.findViewById(R.id.plants_name);
        growth = view.findViewById(R.id.growth_comment);


        // Write a message to the database
        //FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference rooRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference myRef = rooRef.child("plant_information");


        // Read from the database
        myRef.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<String> list = new ArrayList<>();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            String uid = ds.getValue().toString();
                            list.add(uid);
                        }

                        plants_name.setText(list.get(0));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
        );




        return inflater.inflate(R.layout.activity_vet_comment, container, false);



    }
}


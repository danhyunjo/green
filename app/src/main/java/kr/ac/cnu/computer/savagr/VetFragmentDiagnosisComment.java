package kr.ac.cnu.computer.savagr;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
    TextView plants_name, loca, seas, water, grow, pest, sol;
    View view;
    Button next_comment;
    EditText comment;
    private static final String TAG = "DiagnosisComment";
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_vet_comment, container, false);

        next_comment = view.findViewById(R.id.next_comment);
        plants_name = view.findViewById(R.id.plants_name);
        loca = view.findViewById(R.id.loca);
        seas = view.findViewById(R.id.seas);
        water = view.findViewById(R.id.water);
        grow = view.findViewById(R.id.grow);
        pest = view.findViewById(R.id.pest_control);
        sol = view.findViewById(R.id.sol);
        comment = view.findViewById(R.id.comment);

        next_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();

                VetComment info = new VetComment(comment.getText().toString());
                myRef.child("comment").setValue(info);





                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {


                    }
                });
                Intent intent = new Intent(getActivity(),VetUpload.class);
                startActivity(intent);
            }
        });

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> list = new ArrayList<>();
                for (DataSnapshot ds : snapshot.child("plant_information").getChildren()) {
                    String uid = ds.getValue().toString();
                    list.add(uid);
                }
                plants_name.append(list.get(4));
                loca.append(list.get(1));
                seas.append(list.get(5));
                water.append(list.get(6));
                grow.append(list.get(0));
                pest.append(list.get(3));
                sol.append(list.get(2));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;



    }
}


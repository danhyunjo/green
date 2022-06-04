package kr.ac.cnu.computer.savagr;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class UserFragmentAlarm extends Fragment {
    Button user_alarm;
    ViewGroup view;
    UserNavi userNavi;
    TextView alarm;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        userNavi = (UserNavi) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        userNavi = null;
    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = (ViewGroup)inflater.inflate(R.layout.activity_user_alarm, container, false);
        user_alarm = view.findViewById(R.id.user_alarm);

        user_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userNavi.fragmentChange(1);

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
                user_alarm.append(list.get(4));
                user_alarm.append("\n");
                user_alarm.append(list.get(2));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}
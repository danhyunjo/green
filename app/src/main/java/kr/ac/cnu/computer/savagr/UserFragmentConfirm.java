package kr.ac.cnu.computer.savagr;

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

public class UserFragmentConfirm extends Fragment {
    TextView comment_confirm;
    View view;
    Button to_usermain;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.activity_user_confirm, container, false);

        comment_confirm = view.findViewById(R.id.comment_confirm);
        to_usermain = view.findViewById(R.id.to_usermain);


        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> list = new ArrayList<>();
                for (DataSnapshot ds : snapshot.child("comment").getChildren()) {
                    String uid = ds.getValue().toString();
                    list.add(uid);
                }
                comment_confirm.append(list.get(0));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        to_usermain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),UserMain.class);
                startActivity(intent);
            }
        });
        return view;
    }
}

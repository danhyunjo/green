package kr.ac.cnu.computer.savagr;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VetFragmentQuestionList extends Fragment {
    Button vet_question;
    VetNavi vetNavi;
    RecyclerView recyclerView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        vetNavi = (VetNavi) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        vetNavi = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_vet_question_list, container, false);


        vet_question = rootView.findViewById(R.id.question);


        vet_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vetNavi.fragmentChange(2);
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
                vet_question.append(list.get(4));
                vet_question.append("\n");
                vet_question.append(list.get(2));
                vet_question.append("\n CNN모델 분석 결과 세균성 반점병으로 추정");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return rootView;

    }


}
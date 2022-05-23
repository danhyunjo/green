package kr.ac.cnu.computer.savagr;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UserFragmentDiagnosisStep1 extends Fragment {
    Button next_step1;
    UserNavi userNavi;
    View view;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_user_step1, container, false);
        next_step1 = view.findViewById(R.id.next_step1);

        next_step1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),UserDiagnosisStep2.class);
                intent.addFlags((Intent.FLAG_ACTIVITY_NO_ANIMATION));


                startActivity(intent);
            }

        });


        return view;
    }
}
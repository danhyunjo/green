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

public class UserFragmentDiagnosisStep1 extends Fragment {
    Button button;
    UserNavi userNavi;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        userNavi = (UserNavi) getActivity();
    }

    public void onDetach() {
        super.onDetach();
        userNavi = null;
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_user_step1, container, false);
        button = rootView.findViewById(R.id.next_step1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userNavi.fragmentChange();
            }
        });

        return inflater.inflate(R.layout.activity_user_step1, container, false);
    }
}
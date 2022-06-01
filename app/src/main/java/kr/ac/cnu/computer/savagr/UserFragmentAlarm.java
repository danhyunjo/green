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

public class UserFragmentAlarm extends Fragment {
    Button user_alarm;
    ViewGroup view;
    UserNavi userNavi;
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

        return view;
    }
}
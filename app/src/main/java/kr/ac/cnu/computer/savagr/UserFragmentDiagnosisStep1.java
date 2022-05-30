package kr.ac.cnu.computer.savagr;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class UserFragmentDiagnosisStep1 extends Fragment {
    Button next_step1;
    UserNavi userNavi;
    View view;
    EditText plants, pest_control, growth;
    RadioGroup season_group, location_group, soil_water_group, light_group;
    RadioButton winter, sprTofall, location1, location2, location3, location4, location5, location6, location7, location8, location9, location10, location11, location12, location13;
    RadioButton light1, light2, light3, light4, light5, light6;
    RadioButton soil1, soil2, soil3, soil4;
    private String TAG = UserFragmentDiagnosisStep1.class.getSimpleName();

    private WebView webView = null;


    String seasonResult, lightResult, soil_waterResult, locationResult;

    String uid;





    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_user_step1, container, false);

        next_step1 = view.findViewById(R.id.next_step1);

        plants = view.findViewById(R.id.plants_name);
        pest_control = view.findViewById(R.id.pest_control);
        growth = view.findViewById(R.id.growth);

        season_group = view.findViewById(R.id.season_group);
        location_group = view.findViewById(R.id.location_group);
        soil_water_group = view.findViewById(R.id.soil_water_group);
        light_group = view.findViewById(R.id.light_group);

        location1 = view.findViewById(R.id.location1);
        location2 = view.findViewById(R.id.location2);
        location3 = view.findViewById(R.id.location3);
        location4 = view.findViewById(R.id.location4);
        location5 = view.findViewById(R.id.location5);
        location6 = view.findViewById(R.id.location6);
        location7 = view.findViewById(R.id.location7);
        location8 = view.findViewById(R.id.location8);
        location9 = view.findViewById(R.id.location9);
        location10 = view.findViewById(R.id.location10);
        location11 = view.findViewById(R.id.location11);
        location12 = view.findViewById(R.id.location12);
        location13 = view.findViewById(R.id.location13);

        winter = view.findViewById(R.id.winter);
        sprTofall = view.findViewById(R.id.sprTofall);

        light1 = view.findViewById(R.id.light1);
        light2 = view.findViewById(R.id.light2);
        light3 = view.findViewById(R.id.light3);
        light4 = view.findViewById(R.id.light4);
        light5 = view.findViewById(R.id.light5);
        light6 = view.findViewById(R.id.light6);
        webView = view.findViewById(R.id.webview);

        webView.setWebViewClient(new WebViewClient());  // 새 창 띄우기 않기
        webView.setWebChromeClient(new WebChromeClient());


        webView.getSettings().setLoadWithOverviewMode(true);  // WebView 화면크기에 맞추도록 설정 - setUseWideViewPort 와 같이 써야함
        webView.getSettings().setUseWideViewPort(true);  // wide viewport 설정 - setLoadWithOverviewMode 와 같이 써야함

        webView.getSettings().setSupportZoom(false);  // 줌 설정 여부
        webView.getSettings().setBuiltInZoomControls(false);  // 줌 확대/축소 버튼 여부

        webView.getSettings().setJavaScriptEnabled(true); // 자바스크립트 사용여부
//        webview.addJavascriptInterface(new AndroidBridge(), "android");
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true); // javascript가 window.open()을 사용할 수 있도록 설정
        webView.getSettings().setSupportMultipleWindows(true); // 멀티 윈도우 사용 여부

        webView.getSettings().setDomStorageEnabled(true);  // 로컬 스토리지 (localStorage) 사용여부


        //웹페이지 호출
//        webView.loadUrl("http://www.naver.com");
        webView.loadUrl("https://www.nongsaro.go.kr/portal/ps/psa/psab/psabf/spciesRsrchLst.ps?menuId=PS65425");



    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user!=null){
            String uid = user.getUid();
        }

/*
        season_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.winter){
                    seasonResult = winter.getText().toString();
                } else if(i == R.id.sprTofall){
                    seasonResult = sprTofall.getText().toString();
                }
            }
        });

        light_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.light1){
                    lightResult = light1.getText().toString();
                }
                else if(i == R.id.light2){
                    lightResult = light2.getText().toString();
                }
                else if(i == R.id.light3){
                    lightResult = light3.getText().toString();
                }
                else if(i == R.id.light4){
                    lightResult = light4.getText().toString();
                }
                else if(i == R.id.light5){
                    lightResult = light5.getText().toString();
                }
                else if(i == R.id.light6){
                    lightResult = light6.getText().toString();
                }

            }
        });

 */

        soil_water_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            @Nullable
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.soil1){
                    soil_waterResult = soil1.getText().toString();
                }else if (i == R.id.soil2){
                    soil_waterResult = soil2.getText().toString();
                }
                else if (i == R.id.soil3){
                    soil_waterResult = soil3.getText().toString();
                }
                else if (i == R.id.soil4){
                    soil_waterResult = soil4.getText().toString();
                }
            }
        });



        location_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            @Nullable
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.location1){
                    locationResult = location1.getText().toString();
                } else if(i == R.id.location2){
                    locationResult = location2.getText().toString();
                }
                else if(i == R.id.location3){
                    locationResult = location3.getText().toString();
                }
                else if(i == R.id.location4){
                    locationResult = location4.getText().toString();
                }
                else if(i == R.id.location5){
                    locationResult = location5.getText().toString();
                }else if(i == R.id.location6){
                    locationResult = location6.getText().toString();
                }else if(i == R.id.location7){
                    locationResult = location7.getText().toString();
                }else if(i == R.id.location8){
                    locationResult = location8.getText().toString();
                }else if(i == R.id.location9){
                    locationResult = location9.getText().toString();
                }else if(i == R.id.location10){
                    locationResult = location10.getText().toString();
                }else if(i == R.id.location11){
                    locationResult = location11.getText().toString();
                }else if(i == R.id.location12){
                    locationResult = location12.getText().toString();
                }else if(i == R.id.location13){
                    locationResult = location13.getText().toString();
                }

            }
        });









        next_step1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();

                PlantInformation info = new PlantInformation(plants.getText().toString(),growth.getText().toString(),pest_control.getText().toString(), soil_waterResult, locationResult);
                myRef.child("plant_information").setValue(info);





                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String value = snapshot.getValue(String.class);
                        Log.d(TAG, "Value is : " + value);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.w(TAG, error.getMessage());

                    }
                });
                Intent intent = new Intent(getActivity(),UserDiagnosisStep2.class);
                intent.addFlags((Intent.FLAG_ACTIVITY_NO_ANIMATION));


                startActivity(intent);
            }

        });


        return view;
    }
}
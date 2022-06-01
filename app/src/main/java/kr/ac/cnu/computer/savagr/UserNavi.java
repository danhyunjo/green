package kr.ac.cnu.computer.savagr;



import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserNavi extends AppCompatActivity {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private UserFragmentConfirm user_fragmentConfirm = new UserFragmentConfirm();
    private UserFragmentDiagnosisStep1 fragmentDiagonsis = new UserFragmentDiagnosisStep1();
    private UserFragmentAlarm fragmentAlarm = new UserFragmentAlarm();
    private UserFragmentMyPage fragmentMyPage = new UserFragmentMyPage();
    private UserFragmentMain fragmentMain = new UserFragmentMain();
    private long backKeyPressedTime = 0;
    private Toast toast;
    UserFragmentDiagnosisStep2 userFragmentDiagnosisStep2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_navigation);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentMain).commitAllowingStateLoss();
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setSelectedItemId(R.id.invisible);
        bottomNavigationView.setOnItemSelectedListener(new ItemSelectedListener());
    }



    class ItemSelectedListener implements BottomNavigationView.OnItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (menuItem.getItemId()) {
                case R.id.invisible:
                    transaction.replace(R.id.frameLayout, fragmentMain).commitAllowingStateLoss();
                    break;
                case R.id.Diagnosis:
                    transaction.replace(R.id.frameLayout, fragmentDiagonsis).commitAllowingStateLoss();
                    break;
                case R.id.Alarm:
                    transaction.replace(R.id.frameLayout, fragmentAlarm).commitAllowingStateLoss();
                    break;
                case R.id.MyPage:
                    transaction.replace(R.id.frameLayout, fragmentMyPage).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }
    public void fragmentChange(int index){
        if(index == 1){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, user_fragmentConfirm).commit();
        }
    }

    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2500) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "뒤로가기 버튼을 한 번 더 누르시면 종료됩니다.", Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        if (System.currentTimeMillis() <= backKeyPressedTime + 2500) {
            finishAffinity();
            System.runFinalization();
            System.exit(0);
        }
    }
}
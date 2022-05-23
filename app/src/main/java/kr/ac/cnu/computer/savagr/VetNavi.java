package kr.ac.cnu.computer.savagr;



        import android.view.MenuItem;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.widget.Toast;

        import androidx.fragment.app.FragmentManager;
        import androidx.fragment.app.FragmentTransaction;

        import com.google.android.material.bottomnavigation.BottomNavigationView;

public class VetNavi extends AppCompatActivity {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private VetFragmentQuestionList vet_fragmentDiagnosis = new VetFragmentQuestionList();
    private VetFragmentMyPage vet_fragmentMyPage = new VetFragmentMyPage();
    private VetFragmentDiagnosisComment vet_frameDiagnosisComment = new VetFragmentDiagnosisComment();
    private long backKeyPressedTime = 0;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet_navigation);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, vet_fragmentDiagnosis).commitAllowingStateLoss();
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnItemSelectedListener(new ItemSelectedListener());

    }

    class ItemSelectedListener implements BottomNavigationView.OnItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (menuItem.getItemId()) {
                case R.id.vet_Diagnosis:
                    transaction.replace(R.id.frameLayout, vet_fragmentDiagnosis).commitAllowingStateLoss();
                    break;
                case R.id.vet_MyPage:
                    transaction.replace(R.id.frameLayout, vet_fragmentMyPage).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }
    public void fragmentChange(int index){
        if(index == 1){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, vet_fragmentDiagnosis).commit();
        }else if(index == 2){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, vet_frameDiagnosisComment).commit();
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
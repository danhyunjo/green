package kr.ac.cnu.computer.savagr;



        import android.os.Bundle;

        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;

public class VetQuestionList extends AppCompatActivity {
    VetFragmentDiagnosisComment vetFragmentDiagnosisComment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet_question_list);
    }

    public void fragmentChange() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, vetFragmentDiagnosisComment).commit();
    }
}
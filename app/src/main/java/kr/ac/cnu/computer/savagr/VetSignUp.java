package kr.ac.cnu.computer.savagr;


        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;

public class VetSignUp extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet_signup);

        Button button = findViewById(R.id.vet_sign_up_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VetLogIn.class);
                startActivity(intent);
            }
        });
    }
}
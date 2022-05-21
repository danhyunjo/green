package kr.ac.cnu.computer.savagr;



        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;

public class VetLogIn extends AppCompatActivity {
    TextView text_button;
    Button login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet_login);

        text_button = findViewById(R.id.vet_sign_up);
        login = findViewById(R.id.vet_login_button);

        text_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VetSignUp.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VetNavi.class);
                startActivity(intent);
            }
        });
    }
}
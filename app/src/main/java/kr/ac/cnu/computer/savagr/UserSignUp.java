package kr.ac.cnu.computer.savagr;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UserSignUp extends AppCompatActivity {

    private EditText email_join;
    private EditText pwd_join;
    private EditText name_join;
    private Button btn;

    FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);
        email_join = (EditText) findViewById(R.id.user_id);
        pwd_join = (EditText) findViewById(R.id.user_pw);

        name_join=(EditText)findViewById(R.id.user_name);
        btn = (Button) findViewById(R.id.user_sign_up_button);


        firebaseAuth = FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = email_join.getText().toString().trim();
                final String pwd = pwd_join.getText().toString().trim();

                final String name = name_join.getText().toString().trim();
                //공백인 부분을 제거하고 보여주는 trim();


                firebaseAuth.createUserWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(UserSignUp.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(UserSignUp.this, UserLogIn.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    Toast.makeText(UserSignUp.this, "등록 에러", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        });
            }
        });


    }
}
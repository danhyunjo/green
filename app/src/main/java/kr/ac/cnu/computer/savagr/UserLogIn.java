package kr.ac.cnu.computer.savagr;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import kr.ac.cnu.computer.savagr.R;
import kr.ac.cnu.computer.savagr.UserNavi;
import kr.ac.cnu.computer.savagr.UserSignUp;

public class UserLogIn extends AppCompatActivity {

    Button mLoginBtn;
    TextView mResigettxt;
    EditText mEmailText, mPasswordText;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);


        firebaseAuth =  FirebaseAuth.getInstance();
        //버튼 등록하기
        mResigettxt = findViewById(R.id.user_sign_up);
        mLoginBtn = findViewById(R.id.user_login_button);
        mEmailText = findViewById(R.id.login_user_id);
        mPasswordText = findViewById(R.id.login_user_pw);


        //가입 버튼이 눌리면
        mResigettxt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //intent함수를 통해 register액티비티 함수를 호출한다.
                startActivity(new Intent(getApplicationContext(), UserSignUp.class));

            }
        });

        //로그인 버튼이 눌리면
        mLoginBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String email = mEmailText.getText().toString().trim();
                String pwd = mPasswordText.getText().toString().trim();
                firebaseAuth.signInWithEmailAndPassword(email,pwd)
                        .addOnCompleteListener(UserLogIn.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(getApplicationContext(), UserNavi.class);
                                    startActivity(intent);

                                }else{
                                    Toast.makeText(UserLogIn.this,"로그인 오류",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
}
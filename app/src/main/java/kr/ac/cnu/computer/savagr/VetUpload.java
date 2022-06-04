package kr.ac.cnu.computer.savagr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class VetUpload extends AppCompatActivity {
    Button vet_upload;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet_upload);
        vet_upload = findViewById(R.id. vet_upload);

        vet_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),VetNavi.class);
                startActivity(intent);
            }
        });
    }
}

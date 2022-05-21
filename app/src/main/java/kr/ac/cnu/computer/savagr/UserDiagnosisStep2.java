package kr.ac.cnu.computer.savagr;


import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

public class UserDiagnosisStep2 extends AppCompatActivity implements View.OnClickListener {
    ImageButton camera_btn;
    ImageView image_preview;
    ImageButton gallery_btn;


    Uri photoUri;
    File file;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_step2);

        File sdcard = Environment.getExternalStorageDirectory();
        file = new File(sdcard, "capture.jpg");

        camera_btn = (ImageButton) findViewById(R.id.camera_btn);
        image_preview = (ImageView) findViewById(R.id.image_preview);
        gallery_btn = (ImageButton) findViewById(R.id.gallery_btn);


        camera_btn.setOnClickListener(this);
        gallery_btn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.camera_btn:


                Intent intent_left = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent_left, 0);


                break;

            case R.id.gallery_btn:

                opengallery_left();

                AndPermission.with(this)
                        .runtime()
                        .permission(Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE)
                        .onGranted(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> permissions) {
                                showToast("허용된 권한 갯수 : " + permissions.size());
                            }
                        })
                        .onDenied(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> permissions) {
                                showToast("거부된 권한 갯수 : " + permissions.size());
                            }
                        })
                        .start();
                break;


        }

    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == RESULT_OK) { // Bundle로 데이터를 입력
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            image_preview.setImageBitmap(imageBitmap);
        }

        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                Uri fileUri = data.getData();

                ContentResolver resolver = getContentResolver();

                try {
                    InputStream instream = resolver.openInputStream(fileUri);
                    Bitmap imgBitmap = BitmapFactory.decodeStream(instream);
                    image_preview.setImageBitmap(imgBitmap);
                    instream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /*
        private File createImageFile() throws IOException{

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "_";
            File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            //File StorageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File image = File.createTempFile(imageFileName, ".jpg", storageDir);

            return image;

        }
    */
    public void opengallery_left() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(intent, 101);

    }


    /*
    public void capture(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent, 0);
    }
*/


}
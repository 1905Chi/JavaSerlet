package com.example.appfood.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.appfood.api.APIService;
import com.example.appfood.model.ImageUpload;
import com.example.appfood.model.UserModel;
import com.example.appfood.retrofitClient.RetrofitClient;
import com.example.appfood.util.Constants;
import com.example.appfood.util.RealPathUtil;
import com.example.appfood.util.SharedPrefManager;
import com.example.appfood.R;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadActivity extends AppCompatActivity {
    public static final  int MY_REQUEST_CODE = 100;
    public static  final String TAG = MainActivity.class.getName();
    private ImageView imgViewChoose;
    private Button btnChoseFile, btnUploadImage;
    private Uri mUri;

    APIService apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        AnhXa();


        btnChoseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckPermission();
            }
        });

        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUri != null) {
                    UploadImage();

                }
            }
        });
    }

    public static  String[] storge_permissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public  static String [] getStorge_permissions_33 = {
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_AUDIO,
            Manifest.permission.READ_MEDIA_VIDEO
    };

    public  static  String [] permissions() {
        String[] p;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            p = getStorge_permissions_33;
        } else {
            p = storge_permissions;
        }
        return p;
    }

    private void CheckPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            openGallery();
            return;
        }
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        } else  {
            requestPermissions(permissions(), MY_REQUEST_CODE);
        }
    }
    @Override
    public  void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            }
        }
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"));
    }

    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.e(TAG, "onActivityResult");
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data == null) {
                            return;
                        }
                        Uri uri = data.getData();
                        mUri = uri;
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            imgViewChoose.setImageBitmap(bitmap);

                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    private void AnhXa() {
        btnChoseFile = (Button) findViewById(R.id.btnChoseFile);
        btnUploadImage = (Button) findViewById(R.id.btnUploadImage);
        imgViewChoose = (ImageView) findViewById(R.id.imgViewChoose);
    }

    public  void UploadImage() {
        UserModel user = SharedPrefManager.getInstance(this).getUser();
        String userId = String.valueOf(user.getId());

        RequestBody requestUserId = RequestBody.create(MediaType.parse("multipart/form-data"), userId);
        String IMAGE_PATH = RealPathUtil.getRealPath(this, mUri);
        Log.e("ffff", IMAGE_PATH);
        File file = new File(IMAGE_PATH);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part partbodyavatar = MultipartBody.Part.createFormData(Constants.MY_IMAGES, file.getName(), requestFile);

        apiService = RetrofitClient.getRetrofitCategory().create(APIService.class);
        apiService.upload(requestUserId, partbodyavatar).enqueue(new Callback<List<ImageUpload>>() {
            @Override
            public void onResponse(Call<List<ImageUpload>> call, Response<List<ImageUpload>> response) {
                List<ImageUpload> imageUploads = response.body();
                if (imageUploads.size() > 0) {
                    for (int i = 0; i < imageUploads.size(); i++) {
                        Glide.with(UploadActivity.this)
                                .load(imageUploads.get(i).getAvatar())
                                .into(imgViewChoose);

                        Toast.makeText(UploadActivity.this, "Thành công", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(UploadActivity.this, "Thất bại", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<ImageUpload>> call, Throwable t) {
                Toast.makeText(UploadActivity.this, "Thành công, vui long dang nhap lai", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UploadActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
package com.example.apachepoi;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn2hssf,btn2xwpf,btn2hslf,btn2xssfwbk,btninput;
    ActivityResultLauncher<String[]> mPermissionResultlauncher;
    private boolean isReadPermissionGranted = false;
    private boolean isWritePermissionGranted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn2hssf= findViewById(R.id.btn2hssf);
        btn2xwpf= findViewById(R.id.btn2xwpfwbk);
        btn2hslf= findViewById(R.id.button2hslfss);
        btn2xssfwbk= findViewById(R.id.toxssfwbk);
        btninput= findViewById(R.id.btninputd);

                btninput.setOnClickListener(v -> {
                   Intent intent= new Intent(MainActivity.this, MainActivity3.class);
                   startActivity(intent);
                });

                btn2hssf.setOnClickListener(v -> {
                    Intent intent = new Intent(MainActivity.this, HssfWbk.class);
                    startActivity(intent);
                }); btn2xwpf.setOnClickListener(v -> {
                    Intent intent=new Intent(MainActivity.this,XWPFWbk.class);
                    startActivity(intent);
                }); btn2hslf.setOnClickListener(v -> {
                    Intent intent=new Intent(MainActivity.this,HslfSlsh.class);
                    startActivity(intent);
                });btn2xssfwbk.setOnClickListener(v -> {
                    Intent intent=new Intent(MainActivity.this,XSSFWbk.class);
                    startActivity(intent);
                });
        mPermissionResultlauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
            if (result.get(Manifest.permission.READ_EXTERNAL_STORAGE) != null) {
                isReadPermissionGranted = Boolean.TRUE.equals(result.get(Manifest.permission.READ_EXTERNAL_STORAGE));
            }if (result.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) != null) {
                isWritePermissionGranted = Boolean.TRUE.equals(result.get(Manifest.permission.WRITE_EXTERNAL_STORAGE));
            }if (result.get(Manifest.permission.ACCESS_MEDIA_LOCATION) != null) {
                isWritePermissionGranted = Boolean.TRUE.equals(result.get(Manifest.permission.ACCESS_MEDIA_LOCATION));
            }
        });
        requestPermission();
    }
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private void requestPermission() {
        isReadPermissionGranted = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        isWritePermissionGranted = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        boolean isAccessMediaPermissionGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

        List<String> permissionRequest = new ArrayList<>();
        if (!isReadPermissionGranted) {
            permissionRequest.add(android.Manifest.permission.READ_EXTERNAL_STORAGE);
        }if (!isWritePermissionGranted) {
            permissionRequest.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        } if (!isAccessMediaPermissionGranted) {
            permissionRequest.add(Manifest.permission.ACCESS_MEDIA_LOCATION);
        }
    }

}
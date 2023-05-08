package com.example.apachepoi;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button btn2hssf,btn2xwpf,btn2hslf;
    ActivityResultLauncher<String[]> mPermissionResultlauncher;
    private boolean isReadPermissionGranted = false;
    private boolean isWritePermissionGranted = false;
    private boolean isAccessMediaPermissionGranted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn2hssf=(Button)findViewById(R.id.btn2hssf);
        btn2xwpf=(Button)findViewById(R.id.btn2xwpfwbk);
        btn2hslf=(Button) findViewById(R.id.button2hslfss);

        btn2hssf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,HssfWbk.class);
                startActivity(intent);
            }
        }); btn2xwpf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,XWPFWbk.class);
                startActivity(intent);
            }
        }); btn2hslf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,HslfSlsh.class);
                startActivity(intent);
            }
        });
        mPermissionResultlauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
            @Override
            public void onActivityResult(Map<String, Boolean> result) {
                if (result.get(android.Manifest.permission.READ_EXTERNAL_STORAGE) != null) {
                    isReadPermissionGranted = Boolean.TRUE.equals(result.get(android.Manifest.permission.READ_EXTERNAL_STORAGE));
                }if (result.get(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != null) {
                    isWritePermissionGranted = Boolean.TRUE.equals(result.get(android.Manifest.permission.WRITE_EXTERNAL_STORAGE));
                }if (result.get(android.Manifest.permission.ACCESS_MEDIA_LOCATION) != null) {
                    isWritePermissionGranted = Boolean.TRUE.equals(result.get(android.Manifest.permission.ACCESS_MEDIA_LOCATION));
                }

            }
        });
        requestPermission();
    }
    private void requestPermission() {
        isReadPermissionGranted = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        isWritePermissionGranted = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        isAccessMediaPermissionGranted = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

        List<String> permissionRequest = new ArrayList<String>();
        if (!isReadPermissionGranted) {
            permissionRequest.add(android.Manifest.permission.READ_EXTERNAL_STORAGE);
        }if (!isWritePermissionGranted) {
            permissionRequest.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        } if (!isAccessMediaPermissionGranted) {
            permissionRequest.add(Manifest.permission.ACCESS_MEDIA_LOCATION);
        }
    }
}
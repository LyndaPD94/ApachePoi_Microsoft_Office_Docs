package com.example.apachepoi;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class HssfWbk extends AppCompatActivity {
    Button btnexprtxl;
    EditText xlsxtv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hssf_wbk);
        btnexprtxl=(Button) findViewById(R.id.btnexpxls);
        xlsxtv=(EditText) findViewById(R.id.xlstv);

        btnexprtxl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    exprtxls();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void exprtxls() throws IOException {
        try {

            File exportDir = new File(Environment.getExternalStorageDirectory() + "/Documents");
            if (!exportDir.exists()) {
                exportDir.mkdirs();
            }
            File file = new File(exportDir, "hssf_example.xls");
            file.createNewFile();
            try {
                if (file.exists()) {
                    System.out.println("file.xls " + exportDir.getAbsolutePath());
                    Toast.makeText(getApplicationContext(), "Writing data to excel file...", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "ATTENTION:The file already exists!", Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Exported",Toast.LENGTH_SHORT);
        }
    }

}
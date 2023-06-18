package com.example.apachepoi;

import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class XSSFWbk extends AppCompatActivity {
    EditText xddftv;
    Button btnexprtxls;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xssfwbk);
        xddftv= findViewById(R.id.xddftv);
        btnexprtxls= findViewById(R.id.exprtxls1);
        btnexprtxls.setOnClickListener(v -> {
            try {
                exprtxls();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    private void exprtxls() {

        try {
            File exportDir = new File(Environment.getExternalStorageDirectory()+"/Documents");
            if (!exportDir.exists()) {
                exportDir.mkdirs();
            }
            try {
                File file = new File(exportDir, "xssf_example.xlsx");
                try {
                    XSSFWorkbook workbook =new XSSFWorkbook();
                    XSSFSheet sheet=workbook.createSheet("Sheet 1");
                    XSSFRow row1=sheet.createRow(0);
                    XSSFCell cell1=row1.createCell(0);
                    cell1.setCellValue("Description");
                    XSSFCell cell2=row1.createCell(1);
                    cell2.setCellValue("Amount");
                    XSSFRow row2=sheet.createRow(1);
                    XSSFCell cell3=row2.createCell(0);
                    cell3.setCellValue(5);
                    XSSFCell cell4= row2.createCell(1);
                    cell4.setCellValue(5);
                    XSSFCell cell5=row2.createCell(2);
                    cell5.setCellFormula("SUM(A2+B2)");
                    workbook.createSheet("sheet 2");

                    FileOutputStream fileOutputStream=new FileOutputStream(file);
                    workbook.write(fileOutputStream);
                    fileOutputStream.close();
                    Toast.makeText(getApplicationContext(), "Exported", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.getCause();
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }catch(Exception e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error 2:File does not contain any data.", Toast.LENGTH_LONG).show();
        }
    }
}
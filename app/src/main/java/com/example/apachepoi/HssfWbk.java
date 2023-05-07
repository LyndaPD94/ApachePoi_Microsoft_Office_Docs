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
            File file = new File(exportDir, "datos.xls");
            file.createNewFile();
            try {
                if (file.exists()) {
                    System.out.println("file.xls " + exportDir.getAbsolutePath());
                    Toast.makeText(getApplicationContext(), "Writing data to excel file...", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "ATTENTION:The file already exists!", Toast.LENGTH_LONG).show();
                }
                HSSFWorkbook wb = new HSSFWorkbook();
                Sheet sheet = wb.createSheet("Hssf Workbook");
                Row row = sheet.createRow(0);
                row.setHeightInPoints(12);

                Row row8 = sheet.createRow(1);
                Cell cell8 = row8.createCell(0);
                cell8.setCellValue(0);
                Cell cell10 = row8.createCell(1);
                cell10.setCellValue(1);
                createCell(wb, row8, 0, HorizontalAlignment.CENTER, VerticalAlignment.BOTTOM);

                Cell cell0 = row.createCell(2);
                cell0.setCellValue("value1");
                Cell cell = row.createCell(3);
                cell.setCellValue("value2");
                Cell cell2 = row.createCell(4);
                cell2.setCellValue("value3");
                Cell cell3 = row.createCell(5);
                cell3.setCellValue("value4");

                CellStyle style = wb.createCellStyle();
                style.setBottomBorderColor(IndexedColors.GREEN.getIndex());
                style.setLeftBorderColor(IndexedColors.GREEN.getIndex());
                style.setRightBorderColor(IndexedColors.BLUE.getIndex());
                style.setTopBorderColor(IndexedColors.BLUE.getIndex());
                cell0.setCellStyle(style);
                cell.setCellStyle(style);
                cell2.setCellStyle(style);
                cell3.setCellStyle(style);

                FileOutputStream fileOut = new FileOutputStream(file);
                wb.write(fileOut);
                fileOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Exported",Toast.LENGTH_SHORT);
        }
    }
    private void  createCell(Workbook wb, Row row, int column, HorizontalAlignment halign, VerticalAlignment valign) {

                Cell cell9 = row.createCell(0);
                cell9.setCellValue("value");
                CellStyle cellStyle = wb.createCellStyle();
                cell9.setCellStyle(cellStyle);
            }

}
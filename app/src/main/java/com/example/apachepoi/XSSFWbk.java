package com.example.apachepoi;

import static com.example.apachepoi.DB_Helper.TABLE2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.AxisCrossBetween;
import org.apache.poi.xddf.usermodel.chart.AxisCrosses;
import org.apache.poi.xddf.usermodel.chart.AxisPosition;
import org.apache.poi.xddf.usermodel.chart.AxisTickMark;
import org.apache.poi.xddf.usermodel.chart.BarDirection;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.XDDFBarChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFChart;
import org.apache.poi.xddf.usermodel.chart.XDDFChartAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory;
import org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFValueAxis;
import org.apache.poi.xddf.usermodel.text.CapsType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFChartSheet;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class XSSFWbk extends AppCompatActivity {
    EditText xddftv;
    Button btnexprtxls;
    ArrayList<Sales>data;
    List<String> list1 = new ArrayList<>(5);
    List<Double> list2 = new ArrayList<>(5);
    private static final int COLUMN0 = 0;
    private static final int COLUMN1 = 1;

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

                    //--------------------------------------------------------------------------------
                    /*DB_Helper db_helper=new DB_Helper(getApplicationContext(),"IER.db",null,1);
                    System.out.println("Read Database");
                    db_helper.getWriteableDatabase();
                    SQLiteDatabase db;
                    Cursor cur;
                    data=new ArrayList<>();
                    db=db_helper.getReadableDatabase();
                    cur=db.rawQuery(" SELECT Trans_ID, Description, Total FROM "+TABLE2,null);
                    Sales sales;
                    while (cur.moveToNext()){
                        sales=new Sales();
                        sales.setTrans_ID(cur.getString(0));
                        sales.setDescription(cur.getString(1));
                        sales.setTotal(cur.getString(2));
                        data.add(sales);
                    }
                    for(int i=0;i<data.size();i++){
                        while (cur.moveToPosition(i++)){
                            list1.add(cur.getString(0));
                            list2.add((double) cur.getInt(1));
                        }
                    }*/
                    String[] categories = list1.toArray(new String[0]);
                    Double[] Othervalues = list2.toArray(new Double[0]);

                    XSSFSheet sheet2= workbook.createSheet("Chart");
                    XSSFChart chart=sheet2.createDrawingPatriarch().createChart(new XSSFClientAnchor());
                    XSSFChartSheet chartSheet = null;
                    //XDDFChart chart=sheet2.createDrawingPatriarch().createChart(new XSSFClientAnchor());

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
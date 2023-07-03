package com.example.apachepoi;

import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.apache.poi.xddf.usermodel.chart.AxisCrossBetween;
import org.apache.poi.xddf.usermodel.chart.AxisCrosses;
import org.apache.poi.xddf.usermodel.chart.AxisPosition;
import org.apache.poi.xddf.usermodel.chart.AxisTickMark;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.XDDFBarChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFChartAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFValueAxis;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFChart;
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
  String seriesText = "Data,";
    String[] series = seriesText.split(",");
    List<String> list1 = new ArrayList<>(5);
    List<Double> list2 = new ArrayList<>(5);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xssfwbk);
        xddftv= findViewById(R.id.xddftv);
        btnexprtxls= findViewById(R.id.exprtxls1);

        list1.add("Data 1");
        list1.add("Data 2");
        list1.add("Data 3");
        list1.add("Data 4");
        list1.add("Data 5");
        list2.add(50d);
        list2.add(10d);
        list2.add(110d);
        list2.add(20d);
        list2.add(5d);

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


                    XSSFSheet sheet2= workbook.createSheet("Chart");
                    XSSFChart chart=sheet2.createDrawingPatriarch().createChart(new XSSFClientAnchor());
                    //XDDFChart chart=sheet2.createDrawingPatriarch().createChart(new XSSFClientAnchor());
                    chart.createValueAxis(AxisPosition.BOTTOM);
                    chart.createValueAxis(AxisPosition.LEFT);
                    XDDFChartAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
                    bottomAxis.setTitle(series[0]);
                    XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
                    //leftAxis.setTitle(series[0] + "," + series[1]);
                    leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);
                    leftAxis.setMajorTickMark(AxisTickMark.OUT);
                    leftAxis.setCrossBetween(AxisCrossBetween.BETWEEN);
                    XDDFBarChartData barChartData= (XDDFBarChartData) chart.createData(ChartTypes.BAR,bottomAxis,leftAxis);
                    chart.plot(barChartData);

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
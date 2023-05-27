package com.example.apachepoi;


import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.AxisCrossBetween;
import org.apache.poi.xddf.usermodel.chart.AxisCrosses;
import org.apache.poi.xddf.usermodel.chart.AxisPosition;
import org.apache.poi.xddf.usermodel.chart.AxisTickMark;
import org.apache.poi.xddf.usermodel.chart.BarDirection;
import org.apache.poi.xddf.usermodel.chart.BarGrouping;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.LegendPosition;
import org.apache.poi.xddf.usermodel.chart.XDDFBarChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFChart;
import org.apache.poi.xddf.usermodel.chart.XDDFChartAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFChartLegend;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory;
import org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFValueAxis;
import org.apache.poi.xwpf.usermodel.XWPFChart;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ChartInfo {
    public ChartInfo() {}
    Context context;
    private static final int COLUMN_LANGUAGES = 0;
    private static final int COLUMN_COUNTRIES = 1;
    private static final int COLUMN_SPEAKERS = 2;


    public void main(String[] args) throws Exception, IOException {
        try{
            File file =new File("C://Users/Usuario/Documents/.Java_Apache/xwpf.docx");
            File file2= new File("C://Users/Usuario/AndroidStudioProjects/ApachePoi/app/sampledata/bar-chart-data.txt");

            if(!file.exists()){
                file.mkdirs();
                System.out.print("File created");
            }if(!file2.canRead()){
                Toast.makeText(context,"cannot read file input",Toast.LENGTH_SHORT).show();
            }

            try (BufferedReader modelReader = Files.newBufferedReader(file2.toPath(), StandardCharsets.UTF_8)) {

                String chartTitle = modelReader.readLine();  // first line is chart title
                String seriesText = modelReader.readLine();
                String[] series = seriesText == null ? new String[0] : seriesText.split(",");
                // Category Axis Data
                List<String> listLanguages = new ArrayList<>(10);

                // Values
                List<Double> listCountries = new ArrayList<>(10);
                List<Double> listSpeakers = new ArrayList<>(10);

                // set model
                String ln;
                while((ln = modelReader.readLine()) != null) {
                    String[] vals = ln.split(",");
                    listCountries.add(Double.valueOf(vals[0]));
                    listSpeakers.add(Double.valueOf(vals[1]));
                    listLanguages.add(vals[2]);
                }

                String[] categories = listLanguages.toArray(new String[0]);
                Double[] values1 = listCountries.toArray(new Double[0]);
                Double[] values2 = listSpeakers.toArray(new Double[0]);

                XWPFDocument doc = new XWPFDocument();
                XWPFChart chart = doc.createChart(XDDFChart.DEFAULT_WIDTH * 10, XDDFChart.DEFAULT_HEIGHT * 15);
                XDDFChartAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
                bottomAxis.setTitle(series[2]);
                XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
                leftAxis.setTitle(series[0]+","+series[1]);
                leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);
                leftAxis.setMajorTickMark(AxisTickMark.OUT);
                leftAxis.setCrossBetween(AxisCrossBetween.BETWEEN);

                final int numOfPoints = categories.length;
                final String categoryDataRange = chart.formatRange(new CellRangeAddress(1, numOfPoints, COLUMN_LANGUAGES, COLUMN_LANGUAGES));
                final String valuesDataRange = chart.formatRange(new CellRangeAddress(1, numOfPoints, COLUMN_COUNTRIES, COLUMN_COUNTRIES));
                final String valuesDataRange2 = chart.formatRange(new CellRangeAddress(1, numOfPoints, COLUMN_SPEAKERS, COLUMN_SPEAKERS));
                final XDDFDataSource<?> categoriesData = XDDFDataSourcesFactory.fromArray(categories, categoryDataRange, COLUMN_LANGUAGES);
                final XDDFNumericalDataSource<? extends Number> valuesData = XDDFDataSourcesFactory.fromArray(values1, valuesDataRange, COLUMN_COUNTRIES);
                valuesData.setFormatCode("General");
                values1[6] = 16.0; // if you ever want to change the underlying data, it has to be done before building the data source
                final XDDFNumericalDataSource<? extends Number> valuesData2 = XDDFDataSourcesFactory.fromArray(values2, valuesDataRange2, COLUMN_SPEAKERS);
                valuesData2.setFormatCode("General");


                XDDFBarChartData bar = (XDDFBarChartData) chart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
                bar.setBarGrouping(BarGrouping.CLUSTERED);

                XDDFBarChartData.Series series1 = (XDDFBarChartData.Series) bar.addSeries(categoriesData, valuesData);
                series1.setTitle(series[0], chart.setSheetTitle(series[0], COLUMN_COUNTRIES));

                XDDFBarChartData.Series series2 = (XDDFBarChartData.Series) bar.addSeries(categoriesData, valuesData2);
                series2.setTitle(series[1], chart.setSheetTitle(series[1], COLUMN_SPEAKERS));

                bar.setVaryColors(true);
                bar.setBarDirection(BarDirection.COL);
                chart.plot(bar);
//-----------------------------------------CHART-----------------------------------------------------------------------------------------------------
                XDDFChartLegend legend = chart.getOrAddLegend();
                legend.setPosition(LegendPosition.LEFT);
                legend.setOverlay(false);

                chart.setTitleText(chartTitle);
                chart.setTitleOverlay(false);
                chart.setAutoTitleDeleted(false);
                OutputStream out = new FileOutputStream(file);
                 // save the result
                doc.write(out);
                System.out.println("Done");
                Toast.makeText(context,"created",Toast.LENGTH_SHORT).show();
            }catch(Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
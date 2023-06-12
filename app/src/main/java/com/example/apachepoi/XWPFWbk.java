package com.example.apachepoi;

import static com.example.apachepoi.Structure_BBDD.DATABASE_NAME;
import static com.example.apachepoi.Structure_BBDD.TABLE1;

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
import org.apache.poi.xddf.usermodel.chart.BarGrouping;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.LegendPosition;
import org.apache.poi.xddf.usermodel.chart.XDDFBarChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFChart;
import org.apache.poi.xddf.usermodel.chart.XDDFChartAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFChartLegend;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory;
import org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFValueAxis;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TableRowAlign;
import org.apache.poi.xwpf.usermodel.TableWidthType;
import org.apache.poi.xwpf.usermodel.XWPFChart;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class XWPFWbk extends AppCompatActivity {
    Button btnexpdocx, exprtsqldoc;
    EditText docxtv;
    DB_Helper helper;
    ArrayList<Sales> data;


    private static final int COLUMN_LANGUAGES = 0;
    private static final int COLUMN_COUNTRIES = 1;
    private static final int COLUMN_SPEAKERS = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_xwpfwbk);
        btnexpdocx = (Button) findViewById(R.id.exprtdocx);
        exprtsqldoc = (Button) findViewById(R.id.expsqldoc);
        docxtv = (EditText) findViewById(R.id.doocxtv);
        exprtsqldoc.setOnClickListener(v -> {
            try {
                exportsqldoc();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        btnexpdocx.setOnClickListener(v -> {
            try {
                exportdoc();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void exportdoc() {

        try {
            File exportDir = new File(Environment.getExternalStorageDirectory() + "/Documents");
            if (!exportDir.exists()) {
                exportDir.mkdirs();
            }
            try {
                File file = new File(exportDir, "xwpf_example.docx");
                try {

                    XWPFDocument xwpfDocument = new XWPFDocument();
                    XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
                    XWPFRun xwpfRun = xwpfParagraph.createRun();
                    xwpfRun.setText("XWPF tiene una API central bastante estable, que proporciona acceso de lectura y escritura a las partes principales de un archivo de .docx Word, pero no está completo. Para algunas cosas, puede ser necesario sumergirse en el XMLBeans de bajo nivel objetos para manipular la estructura OOXML. Para la extracción de texto básica, utilice org.apache.poi.xwpf.extractor.XWPFWordExtractor. Acepta una entrada stream o un XWPFDocument. El método getText() se puede utilizar para Obtenga el texto de todos los párrafos, junto con tablas, encabezados, etc.");
                    xwpfRun.setFontFamily("Arial");
                    xwpfRun.addBreak();
//----------------------------------------------TABLE----------------------------------------------------------

                    XWPFTable table = xwpfDocument.createTable();
                    XWPFTableRow row = table.getRow(0);
                    XWPFTableCell cell = row.getCell(0);
                    XWPFParagraph paragraph = cell.getXWPFDocument().createParagraph();
                    paragraph.setAlignment(ParagraphAlignment.CENTER);
                    XWPFRun runa = paragraph.createRun();


                    runa.getDocument().getTable(table.getCTTbl()).getRow(0).getCell(0).setText("Description");
                    runa.getDocument().getTable(table.getCTTbl()).getRow(0).createCell().setText("Amount");
                    runa.getDocument().getTable(table.getCTTbl()).getRow(0).getCell(0).setColor("78C200");
                    runa.getDocument().getTable(table.getCTTbl()).getRow(0).getCell(1).setColor("78C200");
                    runa.getDocument().getTable(table.getCTTbl()).createRow().getCell(0).setText("item 1");
                    runa.getDocument().getTable(table.getCTTbl()).getRow(1).getCell(1).setText("44");
                    runa.getDocument().getTable(table.getCTTbl()).createRow().getCell(0).setText("item 2");
                    runa.getDocument().getTable(table.getCTTbl()).getRow(2).getCell(1).setText("5");
                    runa.getDocument().getTable(table.getCTTbl()).createRow().getCell(0).setText("item 3");
                    runa.getDocument().getTable(table.getCTTbl()).getRow(3).getCell(1).setText("57");
                    runa.getDocument().getTable(table.getCTTbl()).createRow().getCell(0).setText("item 4");
                    runa.getDocument().getTable(table.getCTTbl()).getRow(4).getCell(1).setText("54");
                    runa.getDocument().getTable(table.getCTTbl()).setTableAlignment(TableRowAlign.CENTER);
                    runa.addBreak();


                    XWPFTable table0 = xwpfDocument.createTable();
                    XWPFTableRow row0 = table0.getRow(0);
                    XWPFTableCell cell0 = row0.getCell(0);
                    cell0.setText("Description");
                    cell0.setColor("78C200");
                    row0.createCell().setText("Amount");
                    row0.getCell(1).setColor("78C200");
                    XWPFTableRow row2 = table0.createRow();
                    XWPFTableCell cell1 = row2.getCell(0);
                    XWPFParagraph paragraph1 = cell1.getXWPFDocument().createParagraph();
                    paragraph1.setAlignment(ParagraphAlignment.CENTER);
                    cell1.setText("item 1");
                    row2.getCell(1).setText("12");

                    XWPFTableRow row3 = table0.createRow();
                    XWPFTableCell cell2 = row3.getCell(0);
                    XWPFParagraph paragraph2 = cell1.getXWPFDocument().createParagraph();
                    paragraph2.setAlignment(ParagraphAlignment.CENTER);
                    cell2.setText("item 2");
                    row2.createCell().setText("55");


                    XWPFParagraph paragraph0 = xwpfRun.getDocument().createParagraph();
                    XWPFRun run2 = paragraph0.createRun();
                    run2.addBreak();
                    run2.setFontFamily("Times New Roman");
                    run2.setText("Desde un XWPFParagraph, es posible obtener los elementos XWPFRun existentes que componen el texto. Para agregar nuevo texto, el método createRun() agregará un nuevo XWPFRun al final de la lista. insertNewRun(int) puede ser se utiliza para agregar un nuevo XWPFRun en un punto específico de la párrafo.Una vez que tenga un XWPFRun, puede usar el método setText(String) para realizar cambios en el texto. Para agregar elementos de espacio en blanco como tabulaciones y saltos de línea, es necesario utilizar métodos como addTab() y addCarriageReturn().El texto del documento también se encuentra en la corriente principal. Su inicio location se da como FIB.fcMin y su longitud viene dada en bytes por FIB.ccpText. Estos dos valores no son muy útiles para obtener el texto Debido a Unicode. Puede haber texto Unicode entremezclado con ASCII Mensaje de texto. Eso nos lleva a la mesa de piezas." +
                            "\n" +
                            "La tabla de piezas se utiliza para dividir el texto en no unicode y unicode pedazos. El tamaño y el desplazamiento se indican en FIB.fcClx y FIB.lcbClx respectivamente. La tabla de piezas puede contener modificadores de propiedad (prm). Estos son para archivos complejos (guardados rápidamente) y se omiten. Cada pieza de texto Contiene desplazamientos en la secuencia principal que contienen texto para esa pieza. Si la pieza utiliza unicode, el desplazamiento del archivo se enmascara con un bit determinado. Luego tienes que desenmascarar el bit y dividir por 2 para obtener el archivo real compensar.");
                    paragraph0.setPageBreak(true);
                    XWPFRun run3 = paragraph0.createRun();
                    run3.addBreak();
                    run3.setText("Los estilos de párrafo sin comprimir se representan mediante el Pargraph Estructura de datos de propiedades (PAP). Los estilos de carácter sin comprimir son representado por la estructura de datos Propiedades de caracteres (CHP). Los estilos para el texto del documento se almacenan en formato comprimido en el directorio correspondientes Páginas de disco formateadas (FKP). Se refiere un PAP comprimido a como PAPX y un CHP comprimido es un CHPX. Las ubicaciones de FKP son Almacenado en la tabla de contenedores. Hay mesas de contenedores separadas para CHPX y PAPAX. Las ubicaciones y tamaños de las mesas de contenedores se almacenan en el FIB.");
                    run3.setFontSize(12);
                    run3.setFontFamily("Book Antiqua");
                    paragraph0.setIndentationFirstLine(1);
                    paragraph0.setAlignment(ParagraphAlignment.CENTER);
                    paragraph0.setSpacingAfterLines(1);
                    paragraph0.setBorderBottom(Borders.DOTTED);
                    paragraph0.setBorderTop(Borders.DOTTED);
                    paragraph0.setBorderLeft(Borders.ARCHED_SCALLOPS);
                    paragraph0.setBorderRight(Borders.ARCHED_SCALLOPS);
                    run3.addBreak(BreakType.PAGE);
                    XWPFRun run4 = paragraph0.createRun();
                    XWPFParagraph paragraph3 = run4.getDocument().createParagraph();
                    paragraph3.setIndentationFirstLine(1);
                    paragraph3.setAlignment(ParagraphAlignment.CENTER);
                    paragraph3.setSpacingAfterLines(1);
                    paragraph3.setBorderBottom(Borders.BASIC_BLACK_SQUARES);
                    paragraph3.setBorderTop(Borders.BASIC_BLACK_SQUARES);
                    paragraph3.setBorderLeft(Borders.BASIC_BLACK_SQUARES);
                    paragraph3.setBorderRight(Borders.BASIC_BLACK_SQUARES);
                    XWPFParagraph paragraph4 = run4.getDocument().createParagraph();
                    paragraph4.createRun().setText("Bar Chart");
//---------------------------------------------------------------------------------------------------
                    String chartTitle = "10 languages with most speakers as first language";  // first line is chart title
                    String seriesText = "Speakers,Language";
                    String[] series = seriesText.split(",");
                    // Category Axis Data"seriesText)
                    List<String> listLanguages = new ArrayList<>(5);
                    List<Double> listCountries = new ArrayList<>(5);
                    List<Double> listSpeakers = new ArrayList<>(5);
                    listCountries.add(58d);
                    listCountries.add(4d);
                    listCountries.add(28d);
                    listCountries.add(118d);
                    listCountries.add(4d);
                    listSpeakers.add(315d);
                    listSpeakers.add(243d);
                    listSpeakers.add(129d);
                    listSpeakers.add(378d);
                    listSpeakers.add(260d);
                    listLanguages.add("english");
                    listLanguages.add("spanish");
                    listLanguages.add("french");
                    listLanguages.add("chinese");
                    listLanguages.add("latin");
                    String[] categories = listLanguages.toArray(new String[0]);
                    Double[] values1 = listCountries.toArray(new Double[0]);
                    Double[] values2 = listSpeakers.toArray(new Double[0]);
                    XWPFChart chart = run4.getDocument().createChart(XDDFChart.DEFAULT_WIDTH * 10, XDDFChart.DEFAULT_HEIGHT * 15);
                    XDDFChartAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
                    bottomAxis.setTitle(series[0]);
                    XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
                    leftAxis.setTitle(series[0] + "," + series[1]);
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
                    values1[0] = 5.0; // if you ever want to change the underlying data, it has to be done before building the data source
                    final XDDFNumericalDataSource<? extends Number> valuesData2 = XDDFDataSourcesFactory.fromArray(values2, valuesDataRange2, COLUMN_SPEAKERS);
                    valuesData2.setFormatCode("General");
                    XDDFBarChartData bar = (XDDFBarChartData) chart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
                    bar.setBarGrouping(BarGrouping.STANDARD);
                    XDDFBarChartData.Series series1 = (XDDFBarChartData.Series) bar.addSeries(categoriesData, valuesData);
                    series1.setTitle(series[0], chart.setSheetTitle(series[0], COLUMN_COUNTRIES));
                    XDDFBarChartData.Series series2 = (XDDFBarChartData.Series) bar.addSeries(categoriesData, valuesData2);
                    series2.setTitle(series[1], chart.setSheetTitle(series[1], COLUMN_SPEAKERS));

                    bar.setVaryColors(true);
                    bar.setBarDirection(BarDirection.COL);
                    chart.plot(bar);
                    XDDFChartLegend legend = chart.getOrAddLegend();
                    legend.setPosition(LegendPosition.LEFT);
                    legend.setOverlay(false);
                    chart.setTitleText(chartTitle);
                    chart.setTitleOverlay(false);
                    chart.setAutoTitleDeleted(false);
//-----------------------------------------Line CHART-----------------------------------------------------------------------------------------------------
                    XWPFParagraph paragraph5 = run4.getDocument().createParagraph();
                    paragraph5.createRun().setText("Line Graph");
                    String seriesText2 = "Speakers,Language";
                    String[] seriesline = seriesText2.split(",");

                    // Category Axis Data"seriesText)
                    List<String> listLanguages2 = new ArrayList<>(5);
                    // Values
                    List<Double> listCountries2 = new ArrayList<>(5);
                    List<Double> listSpeakers2 = new ArrayList<>(5);
                    //String[] vals = ln.split(",");
                    listCountries2.add(58d);
                    listCountries2.add(4d);
                    listCountries2.add(28d);
                    listCountries2.add(118d);
                    listCountries2.add(4d);
                    listSpeakers2.add(315d);
                    listSpeakers2.add(243d);
                    listSpeakers2.add(129d);
                    listSpeakers2.add(378d);
                    listSpeakers2.add(260d);
                    listLanguages2.add("english");
                    listLanguages2.add("spanish");
                    listLanguages2.add("french");
                    listLanguages2.add("chinese");
                    listLanguages2.add("latin");
                    String[] linecategories = listLanguages2.toArray(new String[0]);
                    Double[] lvalues1 = listCountries2.toArray(new Double[0]);
                    Double[] lvalues2 = listSpeakers2.toArray(new Double[0]);
                    XWPFRun run5 = xwpfParagraph.createRun();
                    XWPFParagraph xwpfParagraph2 = run5.getDocument().createParagraph();
                    xwpfParagraph2.setAlignment(ParagraphAlignment.CENTER);
                    XDDFChart chart2 = run5.getDocument().createChart(XDDFChart.DEFAULT_WIDTH * 10, XDDFChart.DEFAULT_HEIGHT * 15);
                    XDDFChartAxis xaxisline = chart2.createCategoryAxis(AxisPosition.BOTTOM);
                    xaxisline.setTitle(seriesline[0]);
                    XDDFValueAxis yvalueAxisline = chart2.createValueAxis(AxisPosition.LEFT);
                    xaxisline.setTitle(seriesline[0] + "," + seriesline[1]);
                    yvalueAxisline.setCrosses(AxisCrosses.AUTO_ZERO);
                    yvalueAxisline.setMajorTickMark(AxisTickMark.OUT);
                    yvalueAxisline.setCrossBetween(AxisCrossBetween.BETWEEN);
                    final int lnumOfPoints = linecategories.length;
                    final String categoryDataRange2 = chart2.formatRange(new CellRangeAddress(1, lnumOfPoints, COLUMN_LANGUAGES, COLUMN_LANGUAGES));
                    final String lineDataRange = chart2.formatRange(new CellRangeAddress(1, lnumOfPoints, COLUMN_COUNTRIES, COLUMN_COUNTRIES));
                    final String lineDataRange2 = chart2.formatRange(new CellRangeAddress(1, lnumOfPoints, COLUMN_SPEAKERS, COLUMN_SPEAKERS));
                    final XDDFDataSource<?> categoriesLData = XDDFDataSourcesFactory.fromArray(linecategories, categoryDataRange2, COLUMN_LANGUAGES);
                    final XDDFNumericalDataSource<? extends Number> lvaluesData = XDDFDataSourcesFactory.fromArray(lvalues1, lineDataRange, COLUMN_COUNTRIES);
                    lvaluesData.setFormatCode("General");
                    lvalues1[0] = 5.0; // if you ever want to change the underlying data, it has to be done before building the data source
                    final XDDFNumericalDataSource<? extends Number> lvaluesData2 = XDDFDataSourcesFactory.fromArray(lvalues2, lineDataRange2, COLUMN_SPEAKERS);
                    lvaluesData2.setFormatCode("General");
                    XDDFChartData line = (XDDFChartData) chart2.createData(ChartTypes.LINE, xaxisline, yvalueAxisline);
                    line.setVaryColors(true);
                    XDDFChartData.Series lseries1 = (XDDFChartData.Series) line.addSeries(categoriesLData, lvaluesData);
                    lseries1.setTitle(seriesline[0], chart2.setSheetTitle(seriesline[0], COLUMN_COUNTRIES));
                    XDDFChartData.Series lseries2 = (XDDFChartData.Series) line.addSeries(categoriesLData, lvaluesData2);
                    lseries2.setTitle(seriesline[1], chart2.setSheetTitle(seriesline[1], COLUMN_SPEAKERS));
                    line.setVaryColors(true);
                    line.setVaryColors(true);
                    chart2.plot(line);

                    OutputStream fileOut = new FileOutputStream(file);
                    xwpfDocument.write(fileOut);
                    fileOut.close();
                    Toast.makeText(getApplicationContext(), "Exported", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.getCause();
                    Toast.makeText(getApplicationContext(), "Error 1", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error 2", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error 3:File does not contain any data.", Toast.LENGTH_LONG).show();
        }
    }


    private void exportsqldoc() {
        try {
            try {
                File dbFile = getDatabasePath(DATABASE_NAME);
                helper = new DB_Helper(getApplicationContext(), "POI.db", null, 1);
                System.out.println(dbFile);
                File exportDir = new File(Environment.getExternalStorageDirectory() + "/Documents");
                if (!exportDir.exists()) {
                    exportDir.mkdirs();
                }
                try {
                    File file = new File(exportDir, "xwpf_example_hssfsql.docx");
                    try {
                        XWPFDocument xwpfDocument = new XWPFDocument();
                        XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
                        XWPFRun run3 = xwpfParagraph.createRun();
                        helper.getWriteableDatabase();
                        SQLiteDatabase db;
                        helper.exportAll();
                        Cursor cur;
                        data = new ArrayList<>();
                        db = helper.getReadableDatabase();
                        xwpfDocument.createParagraph();

                        XWPFTable table = run3.getDocument().createTable();
                        XWPFTableRow row = table.getRow(0);
                        row.getCell(0);
                        cur = db.rawQuery("select * from " + TABLE1, null);
                        while (cur.moveToNext()) {
                            String[] arrStr = {
                                    String.valueOf(cur.getString(0)),
                                    String.valueOf(cur.getString(1)),
                                    String.valueOf(cur.getString(2)),
                                    String.valueOf(cur.getString(3)),
                                    String.valueOf(cur.getString(4)),
                                    String.valueOf(cur.getString(5)),
                                    String.valueOf(cur.getString(6))};
                            for (int j = 0; j < arrStr.length; j++) {
                                while (cur.moveToPosition(j++)) {
                                    XWPFTableRow row0 = table.createRow();
                                    row0.createCell().setText(cur.getString(0));
                                    row0.createCell().setText(cur.getString(1));
                                    row0.createCell().setText(cur.getString(2));
                                    row0.createCell().setText(cur.getString(3));
                                    row0.createCell().setText(cur.getString(4));
                                    row0.createCell().setText(cur.getString(5));
                                    row0.createCell().setText(cur.getString(6));
                                    row0.getCell(0).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                                }
                            }
                        }

                        XWPFTableCell cell1 = row.createCell();
                        cell1.setText(cur.getColumnName(0));
                        cell1.setWidthType(TableWidthType.AUTO);
                        cell1.setColor("D44012");
                        XWPFTableCell cell2 = row.createCell();
                        cell2.setText(cur.getColumnName(1));
                        cell2.setColor("D44012");
                        XWPFTableCell cell3 = row.createCell();
                        cell3.setText(cur.getColumnName(2));
                        cell3.setColor("D44012");
                        XWPFTableCell cell4 = row.createCell();
                        cell4.setText(cur.getColumnName(3));
                        cell4.setColor("D44012");
                        XWPFTableCell cell5 = row.createCell();
                        cell5.setText(cur.getColumnName(4));
                        cell5.setColor("D44012");
                        XWPFTableCell cell6 = row.createCell();
                        cell6.setText(cur.getColumnName(5));
                        cell6.setColor("D44012");
                        XWPFTableCell cell7 = row.createCell();
                        cell7.setText(cur.getColumnName(6));
                        cell7.setColor("D44012");


                        cur.close();

                        OutputStream fileOut = new FileOutputStream(file);
                        xwpfDocument.write(fileOut);
                        fileOut.close();
                        Toast.makeText(getApplicationContext(), "Exported", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.getCause();
                        Toast.makeText(getApplicationContext(), "Error 1", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error 2", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Error 3:File does not contain any data.", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }/* private void exportdocsqltbl2 () {
        try {
            try {
                File dbFile = getDatabasePath(DATABASE_NAME);
                helper = new DB_Helper(getApplicationContext(), "POI.db", null, 1);
                System.out.println(dbFile);
                File exportDir = new File(Environment.getExternalStorageDirectory() + "/Documents");
                if (!exportDir.exists()) {
                    exportDir.mkdirs();
                }
                try {
                    File file = new File(exportDir, "xwpf_example_hssfsql.docx");
                    try {
                        XWPFDocument xwpfDocument = new XWPFDocument();
                        XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
                        xwpfParagraph.createRun();
                        helper.getWriteableDatabase();
                        SQLiteDatabase db;
                        helper.exportAll();
                        Cursor cur;
                        XWPFTable table = xwpfDocument.createTable();
                        data = new ArrayList<>();
                        db = helper.getReadableDatabase();
                        XWPFTableRow row = table.getRow(0);
                        XWPFTableCell cell0 = row.getCell(0);
                        XWPFParagraph paragraph0=cell0.getXWPFDocument().createParagraph();
                        paragraph0.setAlignment(ParagraphAlignment.CENTER);
                        XWPFRun runa=paragraph0.createRun();
                        runa.setFontFamily("Times New Roman");
                        runa.setBold(true);
                        cur = db.rawQuery("select * from " + TABLE1, null);
                        while (cur.moveToNext()) {
                            String[] arrStr = {
                                    String.valueOf(cur.getString(0)),
                                    String.valueOf(cur.getString(1)),
                                    String.valueOf(cur.getString(2)),
                                    String.valueOf(cur.getString(3)),
                                    String.valueOf(cur.getString(4)),
                                    String.valueOf(cur.getString(5)),
                                    String.valueOf(cur.getString(6))};
                            for (int j = 0; j < arrStr.length; j++) {
                                while (cur.moveToPosition(j++)) {
                                    XWPFTableCell cell = row.getCell(0);
                                    XWPFParagraph paragraph=cell.getXWPFDocument().createParagraph();
                                    table.createRow();
                                    XWPFRun runb=paragraph.createRun();
                                    runb.getDocument().getTable(table.getCTTbl()).getRow(0).createCell().setText(cur.getString(0));
                                    runb.getDocument().getTable(table.getCTTbl()).getRow(0).createCell().setText(cur.getString(1));
                                    runb.getDocument().getTable(table.getCTTbl()).getRow(0).createCell().setText(cur.getString(2));
                                    runb.getDocument().getTable(table.getCTTbl()).getRow(0).createCell().setText(cur.getString(3));
                                    runb.getDocument().getTable(table.getCTTbl()).getRow(0).createCell().setText(cur.getString(4));
                                    runb.getDocument().getTable(table.getCTTbl()).getRow(0).createCell().setText(cur.getString(5));
                                    runb.getDocument().getTable(table.getCTTbl()).getRow(0).createCell().setText(cur.getString(6));
                                }
                            }
                        }

                        XWPFTableCell cell1 = row.createCell();
                        cell1.setText(cur.getColumnName(0));
                        cell1.setWidthType(TableWidthType.AUTO);
                        cell1.setColor("D44012");
                        XWPFTableCell cell2 = row.createCell();
                        cell2.setText(cur.getColumnName(1));
                        cell2.setColor("D44012");
                        XWPFTableCell cell3 = row.createCell();
                        cell3.setText(cur.getColumnName(2));
                        cell3.setColor("D44012");
                        XWPFTableCell cell4 = row.createCell();
                        cell4.setText(cur.getColumnName(3));
                        cell4.setColor("D44012");
                        XWPFTableCell cell5 = row.createCell();
                        cell5.setText(cur.getColumnName(4));
                        cell5.setColor("D44012");
                        XWPFTableCell cell6 = row.createCell();
                        cell6.setText(cur.getColumnName(5));
                        cell6.setColor("D44012");
                        XWPFTableCell cell7 = row.createCell();
                        cell7.setText(cur.getColumnName(6));
                        cell7.setColor("D44012");


                        cur.close();

                        OutputStream fileOut = new FileOutputStream(file);
                        xwpfDocument.write(fileOut);
                        fileOut.close();
                        Toast.makeText(getApplicationContext(), "Exported", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.getCause();
                        Toast.makeText(getApplicationContext(), "Error 1", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error 2", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Error 3:File does not contain any data.", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
                    XDDFChart chart = run3.getDocument().createChart();
                    XDDFCategoryAxis categoryAxis=chart.createCategoryAxis(AxisPosition.BOTTOM);
                    categoryAxis.getOrAddMajorGridProperties().getLineProperties().addDashStop();
                    categoryAxis.setTitle("Title");
                    categoryAxis.getLabelAlignment().compareTo(AxisLabelAlignment.CENTER);
                    XDDFValueAxis valueAxis=chart.createValueAxis(AxisPosition.BOTTOM);
                    //XDDFChartData xddfChartData=chart.createData(ChartTypes.BAR,categoryAxis,valueAxis);
                    //xddfChartData.getValueAxes().set(0,valueAxis);
                    //XDDFChartAxis axis= chart.createCategoryAxis(AxisPosition.BOTTOM);
                    //XDDFValueAxis valueAxis=chart.createValueAxis(AxisPosition.BOTTOM);
                    //XDDFChartData chartData=chart.createData(ChartTypes.BAR,axis,valueAxis);
                    //XDDFChartLegend chartLegend=chart.getOrAddLegend();
                    //XDDFLegendEntry legendEntry= chartLegend.addEntry();
                    //XDDFTextBody textBody=legendEntry.getTextBody();
                    //valueAxis.setTitle("Title");*/
}
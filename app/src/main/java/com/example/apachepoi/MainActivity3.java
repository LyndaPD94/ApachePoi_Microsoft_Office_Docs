package com.example.apachepoi;



import static com.example.apachepoi.Structure_BBDD.TABLE1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity3 extends AppCompatActivity {
    ListHelper helper;
    public ArrayList<Sales> data;
    Button btinsert,btsearch;
    EditText transid,date2,descrip2,amt2,price2,total2,coment2;


    private float TotalI;
    private float AmtI;
    private float PriceI;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_sales, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mmenu:
                MainMenu();
                return true;
            case R.id.newsale:
                NewSale();
                return true;
            case R.id.saleshist:
                SalesHist();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //Sales
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        DB_Helper hiveDB_helper=new DB_Helper(MainActivity3.this);
        helper=new ListHelper(getApplicationContext(),"IER.db",null,1);


        transid=(EditText) findViewById(R.id.transid);
        date2=(EditText) findViewById(R.id.date2);
        descrip2=(EditText) findViewById(R.id.descrip2);
        amt2=(EditText) findViewById(R.id.amt2);
        price2=(EditText) findViewById(R.id.price2);
        total2=(EditText) findViewById(R.id.total2);
        coment2=(EditText) findViewById(R.id.comment2);

        btsearch=(Button) findViewById(R.id.btsearch);
        btinsert=(Button) findViewById(R.id.btinsert);

        btinsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Calendar calendar = Calendar.getInstance();
                    String DateNow = MonthDay.now().toString() + "-" + calendar.get(Calendar.YEAR);

                    PriceI = Float.parseFloat(price2.getText().toString());
                    AmtI = Float.parseFloat(amt2.getText().toString());
                    TotalI = PriceI * AmtI;

                    SQLiteDatabase db = helper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(Structure_BBDD.COLUMNA2, DateNow);
                    values.put(Structure_BBDD.COLUMNA3, descrip2.getText().toString());
                    values.put(Structure_BBDD.COLUMNA4, amt2.getText().toString());
                    values.put(Structure_BBDD.COLUMNA5, price2.getText().toString());
                    values.put(Structure_BBDD.COLUMNA6, TotalI);
                    values.put(Structure_BBDD.COLUMNA7, coment2.getText().toString());
                    long newRowId = db.insert(TABLE1, null, values);
                    Toast.makeText(getApplicationContext(), "The register was saved with ID: " + newRowId, Toast.LENGTH_SHORT).show();
                    transid.setText("");
                    date2.setText("");
                    descrip2.setText("");
                    amt2.setText("");
                    price2.setText("");
                    total2.setText("");
                    coment2.setText("");
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                }
            }
        });
        btsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getReadableDatabase();
                String[] projection = {
                        Structure_BBDD.COLUMNA2,
                        Structure_BBDD.COLUMNA3,
                        Structure_BBDD.COLUMNA4,
                        Structure_BBDD.COLUMNA5,
                        Structure_BBDD.COLUMNA6,
                        Structure_BBDD.COLUMNA7
                };
                String selection = Structure_BBDD.COLUMNAID + " = ?";
                String[] selectionArgs = {transid.getText().toString()};
                try {
                    Cursor cursor = db.query(
                            TABLE1,   // The table to query
                            projection,             // The array of columns to return (pass null to get all)
                            selection,              // The columns for the WHERE clause
                            selectionArgs,          // The values for the WHERE clause
                            null,                   // don't group the rows
                            null,                   // don't filter by row groups
                            null               // The sort order
                    );
                    cursor.moveToFirst();

                    date2.setText("");
                    descrip2.setText("");
                    amt2.setText("");
                    price2.setText("");
                    total2.setText("");
                    coment2.setText("");


                    date2.setText(cursor.getString(0));
                    descrip2.setText(cursor.getString(1));
                    amt2.setText(cursor.getString(2));
                    price2.setText(cursor.getString(3));
                    total2.setText(cursor.getString(4));
                    coment2.setText(cursor.getString(5));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERROR: Could not find the requested register. ", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    private void MainMenu() {
        try {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
    private void NewSale() {
        try {
            Intent intent = new Intent(this, MainActivity3.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    } private void SalesHist() {
        try {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
}
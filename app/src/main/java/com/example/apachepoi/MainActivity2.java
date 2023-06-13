package com.example.apachepoi;


import static com.example.apachepoi.Structure_BBDD.DATABASE_NAME;
import static com.example.apachepoi.Structure_BBDD.TABLE1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    public ArrayList<Sales> data;
    private RecyclerView recyclerView;

    ListHelper helper;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_sales, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        new DB_Helper(MainActivity2.this);
        helper= new ListHelper(getApplicationContext(),DATABASE_NAME, null, 1);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        data=new ArrayList<>();

        getAdapter();
        getInfo();

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
    private void getAdapter(){
        SalesAdapter adapter = new SalesAdapter(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
    private  void  getInfo(){
        SQLiteDatabase db=helper.getReadableDatabase();
        Sales sales;
        Cursor cur = db.rawQuery("select * from "+TABLE1 ,null);
        while (cur.moveToNext()) {
            sales= new Sales();
            sales.setTrans_ID(cur.getString(0));
            sales.setDate(cur.getString(1));
            sales.setDescription(cur.getString(2));
            sales.setAmount(cur.getString(3));
            sales.setPrice(cur.getString(4));
            sales.setTotal(cur.getString(5));
            sales.setNotes(cur.getString(6));
            data.add(sales);
        }
        cur.close();
    }
}
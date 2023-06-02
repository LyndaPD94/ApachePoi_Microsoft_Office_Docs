package com.example.apachepoi;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SalesAdapter extends RecyclerView.Adapter<SalesAdapter.ViewHolder>{
    LayoutInflater inflater;
    ArrayList<Sales> data;
    DB_Helper helper;
    DB_Helper DB_helper;
    private float TotalI;
    private float AmtI;
    private float PriceI;
public SalesAdapter(Context context){this.inflater=LayoutInflater.from(context);}
public SalesAdapter(ArrayList<Sales>data){this.data=data;}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.sales_list,parent,false);
    return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.transid4.setText(data.get(position).getTrans_ID());
        holder.date4.setText(data.get(position).getDate());
        holder.descrip4.setText(data.get(position).getDescription());
        holder.amt4.setText(data.get(position).getAmount());
        holder.price4.setText(data.get(position).getPrice());
        holder.total4.setText(data.get(position).getTotal());
        holder.notes4.setText(data.get(position).getNotes());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        EditText transid4,date4,descrip4,amt4,price4,total4,notes4;
        Button btnupdate;
        DB_Helper helper;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            DB_helper=new DB_Helper(itemView.getContext());
            helper= new DB_Helper(itemView.getContext().getApplicationContext(), "POI.db", null, 1);

            btnupdate=(Button)itemView.findViewById(R.id.btnupdate5) ;
            transid4=(EditText) itemView.findViewById(R.id.transid4);
            date4=(EditText) itemView.findViewById(R.id.date4);
            descrip4=(EditText) itemView.findViewById(R.id.descrip4);
            amt4=(EditText) itemView.findViewById(R.id.amt4);
            price4=(EditText)itemView.findViewById(R.id.price4);
            total4=(EditText) itemView.findViewById(R.id.total4);
            notes4=(EditText) itemView.findViewById(R.id.notes4);

            btnupdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        PriceI= Float.parseFloat(price4.getText().toString());
                        AmtI=Float.parseFloat(amt4.getText().toString());
                        TotalI=PriceI*AmtI;
                        SQLiteDatabase db = helper.getReadableDatabase();
                        ContentValues values = new ContentValues();

                        values.put(Structure_BBDD.COLUMNA2,date4.getText().toString() );
                        values.put(Structure_BBDD.COLUMNA3,descrip4.getText().toString() );
                        values.put(Structure_BBDD.COLUMNA4,amt4.getText().toString() );
                        values.put(Structure_BBDD.COLUMNA5,price4.getText().toString() );
                        values.put(Structure_BBDD.COLUMNA6,TotalI );
                        values.put(Structure_BBDD.COLUMNA7,notes4.getText().toString() );
                        String selection = Structure_BBDD.COLUMNAID + " LIKE ?";
                        String[] selectionArgs = {transid4.getText().toString()};

                        int count = db.update(
                                Structure_BBDD.TABLE1,
                                values,
                                selection,
                                selectionArgs);
                        Toast.makeText(itemView.getContext(), "Register "+transid4.getText()+" was successfully updated.",Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(itemView.getContext(), "ERROR",Toast.LENGTH_LONG).show();
                    }
                }
            });


        }
    }
}

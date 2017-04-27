package manan.chaii;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class mydialog extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner spinner;
    String qnt;
    String productnam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydialog);
        Intent i =getIntent();
        productnam=i.getStringExtra("productnam");
        int productimage=i.getIntExtra("productimage",R.drawable.tea);
        ImageView img=(ImageView) findViewById(R.id.item);
        TextView t=(TextView)findViewById(R.id.itemnam);
        img.setImageResource(productimage);
        t.setText(productnam);
        spinner=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter array=ArrayAdapter.createFromResource(this,R.array.count,android.R.layout.simple_spinner_item);
        spinner.setAdapter(array);
        spinner.setOnItemSelectedListener(this);



    }
    public void ok(View v)
    {

        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
        SharedPreferences sharedpref=getSharedPreferences("items", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=sharedpref.edit();
        qnt = (String) parent.getItemAtPosition(i);
        // Toast.makeText(mydialog.this,productnam,Toast.LENGTH_LONG).show();
        if(productnam.equals("Yellow Tea"))
        {
            edit.putString("Yellow Tea",qnt);
            edit.apply();
            // Toast.makeText(mydialog.this,sharedpref.getString("Yellow Tea",""),Toast.LENGTH_LONG).show();
        }
        else if(productnam.equals("Green Tea"))
        {
            edit.putString("Green Tea",qnt);
            edit.apply();
        }
        else if(productnam.equals("Black Tea"))
        {
            edit.putString("Black Tea",qnt);
            edit.apply();
        }
        else
        {
            edit.putString("Coffee",qnt);
            edit.apply();
        }
        Toast.makeText(mydialog.this,sharedpref.getString(productnam,""),Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

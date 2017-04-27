package manan.chaii;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class buyeraddress extends AppCompatActivity {
    EditText edt;
    String name;
    String address;
    String contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyeraddress);
        edt=(EditText)findViewById(R.id.buyernam1);

        SharedPreferences share=getSharedPreferences("user", Context.MODE_PRIVATE);
        edt.setText(share.getString("username",""));
    }

    public void payment(View v)
    {

        edt=(EditText)findViewById(R.id.buyernam1);
        name=edt.getText().toString();
        edt=(EditText)findViewById(R.id.buyerdel1);
        address=edt.getText().toString();
        edt=(EditText)findViewById(R.id.buyercontact1);
        contact=edt.getText().toString();

        if(name.equals("")||address.equals("")||contact.equals(""))
        {
            Toast.makeText(this,"Please enter valid Name, Address and Contact No. ",Toast.LENGTH_SHORT).show();
        }
        else
        {
            SharedPreferences share=getSharedPreferences("payment",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=share.edit();
            editor.putString("name",name);
            editor.putString("address",address);
            editor.putString("contact_no",contact);
            editor.apply();
            Intent i=new Intent(buyeraddress.this,confirmation.class);
            startActivity(i);
        }
    }
}

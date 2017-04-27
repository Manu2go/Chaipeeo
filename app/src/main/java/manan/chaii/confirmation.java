package manan.chaii;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class confirmation extends AppCompatActivity {
    TextView temp;
    float sum=0;
    float pay;
    int uID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        Resources res=getResources();
        String[] items=res.getStringArray(R.array.products);
        String[] price=res.getStringArray(R.array.price);
        int[] id={R.id.ytq,R.id.gtq,R.id.btq,R.id.cq};
        int[] idp={R.id.ytp,R.id.gtp,R.id.btp,R.id.cp};
        SharedPreferences user = getSharedPreferences("user", Context.MODE_PRIVATE);
        uID=Integer.parseInt(user.getString("uID","0"));
        SharedPreferences shared=getSharedPreferences("items", Context.MODE_PRIVATE);

        for(int i=0;i<4;i++)
        {
            temp = (TextView) findViewById(id[i]);
            String s=shared.getString(items[i], "");
            sum = sum+ ((Float.parseFloat(price[i]))*(Float.parseFloat(s)));
            temp.setText(s);

            temp=(TextView)findViewById(idp[i]);
            temp.setText(price[i]);
        }
        temp = (TextView) findViewById(R.id.amtq);
        temp.setText(Float.toString(sum));


    }

    public void pay(View v)
    {
        SharedPreferences share=getSharedPreferences("user",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=share.edit();
        String s=share.getString("wallet","");
        pay=Float.parseFloat(s);


        if(pay>=sum)
        {
            SharedPreferences payment=getSharedPreferences("payment",Context.MODE_PRIVATE);
            SharedPreferences items=getSharedPreferences("items",Context.MODE_PRIVATE);
            register(payment.getString("name",""),payment.getString("address",""),
                    payment.getString("contact_no",""),items.getString("Yellow Tea",""),
                    items.getString("Green Tea",""),items.getString("Black Tea","")
                    ,items.getString("Coffee",""));
        }
        else
        {
            Toast.makeText(confirmation.this,"Note enough balance in your Wallet",Toast.LENGTH_LONG).show();
        }
    }

    private void register(String name, String address, String contact, String tea,String green_tea,String black_tea,String coffee) {
        final String urlSuffix =URLs.URL_ORDER+"?name="+name+"&address="+address+"&contact="+contact+"&tea="+tea+
                "&greentea="+green_tea+"&blacktea="+black_tea+"&coffee="+coffee+"&uID="+uID;

        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(confirmation.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Log.i("ertt",s);
                Intent i=new Intent(confirmation.this,confirmationdialog.class);
                if(s.equals("1"))
                {
                    i.putExtra("message","Payment Successful");
                    SharedPreferences share=getSharedPreferences("user",Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit=share.edit();
                    edit.putString("wallet",String.valueOf(pay-sum));
                    edit.commit();
                }
                else
                {
                    i.putExtra("message","Payment Unsuccessful");

                }
                startActivity(i);

            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(urlSuffix);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestProperty("Cookie", "__test=9e1a44bec2fef481f4757dd8eb7d5814; expires=Fri, 01-01-38 05:25:55 GMT; path=/");
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String result;

                    result = bufferedReader.readLine();

                    return result;
                }catch(Exception e){
                    return e.toString();
                }
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(urlSuffix);
    }

}

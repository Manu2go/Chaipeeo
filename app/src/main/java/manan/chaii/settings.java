package manan.chaii;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class settings extends AppCompatActivity {

    String currentpswrd;
    String newpswrd;
    String newemail;
    String REGISTER_URL;

    TextView crntpswrd;
    TextView pswrd;
    TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        crntpswrd=(TextView)findViewById(R.id.currentpswrd);
        pswrd=(TextView)findViewById(R.id.newpswrd);
        email=(TextView)findViewById(R.id.newemail);

    }

    public void confirm(View v)
    {
        SharedPreferences shared =getSharedPreferences("user",Context.MODE_PRIVATE);
        String user=shared.getString("username","");
        if(v.getId()==R.id.pswrdchng_button)
        {
            REGISTER_URL=URLs.URL_CHANGE_PASSWORD;
            register(crntpswrd.getText().toString(),pswrd.getText().toString(),user);
        }
        else
        {
            REGISTER_URL=URLs.URL_CHANGE_EMAILID;
            register(null,email.getText().toString(),user);
        }

    }

    private void register(String a1, String a2,String a3) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            login11 ruc = new login11();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                if(s.equals("1"))
                {
                    Toast.makeText(settings.this,"Changes Made Successfuly",Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(settings.this,"Change Operation Unsuccessful",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> data = new HashMap<String, String>();

                if(params[0]!=null)
                {
                    data.put("crntpswrd", params[0]);
                    data.put("nwpswrd", params[1]);
                }
                else
                {
                    data.put("nwemail",params[1]);
                }
                data.put("username",params[2]);

                String result = ruc.sendPostRequest(REGISTER_URL, data);
                return result;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(a1, a2,a3);
    }


}

package manan.chaii;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class login1 extends AppCompatActivity implements View.OnClickListener {

    private EditText Usrnam;
    private EditText Psswrd;

    private Button signin;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        Psswrd = (EditText) findViewById(R.id.psswrd);
        Usrnam = (EditText) findViewById(R.id.usrnam);
        signin = (Button) findViewById(R.id.signin_button);

        signin.setOnClickListener(this);
    }

    public void signup(View v) {
        Intent i = new Intent(login1.this, login.class);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        if (v == signin) {
            registerUser();

        }
    }

    private void registerUser() {

        String name = Usrnam.getText().toString();
        String passwrd = Psswrd.getText().toString();


        register(name, passwrd);
    }

    private void register(final String username, String password) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            manan.chaii.login11 ruc = new manan.chaii.login11();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(login1.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                try {
                    JSONObject res = new JSONObject(s);
                    String error=res.getString("error");
                    if(error.equals("false")){
                        finish();
                        Intent a = new Intent(login1.this, order.class);

                        SharedPreferences user = getSharedPreferences("user", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = user.edit();
                        edit.putString("username", res.getString("name"));
                        edit.putString("wallet", res.getString("money"));
                        edit.putString("uID", res.getString("id"));
                        edit.putString("login_status","1");
                        edit.commit();

                        //  Toast.makeText(login1.this,words[0]+" "+words[1],Toast.LENGTH_LONG).show();
                        startActivity(a);

                    }
                    else if(error.equals("true")){
                        Toast.makeText(login1.this, "Login unsuccessful!", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(login1.this, "Login unsuccessful!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                    Log.i("ert",e.toString());
                }
            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String, String>();
                data.put("username", params[0]);
                data.put("password", params[1]);


                String result = ruc.sendPostRequest(URLs.URL_LOGIN, data);
                Log.i("eru",result);
                return result;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(username, password);
    }
}
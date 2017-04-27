package manan.chaii;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

//import manan.chaii.R;

public class confirmationactivity extends AppCompatActivity {

   /* String paymentid;
    String paymentstatus;
    String paymentamount;*/
    TextView temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmationactivity);

        SharedPreferences share = getSharedPreferences("user", Context.MODE_PRIVATE);
        temp = (TextView) findViewById(R.id.paymentId);
        temp.setText(share.getString("paymentid", ""));
        temp = (TextView) findViewById(R.id.paymentStatus);
        temp.setText(share.getString("paymentstatus", ""));
        temp = (TextView) findViewById(R.id.paymentAmount);
        temp.setText(share.getString("wallet", ""));

    }
}
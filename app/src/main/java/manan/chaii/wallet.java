package manan.chaii;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.payUMoney.sdk.PayUmoneySdkInitilizer;

import org.json.JSONException;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.payUMoney.sdk.PayUmoneySdkInitilizer;
import com.payUMoney.sdk.SdkCobbocEvent;
import com.payUMoney.sdk.SdkConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


import java.math.BigDecimal;

//Implementing click listener to our class
public class wallet extends AppCompatActivity implements View.OnClickListener {

        //The views
        private Button buttonPay;
        private EditText editTextAmount;
    public static final String TAG = "PayUMoneySDK Sample";

        //Payment Amount
        private float amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        buttonPay = (Button) findViewById(R.id.pay);
        editTextAmount = (EditText) findViewById(R.id.amount);

        buttonPay.setOnClickListener(this);
       // Intent intent = new Intent(this, PayPalService.class);

       // intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        //startService(intent);
    }
    @Override
    public void onClick(View v) {
        if(editTextAmount.getText().toString().equals("")){
            Toast.makeText(this,"Please enter amount",Toast.LENGTH_LONG).show();
        }
        else {
            getPayment();
        }
    }

   /* @Override
    public void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

    //Paypal intent request code to track onActivityResult method
    public static final int PAYPAL_REQUEST_CODE = 123;


    //Paypal Configuration Object
  /*  private static PayPalConfiguration config = new PayPalConfiguration()
            // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
            // or live (ENVIRONMENT_PRODUCTION)
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(PayPalConfig.PAYPAL_CLIENT_ID);*/


    private String getTxnId() {
        return ("0nf7" + System.currentTimeMillis());
    }

    private void getPayment()
    {
        amount=Float.parseFloat(editTextAmount.getText().toString());
        //Getting the amount from editText
        PayUmoneySdkInitilizer.PaymentParam.Builder builder = new PayUmoneySdkInitilizer.PaymentParam.Builder();

       /* builder.setAmount(amount)
                .setTnxId(getTxnId())
                .setPhone("8952826431")
                .setProductName("chaii")
                .setFirstName("Manan")
                .setEmail("agamanan96@gmail.com")
                .setsUrl("https://www.payumoney.com/mobileapp/payumoney/success.php")
                .setfUrl("https://www.payumoney.com/mobileapp/payumoney/failure.php")
                .setUdf1("")
                .setUdf2("")
                .setUdf3("")
                .setUdf4("")
                .setUdf5("")
                .setIsDebug(true)
                .setKey("0aBmJ96F")
                .setMerchantId("5660411 ");// Debug Merchant ID*/
        builder.setAmount(amount)
                .setTnxId(getTxnId())
                .setPhone("8882434664")
                .setProductName("product_name")
                .setFirstName("piyush")
                .setEmail("piyush.jain@payu.in")
                .setsUrl("https://www.payumoney.com/mobileapp/payumoney/success.php")
                .setfUrl("https://www.payumoney.com/mobileapp/payumoney/failure.php")
                .setUdf1("")
                .setUdf2("")
                .setUdf3("")
                .setUdf4("")
                .setUdf5("")
                .setIsDebug(true)
                .setKey("dRQuiA")
                .setMerchantId("4928174");
        PayUmoneySdkInitilizer.PaymentParam paymentParam = builder.build();

        String serverCalculatedHash="9f1ce50ba8995e970a23c33e665a990e648df8de3baf64a33e19815acd402275617a16041e421cfa10b7532369f5f12725c7fcf69e8d10da64c59087008590fc";
        paymentParam.setMerchantHash(serverCalculatedHash);
        PayUmoneySdkInitilizer.startPaymentActivityForResult(this, paymentParam);

    }

    public static String hashCal(String str) {
        byte[] hashseq = str.getBytes();
        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-512");
            algorithm.reset();
            algorithm.update(hashseq);
            byte messageDigest[] = algorithm.digest();
            for (byte aMessageDigest : messageDigest) {
                String hex = Integer.toHexString(0xFF & aMessageDigest);
                if (hex.length() == 1) {
                    hexString.append("0");
                }
                hexString.append(hex);
            }
        } catch (NoSuchAlgorithmException ignored) {
        }
        return hexString.toString();
    }

    private void calculateServerSideHashAndInitiatePayment(final PayUmoneySdkInitilizer.PaymentParam paymentParam) {

        // Replace your server side hash generator API URL
        String url = "https://test.payumoney.com/payment/op/calculateHashForTest";

        Toast.makeText(this, "Please wait... Generating hash from server ... ", Toast.LENGTH_LONG).show();
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.has(SdkConstants.STATUS)) {
                      //  Log.d("Filter","Inside if statement");
                        String status = jsonObject.optString(SdkConstants.STATUS);
                      //  Log.d("Filter","Status: "+status);
                        if (status != null || status.equals("1")) {


                            String hash = jsonObject.getString(SdkConstants.RESULT);
                            Log.i("app_activity", "Server calculated Hash :  " + hash);

                            paymentParam.setMerchantHash(hash);

                            PayUmoneySdkInitilizer.startPaymentActivityForResult(wallet.this, paymentParam);
                        } else {
                            Toast.makeText(wallet.this,
                                    jsonObject.getString(SdkConstants.RESULT),
                                    Toast.LENGTH_SHORT).show();
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                if (error instanceof NoConnectionError) {
                    Toast.makeText(wallet.this,
                            wallet.this.getString(R.string.connect_to_internet),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(wallet.this,
                            error.getMessage(),
                            Toast.LENGTH_SHORT).show();

                }

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return paymentParam.getParams();
            }
        };
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PayUmoneySdkInitilizer.PAYU_SDK_PAYMENT_REQUEST_CODE) {

            /*if(data != null && data.hasExtra("result")){
              String responsePayUmoney = data.getStringExtra("result");
                if(SdkHelper.checkForValidString(responsePayUmoney))
                    showDialogMessage(responsePayUmoney);
            } else {
                showDialogMessage("Unable to get Status of Payment");
            }*/


            if (resultCode == RESULT_OK) {
                Log.i(TAG, "Success - Payment ID : " + data.getStringExtra(SdkConstants.PAYMENT_ID));
                String paymentId = data.getStringExtra(SdkConstants.PAYMENT_ID);
                showDialogMessage("Payment Success Id : " + paymentId);
                SharedPreferences share=getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit=share.edit();
                edit.putString("wallet",editTextAmount.getText().toString());
                edit.putString("paymentid",paymentId);
                edit.putString("paymentstatus","Result_OK");
                edit.apply();
                Intent i=new Intent(wallet.this,confirmationactivity.class);
                startActivity(i);
            } else if (resultCode == RESULT_CANCELED) {
                Log.i(TAG, "failure");
                showDialogMessage("cancelled");
            } else if (resultCode == PayUmoneySdkInitilizer.RESULT_FAILED) {
                Log.i("app_activity", "failure");

                if (data != null) {
                    if (data.getStringExtra(SdkConstants.RESULT).equals("cancel")) {

                    } else {
                        showDialogMessage("failure");
                    }
                }
                //Write your code if there's no result
            } else if (resultCode == PayUmoneySdkInitilizer.RESULT_BACK) {
                Log.i(TAG, "User returned without login");
                showDialogMessage("User returned without login");
            }
        }
    }

    private void showDialogMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(TAG);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }
}

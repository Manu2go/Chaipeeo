package manan.chaii;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {
    private ImageView splashImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences user = getSharedPreferences("user", Context.MODE_PRIVATE);
        if(user.getString("login_status","0").equals("1")){
            finish();
            Intent i=new Intent(this, manan.chaii.order.class);
            startActivity(i);
        }
        else {
            splashImageView = new ImageView(this);
            splashImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            splashImageView.setImageResource(R.drawable.index);
            setContentView(splashImageView);

            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                public void run() {
                    change();

                }

            }, 3000);
        }

    }
    public void change(){

        Intent i=new Intent(this, manan.chaii.login1.class);
        finish();
        startActivity(i);
    }

}
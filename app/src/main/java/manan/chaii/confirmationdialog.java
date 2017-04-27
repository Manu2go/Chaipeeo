package manan.chaii;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class confirmationdialog extends AppCompatActivity implements View.OnClickListener{

    TextView text;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmationdialog);
        text=(TextView)findViewById(R.id.message);
        button=(Button)findViewById(R.id.messagebtn);
        Intent i=getIntent();
        String s=i.getStringExtra("message");
        text.setText(s);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(text.getText().toString().equals("Payment Successful")){
            Intent i =new Intent(confirmationdialog.this,order.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK );
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
            startActivity(i);
        }
        else{
            finish();
        }
    }
}

package pwr.wppt.inf.kalkulator;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.view.View.OnClickListener;

public class log_in extends Activity implements OnClickListener{
	TextView tv1;
	TextView tv2;
	View b1;
	EditText login;
	EditText password;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        tv1=(TextView) findViewById(R.id.textView1);
        tv2=(TextView) findViewById(R.id.textView2);
        b1=(Button) findViewById(R.id.button1);
        tv1.setTextColor(0xFFFF9900);
        tv2.setTextColor(0xFFFF9900);
        b1.setOnClickListener(this);
        
    }

	@Override
	public void onClick(View v) {
		Intent i;
		i=new Intent(this,calculate.class);
		startActivity(i);
	}
}

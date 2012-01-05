package pwr.wppt.inf.kalkulator;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class register extends Activity {
	TextView tv1;
	TextView tv2;
	TextView tv3;
	Button b1;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        
        tv1=(TextView) findViewById(R.id.textView1);
        tv2=(TextView) findViewById(R.id.textView2);
        tv3=(TextView) findViewById(R.id.textView3);
        b1=(Button) findViewById(R.id.button1);
        
        tv1.setTextColor(0xFFFF9900);
        tv2.setTextColor(0xFFFF9900);
        tv3.setTextColor(0xFFFF9900);
        b1.setTextColor(0xFFFF9900);

    }
}

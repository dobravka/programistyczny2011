package pwr.wppt.inf.kalkulator;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;


public class Kalkulator extends Activity implements OnClickListener {
	private View login;
	private View register;
	private final int REQ_CODE=1;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        login= findViewById(R.id.button1);
        login.setOnClickListener(this);
        register = findViewById(R.id.button2);
        register.setOnClickListener(this);


    }
    
    public void onClick(View v) {
    	Intent i;
    	switch(v.getId()){
    	case R.id.button1:
    		i=new Intent(this,log_in.class);
    		startActivity(i);
    		break;
    	case R.id.button2:
    		i=new Intent(this,register.class);
    		startActivity(i);
    		break;
    	}

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE && resultCode == RESULT_OK) {
          
        }
    }
    
    @Override
    public void onDestroy(){
    	
    	super.onDestroy();
    }


}
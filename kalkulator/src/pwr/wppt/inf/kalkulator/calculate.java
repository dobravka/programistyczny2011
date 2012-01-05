package pwr.wppt.inf.kalkulator;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

public class calculate extends Activity implements OnClickListener {

	View show_map;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calculate);
		show_map=findViewById(R.id.button1);
		show_map.setOnClickListener(this);
		
	}
	
	
	@Override
	public void onClick(View v) {
		
		Intent i;
		i=new Intent(this,Map.class);
		startActivity(i);
		
	}

}

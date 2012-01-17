package pwr.wppt.inf.kalkulator;

import android.app.Activity;
import android.location.Location;
import android.os.*;
import android.content.*;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class calculate extends Activity implements OnClickListener {
	private myService myService; //bound service instance
	private boolean serviceStarted;
	View show_map;
	View data;
	View start;
	View stop;
	public TextView tv;
	private Location loc;
	private boolean initiated=false;
	private float distance=0;

	
    //What's going on when we create this Activity
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calculate);
		tv=(TextView)findViewById(R.id.textView1);
		show_map=findViewById(R.id.button1);
		show_map.setOnClickListener(this);
		data=findViewById(R.id.button2);
		data.setOnClickListener(this);
		start=findViewById(R.id.button3);
		start.setOnClickListener(this);
		stop=findViewById(R.id.button4);
		stop.setVisibility(View.INVISIBLE);
		stop.setOnClickListener(this);

		
	}
	
	//What's going on when we destroy this Activity
	public void onDestroy(){
		super.onDestroy();
	}
	
	//Click Listener dispatch mechanism
	@Override
	public void onClick(View v) {
		Intent i;
    	switch(v.getId()){
    	case R.id.button1:
    		i=new Intent(this,Map.class);
    		startActivity(i);
    		break;
    	case R.id.button2:
    		i=new Intent(this,data.class);
    		startActivity(i);
    		break;
    	case R.id.button3:
    		startService();

    		break;
    	case R.id.button4:
    		stopService();
    		break;
    	}

	}
	
	//connection between this activity and service myService
	ServiceConnection myServConn = new ServiceConnection() {
	    @Override
	    public void onServiceDisconnected(ComponentName arg0) {
	        myService = null;
	    }
	    @Override
	    public void onServiceConnected(ComponentName arg0, IBinder binder) {
	        myService = ((myService.MyBinder)binder).getMyService();
	    }
	};
	
	//What's going on when we start our service
	private void startService() {
	    Intent intent = new Intent(this, myService.class); 
	    startService(intent);
	    //Bind MyService here
	    bindService(intent, myServConn, BIND_AUTO_CREATE);
		stop.setVisibility(View.VISIBLE);
	    serviceStarted = true;
	    

	    

	    //new thread
	    new Thread(new Runnable()
        {
	    	float temp;
	    	Location begin;
            @Override
            public void run()
            {
                try
                {

        	    	

        	    	while(!initiated){
        	    		try{

        		    		loc=myService.getLocation();

        	    		}
        	    		catch(Exception e){

        	    		}

        	    		if(loc!=null){
        	    			begin=loc;
        	    			initiated=true;
        	    			toast("zadzialalo");
        	    		}
        	    		
        	    	}
        	        while(true){
        	        	loc=myService.getLocation();
        	        	temp=begin.distanceTo(loc);
        	        	distance=distance+temp;
        	        	Message msg=new Message();
        	        	msg.obj=Float.toString((distance/1000));
        	        	msg.what=SUCCESS;
                        handler.sendMessage(msg);
        	        	begin=loc;
        	        	try {
        					Thread.sleep(500);
        				} catch (InterruptedException e) {
        					e.printStackTrace();
        				}
        				
        	        }

                }
                catch (Exception e)
                {
                    handler.sendEmptyMessage(FAILURE);
                }
            }
        }).start();
	    
	    
	    

	}
	
	//What's going on when we stop our service
	private void stopService() {
	    if(serviceStarted) {
	        Intent intent = new Intent(this, myService.class);
	        //Unbind MyService here
	        unbindService(myServConn);
	        stopService(intent);
    		stop.setVisibility(View.INVISIBLE);

	        serviceStarted = false;
	    }
	}
	
	//making toast method
	private void toast(String text){
		Message msg = new Message();
		msg.what=TOAST;
		msg.obj=text;
		handler.sendMessage(msg);
	}
    
	//new handler   
	//it has been created for setting text into TextView tv from main thread.
    private final int SUCCESS=0;
    private final int FAILURE=1;
    private final int TOAST=2;
    
    protected Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            if (msg.what == SUCCESS)
            {
            	tv.setText("przejechales "+(String)msg.obj+" km");
            }
            else if (msg.what == FAILURE)
            {
                System.err.println("something went wrong in side thread");
            }
            else if (msg.what == TOAST){
            	Toast.makeText(getApplicationContext(), (String)msg.obj, Toast.LENGTH_SHORT).show();
            }
        }
    };
	
}



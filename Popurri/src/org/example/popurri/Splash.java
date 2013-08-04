package org.example.popurri;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		Thread timer = new Thread(){
			@Override
			public void run() {
				
				try{
					sleep(5000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					finish();
					Intent i = new Intent(Splash.this,Principal.class);
					startActivity(i);
				}
			}
		};
		timer.start();
	}
}

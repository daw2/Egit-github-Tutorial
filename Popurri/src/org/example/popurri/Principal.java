package org.example.popurri;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Principal extends Activity {
	
	private ProgressDialog _progressDialog;
	private int _progress = 0;
	private Handler _progresHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button btn = (Button) findViewById(R.id.btnDialogo);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showDialog(0);
				
			}
		});
		
		Button btn2 = (Button) findViewById(R.id.btnDialogoProgresion);
		btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showDialog(1);
				_progress = 0;
				_progressDialog.setProgress(0);
				_progresHandler.sendEmptyMessage(0);	
				
				
			}
		});
		_progresHandler = new Handler(){
			public void handleMessage(Message msg){
				super.handleMessage(msg);
				if(_progress >= 100){
					_progressDialog.dismiss();
				}else{
					_progress++;
					_progressDialog.incrementProgressBy(1);
					_progresHandler.sendEmptyMessageDelayed(0, 100);
				}
			}
		};
		
		Button btn3 = (Button)findViewById(R.id.btnSalir);
		btn3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showDialog(2);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}
	
	@Override
	protected Dialog onCreateDialog(int id){
		switch(id){
		case 0:
			return new AlertDialog.Builder(this)
			.setIcon(R.drawable.ic_launcher)
			.setTitle("Esto es un diálogo simple")
			.setPositiveButton("OK", new
					DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(getBaseContext(), "OK clicked!", Toast.LENGTH_SHORT).show();	
						}
					})
			.setNegativeButton("Cancel", new
					DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(getBaseContext(), "Cancel clicked!", Toast.LENGTH_SHORT).show();
							
						}
					})
		.create();	
		case 1:
			_progressDialog = new ProgressDialog(this);
			_progressDialog.setIcon(R.drawable.ic_launcher);
			_progressDialog.setTitle("Descargando archivo...");
			_progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			_progressDialog.setButton(DialogInterface.BUTTON_POSITIVE ,"OK", new 
				DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(getBaseContext(), "OK clicked!", Toast.LENGTH_SHORT).show();
						
					}
				}	);
			_progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new
					DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(getBaseContext(), "Cancel clicked!", Toast.LENGTH_SHORT).show();
							
						}
					});
			return _progressDialog;
		case 2:
			return new AlertDialog.Builder(this)
			.setIcon(R.drawable.ic_launcher)
			.setTitle("¿Estás seguro que quieres salir de la aplicación?")
			.setPositiveButton("Salir", new
					DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Principal.this.finish();
							
						}
					})
			.setNegativeButton("Cancelar", null)
			.show();
				
		}
		return null;
	}

}

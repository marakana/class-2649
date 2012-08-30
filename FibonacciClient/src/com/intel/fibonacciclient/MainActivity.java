package com.intel.fibonacciclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.intel.fibonaccicommon.IFibonacciService;

public class MainActivity extends Activity {
	EditText editN;
	TextView textOut;
	IFibonacciService service;

	static final Intent INTENT = new Intent("com.intel.fibonaccicommon.IFibonacciService");
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editN = (EditText) findViewById(R.id.edit_n);
		textOut = (TextView) findViewById(R.id.text_out);
		
		bindService(INTENT, new FibonacciServiceConnection(), Context.BIND_AUTO_CREATE);
	}

	class FibonacciServiceConnection implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder binder) {
			service = IFibonacciService.Stub.asInterface(binder);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			service = null;
		}	
	}
	
	static final int LOOP = 10;

	/** Called when button_go is clicked on. 
	 * @throws RemoteException */
	public void onClickButtonGo(View v) throws RemoteException {
		
		// Assert we have a service
		if(service==null) return;
		
		long n = Long.parseLong(editN.getText().toString());

		long resultJ = 0, resultN = 0;
		long start = System.nanoTime();
		for (int i = 0; i < LOOP; i++) {
			resultJ = service.fibJI(n);
		}
		long timeJ = System.nanoTime() - start;
		textOut.append(String.format("\nfibJI(%d)=%d (~%d ns)", n, resultJ,
				timeJ / LOOP));

		start = System.nanoTime();
		for (int i = 0; i < LOOP; i++) {
			resultN = service.fibNI(n);
		}
		long timeN = System.nanoTime() - start;
		textOut.append(String.format("\nfibNI(%d)=%d (~%d ns)", n, resultN,
				timeN / LOOP));

	}

}

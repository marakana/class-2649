package com.intel.fibonacciclient;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.intel.fibonaccicommon.FibonacciManager;

public class MainActivity extends Activity {
	EditText editN;
	TextView textOut;
	FibonacciManager manager;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
 
		editN = (EditText) findViewById(R.id.edit_n);
		textOut = (TextView) findViewById(R.id.text_out);
		
		manager = new FibonacciManager(this);
	}

	static final int LOOP = 10;

	/** Called when button_go is clicked on. 
	 * @throws RemoteException */
	public void onClickButtonGo(View v) {		
		long n = Long.parseLong(editN.getText().toString());

		long resultJ = 0, resultN = 0;
		long start = System.nanoTime();
		for (int i = 0; i < LOOP; i++) {
			resultJ = manager.fibJI(n);
		}
		long timeJ = System.nanoTime() - start;
		textOut.append(String.format("\nfibJI(%d)=%d (~%d ns)", n, resultJ,
				timeJ / LOOP));

		start = System.nanoTime();
		for (int i = 0; i < LOOP; i++) {
			resultN = manager.fibNI(n);
		}
		long timeN = System.nanoTime() - start;
		textOut.append(String.format("\nfibNI(%d)=%d (~%d ns)", n, resultN,
				timeN / LOOP));

	}

}

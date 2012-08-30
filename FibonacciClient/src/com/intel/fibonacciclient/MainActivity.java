package com.intel.fibonacciclient;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.intel.fibonaccicommon.FibonacciManager;
import com.intel.fibonaccicommon.Request;
import com.intel.fibonaccicommon.Response;

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

	/**
	 * Called when button_go is clicked on.
	 * 
	 * @throws RemoteException
	 */
	public void onClickButtonGo(View v) {
		long n = Long.parseLong(editN.getText().toString());

		Response responseJ, responseN;

		// Java
		responseJ = manager.fib( new Request(Request.ALGORITHM_JAVA_RECURSIVE, n) );
		textOut.append(String.format("\nfibJ(%d)=%d (~%d ms)", n, responseJ.getResult(),
				responseJ.getTime()));

		// Native
		responseN = manager.fib( new Request(Request.ALGORITHM_NATIVE_RECURSIVE, n) );
		textOut.append(String.format("\nfibN(%d)=%d (~%d ms)", n, responseN.getResult(),
				responseN.getTime()));

	}

}

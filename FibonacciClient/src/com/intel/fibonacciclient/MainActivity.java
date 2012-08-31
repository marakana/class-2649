package com.intel.fibonacciclient;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.intel.fibonaccicommon.FibonacciManager;
import com.intel.fibonaccicommon.IFibListener;
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

		IFibListener listener = new IFibListenerImpl();
		manager.asyncFib(new Request(Request.ALGORITHM_JAVA_RECURSIVE, n),
				listener);
	}

	class IFibListenerImpl extends IFibListener.Stub {
		
		@Override
		public void onResponse(Response response) throws RemoteException {
			final Response r = response;
			MainActivity.this.runOnUiThread( new Runnable() {

				@Override
				public void run() {
					textOut.append(String.format("\nfib()=%d (~%d ms)",
							r.getResult(), r.getTime()));
				}
				
			});
		}
	}
}

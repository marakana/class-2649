package com.intel.fibonacciclient;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.intel.fibonaccicommon.FibonacciManager;
import com.intel.fibonaccicommon.IFibListener;
import com.intel.fibonaccicommon.Request;
import com.intel.fibonaccicommon.Response;

public class MainActivity extends Activity {
	static EditText editN;
	static TextView textOut;
	FibonacciManager manager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editN = (EditText) findViewById(R.id.edit_n);
		textOut = (TextView) findViewById(R.id.text_out);

		manager = new FibonacciManager(this);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		manager.init();
	}

	@Override
	protected void onStop() {
		super.onDestroy();
		manager.close();
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

	static Handler responseHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what!=MSG_CODE) return;
			Response r = (Response) msg.obj;
			textOut.append(String.format("\nfib()=%d (~%d ms)", r.getResult(),
					r.getTime()));
		}
	};

	static final int MSG_CODE = 47;
	class IFibListenerImpl extends IFibListener.Stub {

		@Override
		public void onResponse(Response response) throws RemoteException {
			Message msg = responseHandler.obtainMessage(MSG_CODE, response);
			responseHandler.sendMessage(msg);
		}
	}
}

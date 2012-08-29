package com.intel.fibonacci;

import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	EditText editN;
	TextView textOut;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Debug.startMethodTracing("Fibonacci.trace");
		
		setContentView(R.layout.activity_main);

		editN = (EditText) findViewById(R.id.edit_n);
		textOut = (TextView) findViewById(R.id.text_out);
	}

	@Override
	protected void onStop() {
		super.onStop();
		Debug.stopMethodTracing();
	}

	static final int LOOP = 10;

	/** Called when button_go is clicked on. */
	public void onClickButtonGo(View v) {
		long n = Long.parseLong(editN.getText().toString());

		long resultJ = 0, resultN = 0;
		long start = System.currentTimeMillis();
		for (int i = 0; i < LOOP; i++) {
			resultJ = FibLib.fibJ(n);
		}
		long timeJ = System.currentTimeMillis() - start;
		textOut.append(String.format("\nfibJ(%d)=%d (~%d ms)", n, resultJ,
				timeJ / LOOP));

		start = System.currentTimeMillis();
		for (int i = 0; i < LOOP; i++) {
			resultN = FibLib.fibN(n);
		}
		long timeN = System.currentTimeMillis() - start;
		textOut.append(String.format("\nfibN(%d)=%d (~%d ms)", n, resultN,
				timeN / LOOP));

	}

}

package com.intel.fibonacciservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class FibonacciService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return new IFibonacciServiceImpl();
	}

}

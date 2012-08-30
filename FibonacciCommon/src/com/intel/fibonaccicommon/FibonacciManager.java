package com.intel.fibonaccicommon;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

public class FibonacciManager {
	static final Intent INTENT = new Intent("com.intel.fibonaccicommon.IFibonacciService");
	Context context;
	IFibonacciService service;
	FibonacciServiceConnection connection;

	/** Constructor */
	public FibonacciManager(Context context) {
		this.context = context;
		connection = new FibonacciServiceConnection();
		context.bindService(INTENT, connection, Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		
		context.unbindService(connection);
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
	
	
	// --- Proxy calls ---
	
	public long fibJ(long n) {
		try {
			return service.fibJ(n);
		} catch (RemoteException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public long fibJI(long n) {
		try {
			return service.fibJI(n);
		} catch (RemoteException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public long fibN(long n) {
		try {
			return service.fibN(n);
		} catch (RemoteException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public long fibNI(long n) {
		try {
			return service.fibNI(n);
		} catch (RemoteException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public Response fib(Request request) {
		try {
			return service.fib(request);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

}

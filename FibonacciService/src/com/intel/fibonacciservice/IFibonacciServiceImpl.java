package com.intel.fibonacciservice;

import android.os.RemoteException;
import android.util.Log;

import com.intel.fibonaccicommon.IFibListener;
import com.intel.fibonaccicommon.IFibonacciService;
import com.intel.fibonaccicommon.Request;
import com.intel.fibonaccicommon.Response;

public class IFibonacciServiceImpl extends IFibonacciService.Stub {

	@Override
	public long fibJ(long n) throws RemoteException {
		return FibLib.fibJ(n);
	}

	@Override
	public long fibJI(long n) throws RemoteException {
		return FibLib.fibJI(n);
	}

	@Override
	public long fibN(long n) throws RemoteException {
		return FibLib.fibN(n);
	}

	@Override
	public long fibNI(long n) throws RemoteException {
		return FibLib.fibNI(n);
	}

	@Override
	public Response fib(Request request) throws RemoteException {
		long result = -1;
		long time = System.currentTimeMillis();
		
		switch(request.getAlgoritm()) {
		case Request.ALGORITHM_JAVA_RECURSIVE:
			result = FibLib.fibJ(request.getN()); break;
		case Request.ALGORITHM_JAVA_ITERATIVE:
			result = FibLib.fibJI(request.getN()); break;
		case Request.ALGORITHM_NATIVE_RECURSIVE:
			result = FibLib.fibN(request.getN()); break;
		case Request.ALGORITHM_NATIVE_ITERATIVE:
			result = FibLib.fibNI(request.getN()); break;
		}
		
		time = System.currentTimeMillis() - time;

		return new Response(time, result);
	}

	@Override
	public void asyncFib(Request request, IFibListener listener)
			throws RemoteException {
		Response response = this.fib(request);
		try {
			listener.onResponse(response);
		} catch (Exception e) {
			Log.e("IFibSeviceImpl", "Looks like the client is gone!", e);
			e.printStackTrace();
		}
	}

}

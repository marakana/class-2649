package com.intel.fibonacciservice;

import android.os.RemoteException;

import com.intel.fibonaccicommon.IFibonacciService;

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

}

package com.intel.fibonaccicommon;

import com.intel.fibonaccicommon.Request;
import com.intel.fibonaccicommon.Response;
import com.intel.fibonaccicommon.IFibListener;

interface IFibonacciService {
	long fibJ(long n);
	long fibJI(long n);
	long fibN(long n);
	long fibNI(long n);
	
	Response fib(in Request request);
	oneway void asyncFib(in Request request, in IFibListener listener);
}
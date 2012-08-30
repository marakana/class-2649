package com.intel.fibonaccicommon;

import com.intel.fibonaccicommon.Request;

interface IFibonacciService {
	long fibJ(long n);
	long fibJI(long n);
	long fibN(long n);
	long fibNI(long n);
	
	long fib(in Request request);
}
package com.intel.fibonaccicommon;

import com.intel.fibonaccicommon.Response;

oneway interface IFibListener {
	void onResponse(in Response response);
}
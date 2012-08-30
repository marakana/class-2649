package com.intel.fibonaccicommon;

import android.os.Parcel;
import android.os.Parcelable;

public class Request implements Parcelable {
	private int algoritm;
	private long n;
	
	public Request(int algoritm, long n) {
		super();
		this.algoritm = algoritm;
		this.n = n;
	}

	// --- Parcelable stuff ---
	
	public Request(Parcel source){
		super();
		this.algoritm = source.readInt();
		this.n = source.readLong();
	}
	
	public static final Parcelable.Creator<Request> CREATOR = new Parcelable.Creator<Request>() {

		@Override
		public Request createFromParcel(Parcel source) {
			return new Request(source);
		}

		@Override
		public Request[] newArray(int size) {
			return new Request[size];
		}
	};
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(algoritm);
		dest.writeLong(n);
	}

	// --- Setters and getters ---
	
	public int getAlgoritm() {
		return algoritm;
	}

	public void setAlgoritm(int algoritm) {
		this.algoritm = algoritm;
	}

	public long getN() {
		return n;
	}

	public void setN(long n) {
		this.n = n;
	}


	
}

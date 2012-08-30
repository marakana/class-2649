package com.intel.fibonaccicommon;

import android.os.Parcel;
import android.os.Parcelable;

public class Response implements Parcelable {
	private long time;
	private long result;
	
	public Response(long time, long result) {
		super();
		this.time = time;
		this.result = result;
	}

	// --- Parcelable stuff ---
	
	
	public Response(Parcel source){
		super();
		this.time = source.readLong();
		this.result = source.readLong();
	}
	
	public static final Parcelable.Creator<Response> CREATOR = new Parcelable.Creator<Response>() {

		@Override
		public Response createFromParcel(Parcel source) {
			return new Response(source);
		}

		@Override
		public Response[] newArray(int size) {
			return new Response[size];
		}
	};
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(time);
		dest.writeLong(result);
	}
	
	// --- Setters and getters
	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long getResult() {
		return result;
	}

	public void setResult(long result) {
		this.result = result;
	}
}

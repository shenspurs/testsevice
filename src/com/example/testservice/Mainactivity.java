package com.example.testservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class Mainactivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Intent intent = new Intent(this, ServiceDemo.class);
		startActivity(intent);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

package com.intel.fibonacci;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	EditText editN;
	TextView textOut;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        editN = (EditText) findViewById(R.id.edit_n);
        textOut = (TextView) findViewById(R.id.text_out);
    }
    
    /** Called when button_go is clicked on. */
    public void onClickButtonGo(View v) {
    		long n = Long.parseLong( editN.getText().toString() );
    		
    		long resultJ = FibLib.fibJ(n);
    		textOut.append( String.format("\nfibJ(%d)=%d", n, resultJ) );
    		
    		long resultN = FibLib.fibN(n);
    		textOut.append( String.format("\nfibN(%d)=%d", n, resultN) );

    }

}

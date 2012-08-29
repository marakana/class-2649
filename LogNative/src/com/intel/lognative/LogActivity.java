package com.intel.lognative;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LogActivity extends Activity {
	EditText editTag;
	EditText editMsg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        
        editTag = (EditText) findViewById(R.id.edit_tag);
        editMsg = (EditText) findViewById(R.id.edit_msg);
    }

    public void onClickLog(View v) {
    		String tag = editTag.getText().toString();
    		String msg = editMsg.getText().toString();
    		LogLib.log(LogLib.DEBUG, tag, msg);
    }
}

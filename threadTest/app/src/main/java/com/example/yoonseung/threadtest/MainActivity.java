package com.example.yoonseung.threadtest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.content.Intent; // yoon // for service module
import android.widget.Toast;
import android.app.Activity;


public class MainActivity extends ActionBarActivity {

    Intent globalService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // yoon // add bgn ...

        globalService = new Intent(this,GlobalTouchService.class);

        Button bgnThread = (Button) findViewById(R.id.button_RunThread);

        bgnThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Button clkedBtn = (Button) v;
                Intent intent = new Intent(MainActivity.this, runThread.class);

                if (clkedBtn.getText() == "Thread Bgn") {
                    clkedBtn.setText("Thread End");
                    startService(intent);
                }
                else {
                    clkedBtn.setText("Thread Bgn");
                    stopService(intent);
                }


            }
        });

        // yoon // add ... end

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // yoon // add
    public void buttonClicked(View v){

        if(v.getTag() == null){
            startService(globalService);
            v.setTag("on");
            Toast.makeText(this, "Start Service", Toast.LENGTH_SHORT).show();
        }
        else{
            stopService(globalService);
            v.setTag(null);
            Toast.makeText(this, "Stop Service", Toast.LENGTH_SHORT).show();
        }

    }
}

package com.example.administrator.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText inputBgt = (EditText) findViewById(R.id.inputBgt);
        final Button enterB = (Button) findViewById(R.id.enter_button);

        final SharedPreferences sp = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor  = sp.edit();

        enterB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strBudget = inputBgt.getText().toString();
                int intBudget = Integer.parseInt(strBudget);
                editor.putInt("Budget", intBudget);
                editor.putBoolean("budget_isSet", true);
                editor.commit();
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
                finish();
            }
        });

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
}

package com.example.administrator.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final SharedPreferences sp = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor  = sp.edit();
        final int balance = sp.getInt("Budget", 0);
        String balanceStr = Integer.toString(balance);

        final TextView output = (TextView) findViewById(R.id.output);
        final EditText inputExp = (EditText) findViewById(R.id.inputExp);
        final Button enterB = (Button) findViewById(R.id.enter_button);
        final Button setBudget = (Button) findViewById(R.id.set_button);
        final TextView warning = (TextView) findViewById(R.id.warning);
        warning.setVisibility(View.GONE);

        output.setText(balanceStr);

        enterB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int expenseInt;
                String expense = inputExp.getText().toString();
                inputExp.setText("");

                try {
                    expenseInt = Integer.parseInt(expense);
                    int bal = sp.getInt("Budget", 0);
                    if (bal == 0) {
                        Context context = getApplicationContext();
                        CharSequence text = "You have depleted your Budget!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.setGravity(Gravity.CENTER,0,0);
                        toast.show();
                        warning.setText("You have depleted your Budget");
                        warning.setTextColor(getResources().getColor(R.color.red));
                        warning.setVisibility(View.VISIBLE);
                    } else {
                        bal = bal - expenseInt;
                        editor.putInt("Budget", bal);
                        editor.commit();
                        output.setText(Integer.toString(bal));
                    }
                } catch (Exception e) {
                    inputExp.setHint("Please enter an integer");
                }


                // Log.d("Decrease", "bal " + bal + " expenseInt " + expenseInt);
            }
        });

        setBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
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

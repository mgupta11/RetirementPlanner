package in.incrementalsolutions.retirementplanner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class Results extends ActionBarActivity implements View.OnClickListener{

    // Declaring variables

    TextView result;
    TextView result_without_savings;
    TextView result_with_savings;
    TextView result_header_text;
    Button home;
    Button assumptions;

    String username;
    String userage;
    String userretirement;
    Integer unknownmonthlyexpenses;
    Integer unknownmonthlysavings;

    Integer annualunknownexpense;

    Integer current_expenses;
    Integer future_expenses;
    Integer current_savings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        //Get handles to all UI elements

        result_header_text = (TextView)findViewById(R.id.result_text);
        result_without_savings = (TextView)findViewById(R.id.result_without_savings);
        result_with_savings = (TextView)findViewById(R.id.result_with_savings);
        result = (TextView)findViewById(R.id.result);
        home = (Button)findViewById(R.id.result_to_home);
        assumptions = (Button)findViewById(R.id.assumptions);

        //Set event handlers

        home.setOnClickListener(Results.this);
        assumptions.setOnClickListener(Results.this);


        SharedPreferences MyInfoPreferences = getSharedPreferences(MyInfo.mydb, Context.MODE_PRIVATE);
        username = MyInfoPreferences.getString("UserNamekey","");
        userage = MyInfoPreferences.getString("Useragekey","");
        userretirement = MyInfoPreferences.getString("Userretirementagekey","");


        SharedPreferences UnknownExpPreferences = getSharedPreferences(UnknownExpenses.mydb,Context.MODE_PRIVATE);
        unknownmonthlyexpenses = UnknownExpPreferences.getInt("monthly_known_expense_key",0);
        unknownmonthlysavings = UnknownExpPreferences.getInt("monthly_known_savings_key",0);

        SharedPreferences UnknownAnnualExpPreference = getSharedPreferences(UnknownExpenseAnnually.mydb,Context.MODE_PRIVATE);
        annualunknownexpense = UnknownAnnualExpPreference.getInt("total_annual_known_expenses_key ",0);

        SharedPreferences KnownexpPreference = getSharedPreferences(KnownExpenses.mydb,Context.MODE_PRIVATE);
        current_expenses = KnownexpPreference.getInt("current_expenses",0);
        future_expenses = KnownexpPreference.getInt("future_expenses",0);
        current_savings = KnownexpPreference.getInt("current_savings",0);


        /*

        //Receiving Monthly and Yearly expenses, savings and customer demographics

        Intent receive_monthly_expenses = getIntent();
        Integer monthly_expenses = receive_monthly_expenses.getIntExtra("carrierme",0);

        Intent receive_yearly_expenses = getIntent();
        Integer yearly_expenses = receive_yearly_expenses.getIntExtra("carrierye",0);

        Intent receive_monthly_savings = getIntent();
        Integer monthly_savings = receive_monthly_savings.getIntExtra("carrierms",0);

        Intent receive_username = getIntent();
        String username1 = receive_username.getStringExtra("carrierun");

        */

        result_header_text.setText("Dear " +username +", Our systems have been hard at work trying to get the minimum savings required to meet your retired expectations. Below cases display the current monthly saving required by you in order to meet your retirement goals");

        result_with_savings.setText(username +", Considering your current savings patter, you should save " +unknownmonthlyexpenses +" per month henceforth, to enjoy a perfect retirement.");
        result_without_savings.setText(username +", If we do not consider your current savings pattern, then you should have a minimum monthly savings of " +unknownmonthlyexpenses +" for enjoying a perfect retirement");

        result.setText("Here are some of the suggestions we have for you " +username);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_results, menu);
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

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.result_to_home:{

                Intent back_to_home = new Intent(Results.this, Home.class);
                startActivity(back_to_home);
                break;
            }

            case R.id.assumptions: {

                Intent over_to_assumptions = new Intent(Results.this, Assumptions.class);
                startActivity(over_to_assumptions);
                Toast.makeText(getApplicationContext(),"hello", Toast.LENGTH_LONG).show();
                break;
            }

        }

    }
}

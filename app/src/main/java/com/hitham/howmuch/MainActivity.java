package com.hitham.howmuch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    //constraint
    //TODO : Create the base url
    private final String BASE_URL="https://apiv2.bitcoinaverage.com/indices/global/ticker/BTC";

    //member variable
    TextView mCurrencyPrice;
    Spinner mFilterFiatCurrency;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCurrencyPrice=findViewById(R.id.coins_priceTV);
        mFilterFiatCurrency=findViewById(R.id.spinnerID);

        final ArrayAdapter adapter=ArrayAdapter.createFromResource(this,R.array.currency_array,
                R.layout.support_simple_spinner_dropdown_item);
        mFilterFiatCurrency.setAdapter(adapter);

        //TODO : set Item selected from spinner
        mFilterFiatCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Log.d("bitcoins"," is : "+adapterView.getItemAtPosition(position));
                letsDoSomeNetwork(BASE_URL+adapterView.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void letsDoSomeNetwork(String url) {
        AsyncHttpClient client=new AsyncHttpClient();


    }

}

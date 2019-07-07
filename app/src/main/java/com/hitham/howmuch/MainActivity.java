package com.hitham.howmuch;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
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

        //check network
        if(isNetworkIsAvaliable()){
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

        }else {
            AlertDialog.Builder alertWifi=new AlertDialog.Builder(this);
            alertWifi.setTitle("NO Connection");
            alertWifi.setIcon(R.drawable.error_face);
            alertWifi.setIcon(R.drawable.ic_signal_wifi_off);
            alertWifi.setView(R.drawable.ic_signal_wifi_off);
            alertWifi.setCancelable(false);
            alertWifi.setMessage("please turn on wifi to use app");
            alertWifi.setPositiveButton("Close App", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alertWifi.show();
        }


    }

    private void letsDoSomeNetwork(String url) {
        AsyncHttpClient client=new AsyncHttpClient();


    }
    //TODO: get is network is available

    private Boolean isNetworkIsAvaliable(){
        ConnectivityManager connectivityManager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activenetworkInfo=connectivityManager.getActiveNetworkInfo();
        return activenetworkInfo !=null && activenetworkInfo.isConnected();
    }

}

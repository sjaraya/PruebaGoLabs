package com.example.sergioaraya.pruebagolabs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sergioaraya.pruebagolabs.Classes.Singleton;

import org.w3c.dom.Text;

public class PersonInfo extends AppCompatActivity {

    Singleton singleton = Singleton.getInstance();
    private TextView personName;
    private TextView personEmail;
    private TextView personCity;
    private TextView personSuite;
    private TextView personStreet;
    private TextView personCatchPhrase;
    private TextView personBs;
    private TextView personCName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);

        personName = (TextView) findViewById(R.id.person_info_name);
        personEmail = (TextView) findViewById(R.id.person_info_email);


        personCity = (TextView) findViewById(R.id.address_city);
        personSuite = (TextView) findViewById(R.id.address_suite);
        personStreet = (TextView) findViewById(R.id.address_street);

        personCatchPhrase = (TextView) findViewById(R.id.person_info_catchphrase);
        personCName = (TextView) findViewById(R.id.person_info_company_name);
        personBs = (TextView) findViewById(R.id.person_info_bs);

        personName.setText(singleton.getPerson().getName());
        personEmail.setText(singleton.getPerson().getEmail());
        personCity.setText(singleton.getPerson().getAddress().getCity());
        personSuite.setText(singleton.getPerson().getAddress().getSuite());
        personStreet.setText(singleton.getPerson().getAddress().getStreet());
        personCatchPhrase.setText(singleton.getPerson().getCompany().getCatchPhrase());
        personBs.setText(singleton.getPerson().getCompany().getBs());
        personCName.setText(singleton.getPerson().getCompany().getName());

    }
}

package com.example.sergioaraya.pruebagolabs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.sergioaraya.pruebagolabs.Adapter.AdapterPerson;
import com.example.sergioaraya.pruebagolabs.Classes.Address;
import com.example.sergioaraya.pruebagolabs.Classes.Company;
import com.example.sergioaraya.pruebagolabs.Classes.Constants;
import com.example.sergioaraya.pruebagolabs.Classes.Geo;
import com.example.sergioaraya.pruebagolabs.Classes.Person;
import com.example.sergioaraya.pruebagolabs.Classes.Singleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Constants constants = Constants.getInstance();
    Singleton singleton = Singleton.getInstance();

    private Person person;
    private Address address;
    private Geo geo;
    private Company company;
    private ArrayList<Person> persons = new ArrayList<>();
    private AdapterPerson adapterPerson;
    private ListView personsList;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading person...");
        progressDialog.show();

        new taskParseJsonToGetPersons().execute(constants.getUrlPersons());

    }

    private void loadPersons() {

        personsList = (ListView) findViewById(R.id.persons_list);
        adapterPerson = new AdapterPerson(this, singleton.getPersons());
        personsList.setAdapter(adapterPerson);

        personsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                singleton.setPerson((Person) parent.getAdapter().getItem(position));
                Intent intent = new Intent(getApplicationContext(), PersonInfo.class);
                startActivity(intent);
            }
        });
    }

    private class taskParseJsonToGetPersons extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return loadContentFromNetwork(urls[0]);
        }

        protected void onPostExecute(String result) {

            JSONArray temporalPersons = null;
            try {
                Log.v("Resultado", result);
                temporalPersons = new JSONArray(result);
            } catch (JSONException e1) {
                e1.printStackTrace();
            }

            for (int i = 0; i < temporalPersons.length(); i++) {

                JSONObject temporalPerson = null;

                try {

                    temporalPerson = temporalPersons.getJSONObject(i);

                    person = new Person();
                    person.setName(temporalPerson.getString("name"));
                    person.setUsername(temporalPerson.getString("username"));
                    person.setEmail(temporalPerson.getString("email"));

                    JSONObject temporalAddress = null;
                    temporalAddress = temporalPerson.getJSONObject("address");

                    address = new Address();
                    address.setStreet(temporalAddress.getString("street"));
                    address.setSuite(temporalAddress.getString("suite"));
                    address.setCity(temporalAddress.getString("city"));
                    address.setZipcode(temporalAddress.getString("zipcode"));

                    JSONObject temporalGeo = null;
                    temporalGeo = temporalAddress.getJSONObject("geo");

                    geo = new Geo();
                    geo.setLat(temporalGeo.getString("lat"));
                    geo.setLng(temporalGeo.getString("lng"));
                    address.setGeo(geo);
                    person.setAddress(address);

                    person.setPhone(temporalPerson.getString("phone"));
                    person.setWebsite(temporalPerson.getString("website"));

                    JSONObject temporalCompany = null;
                    temporalCompany = temporalPerson.getJSONObject("company");

                    company = new Company();
                    company.setName(temporalCompany.getString("name"));
                    company.setCatchPhrase(temporalCompany.getString("catchPhrase"));
                    company.setBs(temporalCompany.getString("bs"));
                    person.setCompany(company);

                    persons.add(person);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            singleton.setPersons(persons);
            progressDialog.dismiss();
            loadPersons();
        }

        private String loadContentFromNetwork(String url) {
            try {
                InputStream mInputStream = (InputStream) new URL(url).getContent();
                InputStreamReader mInputStreamReader = new InputStreamReader(mInputStream);
                BufferedReader responseBuffer = new BufferedReader(mInputStreamReader);
                StringBuilder strBuilder = new StringBuilder();
                String line = null;
                while ((line = responseBuffer.readLine()) != null) {
                    strBuilder.append(line);
                }
                return strBuilder.toString();

            } catch (Exception e) {
                Log.v("Error loading content.", e.getMessage());
            }
            return null;
        }
    }

}

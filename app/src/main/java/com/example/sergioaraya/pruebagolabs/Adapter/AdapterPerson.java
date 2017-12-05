package com.example.sergioaraya.pruebagolabs.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.sergioaraya.pruebagolabs.Classes.Person;
import com.example.sergioaraya.pruebagolabs.Classes.Singleton;
import com.example.sergioaraya.pruebagolabs.R;

import java.util.ArrayList;

/**
 * Created by SergioAraya on 05/12/2017.
 */

public class AdapterPerson extends BaseAdapter implements Filterable {


    private Activity activity;

    private static LayoutInflater inflater = null;
    private ArrayList<Person> originalItems;
    private ArrayList<Person> filteredItems;

    public AdapterPerson(Activity activity, ArrayList<Person> items) {
        this.activity = activity;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.originalItems = items;
        this.filteredItems = items;
    }

    @Override
    public int getCount() {
        return filteredItems.size();
    }

    @Override
    public Object getItem(int i) {
        return filteredItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {

        View view = convertView;

        if (convertView == null) {
            view = inflater.inflate(R.layout.item_person, null);
        }

        final Person person = filteredItems.get(position);

        TextView personName = (TextView) view.findViewById(R.id.person_name);
        personName.setText(person.getName());

        TextView personEmail = (TextView) view.findViewById(R.id.person_email);
        personEmail.setText(person.getEmail());

        view.setPadding(25,25,25,25);
        return view;
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}

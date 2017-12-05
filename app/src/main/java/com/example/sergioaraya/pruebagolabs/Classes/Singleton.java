package com.example.sergioaraya.pruebagolabs.Classes;

import java.util.ArrayList;

/**
 * Created by SergioAraya on 05/12/2017.
 */

public class Singleton {

    private static Singleton singleton;
    private Person person;
    private ArrayList<Person> persons = new ArrayList<>();
    private Singleton() {}

    public static synchronized Singleton getInstance() {

        if (singleton == null){

            singleton = new Singleton();
        }

        return singleton;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }
}

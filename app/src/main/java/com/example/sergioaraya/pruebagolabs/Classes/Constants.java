package com.example.sergioaraya.pruebagolabs.Classes;

/**
 * Created by SergioAraya on 05/12/2017.
 */

public class Constants {

    private static Constants constants;

    private static final String url_persons = "http://jsonplaceholder.typicode.com/users";

    private Constants() {}

    public static synchronized Constants getInstance() {

        if (constants == null){

            constants = new Constants();

        }

        return constants;
    }

    public static String getUrlPersons() {
        return url_persons;
    }
}

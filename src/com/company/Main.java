package com.company;

import java.util.ArrayList;
import java.util.Map;

public class Main {

    public static void main(String[] args){
        Database database = new Database();
        ArrayList<User> users = database.getUsers();

        database.addUser();

        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i));
        }

        database.deleteUser();

        users = database.getUsers();

        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i));
        }

        System.out.println("Hello world ");
        int a = 5;
        a += 5;

        for (int i = 0; i < a; i++) {
            int h = i;
            System.out.println(Math.pow(h,2));
        }
    }
}

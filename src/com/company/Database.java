package com.company;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {


    private PreparedStatement ps;
    private ResultSet rs;
    private Scanner in = new Scanner(System.in);
    private Connection connection;
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private String databaseURL = "jdbc:mysql:" +
            "//localhost:3306/amp?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String user = "root";
    private static String password = "";

    public Database() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection
                    ("jdbc:mysql://192.168.64.2:3306/amp", "abylay", "abylay");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addUser () {
        String name, surname;
        Date date;
        System.out.println("Enter your name: ");
        name = in.next();
        System.out.println("Enter your surname: ");
        surname = in.next();
        System.out.println("Enter your date(format: (yyyy-mm-dd)):");
        System.out.println("Example: 2020-04-05");
        String sdate = in.next();

        date = Date.valueOf(sdate);

        //INSERT INTO users (id, name, surname, date) VALUES ()
        try {
            ps = connection.prepareStatement("INSERT INTO users(id, name, surname, date) VALUES (NULL, ?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, surname);
            ps.setObject(3, date);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();

        try {
            ps = connection.prepareStatement("SELECT * FROM users");
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                Date date = (Date) rs.getObject("date");
                User user = new User(id,name,surname,date);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void deleteUser() {
        System.out.println("Enter id: ");
        int id = in.nextInt();
        try {
            ps = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addList() {
        //TODO I need to create method add list
    }
}

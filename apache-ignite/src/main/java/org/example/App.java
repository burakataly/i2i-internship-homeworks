package org.example;

import org.apache.ignite.client.ClientConnectionException;

import java.sql.*;

public class App {
    public static void main( String[] args ) {
        String jdbcURL = "jdbc:ignite:thin://127.0.0.1";

        try {
            Class.forName("org.apache.ignite.IgniteJdbcThinDriver");
            Connection connection = DriverManager.getConnection(jdbcURL);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM SUBSCRIBER");

            while (resultSet.next()){
                int id = resultSet.getInt("SUBSC_ID");
                String name = resultSet.getString("SUBSC_NAME");
                String surname = resultSet.getString("SUBSC_SURNAME");
                String msisdn = resultSet.getString("MSISDN");
                int tariffId = resultSet.getInt("TARIFF_ID");
                Timestamp startDate = resultSet.getTimestamp("START_DATE");

                System.out.println("ID: " + id + ", Name: " + name + ", Surname: " + surname +
                        ", MSISDN: " + msisdn + ", Tariff ID: " + tariffId +
                        ", Start Date: " + startDate);
            }

        }catch(ClientConnectionException | ClassNotFoundException ce){
            System.out.println(ce.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

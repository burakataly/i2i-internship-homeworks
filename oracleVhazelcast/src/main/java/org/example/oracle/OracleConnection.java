package org.example.oracle;
import java.sql.*;
import java.util.Random;

public class OracleConnection {
    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:DEMO";
    private static final String JDBC_USER = "burak";
    private static final String JDBC_PASSWORD = "123";

    public static void main(String[] args) {
        Connection oracleConnection = OracleConnection.connect();
        if (oracleConnection == null){
            System.out.println("couldn't connect to the oracle db");
        }
        else{
            OracleConnection.insertData(oracleConnection, "TABLE1", 100000);
            OracleConnection.selectData(oracleConnection, "TABLE1");
        }

    }
    public static Connection connect(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            if (connection != null) {
                System.out.println("Connected to the Oracle database successfully!");
                return connection;
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
        return null;
    }

    public static void insertData(Connection connection, String table_name, int n){
        String insertSQL = "INSERT INTO " + table_name + " (num) VALUES (?)";
        Random random = new Random();
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)){
            long startTime = System.currentTimeMillis();
            for(int i = 1; i <= n;i++){
                int randomNumber = random.nextInt(n) + 1;
                preparedStatement.setInt(1, randomNumber);
                preparedStatement.executeUpdate();
            }
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            System.out.println("Inserted integers from " + 1 + " to " + n + " in " + duration + " milliseconds");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void selectData(Connection connection, String table_name){
        String selectSQL = "SELECT * FROM " + table_name;
        try (Statement statement = connection.createStatement()) {
            long startTime = System.currentTimeMillis();
            ResultSet resultSet = statement.executeQuery(selectSQL);
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            System.out.println("Time taken to select all data: " + duration + " milliseconds");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

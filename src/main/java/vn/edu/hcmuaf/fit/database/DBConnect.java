package vn.edu.hcmuaf.fit.database;

import java.sql.*;

public class DBConnect {
    private static DBConnect install;
    private static DBConnect instance;
    private static String DB_URL = "jdbc:mysql://localhost:3306/project";
    private static String USER = "root";
    private static String PASS = "";

    private Connection connection;

    private DBConnect() {
    }
    public static DBConnect getInstall() {
        if (install == null) install = new DBConnect();
        return install;
    }


    public static DBConnect getInstance() {
        if (instance == null) {
            instance = new DBConnect();
            try {
                instance.connect();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    private void connect() throws SQLException, ClassNotFoundException {
        if (connection == null || connection.isClosed()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        }
    }

    public PreparedStatement get(String query) {
        try {
            connect();
            return connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}

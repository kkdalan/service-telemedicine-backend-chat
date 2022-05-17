package com.fet.telemedicine.backend.chat.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/demo?useSSL=false&serverTimezone=UTC&";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "0000";

    private static ThreadLocal<Connection> connContainer = new ThreadLocal<Connection>();

    public static Connection getCOnnection() {
	Connection conn = connContainer.get();
	try {
	    if (conn == null) {
		Class.forName(DRIVER);
		conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    connContainer.set(conn);
	}
	return conn;
    }

    public static void closeConnection() {
	Connection conn = connContainer.get();
	try {
	    if (conn != null) {
		conn.close();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    connContainer.remove();
	}
    }
}

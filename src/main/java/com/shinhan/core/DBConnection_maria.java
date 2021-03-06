package com.shinhan.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DBConnection_maria {
	
	private static final String DB_DRIVER_CLASS = "org.mariadb.jdbc.Driver";
	private static final String DB_URL = "jdbc:mariadb://127.0.0.1:3306/";
	private static final String DB_USERNAME = "jtdba";
	private static final String DB_PASSWORD = "Shinhan1!";
	private static Connection conn;
	PreparedStatement pstmt = null;
	
	public static void connectDB() {
	try {
		Class.forName(DB_DRIVER_CLASS);
		Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
		System.out.println("연결성공");
	} catch (ClassNotFoundException e) {
		System.out.println("드라이브 로딩 실패");
	} catch (SQLException e) {
		System.out.println("DB 연결 실패");
	}
	}
}

package another;

import java.util.Scanner;
import java.sql.*;


public class Start {
	static Connection conn=null;
	static String inPut;

	static Connection sqlmeth() {
		final String user="admin";
		final String password="12345678";
		final String db_url="jdbc:oracle:thin:@database-1.cfjl8y0qm7ts.us-east-2.rds.amazonaws.com:1521:ORCL";

		try {
			Connection conn=DriverManager.getConnection(db_url,user,password);
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			System.out.println("connected to server");
			return conn;

		}catch(ClassNotFoundException e) {
			System.out.println("no driver class");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("here?");
		return null;
		 
	}
	public static void main(String[] args){
		if(conn==null){
			System.out.println("building connection");
			conn=sqlmeth();
		}
		Scanner input= new Scanner(System.in);
		System.out.println("Welcome to the login page. Please enter User name or enter 'N' for now user._____________|");
		inPut=input.nextLine();

		if(inPut.equals("N")) {
			new newuser();
		}else {
			System.out.println("Please enter your pass word._____________________________________________________________|");
			String pw=input.next();
			new dothing(inPut,pw);
		}
		input.close();
	}
}





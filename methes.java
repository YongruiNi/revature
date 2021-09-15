package another;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class methes {

	static void ISNU(String username, String pw, String fN, String lN) {
		try {
			String sst= "INSERT INTO USERS(PASSWORD, FN, LN,USERNAME,TYPE) VALUES (?,?,?,?,?)";
			PreparedStatement pst=Start.conn.prepareStatement(sst);{
				pst.setString(1,pw);
				pst.setString(2,fN); 
				pst.setString(3,lN); 
				pst.setString(4,username); 
				pst.setString(5,"n"); 
				pst.executeUpdate(); 
			}}catch (SQLException e) {
				e.printStackTrace();
			}
	}
	static void ISNU(String username, String pw, String fN, String lN, String f2n, String l2n) {
		try {
			String sst= "INSERT INTO USERS(PASSWORD, FN, LN,SFN,SLM,USERNAME,TYPE) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement pst=Start.conn.prepareStatement(sst);{
				pst.setString(1,pw);
				pst.setString(2,fN); 
				pst.setString(3,lN); 
				pst.setString(4,f2n); 
				pst.setString(5,l2n); 
				pst.setString(6,username); 
				pst.setString(7,"n"); 
				pst.executeUpdate(); 
			}}catch (SQLException e) {
				e.printStackTrace();
			}

	}

	static int checktype(String username, Connection cn) {
		try {
			boolean notex=true;
			boolean isn=false;
			int cp=3;
			Statement st=cn.createStatement();
			String q="SELECT USERNAME, TYPE FROM USERS WHERE USERNAME='"+username+"'";
			ResultSet rs=st.executeQuery(q);
			while(rs.next()) {
				notex=false;
				if(rs.getString(2).equals("n")) {
					isn=true;
				}
			}
			if(notex) {
				System.out.println("account not taken");
				cp=0;
			}
			if(!notex&&!isn) {
				System.out.println("can transfer");
				cp=1;
			}
			return cp;
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return 3;
	}
	public static void view(String inPut) {
		try {
			String q="SELECT * FROM USERS WHERE USERNAME='"+inPut+"'";
			Statement st=Start.conn.createStatement();
			ResultSet rs=st.executeQuery(q);
			while(rs.next()) {
				System.out.println("vilad");
				System.out.println("User name:--------------------------------"+rs.getString("USERNAME"));
				System.out.println("User's first and last name:---------------"+rs.getString("FN")+" "+rs.getString("LN"));
				System.out.println("User2's first and last name if apply------"+rs.getString("SFN")+" "+rs.getString("SLM"));
				System.out.println("Account type:-----------------------------"+rs.getString("TYPE"));
				System.out.println("Balance     :-----------------------------"+rs.getString("BALANCE")+"\n");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

	}
	public static void transfer(String inPut) {
		System.out.println("Please enter the Username you want to transfer to.");
		Scanner c=new Scanner(System.in);
		String input=c.next();
		while(checktype(input,Start.conn)!=1) {
			System.out.println("Target account not vilad, please try again.");
			input=c.next();
		}
		
		
		String q="SELECT BALANCE FROM USERS WHERE USERNAME='"+Start.inPut+"'";
		String p="SELECT BALANCE FROM USERS WHERE USERNAME='"+input+"'";
		double b=0;
		double amount=0;
		double d=0;
		try{
			Statement st=Start.conn.createStatement();
			ResultSet rs=st.executeQuery(q);
			while(rs.next()) {
				b=rs.getInt("BALANCE");
				}
			rs=st.executeQuery(p);
			while(rs.next()) {
				d=rs.getInt("BALANCE");
				}
			}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Please enter a positive amount");
		while(true) {
			try {
				c=new Scanner(System.in);
				amount=c.nextDouble();
				if(amount>0&&b>amount) {
					b-=amount;
					d+=amount;
					q="UPDATE USERS SET BALANCE ="+b+"WHERE USERNAME='"+Start.inPut+"'";
					p="UPDATE USERS SET BALANCE ="+d+"WHERE USERNAME='"+input+"'";
					try {
					Statement st=Start.conn.createStatement();
					st.executeQuery(q);
					st.executeQuery(p);}catch(SQLException e) {
						e.printStackTrace();
					}
					System.out.println("Your new balance is "+b);
					break;
				}else throw new Exception();
			}catch(InputMismatchException e) {
				System.out.println("need a number,please try again.");
				continue;
			}catch(Exception e) {
				System.out.println("need a + number,please try again.");
				continue;
			}
		}

	}
	public static void deposit(String inPut) {

		String q="SELECT BALANCE FROM USERS WHERE USERNAME='"+Start.inPut+"'";
		double b=0;
		double amount=0;
		try{
			Statement st=Start.conn.createStatement();
			ResultSet rs=st.executeQuery(q);
			while(rs.next()) {
				b=rs.getInt("BALANCE");
				}
			}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Please enter a positive amount");
		while(true) {
			try {
				Scanner c=new Scanner(System.in);
				amount=c.nextDouble();
				if(amount>0) {
					b+=amount;
					q="UPDATE USERS SET BALANCE ="+b+"WHERE USERNAME='"+Start.inPut+"'";
					try {
					Statement st=Start.conn.createStatement();
					st.executeQuery(q);}catch(SQLException e) {
						e.printStackTrace();
					}
					System.out.println("Your new balance is "+b);
					break;
				}else throw new Exception();
			}catch(InputMismatchException e) {
				System.out.println("need a number,please try again.");
				continue;
			}catch(Exception e) {
				System.out.println("need a + number,please try again.");
				continue;
			}
		}
	}
	

	public static void withdrow(String inPut) {
		String q="SELECT BALANCE FROM USERS WHERE USERNAME='"+Start.inPut+"'";
		double b=0;
		double amount=0;
		try{
			Statement st=Start.conn.createStatement();
			ResultSet rs=st.executeQuery(q);
			while(rs.next()) {
				b=rs.getInt("BALANCE");
				}
			}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Please enter a positive amount");
		while(true) {
			try {
				Scanner c=new Scanner(System.in);
				amount=c.nextDouble();
				if(amount>0&&b>amount) {
					b-=amount;
					q="UPDATE USERS SET BALANCE ="+b+"WHERE USERNAME='"+Start.inPut+"'";
					try {
					Statement st=Start.conn.createStatement();
					st.executeQuery(q);}catch(SQLException e) {
						e.printStackTrace();
					}
					System.out.println("Your new balance is "+b+"\n");
					break;
				}else throw new Exception();
			}catch(InputMismatchException e) {
				System.out.println("need a number,please try again.");
				continue;
			}catch(Exception e) {
				System.out.println("need a + number,please try again.");
				continue;
			}
		}

	}
	public static void cancel() {
		try {
			System.out.println("Please enter the user name you want to close");
			Scanner c=new Scanner(System.in);
			String usern=c.next();
			String q="SELECT TYPE FROM USERS WHERE USERNAME='"+usern+"'";
			Statement st=Start.conn.createStatement();
			ResultSet rs=st.executeQuery(q);
			while(!rs.next()) {
				System.out.println("Can't find this new user, please Enter aganin or or entner E to exit");
				usern=c.next();
				if(usern.equals("E"))return;
				q="SELECT TYPE FROM USERS WHERE USERNAME='"+usern+"'";
				rs=st.executeQuery(q);
			}
			q="DELETE USERS WHERE USERNAME='"+usern+"'";
			st.executeQuery(q);
			System.out.println("The account for "+usern+" has been closed.");
		}catch(SQLException e) {
			e.printStackTrace();

		}
	}
	public static void approve() {
		try {
			System.out.println("Please enter the user name you want to approve");
			Scanner c=new Scanner(System.in);
			String usern=c.next();
			String q="SELECT TYPE FROM USERS WHERE USERNAME='"+usern+"' And TYPE ='n'";
			Statement st=Start.conn.createStatement();
			ResultSet rs=st.executeQuery(q);
			while(!rs.next()) {
				System.out.println("Can't find this new user, please Enter aganin or or entner E to exit");
				usern=c.next();
				if(usern.equals("E"))return;
				q="SELECT TYPE FROM USERS WHERE USERNAME='"+usern+"'And TYPE ='n'";
				rs=st.executeQuery(q);
			}
			q="UPDATE USERS SET TYPE ='U' WHERE USERNAME='"+usern+"'";
			st.executeQuery(q);
			System.out.println("This account has been approved now\n");
		}catch(SQLException e) {
			e.printStackTrace();

		}
	}
	public static void view() {
		System.out.println("Please enter the Username you want to view.");
		Scanner c=new Scanner(System.in);
		String input=c.next();
		while(checktype(input,Start.conn)==0) {
			System.out.println("Target account is not vilad, please try again.");
			input=c.next();
		}
		try {
			String q="SELECT * FROM USERS WHERE USERNAME='"+input+"'";
			Statement st=Start.conn.createStatement();
			ResultSet rs=st.executeQuery(q);
			while(rs.next()) {
				System.out.println("Account valid");
				System.out.println("User name:--------------------------------"+rs.getString("USERNAME"));
				System.out.println("User's first and last name:---------------"+rs.getString("FN")+" "+rs.getString("LN"));
				System.out.println("User2's first and last name if apply------"+rs.getString("SFN")+" "+rs.getString("SLM"));
				System.out.println("Account type:-----------------------------"+rs.getString("TYPE"));
				System.out.println("Balance     :-----------------------------"+rs.getString("BALANCE")+"\n");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

	}
	public static void transfer() {
		System.out.println("Please enter the Username you want to transfer To.");
		Scanner c=new Scanner(System.in);
		String input=c.next();
		while(checktype(input,Start.conn)!=1) {
			System.out.println("Target account not vilad, please try again.");
			input=c.next();
		}
		System.out.println("Please enter the Username you want to transfer From.");
		c=new Scanner(System.in);
		String inputf=c.next();
		while(checktype(inputf,Start.conn)!=1) {
			System.out.println("Target account not vilad, please try again.");
			inputf=c.next();
		}
		
		String q="SELECT BALANCE FROM USERS WHERE USERNAME='"+inputf+"'";
		String p="SELECT BALANCE FROM USERS WHERE USERNAME='"+input+"'";
		double b=0;
		double amount=0;
		double d=0;
		try{
			Statement st=Start.conn.createStatement();
			ResultSet rs=st.executeQuery(q);
			while(rs.next()) {
				b=rs.getInt("BALANCE");
				}
			rs=st.executeQuery(p);
			while(rs.next()) {
				d=rs.getInt("BALANCE");
				}
			}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Please enter a positive amount");
		while(true) {
			try {
				c=new Scanner(System.in);
				amount=c.nextDouble();
				if(amount>0&&b>amount) {
					b-=amount;
					d+=amount;
					q="UPDATE USERS SET BALANCE ="+b+"WHERE USERNAME='"+inputf+"'";
					p="UPDATE USERS SET BALANCE ="+d+"WHERE USERNAME='"+input+"'";
					try {
					Statement st=Start.conn.createStatement();
					st.executeQuery(q);
					st.executeQuery(p);}catch(SQLException e) {
						e.printStackTrace();
					}
					System.out.println("Your new balance is "+b+"\n");
					break;
				}else throw new Exception();
			}catch(InputMismatchException e) {
				System.out.println("need a number,please try again.");
				continue;
			}catch(Exception e) {
				System.out.println("need a + number,please try again.");
				continue;
			}
		}


	}
	public static void deposit() {
		System.out.println("Please enter the Username you want to depoist to.");
		Scanner c=new Scanner(System.in);
		String input=c.next();
		while(checktype(input,Start.conn)!=1) {
			System.out.println("Target account not vilad, please try again.");
			input=c.next();
		}
		
		String q="SELECT BALANCE FROM USERS WHERE USERNAME='"+input+"'";
		double b=0;
		double amount=0;
		try{
			Statement st=Start.conn.createStatement();
			ResultSet rs=st.executeQuery(q);
			while(rs.next()) {
				b=rs.getInt("BALANCE");
				}
			}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Please enter a positive amount");
		while(true) {
			try {
				c=new Scanner(System.in);
				amount=c.nextDouble();
				if(amount>0) {
					b+=amount;
					q="UPDATE USERS SET BALANCE ="+b+"WHERE USERNAME='"+input+"'";
					try {
					Statement st=Start.conn.createStatement();
					st.executeQuery(q);}catch(SQLException e) {
						e.printStackTrace();
					}
					System.out.println("His new balance is"+b+"/n");
					break;
				}else throw new Exception();
			}catch(InputMismatchException e) {
				System.out.println("need a number,please try again.");
				continue;
			}catch(Exception e) {
				System.out.println("need a + number,please try again.");
				continue;
			}
		}

	}
	public static void withdrow() {
		System.out.println("Please enter the Username you want to withdrow from.");
		Scanner c=new Scanner(System.in);
		String input=c.next();
		while(checktype(input,Start.conn)!=1) {
			System.out.println("Target account not vilad, please try again.");
			input=c.next();
		}
		String q="SELECT BALANCE FROM USERS WHERE USERNAME='"+input+"'";
		double b=0;
		double amount=0;
		try{
			Statement st=Start.conn.createStatement();
			ResultSet rs=st.executeQuery(q);
			while(rs.next()) {
				b=rs.getInt("BALANCE");
				}
			}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Please enter a positive amount");
		while(true) {
			try {
				c=new Scanner(System.in);
				amount=c.nextDouble();
				if(amount>0&&b>amount) {
					b-=amount;
					q="UPDATE USERS SET BALANCE ="+b+"WHERE USERNAME='"+input+"'";
					try {
					Statement st=Start.conn.createStatement();
					st.executeQuery(q);}catch(SQLException e) {
						e.printStackTrace();
					}
					System.out.println("Your new balance is"+b);
					break;
				}else throw new Exception();
			}catch(InputMismatchException e) {
				System.out.println("need a number,please try again.");
				continue;
			}catch(Exception e) {
				System.out.println("need a + number,please try again.");
				continue;
			}
		}

	}
}

package another;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class dothing {
	Scanner input =new Scanner(System.in);
	public dothing(String inPut, String pw) {
		Scanner input= new Scanner(System.in);
		try {
			Statement st=Start.conn.createStatement();
			String q="SELECT TYPE FROM USERS WHERE USERNAME='"+inPut+"' and PASSWORD ='"+pw+"'";
			ResultSet rs=st.executeQuery(q);
			String tp="df";
			while(!rs.next()) {
				System.out.println("USERNAME and PASSWORD not match");
				System.out.println("please enter you Username or entner E to exit");
				inPut=input.nextLine();
				if(inPut.equals("E"))return;
				System.out.println("please enter you Password");
				pw=input.nextLine();
				q="SELECT TYPE FROM USERS WHERE USERNAME='"+inPut+"' and PASSWORD ='"+pw+"'";
				rs=st.executeQuery(q);
				
			}
			tp=rs.getString(1);
			rs.close();
			System.out.println("Your account type is "+tp);

			switch(tp){
				case "A":
					adm(); 
					break;
				case "E":
					emp(); 
					break;
				case "U":
					cust(); 
					break;
				case "n":
					System.out.println("Waiting to be review. Thank you for applying");
					break;
					
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	private void cust() {
		while(true) {
			System.out.println("V for view account.\nT for transfer.\nW for withdrow.\nD for deposit\nAnykey else to Exit");
			String choice=input.next();
			switch(choice) {
			case "V":
				System.out.println(Start.inPut);
				methes.view(Start.inPut);;
				break;
			case "T":
				methes.transfer("h");
				break;
			case "D":
				methes.deposit("h");
				break;
			case "W":
				methes.withdrow("h");
				break;
			default:
				input.close();
				return;
			}
		}
		
	}

	private void emp() {
		Scanner input =new Scanner(System.in);
		while(true) {
			System.out.println("V for view account.\nA for approve account.\nAnykey else to Exit");
			String choice=input.next();
			switch(choice) {
			case "V":
				methes.view();
				break;
			case "A":
				methes.approve();
				break;
			default:
				input.close();
				return;
				
			}
		}
		
	}

	private void adm() {
		Scanner input =new Scanner(System.in);
		while(true) {
			System.out.println("V for view account.\nT for transfer.\nW for withdrow.\nD for deposit.\nA for approve account.\nC for close account.\nAnykey else to Exit");
			String choice=input.next();
			switch(choice) {
			case "V":
				methes.view();
				break;
			case "T":
				methes.transfer();
				break;
			case "D":
				methes.deposit();
				break;
			case "W":
				methes.withdrow();
				break;
			case "A":
				methes.approve();
				break;
			case "C":
				methes.cancel();
				break;
			default:
				input.close();
				return;
				
			}
		}				
	}
}


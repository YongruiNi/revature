package myproj;

import java.util.HashMap;
import java.util.Scanner;

public class newUser {
	public newUser(){
		System.out.println("Please enter account type, i for individuel account, j for joint account");
		Scanner input= new Scanner(System.in);
		String i=input.nextLine();
		switch(i)
		{
		
		case "i":
			Scanner sc= new Scanner(System.in);
			System.out.println("Please enter user name");
			String UserName=sc.nextLine();
			System.out.println("Please enter pass word");
			String Pw=sc.nextLine();
			System.out.println(Pw+UserName);
			System.out.println("Please enter your first name");
			String fN=sc.nextLine();
			System.out.println("Please enter your last name");
			String lN=sc.nextLine();
			nuser(UserName,Pw,fN,lN);
			sc.close();break;

		case "j":
			Scanner sce= new Scanner(System.in);
			System.out.println("Please enter user name");
			UserName=sce.nextLine();
			System.out.println("Please enter pass word");
			Pw=sce.nextLine();
			System.out.println("Please enter your first name");
			fN=sce.nextLine();
			System.out.println("Please enter your last name");
			lN=sce.nextLine();
			System.out.println("Please enter secend user first name");
			String f2N=sce.nextLine();
			System.out.println("Please enter secend user last name");
			String l2N=sce.nextLine();
			sce.close();
			nuser(UserName,Pw,fN,lN,f2N,l2N); 
			break;
		default:System.out.println("invilade input");
		
		}
	}
	//***************push back individual new user to Hashmap*******************************************************************
	static void nuser(String userName, String pw, String fN, String lN) {
		String un=userName;
		HashMap<String,user> MyHM=dothing.diseri();
		try {
			if(MyHM.containsKey(userName))throw new Exception("This userName has been taken, please try other one");
		}catch(Exception e) {
			Scanner c=new Scanner(System.in);
			while(MyHM.containsKey(un)) {
				System.out.println("This userName has been taken, please try other one");
				un=c.nextLine();
			}c.close();
		}
		user NewUser=new user();
		NewUser.acctype='n';
		NewUser.Pw=pw;
		NewUser.fN=fN;
		NewUser.lN=lN;
		NewUser.userName=un;
		dothing.saVe(MyHM);
		System.out.println("sucess");
	}

	//***************push back jointed new user to Hashmap*******************************************************************	
	static void nuser(String userName, String pw, String fN, String lN, String f2N , String l2N) {
		String un=userName;
		HashMap<String,user> MyHM=dothing.diseri();
		try {
			if(MyHM.containsKey(userName))throw new Exception("This userName has been taken, please try other one");
		}catch(Exception e) {
			Scanner c=new Scanner(System.in);
			while(MyHM.containsKey(un)) {
				System.out.println("please try again");
				un=c.nextLine();
			}c.close();
		}
		user NewUser=new user();
		NewUser.acctype='n';
		NewUser.Pw=pw;
		NewUser.fN=fN;
		NewUser.lN=lN;
		NewUser.userName=un;
		NewUser.f2N=f2N;
		NewUser.l2N=l2N;
		dothing.saVe(MyHM);
		System.out.println("sucess");
	}

}

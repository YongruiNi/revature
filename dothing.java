package myproj;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.Arrays;


class dothing {
	private HashMap <String, user>HM=new HashMap<String,user>();

	//***************diserilization read file and convert HashMap<Str,Str> to HashMap<Str user> and return******************	
	//***************Our work field*****************	
	static HashMap<String,user> diseri()
	{
		HashMap <String, String> HMin=new HashMap<String, String>();
		HashMap <String, user>  HMOut=new HashMap<String, user>();
		try
		{
			FileInputStream fileIn = new FileInputStream("src/files/data.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			HMin = (HashMap<String, String>) in.readObject();
			in.close();
			fileIn.close();
		}catch(IOException i)
		{
			i.printStackTrace();
		}catch(ClassNotFoundException c)
		{
			System.out.println("Map not found");
			c.printStackTrace();
		}
		for (Map.Entry<String,String> entry : HMin.entrySet()) {
			user user=new user();
			String[] s=entry.getValue().split("\\|");			
			user.acctype=s[6].charAt(0);
			user.activity=Arrays.asList(s[8].split("\\."));
			user.balance=Double.valueOf(s[5].toString());
			user.f2N=s[4];
			user.l2N=s[3];
			user.fN=s[2];
			user.lN=s[1];
			user.Pw=s[0];
			user.userName=s[7];
			HMOut.put(entry.getKey(), user);
		}

		System.out.println("file in");
		return HMOut;
	}

	//***************transfer HashMap<Str,user> to HashMap<Str,user> and save***********************************************		
	static void saVe(HashMap <String, user> HMout)
	{
		HashMap <String, String>HM=new HashMap<String, String>();
		StringBuffer sb = new StringBuffer();
		for (Entry<String, user> entry : HMout.entrySet()) {
			for(String  S:entry.getValue().activity) {
				sb.append(S+".");
			}
			String inS=entry.getValue().Pw+"|"+entry.getValue().lN+"|"+entry.getValue().fN+"|"+entry.getValue().l2N+"|"+entry.getValue().l2N+
					"|"+String.valueOf(entry.getValue().balance)+"|"+entry.getValue().acctype+"|"+entry.getValue().userName+"|"+sb.toString();
			HM.put(entry.getKey(), inS);
		}
		try {
			FileOutputStream outStream = new FileOutputStream("src/files/data.txt");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
			objectOutputStream.writeObject(HM);
			outStream.close();

			System.out.println("successful");

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("file saved");
	}

	

	//***************call diseri creat Hashmap, assign use to groups*********************************************************	
	protected dothing(String userName, String pw) {
		String un=userName;
		String Pw=pw;
		HashMap<String, user>MyHM=diseri();
		this.HM=diseri();
		while(true) {
			try {
				if(!MyHM.containsKey(un))throw new Exception("user name and password not match please try again");
				if(MyHM.get(un).Pw!=Pw)throw new Exception("user name and password not match please try again");
			}catch(Exception e) {
				Scanner c=new Scanner(System.in);
				System.out.println("enter e to exit or any key to continue");
		        if(c.nextLine()=="e")break;
		        System.out.println("please enter Username");
				un=c.nextLine();
				System.out.println("please enter password");
				Pw=c.nextLine();
				c.close();
			}
		}


		switch(MyHM.get(un).acctype) {
		case 'u':
			customer(un);
			saVe(this.HM);break;
		case 'e':
			employee();
			saVe(this.HM);
			break;
		case 'a':
			admit(un);
			saVe(this.HM);
			break;
		case 'n':
			System.out.println("waiting to be appoive, please waite");break;
		}
	}

	//***************if is administrator give option to transfer view approve close account**********************************
	private void admit(String un) {
		String ac=null;
		Scanner c=new Scanner(System.in);
		while(!ac.equals("e")) {
			System.out.println("please enter the account you want to edit or e to exit");
			ac=c.nextLine();
			if(ac.equals("e"))break;
			if(this.HM.containsKey(ac)){
				System.out.println("How can I help you? V to view, T transfer, A to approve, C to close account, other keys to exit.");
				String op=c.nextLine();
				switch(op) {
				case "V": view(ac);break;
				case "T": transfer(un,ac);break;
				case "A": appove(ac);break;
				case "C": cancel(ac);break;
				default:ac="e"; break; 
				}
			}else {
				System.out.println("invalid account.");
			}c.close();
		}

	}


	//***************if is employee give option to view and approve account*************************************************
	private void employee() {
		String ac=null;
		Scanner c=new Scanner(System.in);
		while(!ac.equals("e")) {
			System.out.println("please enter the account you want to edit or e to exit");
			ac=c.nextLine();
			if(ac.equals("e"))break;
			if(this.HM.containsKey(ac)){
				System.out.println("How can I help you? enter V to view, A to approve, other keys to exit.");
				String op=c.nextLine();
				switch(op) {
				case "V": view(ac);break;
				case "A": appove(ac);break;
				default:ac="e"; 
				}
			}else {
				System.out.println("invalid account.");
			}
		}c.close();
	}


	//***************if is customer give option to view and transfer account*************************************************
	private void customer(String un) {
		String sw=null;
		Scanner c=new Scanner(System.in);
		while(!sw.equals("e")) {
			System.out.println("Hello.please enter V to view, T to transfer, other keys to exit.");
			String op=c.nextLine();
			switch(op) {
			case "V": view(un);break;
			case "T": transfer(un);break;
			default:sw="e";
			}
		}c.close();
	}
	private void view(String un) {
		user user=this.HM.get(un);
		System.out.println("Account number:---------------------------"+user.userName+"/n=================================================");
		System.out.println("User's first and last name:---------------"+user.fN+" "+user.lN +"/n=================================================");
		System.out.println("User2's first and last name if apply------"+user.f2N+" "+user.l2N +"/n=================================================");
		System.out.println("Account type:-----------------------------"+user.acctype+"/n=================================================");
		System.out.println("Balance     :-----------------------------"+user.balance+"/n=================================================");
		System.out.println("Account activity");
		for(String actv:user.activity) {
			System.out.println(actv);
		}
	}
	private void transfer(String uc) {
		user user=this.HM.get(uc);

		Scanner c=new Scanner(System.in);
		System.out.println("Hello.please enter T to transfer, D to deposit, W to with drow, other keys to exit.");
		String op=c.nextLine();
		switch(op) {
		case "T":
			System.out.println("Please enter the amount");
			while(true) {
				try {
					double amount=c.nextDouble();
					if(amount>user.balance)throw new Exception("don't have enough money");
					if(amount<0)throw new Exception("number can't be negative");
					user tUser=getUserName();
					if(tUser.equals(null))break;
					tUser.balance=tUser.balance+amount;
					tUser.activity.add("from "+uc+"by "+uc+" "+amount);
					this.HM.put(tUser.userName,tUser);
					user.balance=user.balance-amount;
					user.activity.add("from "+uc+"by "+uc+" -"+amount);
					this.HM.put(user.userName,user);
				}catch(Exception e) {
					System.out.println("invalid number, enter e to exit, or any key to continue");
					String ex=c.nextLine();
					if(ex.equals("e"))break;
				}
			}
		case "D":
			System.out.println("Please enter the amount");
			while(true) {
				try {
					double amount=c.nextDouble();
					user.balance=user.balance+amount;
					user.activity.add(("from "+uc+"by "+uc+" "+amount));
					this.HM.put(user.userName,user);
				}catch(Exception e) {
					System.out.println("invalid number, enter e to exit, or any key to continue");
					String ex=c.nextLine();
					if(ex.equals("e"))break;
				}
			}
		case "W":
			System.out.println("Please enter the amount");
			while(true) {
				try {
					double amount=c.nextDouble();
					if(amount>user.balance)throw new Exception("don't have enough money");
					user.balance=user.balance-amount;
					user.activity.add(("from "+uc+"by "+uc+" -"+amount));
					this.HM.put(user.userName,user);
				}catch(Exception e) {
					System.out.println("invalid number, enter e to exit, or any key to continue");
					String ex=c.nextLine();
					if(ex.equals("e"))break;
				}
			}
		default:System.out.println("Have a nice day");
		}c.close();

	}
	private void transfer(String uc, String ac) {
		user user=this.HM.get(uc);

		Scanner c=new Scanner(System.in);
		System.out.println("Hello.please enter T to transfer, D to deposit, W to with drow, other keys to exit.");
		String op=c.nextLine();
		switch(op) {
		case "T":
			System.out.println("Please enter the amount");
			while(true) {
				try {
					double amount=c.nextDouble();
					if(amount>user.balance)throw new Exception("don't have enough money");
					if(amount<0)throw new Exception("number can't be negative");
					user tUser=getUserName();
					if(tUser.equals(null))break;
					tUser.balance=tUser.balance+amount;
					tUser.activity.add("from "+uc+"by "+ac+" "+amount);
					this.HM.put(tUser.userName,tUser);
					user.balance=user.balance-amount;
					user.activity.add("from "+uc+"by "+ac+" -"+amount);
					this.HM.put(user.userName,user);
				}catch(Exception e) {
					System.out.println("invalid number, enter e to exit, or any key to continue");
					String ex=c.nextLine();
					if(ex.equals("e"))break;
				}
			}
		case "D":
			System.out.println("Please enter the amount");
			while(true) {
				try {
					double amount=c.nextDouble();
					user.balance=user.balance+amount;
					user.activity.add("from "+uc+"by "+ac+" "+amount);
					this.HM.put(user.userName,user);
				}catch(Exception e) {
					System.out.println("invalid number, enter e to exit, or any key to continue");
					String ex=c.nextLine();
					if(ex.equals("e"))break;
				}
			}
		case "W":
			System.out.println("Please enter the amount");
			while(true) {
				try {
					double amount=c.nextDouble();
					if(amount>user.balance)throw new Exception("don't have enough money");
					user.balance=user.balance-amount;
					user.activity.add("from "+uc+"by "+ac+" -"+amount);
					this.HM.put(user.userName,user);
				}catch(Exception e) {
					System.out.println("invalid number, enter e to exit, or any key to continue");
					String ex=c.nextLine();
					if(ex.equals("e"))break;
				}
			}
		default:System.out.println("Have a nice day");
		}c.close();

	}

	private user getUserName() {
		Scanner c=new Scanner(System.in);
		while(true) {
			System.out.println("Please enter the target account name");
			String tac=c.nextLine();
			try {
				if(!this.HM.containsKey(tac)) throw new Exception("account cant found");
				System.out.println("Your target account name is "+tac);
				c.close();
				return this.HM.get(tac);
			}catch(Exception e){
				System.out.println("invalid account, enter e to exit, or anykey to continue");
				String ex=c.nextLine();
				c.close();
				if(ex.equals("e")) return null;
			}
		}
	}

	private void appove(String ac) {
		user user=this.HM.get(ac);
		user.acctype='u';
		this.HM.put(ac,user);
		System.out.println("New account has been approved.");
	}
	private void cancel(String ac) {
		this.HM.remove(ac);
		System.out.println("This account has been removed.");
	}
}






























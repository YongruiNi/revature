package myproj;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.Arrays;


class dothing {
	private HashMap <String, user>HM=new HashMap<String,user>();
	public static Scanner c = new Scanner(System.in);
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
			user.acctype=s[7].charAt(0);
			//user.activity=Arrays.asList(s[8].split("\\."));
				user.balance=Double.valueOf(s[5].toString());
				user.f2N=s[5];
				user.l2N=s[4];
				user.fN=s[3];
				user.lN=s[2];
				user.Pw=s[1];
				user.userName=s[0];
			HMOut.put(entry.getKey(), user);
		}

		System.out.println("file in");
		return HMOut;
	}

	//***************transfer HashMap<Str,user> to HashMap<Str,user> and save***********************************************		
	static void saVe(HashMap <String, user> HMout)
	{
		HashMap <String, String>HM=new HashMap<String, String>();
		String delim = ".";
		for (Entry<String, user> entry : HMout.entrySet()) {

			String inS=entry.getValue().Pw+"|"+entry.getValue().lN+"|"+entry.getValue().fN+"|"+entry.getValue().l2N+"|"+entry.getValue().f2N+"|"+
					String.valueOf(entry.getValue().balance)+"|"+entry.getValue().userName+"|"+entry.getValue().acctype;
					//+"|"+String.join(delim, entry.getValue().activity);
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
		this.HM=MyHM;
		
		while(!MyHM.containsKey(un)||!MyHM.get(un).Pw.equals(Pw)) {
				System.out.println("user name and password not match please try again UNF");
				System.out.println("enter e to exit or any key to continue");
		        if(c.nextLine().equals("e")) return;
		        System.out.println("please enter Username");
				un=c.nextLine();
				System.out.println("please enter password");
				Pw=c.nextLine();
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
		String ac="(*_*)";
		while(!ac.equals("e")) {
			System.out.println("please enter the account you want to edit or e to exit");
			if(ac.equals("e")) { return;}
			ac=c.nextLine();
			if(this.HM.containsKey(ac)){
				System.out.println("How can I help you? V to view, T transfer, A to approve, C to close account, other keys to exit.");
				String op=c.nextLine();
				switch(op) {
				case "V": view(ac);ac="e";break;
				case "T": transfer(ac);ac="e";break;
				case "A": appove(ac);ac="e";break;
				case "C": cancel(ac);ac="e";break;
				default:ac="e";break;
				}
			}else System.out.println("invalid account.");
		}

	}


	//***************if is employee give option to view and approve account*************************************************
	private void employee() {
		String ac="(*_*)";
		while(!ac.equals("e")) {
			System.out.println("please enter the account you want to edit or e to exit");
			if(ac.equals("e")) { return;}
				else System.out.println("invalid account.");
			ac=c.nextLine();
			if(this.HM.containsKey(ac)){
				System.out.println("How can I help you? V to view, T transfer, A to approve, C to close account, other keys to exit.");
				String op=c.nextLine();
				switch(op) {
				case "V": view(ac);break;
				case "A": appove(ac);break;
				default:break;
				}
			}
		}
	}


	//***************if is customer give option to view and transfer account*************************************************
	private void customer(String un) {
		String sw="(-_-||)";
		while(!sw.equals("e")) {
			System.out.println("Hello.please enter V to view, T to transfer, other keys to exit.");
			String op=c.nextLine();
			switch(op) {
			case "V": view(un);sw="e";break;
			case "T": transfer(un);sw="e";break;
			default:sw="e";
			}
		}
	}
	public void view(String un) {
		System.out.println(un);
		user user=this.HM.get(un);
		System.out.println("Account number:---------------------------"+user.userName+"\n=================================================");
		System.out.println("User's first and last name:---------------"+user.fN+" "+user.lN +"\n=================================================");
		System.out.println("User2's first and last name if apply------"+user.f2N+" "+user.l2N +"\n=================================================");
		System.out.println("Account type:-----------------------------"+user.acctype+"\n=================================================");
		System.out.println("Balance     :-----------------------------"+user.balance+"\n=================================================");
		//System.out.println("Account activity");
		/*for(String actv:user.activity) {
			System.out.println(actv);
		}*/
	}
	private void transfer(String uc) {
		
		user user=this.HM.get(uc);
		System.out.println("Hello.please enter T to transfer, D to deposit, W to with drow, other keys to exit.");
		String op=c.next();
		switch(op) {
		case "T":
			System.out.println("Please enter the amount");
			System.out.println("A");
			while(true) {
				try {
					double amount=c.nextDouble();
					if(amount>user.balance)throw new Exception();
					if(amount<0)throw new Exception();
					
/*******************************************Otherwise keep going**************************************/					
					
					while(true) {
						
						System.out.println("D");
						System.out.println("Please enter the target account name");
						String ct=c.next();
						
						user tUser;
						System.out.println(ct);
						System.out.println(HM.containsKey(ct));
						if(HM.containsKey(ct)==true) {
							System.out.println(ct);
							tUser=HM.get(ct);
							System.out.println(tUser.balance);
							System.out.println("1");
							tUser.balance+=amount;
							System.out.println("2");
							//String s="from "+uc+" by "+uc+" "+String.valueOf(amount);
							System.out.println("2.5");
							//tUser.activity.add(s);
							System.out.println("3");
							this.HM.put(tUser.userName,tUser);
							System.out.println("4");
							user.balance-=amount;
							//user.activity.add("from "+uc+"by "+uc+" -"+String.valueOf(amount));
							this.HM.put(user.userName,user);
							System.out.println("Done");
							break;
						}else {
							System.out.println("C");
							System.out.println("target account no found, please enter again or e to exit");
							String ex=c.next();
							if(ex.equals("e")) break;
							else tUser=HM.get(ct); 
						}
					}
				}catch(Exception e) {
					System.out.println("B");
					System.out.println("invalid number, enter e to exit, or enter a positive number that less than you balance to continue");
					String eo="T_T";
					if (!c.hasNextDouble()) {eo=c.next();
					}
					if(eo.equals("e"))return;
				}
			}
		case "D":
			while(true) {
				System.out.println("Please enter the valid amount");
				try {
					double amount=c.nextDouble();
					if(amount<0)throw new Exception();
					user.balance+=amount;
					this.HM.put(user.userName,user);
					System.out.println("Done");
				}catch(Exception e) {
					System.out.println("invalid number, enter e to exit, or enter a positive");
					String eo="T_T";
					if (!c.hasNextDouble()) {eo=c.next();
					}
					if(eo.equals("e"))return;
				}
			}
		case "W":
			while(true) {
				System.out.println("Please enter the valid amount");
				try {
					double amount=c.nextDouble();
					if(amount>user.balance)throw new Exception("don't have enough money");
					user.balance-=amount;
					//user.activity.add(("from "+uc+"by "+uc+" -"+amount));
					this.HM.put(user.userName,user);
				}catch(Exception e) {
					System.out.println("invalid number, enter e to exit, or enter a positive");
					String eo="T_T";
					if (!c.hasNextDouble()) {eo=c.next();
					}
					if(eo.equals("e"))return;
				}
			}
		default:System.out.println("Have a nice day");
		}c.close();

	}


	public void appove(String ac) {
		user user=this.HM.get(ac);
		user.acctype='u';
		this.HM.put(ac,user);
		System.out.println("New account has been approved.");
	}
	public void cancel(String ac) {
		this.HM.remove(ac);
		System.out.println("This account has been removed.");
	}
	
	public static void main(String[] args) {
		HashMap<String,user> HMtest=new HashMap<String, user>();
		HMtest=diseri();
		String tst1="me";
		String test2="user1";
		String test3="employee";
		
		System.out.println("acctype :"+HMtest.get(tst1).acctype);
		System.out.println("balance :"+HMtest.get(tst1).balance);
		System.out.println("fN      :"+HMtest.get(tst1).fN);
		System.out.println("Pw      :"+HMtest.get(tst1).Pw);
		//System.out.println("activity:"+HMtest.get(tst1).activity);
		System.out.println("acctype :"+HMtest.get(test2).acctype);
		System.out.println("balance :"+HMtest.get(test2).balance);
		System.out.println("fN      :"+HMtest.get(test2).fN);
		System.out.println("Pw      :"+HMtest.get(test2).Pw);
		//System.out.println("activity:"+HMtest.get(test2).activity);
		System.out.println("acctype :"+HMtest.get(test3).acctype);
		System.out.println("balance :"+HMtest.get(test3).balance);
		System.out.println("fN      :"+HMtest.get(test3).fN);
		System.out.println("Pw      :"+HMtest.get(test3).Pw);
		//System.out.println("activity:"+HMtest.get(test3).activity);
		System.out.println("activity:"+HMtest.get("Nuser").acctype);
	

}
}






























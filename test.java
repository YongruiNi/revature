package myproj;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class test {

	public static void main(String[] args) {

		try {
			HashMap <String, String>HM=new HashMap<String, String>();
			HM.put("me", "000|Ni|Yongrui|Na|Na|10000|a|me");
			HM.put("employee", "001|E1|Yongrui|Na|Na|5|e");
			HM.put("user1", "002|U1|Yon|Na|Na|100|u|itsme");
			HM.put("user2", "003|U2|gru|Na|Na|200|u|itsme");
			HM.put("Nuser", "002|NU|rui|Na|Na|300|n|itsme");
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
		//*********************test diseri**************************
		HashMap<String,user> HMtest=new HashMap<String, user>();
		HMtest=diseri();
		System.out.println("acctype :"+HMtest.get("me").acctype);
		System.out.println("balance :"+HMtest.get("me").balance);
		System.out.println("fN      :"+HMtest.get("me").fN);
		System.out.println("Pw      :"+HMtest.get("me").Pw);
		//System.out.println("activity:"+HMtest.get("me").activity);
		//*********************test save****************************
		saVe(HMtest);
		System.out.println("acctype :"+HMtest.get("me").acctype);
		System.out.println("balance :"+HMtest.get("me").balance);
		System.out.println("fN      :"+HMtest.get("me").fN);
		System.out.println("Pw      :"+HMtest.get("me").Pw);
		//System.out.println("activity:"+HMtest.get("me").activity);

	}

	
	/*
	 
	 */
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
		//user.activity=Arrays.asList(s[8].split("\\."));
			user.balance=Double.valueOf(s[5].toString());
			user.f2N=s[4];
			user.l2N=s[3];
			user.fN=s[2];
			user.lN=s[1];
			user.Pw=s[0];
			user.userName=s[7];
			HMOut.put(entry.getKey(), user);
		}
         
    
		return HMOut;
	}
	static void saVe(HashMap <String, user> HMout)
	{
		HashMap <String, String>HM=new HashMap<String, String>();
		//String delim = ".";
		for (Entry<String, user> entry : HMout.entrySet()) {

			String inS=entry.getValue().Pw+"|"+entry.getValue().lN+"|"+entry.getValue().fN+"|"+entry.getValue().l2N+"|"+entry.getValue().f2N+"|"+
					String.valueOf(entry.getValue().balance)+entry.getValue().acctype+"|"+entry.getValue().userName;
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
	}

}


package another;


import java.util.Scanner;

public class newuser {
	
	
	newuser(){
		Scanner c= new Scanner(System.in);
		System.out.println("Please enter you User name.______________________________________________________________|");
		String username=c.next();
		while(methes.checktype(username,Start.conn)!=0)  {
			System.out.println("This account name have been taken, please try others OR enter E to exit._________________|");
			username=c.next();
			if(username.equals("E")) {
				c.close();
				return;
			}
		}
		System.out.println("Please enter account type, i for individuel account, j for joint account_________________|");
		String type=c.next();
		
		while(!type.equals("i")&&(!type.equals("j")))  {
			System.out.println("invalid input, please enter i for individuel account, j for joint account or E to exit___|");
			type=c.next(type);
			if(type.equals("E")) {
			c.close();
			return;}
		}
	
		switch(type)
		{
		case "i":
			System.out.println("Please enter pass word___________________________________________________________________|");
			String Pw=c.next();
			System.out.println("Please enter your first name_____________________________________________________________|");
			String fN=c.next();
			System.out.println("Please enter your last name______________________________________________________________|");
			String lN=c.next();
			methes.ISNU(username,Pw,fN,lN);
			System.out.println("Application submited, Thank you for using________________________________________________|");
			c.close();
			break;
		case "j":
			System.out.println("Please enter pass word___________________________________________________________________|");
			Pw=c.next();
			System.out.println("Please enter your first name_____________________________________________________________|");
			fN=c.next();
			System.out.println("Please enter your last name______________________________________________________________|");
			lN=c.next();
			System.out.println("Please enter secend user first name______________________________________________________|");
			String f2N=c.next();
			System.out.println("Please enter secend user last name_______________________________________________________|");
			String l2N=c.next();
			methes.ISNU(username,Pw,fN,lN,f2N,l2N); 
			System.out.println("Application submited, Thank you for using________________________________________________|");
			c.close();
			break;
		}

	}


	
}

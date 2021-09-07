package myproj;

import java.util.Scanner;

public class Start {

	public static void main(String[] args){
		Scanner input= new Scanner(System.in);
		System.out.println("Welcome to the login page. Please enter User name or enter 'n' for now user.");
		String inPut=input.nextLine();

		if(inPut.equals("n")) {
			new newUser();
		}else {
			System.out.println("Please enter your pass word");
			String pw=input.nextLine();
			System.out.println(inPut+" "+pw);
			new dothing(inPut,pw);
		}
		input.close();
	}
}



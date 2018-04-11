/*
Name:	MorraApp2.java
Author:	Jacek Smolen
Date:	05/04/2018
*/

import javax.swing.JOptionPane;//import tools for input/output window
public class MorraApp2{
	public static void main(String args[]){

		//variables
		int ctrl;			//control integer - this number controls the flow of program and links intantiable class to the app
		int fingers;		//number of fingers - player input
		String message;		//message to output
		int choice;			//user confirm dialog - YES=0, NO=1, CANCEL=2
		String history;


		//objects
		Morra2 myMorra2;
		myMorra2=new Morra2();


		while(true){

			ctrl=myMorra2.getCtrl();//get control integer value (program control)
			switch (ctrl){


				case 0://GAME START

				//ask questions (user input) and send them to the instatiable class
				message=myMorra2.getMessage();
				choice=JOptionPane.showConfirmDialog(null,message);
				myMorra2.setChoice(choice);

				fingers=Integer.parseInt(JOptionPane.showInputDialog(null,"Choose a number of fingers to play (0-10)"));
				myMorra2.setFingers(fingers);

				myMorra2.round();//execute the round
				message=myMorra2.getMessage();
				JOptionPane.showMessageDialog(null,message);//show who won, bot choice and current score
				break;


				case 1://ANOTHER ROUND

				fingers=Integer.parseInt(JOptionPane.showInputDialog(null,"Choose a number of fingers to play (0-10)"));
				myMorra2.setFingers(fingers);
				myMorra2.round();//execute the round
				message=myMorra2.getMessage();
				JOptionPane.showMessageDialog(null,message);//show who won, bot choice and current score
				break;


				case 2://ROUND END

				myMorra2.finalRound();
				message=myMorra2.getMessage();
				history=myMorra2.getHistory();
				System.out.println(history);
				choice=JOptionPane.showConfirmDialog(null, message);//show who won the game and ask if you want to play another one
				myMorra2.setChoice(choice);
				break;


				case 3://ANOTHER GAME?

				myMorra2.game();
				break;


				case 4://QUIT GAME

				message=myMorra2.getMessage();
				history=myMorra2.getHistory();
				System.out.println(history);
				JOptionPane.showMessageDialog(null,message);
				System.exit(0);


			}

		}

	}
}
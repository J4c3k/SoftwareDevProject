/*
Name:	Morra2.java
Author:	Jacek Smolen
Date:	05/04/2018
*/

import java.util.*;				//need random number
public class Morra2{



	//Data Members

	//shared - data exchanged with the app
	private int ctrl;			//control integer - this number controls the flow of program and links intantiable class to the app
	private int fingers;		//number of fingers - player input
	private String message;		//message to output
	private int choice;			//user confirm dialog - YES=0, NO=1, CANCEL=2


	//local - data only used by this intantiable class
	private int rn;				//bot choice - random number 0-10
	private int sum;			//sum of fingers and rn
	private int playerScore;	//player score
	private int botScore;		//bot score
	private int roundCnt;		//round count
	private int gameCnt;		//game count
	private int [][][] array;	//array holding all the games history (player fingers and bot fingers)
	private String history;		//temp String storage for history values
	private int roundsWon;		//rounds won by human player (game end history)
	private int roundsLost;		//rounds lost by human player (game end history)
	private int playerEven;		//number of even numbers played by player (game end history)
	private int playerOdd;		//number of odd numbers played by player (game end history)
	private int botEven;		//number of even numbers played by bot (game end history)
	private int botOdd;			//number of odd numbers played by bot (game end history)
	private int playerExtra;	//extra points earned by player (game end history)
	private int botExtra;		//extra points earned by bot (game end history)

	//Constructor

	public Morra2(){
		fingers=0;
		rn=0;
		sum=0;
		message="Welcome to Morra Game. Do you want to play as odd or even? (YES=ODD, NO=EVEN)";
		playerScore=0;
		botScore=0;
		ctrl=0;
		choice=0;
		roundCnt=0;
		gameCnt=0;
		array=new int[3][10][10];
		history="";
		roundsWon=0;
		roundsLost=0;
		playerEven=0;
		playerOdd=0;
		botEven=0;
		botOdd=0;
		playerExtra=0;
		botExtra=0;


	}


	//Setters

	public void setChoice(int choice){
		this.choice=choice;
		if (choice==2){
			System.exit(0);
		}

	}

	public void setFingers(int fingers){
		this.fingers=fingers;
		if (fingers>10){
			fingers=10;
		}
		else if (fingers<0){
			fingers=0;
		}
	}


	//Getters

	public int getCtrl(){
		return ctrl;
	}

	public String getMessage(){
		return message;
	}

	public String getHistory(){
			return history;
	}








	//Round

	public void round(){

		rn=(int)Math.round(Math.random()*10);//generate a random integer from 0 to 10
		sum=fingers+rn;//sum

		if((sum%2)==0&&choice==1||((sum%2)==1&&choice==0)){//player wins
			playerScore=playerScore+2;
			message="You win! ";
			array[2][roundCnt][gameCnt]=1;//add to array - player wins
		}
		else{//bot wins
			botScore=botScore+2;
			message="You lose! ";
			array[2][roundCnt][gameCnt]=2;//add to array - player looses
		}

		if(fingers>rn){//player gets extra point
			playerScore++;
		}

		else if(rn>fingers){//bot gets extra point
			botScore++;
		}
		message=(message+"Bot choice was: " + rn + " Player Score: " + playerScore + " Bot Score: " + botScore);//prepare message for round end

		if (playerScore<6&&botScore<6){
			ctrl=1;
		}
		else{
			ctrl=2;
		}
		array[0][roundCnt][gameCnt]=fingers;//add player choice to the historian array
		array[1][roundCnt][gameCnt]=rn;//add bot choice to the historian array
		roundCnt++;//index round count

	}




	//Last round - set the game end message and reset counters

	public void finalRound(){
		history="       Rounds\n";
		for (int n=0;n<=1;n++){//player loop
			if (n==0){
				history=history+"Player |";
			}
			else{
				history=history+"Bot    |";
			}

			for(int i=0;i<roundCnt;i++){//round loop
				if (array[n][i][gameCnt]==10){
					history=history+Integer.toString(array[n][i][gameCnt])+"|";
				}
				else{
					history=history+Integer.toString(array[n][i][gameCnt])+" |";
				}
			}
			history=history+'\n';
		}


		if(playerScore>botScore){
			message="Congratulations! You have won the game! Do you want to play another one?";

		}

		else if(botScore>playerScore){
			message="You lost the game. Better luck next time. Do you want to play another one?";

		}

		else{
			message="Its a tie! Do you want to play another game?";
		}


		playerScore=0;
		botScore=0;
		ctrl=3;
		roundCnt=0;
		gameCnt++;


	}





	//another game or finish control

	public void game(){
		if(choice==0){
			message="Do you want to play as odd or even? (YES=ODD, NO=EVEN)";
			ctrl=0;
		}
		else if(choice==1){
			message="THE END! Game history in the console.";
			history="HISTORY OF GAMES PLAYED:\n\n";

			for(int i=0;i<gameCnt;i++){//game loop

				for(int n=0;n<array[2].length;n++){//round loop

					if (array[2][n][i]==1){//rounds won in one game
						roundsWon++;
					}
					else if (array[2][n][i]==2){//rounds lost in one game
						roundsLost++;
					}
					if (array[0][n][i]>array[1][n][i]){//player extra points in one game
						playerExtra++;
					}
					else if (array[0][n][i]<array[1][n][i]){//bot extra points in one game
						botExtra++;
					}
					if (array[0][n][i]!=0&&array[0][n][i]%2==0){//player even numbers in one game
						playerEven++;
					}
					else if (array[0][n][i]%2==1){//player odd numbers in one game
						playerOdd++;
					}
					if (array[1][n][i]!=0&&array[1][n][i]%2==0){//bot even numbers in one game
						botEven++;
					}
					else if (array[1][n][i]%2==1){//bot odd numbers in one game
						botOdd++;
					}
				}


				history=history+"Game "+(i+1)+"\nRounds won by player: "+roundsWon+"\nRounds lost by player: "+roundsLost;
				history=history+"\nEven numbers player: "+playerEven+"\nEven numbers bot: "+botEven+"\nOdd numbers player: ";
				history=history+playerOdd+"\nOdd numbers bot: "+botOdd+"\nExtra points player: "+playerExtra+"\nExtra points bot: "+botExtra+"\n\n";
				roundsWon=0;
				roundsLost=0;
				playerExtra=0;
				botExtra=0;
				playerEven=0;
				playerOdd=0;
				botEven=0;
				botOdd=0;


			}

			ctrl=4;
		}
		else{
			System.exit(0);
		}
	}
}



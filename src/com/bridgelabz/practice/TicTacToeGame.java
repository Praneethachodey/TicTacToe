package com.bridgelabz.practice;

import java.util.Scanner;

public class TicTacToeGame {

	// uc1-creating board and returning it
	public static char[] createBoard() {
		char[] board = new char[10];
		for (int position = 1; position <= 9; position++) {
			board[position] = ' ';
		}
		return board;
	}

	// uc2-ability to choose a letter for the player
	public static char chooseLetter() {
		System.out.println("choose X or O");
		Scanner scan = new Scanner(System.in);
		String letter = scan.next();
		while (!letter.equalsIgnoreCase("x") && !letter.equalsIgnoreCase("o")) {
			System.out.println("please enter correct letter");
			scan = new Scanner(System.in);
			letter = scan.next();
		}
		scan.close();
		return letter.toUpperCase().charAt(0);
	}
	
	
	//uc3-show-board
	public static void showBoard(char[] board)
	{
		for(int rowJump=0;rowJump<=6;rowJump=rowJump+3) {
		for(int column=1;column<=3;column++)
			System.out.print(board[rowJump+column]+ " ");
		System.out.println();
		}
	}

	public static void main(String[] args) {
		char[] board = createBoard();
		char playerLetter = chooseLetter();
		System.out.println("letter chosen by player : " +playerLetter);
		char computerLetter;
		if(playerLetter=='X')
			computerLetter='O';
		else
			computerLetter='X';	
		showBoard(board);
	}

}

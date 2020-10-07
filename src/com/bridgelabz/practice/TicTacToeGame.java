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
	public static char chooseLetter(Scanner scan) {
		System.out.println("choose X or O");
		String letter = scan.next();
		while (!letter.equalsIgnoreCase("x") && !letter.equalsIgnoreCase("o")) {
			System.out.println("please enter correct letter : x or o");
			scan = new Scanner(System.in);
			letter = scan.next();
		}
		return letter.toUpperCase().charAt(0);
	}

	// uc3-show-board
	public static void showBoard(char[] board) {
		for (int rowJump = 0; rowJump <= 6; rowJump = rowJump + 3) {
			for (int column = 1; column <= 3; column++) {
				System.out.print(board[rowJump + column]);
				if (column < 3)
					System.out.print("|");
			}
			if (rowJump < 6)
				System.out.println("\n-----");
		}
		System.out.println();
	}

	// uc4-ability to take user input to take a move
	public static char[] userInputMove(char[] board, Scanner userInput, char playerLetter) {
		System.out.println("Enter the index from 1-9 to make a move");
		int position = userInput.nextInt();
		while (position >= 10 || position <= 0) {
			System.out.println("enter correct position between 1 and 9");
			userInput = new Scanner(System.in);
			position = userInput.nextInt();
		}
		while (board[position] != ' ') {
			System.out.println("the position is already occupied");
			userInput = new Scanner(System.in);
			position = userInput.nextInt();
		}
		board[position] = playerLetter;
		userInput.close();
		return board;
	}

	//to check who plays first, computer or user
	public static String tossForGameStart(Scanner userInput) {
		System.out.println("choose 0 for heads or 1 fo tails");
		int userSelection=userInput.nextInt();
		int toss=(int)((Math.random()*10)%2);
		if(toss==userSelection)return "player";
		else return "computer";
	}
	
	public static void main(String[] args) {
		char[] board = createBoard();
		Scanner userInput = new Scanner(System.in);
		char playerLetter = chooseLetter(userInput);
		System.out.println("letter chosen by player : " + playerLetter);
		char computerLetter;
		if (playerLetter == 'X')
			computerLetter = 'O';
		else
			computerLetter = 'X';
		showBoard(board);
		userInput = new Scanner(System.in);
		String playFirst = tossForGameStart(userInput);
		System.out.println(playFirst +" plays first");
		userInput = new Scanner(System.in);
		board = userInputMove(board, userInput, playerLetter);
		showBoard(board);
		userInput.close();

	}

}

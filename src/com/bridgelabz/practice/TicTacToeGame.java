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
		String letter = scan.next();
		while (!letter.equalsIgnoreCase("x") && !letter.equalsIgnoreCase("o")) {
			System.out.println("please enter correct letter");
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
	public static char[] userInputMove(char[] board,Scanner userInput ,char playerLetter) {
		int position = Integer.parseInt(userInput.next());
		board[position] = playerLetter;
		userInput.close();
		return board;
	}

	public static void main(String[] args) {
		char[] board = createBoard();
		System.out.println("choose X or O");
		Scanner userInput = new Scanner(System.in);
		char playerLetter = chooseLetter(userInput);
		System.out.println("letter chosen by player : " + playerLetter);
		char computerLetter;
		if (playerLetter == 'X')
			computerLetter = 'O';
		else
			computerLetter = 'X';
		showBoard(board);
		System.out.println("Enter the index from 1-9 to make a move");
		userInput=new Scanner(System.in);
		board = userInputMove(board,userInput,playerLetter);
		showBoard(board);
		userInput.close();

	}

}

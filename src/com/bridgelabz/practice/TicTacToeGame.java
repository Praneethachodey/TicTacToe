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
			position = userInput.nextInt();
		}
		while (!isEmpty(board, position)) {
			System.out.println("the position is already occupied");
			position = userInput.nextInt();
		}
		board[position] = playerLetter;
		userInput.close();
		return board;
	}

	// to check if the position is empty
	public static boolean isEmpty(char[] board, int index) {
		return board[index] == ' ';
	}

	// to check who plays first, computer or user
	public static String tossForGameStart(Scanner userInput) {
		System.out.println("choose 0 for heads or 1 fo tails");
		int userSelection = userInput.nextInt();
		int toss = (int) (Math.floor(Math.random() * 10) % 2);
		if (toss == userSelection)
			return "player";
		else
			return "computer";
	}

	// to check for winner
	public static boolean isWinner(char[] board, char symbol) {
		for (int row = 1; row <= 6; row = row + 3) {
			if (board[row] == symbol && board[row] == board[row + 1] && board[row] == board[row + 2])
				return true;
		}
		for (int column = 1; column <= 3; column++) {
			if (board[column] == symbol && board[column] == board[column + 3] && board[column] == board[column + 6])
				return true;
		}
		if (board[1] == symbol && board[1] == board[5] && board[1] == board[9])
			return true;
		if (board[3] == symbol && board[3] == board[5] && board[3] == board[7])
			return true;
		return false;
	}

	// check for wining index
	public static int winningIndex(char[] board, char letter) {
		for (int index = 1; index <= 9; index++) {
			if (isEmpty(board, index)) {
				board[index] = letter;
				if (isWinner(board, letter)) {
					board[index] = ' ';
					return index;
				}
				board[index] = ' ';
			}
		}
		return 0;
	}

	// computer play
	public static boolean computerPlay(char[] board, char computerLetter, char playerLetter) {
		int index = 0;
		index = winningIndex(board, computerLetter);
		if (index != 0) {
			board[index] = computerLetter;
			return true;
		}
		int blockIndex = toBlockOponent(board, playerLetter);
		if (blockIndex != 0) {
			board[blockIndex] = computerLetter;
			return false;
		}
		index = chooseCorner(board);
		System.out.println("corner : " + index);
		if (index == 0) {
			index = centerOrSides(board);
		}
		board[index] = computerLetter;
		showBoard(board);
		return false;
	}

	// ability to choose one of the corners
	public static int chooseCorner(char[] board) {
		int[] corners = { 1, 3, 7, 9 };
		for (int index = 0; index <= 3; index++)
			if (isEmpty(board, corners[index]))
				return corners[index];
		return 0;
	}

	// ability to choose center or sides
	public static int centerOrSides(char[] board) {
		if (isEmpty(board, 5))
			return 5;
		int[] sides = { 2, 4, 6, 8 };
		for (int index = 0; index <= 3; index++)
			if (isEmpty(board, sides[index]))
				return index;
		return 0;

	}

	// computer play to block opponent
	public static int toBlockOponent(char[] board, char playerLetter) {
		int index = 0;
		index = winningIndex(board, playerLetter);
		return index;
	}

	// to check for a tie
	public static boolean toCheckForTie(char[] board) {
		for (char entry : board) {
			if (entry == ' ')
				return false;
		}
		return true;
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
		String playFirst = tossForGameStart(userInput);
		System.out.println(playFirst + " plays first");
		board = userInputMove(board, userInput, playerLetter);
		showBoard(board);
		if (isWinner(board, playerLetter)) {
			System.out.println("player is the winner");
			return;
		}
		if (computerPlay(board, computerLetter, playerLetter)) {
			System.out.println("computer is the winner");
			return;
		}
		if (toCheckForTie(board))
			System.out.println("its a tie");
		else
			System.out.println("changing turn");
		userInput.close();

	}

}

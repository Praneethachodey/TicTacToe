package com.bridgelabz.practice;

public class TicTacToeGame {
	
	public char[] createBoard()
	{
		char[] board = new char[10];
		for(int i =1;i<=8;i++)
		{
			board[i]=' ';
		}
		return board;
	}

	public static void main(String[] args) {
		TicTacToeGame t = new TicTacToeGame();
		char[] board = t.createBoard();
		for(int i = 1;i<=9;i++)
		{
			System.out.println(board[i]);
		}
	}

}

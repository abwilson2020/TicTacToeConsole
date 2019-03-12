# TicTacToeConsole
Console application which plays tic-tac-toe. 

## Demonstration

## Logic

The bot plays with these given rules checked in the following order:
- If it is the bot's turn and it can win, make the winning move.
- If it is the bot's turn and the opponent will be able to win on their next move, block that move.
- If it is the bot's turn and there is a move which allows for 2 winning moves, make that move
- If it is the bot's turn and the opponent will be able to make a move which gives them 2 winning moves, block it.
- If it is the bot's turn take the move with the most potential winning moves.

## Getting Started

- To use: Download all files, run Main.java. The bot will play first as 'X', To make your selection type the number 1-9 of the space you would like to select, then press 'enter'. 

## Built With

- [IntelliJ Idea Ultimate](https://www.jetbrains.com/idea/)

## Author

- Created by Andrew Wilson,

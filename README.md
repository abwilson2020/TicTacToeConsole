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

- To use: Download all files, run Main.java. To make your selection type the number 1-9 of the space you would like to select, then press 'enter'. The game can be played against another player or against a bot. The bot can play first or second.

## Versions
1.0.0 General Tic Tac Toe gameplay. 2 players alternating turns.  
1.1.0 Added Bot player as replacement to 2 player mode.  
1.1.1 Fixed issue where bot would repeatedly try to play position 7.  
1.2.0 Added 2 player mode and allowed user to choose which mode they play.  
1.3.0 (CURRENT) Added scoreboard and ability for bot to play second.

## TO DO
- [X] 2 player functionality
- [x] Bot player
- [X] Ability to switch gameplay modes
- [X] Bot playing second
- [X] Game scoreboard
- [ ] Gameplay stats (wins/losses/games played/moves made)
- [ ] Gameplay GUI

## Built With

- [IntelliJ Idea Ultimate](https://www.jetbrains.com/idea/)

## Author

- Created by Andrew Wilson

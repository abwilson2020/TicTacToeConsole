import java.util.Scanner;


public class Main {

  private int counter;
  private int roundCounter = 0;
  private int pos1Val = 100;
  private int pos2Val = 100;
  private int pos3Val = 100;
  private int pos4Val = 100;
  private int pos5Val = 100;
  private int pos6Val = 100;
  private int pos7Val = 100;
  private int pos8Val = 100;
  private int pos9Val = 100;

  private char posn[] = new char[10];
  private char player;


  public static void main(String args[]) {
    int xWins = -1;
    int oWins = -1;
    int draw = -1;
    String ch;
    Main Game = new Main();
    Scanner in = new Scanner(System.in);
    String gameMode = Game.gameMode();
    boolean botFirst = Game.botFirst();
    do {
      System.out.println("Generating new game board");
      Game.newBoard();
      Game.play(gameMode, botFirst);
      Game.currentBoard();
      if(Game.checkWinner() == 'X'){
        xWins++;
        System.out.println("X wins the game.");
        System.out.println("Game Score: X: |" + (xWins+1) + "| O: |" + (oWins+1) + "| Draw: |" + (draw+1) + "|");
      } else if (Game.checkWinner() == 'O'){
        oWins++;
        System.out.println("O wins the game.");
        System.out.println("Game Score: X: |" + (xWins+1) + "| O: |" + (oWins+1) + "| Draw: |" + (draw+1) + "|");
      } else if (Game.checkWinner() == 'D'){
        draw = draw + 1;
        System.out.println("---------------");
        System.out.println(" Game is a draw");
        System.out.println("---------------");
        System.out.println("Game Score: X: |" + (xWins+1) + "| O: |" + (oWins+1) + "| Draw: |" + (draw+1) + "|");
      }
      System.out.println("");
      Game.roundCounter = 0;
      System.out.println("Would you like to play again (Enter 'yes'/'no')? ");
      ch = in.nextLine();
      if (ch.equalsIgnoreCase("yes")){
        System.out.print("Would you like to change game mode (Enter 'yes'/'no')?    Current mode is: ");
        if (gameMode.equalsIgnoreCase("yes")){
          System.out.println("Player vs Bot");
        } else {
          System.out.println("Player vs Player");
        }
        if(in.nextLine().equalsIgnoreCase("yes")){
          if (gameMode.equalsIgnoreCase("yes")){
            gameMode = "no";
          } else {
            gameMode = "yes";
          }
          if (gameMode.equalsIgnoreCase("yes")) {
            System.out.println("Would you like to change the order of play? (Enter 'yes'/'no')?");
            if (in.nextLine().equalsIgnoreCase("yes")) {
              botFirst = Game.botFirst();
            }
          }
        }
      }
      if (ch.equalsIgnoreCase("no")){
        System.out.println("Thanks for playing");
        System.exit(1);
      }
      //System.out.println("ch value is  " + ch);
    } while (ch.equalsIgnoreCase("yes"));
  } //end main

  public void newBoard() {

    char posndef[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    int i;
    counter = 0;
    player = 'X';
    for (i = 1; i < 10; i++) {
      posn[i] = posndef[i];
    }
    currentBoard();
  } //end newBoard

  public String currentBoard() {
    System.out.println("\n");
    System.out.println( posn[1] + " | " + posn[2] + " | " + posn[3]);
    System.out.println("-   -   - ");
    System.out.println(posn[4] + " | " + posn[5] + " | " + posn[6]);
    System.out.println("-   -   - ");
    System.out.println(posn[7] + " | " + posn[8] + " | " + posn[9]);
    System.out.println("-   -   - ");
    System.out.println("\n");
    return "currentBoard";
  } //end currentBoard

  public void play(String gameMode, boolean botFirst) {
    int spot;
    char blank = ' ';

    //System.out.println("Player " + getPlayer() + " will go first");
    if(gameMode.equalsIgnoreCase("yes") && botFirst){
      System.out.println("Bot goes first");
    } else if (gameMode.equalsIgnoreCase("yes")){
      System.out.println("Bot will play second");
    }
    System.out.println("--- BEGIN GAME ---");
    do {
      currentBoard();

      System.out.println("Player " + getPlayer() + " choose a position");
      System.out.println("--------------------------");
      System.out.println(" ");

      if (gameMode.equalsIgnoreCase("yes") && botFirst && getPlayer() == 'X'){ //checks to see if the bot should make the move
        boolean posTaken = true;
        while(posTaken){
          spot = AI(botFirst);
          posTaken = checkPosition(spot);
          if(posTaken == false){
            posn[spot] = getPlayer();
          }
        }
      } else if (gameMode.equalsIgnoreCase("yes") && !botFirst && getPlayer() == 'O'){
        boolean posTaken = true;
        while(posTaken){
          spot = AI(botFirst);
          posTaken = checkPosition(spot);
          if(posTaken == false){
            posn[spot] = getPlayer();
          }
        }
      } else { //If it is not the bot's turn, the player chooses
        boolean positionTaken = true;
        while (positionTaken) {
          Scanner in = new Scanner(System.in);
          spot = in.nextInt();
          positionTaken = checkPosition(spot);
          if (positionTaken == false) {
            posn[spot] = getPlayer();
          }
        }
      }
      nextPlayer();

    } while (checkWinner() == blank);

  } //end play

  public char checkWinner() {
    char Winner = ' ';

    // Check if X wins
    if (posn[1] == 'X' && posn[2] == 'X' && posn[3] == 'X') {
      Winner = 'X';
    }
    if (posn[4] == 'X' && posn[5] == 'X' && posn[6] == 'X') {
      Winner = 'X';
    }
    if (posn[7] == 'X' && posn[8] == 'X' && posn[9] == 'X') {
      Winner = 'X';
    }
    if (posn[1] == 'X' && posn[4] == 'X' && posn[7] == 'X') {
      Winner = 'X';
    }
    if (posn[2] == 'X' && posn[5] == 'X' && posn[8] == 'X') {
      Winner = 'X';
    }
    if (posn[3] == 'X' && posn[6] == 'X' && posn[9] == 'X') {
      Winner = 'X';
    }
    if (posn[1] == 'X' && posn[5] == 'X' && posn[9] == 'X') {
      Winner = 'X';
    }
    if (posn[3] == 'X' && posn[5] == 'X' && posn[7] == 'X') {
      Winner = 'X';
    }
    if (Winner == 'X') {
      return Winner;
    }

    // Check if O wins
    if (posn[1] == 'O' && posn[2] == 'O' && posn[3] == 'O') {
      Winner = 'O';
    }
    if (posn[4] == 'O' && posn[5] == 'O' && posn[6] == 'O') {
      Winner = 'O';
    }
    if (posn[7] == 'O' && posn[8] == 'O' && posn[9] == 'O') {
      Winner = 'O';
    }
    if (posn[1] == 'O' && posn[4] == 'O' && posn[7] == 'O') {
      Winner = 'O';
    }
    if (posn[2] == 'O' && posn[5] == 'O' && posn[8] == 'O') {
      Winner = 'O';
    }
    if (posn[3] == 'O' && posn[6] == 'O' && posn[9] == 'O') {
      Winner = 'O';
    }
    if (posn[1] == 'O' && posn[5] == 'O' && posn[9] == 'O') {
      Winner = 'O';
    }
    if (posn[3] == 'O' && posn[5] == 'O' && posn[7] == 'O') {
      Winner = 'O';
    }
    if (Winner == 'O') {
      return Winner;
    }

    // check for Tie
    for (int i = 1; i < 10; i++) {
      if (posn[i] == 'X' || posn[i] == 'O') {
        if (i == 9) {
          char Draw = 'D';
          return Draw;
        }
        continue;
      } else {
        break;
      }
    }
    return Winner;
  } //end checkWinner

  public boolean checkPosition(int spot) {

    if (posn[spot] == 'X' || posn[spot] == 'O') {
      System.out.println("That position is already taken, please choose another");
      return true;
    } else {
      return false;
    }
  } //end checkPosition

  public void nextPlayer() {
    if (player == 'X') {
      player = 'O';
    } else {
      player = 'X';
    }
  } //end nextPlayer

  public char getPlayer() {
    return player;
  } //end getPlayer

  public int AI(boolean botFirst) {
    int choice;
    int value = 100;
    int checkspot = 1;
    boolean done = false;
    //System.out.println("botfirst is " + botFirst);
    roundCounter++; //keep track of the turn for the bot
    while (!done) {
      //check board and assign values
      //ZZZ = 300
      //ZZX = 201
      //ZZO = 195
      //ZXX = 102
      //ZOO =  90
      //ZOX =  96
      //OOO = -15
      //XXX =   3
      if (botFirst) {
        if (checkBotPosition(checkspot) == 'Z') {
          value = 100;
        } else if (checkBotPosition(checkspot) == 'X') {
          value = 1;
        } else {
          value = -5;
        }
      } else {
        if (checkBotPosition(checkspot) == 'Z') {
          value = 100;
        } else if (checkBotPosition(checkspot) == 'X') {
          value = -5;
        } else {
          value = 1;
        }
      }
      //System.out.println("value = " + value);

      //assign values to each of the cells
      switch (checkspot) {
        case 1:
          pos1Val = value;
          break;
        case 2:
          pos2Val = value;
          break;
        case 3:
          pos3Val = value;
          break;
        case 4:
          pos4Val = value;
          break;
        case 5:
          pos5Val = value;
          break;
        case 6:
          pos6Val = value;
          break;
        case 7:
          pos7Val = value;
          break;
        case 8:
          pos8Val = value;
          break;
        case 9:
          pos9Val = value;
          break;
        default:
          System.out.println("How did you get here?!?!?!");
          break;
      }
      if (checkspot == 9) {
        done = true;
      }
      checkspot++;
    }
    if (roundCounter == 1) {
      if (pos5Val == 100) {
        choice = 5;
      } else {
        choice = 1;
      }
    } else {
      choice = getChoice(botFirst);
    }
    System.out.println("Bot has chosen: " + choice);
    return choice;
  }//end AI


  //checkposition used by bot to assign value to each space and make a move
  public char checkBotPosition(int spot) {

    if (posn[spot] == 'X') {
      return 'X';
    } else if (posn[spot] == 'O'){
      return 'O';
    } else {
      return 'Z';
    }
  } //end checkPosition

  //Check to see if the bot has 2 pieces in a row with an empty space in the row. If it does,
  //it selects the empty space.
  public int getChoice(boolean botFirst) {
    int choice;
    //Playing First Logic
    if(botFirst) {
      if (pos1Val + pos2Val + pos3Val == 102) {
        if (pos1Val == 100) {
          choice = 1;
        } else if (pos2Val == 100) {
          choice = 2;
        } else {
          choice = 3;
        }
      } else if (pos4Val + pos5Val + pos6Val == 102) {
        if (pos4Val == 100) {
          choice = 4;
        } else if (pos5Val == 100) {
          choice = 5;
        } else {
          choice = 6;
        }
      } else if (pos7Val + pos8Val + pos9Val == 102) {
        if (pos7Val == 100) {
          choice = 7;
        } else if (pos8Val == 100) {
          choice = 8;
        } else {
          choice = 9;
        }
      } else if (pos1Val + pos4Val + pos7Val == 102) {
        if (pos1Val == 100) {
          choice = 1;
        } else if (pos4Val == 100) {
          choice = 4;
        } else {
          choice = 7;
        }
      } else if (pos2Val + pos5Val + pos8Val == 102) {
        if (pos2Val == 100) {
          choice = 2;
        } else if (pos5Val == 100) {
          choice = 5;
        } else {
          choice = 8;
        }
      } else if (pos3Val + pos6Val + pos9Val == 102) {
        if (pos3Val == 100) {
          choice = 3;
        } else if (pos6Val == 100) {
          choice = 6;
        } else {
          choice = 9;
        }
      } else if (pos1Val + pos5Val + pos9Val == 102) {
        if (pos1Val == 100) {
          choice = 1;
        } else if (pos5Val == 100) {
          choice = 5;
        } else {
          choice = 9;
        }
      } else if (pos3Val + pos5Val + pos7Val == 102) {
        if (pos3Val == 100) {
          choice = 3;
        } else if (pos5Val == 100) {
          choice = 5;
        } else {
          choice = 7;
        }
      } else if (pos1Val + pos2Val + pos3Val == 90) {
        if (pos1Val == 100) {
          choice = 1;
        } else if (pos2Val == 100) {
          choice = 2;
        } else {
          choice = 3;
        }
      } else if (pos4Val + pos5Val + pos6Val == 90) {
        if (pos4Val == 100) {
          choice = 4;
        } else if (pos5Val == 100) {
          choice = 5;
        } else {
          choice = 6;
        }
      } else if (pos7Val + pos8Val + pos9Val == 90) {
        if (pos7Val == 100) {
          choice = 7;
        } else if (pos8Val == 100) {
          choice = 8;
        } else {
          choice = 9;
        }
      } else if (pos1Val + pos4Val + pos7Val == 90) {
        if (pos1Val == 100) {
          choice = 1;
        } else if (pos4Val == 100) {
          choice = 4;
        } else {
          choice = 7;
        }
      } else if (pos2Val + pos5Val + pos8Val == 90) {
        if (pos2Val == 100) {
          choice = 2;
        } else if (pos5Val == 100) {
          choice = 5;
        } else {
          choice = 8;
        }
      } else if (pos3Val + pos6Val + pos9Val == 90) {
        if (pos3Val == 100) {
          choice = 3;
        } else if (pos6Val == 100) {
          choice = 6;
        } else {
          choice = 9;
        }
      } else if (pos1Val + pos5Val + pos9Val == 90) {
        if (pos1Val == 100) {
          choice = 1;
        } else if (pos5Val == 100) {
          choice = 5;
        } else {
          choice = 9;
        }
      } else if (pos3Val + pos5Val + pos7Val == 90) {
        if (pos3Val == 100) {
          choice = 3;
        } else if (pos5Val == 100) {
          choice = 5;
        } else {
          choice = 7;
        }
      } else if (roundCounter == 2 && (pos1Val == -5 || pos3Val == -5 || pos7Val == -5
          || pos9Val == -5)) {
        if (pos1Val == -5) {
          choice = 9;
        } else if (pos3Val == -5) {
          choice = 7;
        } else if (pos7Val == -5) {
          choice = 3;
        } else {
          choice = 1;
        }
      } else if (roundCounter == 2 && (pos2Val == -5 || pos4Val == -5 || pos6Val == -5
          || pos8Val == -5)) {
        if (pos2Val == -5 || pos4Val == -5) {
          choice = 9;
        } else {
          choice = 1;
        }
      } else if (roundCounter == 3 && (pos2Val == -5 || pos4Val == -5 || pos6Val == -5
          || pos8Val == -5)) {
        if (pos2Val == -5) {
          if (pos9Val == -5) {
            choice = 7;
          } else {
            choice = 9;
          }
        } else if (pos4Val == -5) {
          if (pos3Val == -5) {
            choice = 9;
          } else {
            choice = 3;
          }
        } else if (pos6Val == -5) {
          if (pos1Val == -5) {
            choice = 7;
          } else {
            choice = 1;
          }
        } else {
          if (pos3Val == -5) {
            choice = 1;
          } else {
            choice = 3;
          }
        }
      } else if (roundCounter == 4 && (pos2Val == 100 || pos4Val == 100 || pos6Val == 100
          || pos8Val == 100)) {
        if (pos2Val == 100) {
          choice = 2;
        } else if (pos4Val == 100) {
          choice = 4;
        } else if (pos6Val == 100) {
          choice = 6;
        } else {
          choice = 8;
        }
      } else if (
          pos1Val + pos2Val + pos3Val + pos4Val + pos5Val + pos6Val + pos7Val + pos8Val + pos9Val
              == 84) {
        if (pos1Val == 100) {
          choice = 1;
        } else if (pos2Val == 100) {
          choice = 2;
        } else if (pos3Val == 100) {
          choice = 3;
        } else if (pos4Val == 100) {
          choice = 4;
        } else if (pos5Val == 100) {
          choice = 5;
        } else if (pos6Val == 100) {
          choice = 6;
        } else if (pos7Val == 100) {
          choice = 7;
        } else if (pos8Val == 100) {
          choice = 8;
        } else {
          choice = 9;
        }
      } else {
        System.out.println("Bot is confused make a choice for it.");
        Scanner in = new Scanner(System.in);
        choice = in.nextInt();
      }
      //Playing Second Logic
    } else {
      if (pos1Val + pos2Val + pos3Val == 102) {
        if (pos1Val == 100) {
          choice = 1;
        } else if (pos2Val == 100) {
          choice = 2;
        } else {
          choice = 3;
        }
      } else if (pos4Val + pos5Val + pos6Val == 102) {
        if (pos4Val == 100) {
          choice = 4;
        } else if (pos5Val == 100) {
          choice = 5;
        } else {
          choice = 6;
        }
      } else if (pos7Val + pos8Val + pos9Val == 102) {
        if (pos7Val == 100) {
          choice = 7;
        } else if (pos8Val == 100) {
          choice = 8;
        } else {
          choice = 9;
        }
      } else if (pos1Val + pos4Val + pos7Val == 102) {
        if (pos1Val == 100) {
          choice = 1;
        } else if (pos4Val == 100) {
          choice = 4;
        } else {
          choice = 7;
        }
      } else if (pos2Val + pos5Val + pos8Val == 102) {
        if (pos2Val == 100) {
          choice = 2;
        } else if (pos5Val == 100) {
          choice = 5;
        } else {
          choice = 8;
        }
      } else if (pos3Val + pos6Val + pos9Val == 102) {
        if (pos3Val == 100) {
          choice = 3;
        } else if (pos6Val == 100) {
          choice = 6;
        } else {
          choice = 9;
        }
      } else if (pos1Val + pos5Val + pos9Val == 102) {
        if (pos1Val == 100) {
          choice = 1;
        } else if (pos5Val == 100) {
          choice = 5;
        } else {
          choice = 9;
        }
      } else if (pos3Val + pos5Val + pos7Val == 102) {
        if (pos3Val == 100) {
          choice = 3;
        } else if (pos5Val == 100) {
          choice = 5;
        } else {
          choice = 7;
        }
      } else if (pos1Val + pos2Val + pos3Val == 90) {
        if (pos1Val == 100) {
          choice = 1;
        } else if (pos2Val == 100) {
          choice = 2;
        } else {
          choice = 3;
        }
      } else if (pos4Val + pos5Val + pos6Val == 90) {
        if (pos4Val == 100) {
          choice = 4;
        } else if (pos5Val == 100) {
          choice = 5;
        } else {
          choice = 6;
        }
      } else if (pos7Val + pos8Val + pos9Val == 90) {
        if (pos7Val == 100) {
          choice = 7;
        } else if (pos8Val == 100) {
          choice = 8;
        } else {
          choice = 9;
        }
      } else if (pos1Val + pos4Val + pos7Val == 90) {
        if (pos1Val == 100) {
          choice = 1;
        } else if (pos4Val == 100) {
          choice = 4;
        } else {
          choice = 7;
        }
      } else if (pos2Val + pos5Val + pos8Val == 90) {
        if (pos2Val == 100) {
          choice = 2;
        } else if (pos5Val == 100) {
          choice = 5;
        } else {
          choice = 8;
        }
      } else if (pos3Val + pos6Val + pos9Val == 90) {
        if (pos3Val == 100) {
          choice = 3;
        } else if (pos6Val == 100) {
          choice = 6;
        } else {
          choice = 9;
        }
      } else if (pos1Val + pos5Val + pos9Val == 90) {
        if (pos1Val == 100) {
          choice = 1;
        } else if (pos5Val == 100) {
          choice = 5;
        } else {
          choice = 9;
        }
      } else if (pos3Val + pos5Val + pos7Val == 90) {
        if (pos3Val == 100) {
          choice = 3;
        } else if (pos5Val == 100) {
          choice = 5;
        } else {
          choice = 7;
        }
      } else if (pos1Val == 100 || pos3Val == 100 || pos7Val == 100 || pos9Val == 100){
        if (pos1Val == 100){
          choice = 1;
        } else if(pos3Val == 100){
          choice = 3;
        } else if(pos7Val == 100){
          choice = 7;
        } else {
          choice = 9;
        }
      } else {
        System.out.println("Bot is confused make a choice for it.");
        Scanner in = new Scanner(System.in);
        choice = in.nextInt();
      }
    }
    return choice;
  }

  public String gameMode(){
    //see if the user would like to play against the bot. If gameMode is 'yes' they are playing the bot
    Scanner in = new Scanner(System.in);
    System.out.println("Would you like to play against the Bot? (Enter 'yes'/'no')");
    String gameMode = in.nextLine();
    return gameMode;
  }

  public boolean botFirst(){
    //see if the user would like to play against the bot. If gameMode is 'yes' they are playing the bot
    boolean botFirst;
    Scanner in = new Scanner(System.in);
    System.out.println("Should the bot play first? (Enter 'yes'/'no')");
    String first = in.nextLine();
    if (first.equalsIgnoreCase("yes")){
      botFirst = true;
    }
    else {
      botFirst = false;
    }
    return botFirst;
  }
}
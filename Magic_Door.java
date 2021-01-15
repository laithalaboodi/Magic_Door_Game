/*************************************************************************
  * Laith Alaboodi
  * Magic_Door.java
  * magic door game, in this game your goal is to guess the right door combitnation 
  * the number are from 1 to 100 and you get 6 chances  each try and 3 tries. 
  *************************************************************************/

import java.util.*;

public class Magic_Door
{
  // keyboard is a global variable because it is above main()
  static Scanner keyboard = new Scanner(System.in); 
  
  public static void main(String[] args) 
  {
    // Put the main code here.
    welcomeMessage();
    boolean runAgain;
    String userName;
    
    do{
      userName=getUserName();
      guessMagicDoor(userName);
      runAgain=getYorN("\nDoes a new champion want to try to claim the treasure? (y/n)");
    }while(runAgain);
    goodByeMessage();
  }
  
  
  public static void welcomeMessage(){
    System.out.println("******************************************************");
    System.err.println("Try Open the Magic Door!");
    System.out.println("You are a Mighty Warrior trying to open a magic door by guessing its combination.");
    System.out.println("If you can guess the number that opens the door, you will earn the treasure inside. ");
    System.out.println("If you have not opened the door after 6 guesses, you will lose 33% health points. ");
    System.out.println("Good luck - you'll need it! ");
    System.out.println("******************************************************");
  }
  //to get the name
  public static String getUserName(){
    String name;
    name=getString("What does your guild call you? ");
    return name;
  }
  //module to get the random door number
  //also if won or not, also include tries, and number of guess which is 6
  public static void guessMagicDoor(String name){
    System.out.println("Ok " +name+ " Let's do this" );
    Boolean playerAlive;
    playerAlive = true; 
    Boolean won;
    won = false;
    int doorNumber=getRandomNumber(1,100);
    int playerGuess;
    int numberOfTries=1;
    int low = 1;
    int high = 100;
    int numberOfGuess=1;
    //loop to see if player alive
    while(playerAlive && numberOfTries <=3 && !won){
      low = 1;
      high = 100;
      playerAlive = true;
      //if player reaches 3 tries player will not be alive
      if(numberOfTries == 3){
        playerAlive=false;
      }//this is for number of guess
      if(numberOfGuess == 7){
        numberOfGuess=1;
      }
      //if statment with display statment for health
      if(numberOfTries == 2){
        System.err.println("Your hp is at 66%");
        System.out.println("Let's try again...");
      }
      
      if(numberOfTries == 3){
        System.err.println("Your hp is at 33%");
        System.out.println("Let's try again...");
      }
      
      //this loop is the smaller loop because its inside a bigger loop
      //this loop is to replace low number with the lower value and high number with the high value
      while(numberOfGuess <= 6 && !won ){
        playerGuess=getPlayerGuess(low,high);
        if(playerGuess < doorNumber){
          low = playerGuess;
        }
        else if (playerGuess > doorNumber){
          high = playerGuess;
        }//here to display if player won
        else if(playerGuess == doorNumber){
          won = true;
          System.out.println("******************************************************");
          System.out.println("Well done, " +name);
          System.out.println(name+" Opens the door and claims the Sword of Ice!");
          System.out.println("Goodbye, "+ name +" Until we meet again.");
          System.out.println("******************************************************");
        }
        numberOfGuess++;
      }//small loop
      numberOfTries++;
      if(numberOfTries == 4 &&!won){
        System.out.println("******************************************************");
        System.out.println("Wrong, "+name+" The combination was "+doorNumber);
        System.err.println("Your hp is at 0%");
        System.out.println("The light fades from your eyes as you return to your personal Valhalla. ");
        System.out.println("Goodbye, "+ name +" Until we meet again.");
        System.out.println("******************************************************");
      }
      
    }//big loop
  }
  //this function is getting the player input and i pass it to the top module.
  //also includes validation where numbers can not be lower than 1 and higher than 100
  //also it will valdiate the new numbers
  public static int getPlayerGuess(int low, int high){
    int userGuess;
    userGuess=getInteger("please guess a number between "+low+ " to " + high);
    while(userGuess < low || userGuess > high){
      System.out.println("error try again");
      userGuess=getInteger("please guess a number between "+low+ " to " + high);
    }
    return userGuess;
  }
  //the goodbye message
  public static void goodByeMessage(){
    System.out.println("******************************************************");
    System.out.println("Thank you for playing the best game of 1997");
    System.err.println("V1.02");
    System.out.println("******************************************************");
  }
  
  /********************************************************************************************************************
    * This codes below is from input routines
    * 
    *****************************************************************************************************************/
  public static int getInteger(String msg) {
    System.out.println(msg);
    while (!keyboard.hasNextInt()) {
      keyboard.nextLine();
      System.err.println("Invalid integer. Try again.");
      System.out.println(msg);
    }
    int number = keyboard.nextInt();
    keyboard.nextLine(); //flushes the buffer
    return number;
  }
  
  public static String getString(String msg) {
    String answer = "";
    System.out.println(msg);
    try {
      answer = keyboard.nextLine(); 
    }
    catch (Exception e) {
      System.err.println("Error reading input from user. Ending program.");
      System.exit(-1);
    } 
    
    while (answer.replace(" ", "").equals("")) {
      System.err.println("Error: Missing input.");
      try {
        System.out.println(msg);
        answer = keyboard.nextLine(); 
      }
      catch (Exception e) {
        System.err.println("Error reading input from user. Ending program.");
        System.exit(-1);
      } 
    }
    return answer;            
  }
  public static int getRandomNumber (int low, int high) {
    return (int)(Math.random() * ((high + 1) - low)) + low;
  } 
  
  public static boolean getYorN(String msg) {
    String answer = getString(msg);
    
    while (answer.compareToIgnoreCase("y")   != 0 
             && answer.compareToIgnoreCase("yes") != 0 
             && answer.compareToIgnoreCase("n")   != 0 
             && answer.compareToIgnoreCase("no")  != 0) {
      
      if (answer.replace(" ", "").equals("")) {
        System.err.println("Error: Missing y/n input.");
      } else {
        if (answer.compareToIgnoreCase("y")   != 0 
              && answer.compareToIgnoreCase("yes") != 0 
              && answer.compareToIgnoreCase("n")   != 0 
              && answer.compareToIgnoreCase("no")  != 0) {
          System.err.println("Error: Unexpected input.");
        }
      }
      answer = getString(msg);
    } 
    
    if  (answer.compareToIgnoreCase("y")   == 0  
           || answer.compareToIgnoreCase("yes") == 0) {
      return true;
    } 
    else {
      return false;
    }
  }    
}


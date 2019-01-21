/* This program walks a user through various decisions
   to arrive at a resolution. It employs a binary tree with user decisions directing
   it's path. It also employs a stack to save the user's journey.
 */


import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class main {
    //The main function, with user entry for beginning program

  public static void main(String[] args) {
    character user = null; //variable for creating new character
    int choice = init_menu(); //which character user decides upon
    String name = init_name(); //what name user would like to use
    try { //null pointer and IO exception can occur (but won't)
      switch (choice) {
        case 1: //old man character
          user = new old_man(name);
          break;
        case 2: //child character
          user = new the_child(name);
          break;
      }

      /*ONLY FOR TESTING DELETE, COPY CONSTRUCTOR, AND DISPLAY ALL
      if(test_menu(user) > 0)
        System.exit(1); //THIS IS ONLY FOR TESTING */

      System.out.print("\n\nHello " + name + ", you have life of ");
      user.display_health(); //displays character health (depends on character)
      System.out.println("\n\nYour eyes open to a dream.");
      //entry point into dream state
      if(user.enter_dream() == -1) {
        String file = "src/winners.txt";
        String read;
        FileWriter writer = new FileWriter("src/winners.txt", true);
        BufferedWriter bwrite = new BufferedWriter(writer);
        bwrite.newLine();
        bwrite.write(name);
        bwrite.close();

        System.out.println("\nPrevious Game winners:");
        BufferedReader buff = new BufferedReader(new FileReader(file));
        read = buff.readLine();
        while(read != null) {
          System.out.println(read);
          read = buff.readLine();
        }
        buff.close();
      }
    }
    //catches all exceptions and exits program
    catch (IOException | NullPointerException err) {
      err.printStackTrace();
    }
    //test_stack(user);
  }

  //Welcomes user, lets them choose a character, with error checking (old man is given if user enters letter)
  private static int init_menu(){
    System.out.println("\nWelcome to the Dream State. You are tasked with choosing a character"
        + "\nand navigating their dreams so that they can find their lost loved one."
        + "\n\nTo begin, which character would you like? 1) The Old Man 2) The Young Child: ");
    Scanner input = new Scanner(System.in); //new scanner object
    int choice = 0; //for user to decide on character path
    try {
      while (choice == 0) {
        choice = input.nextInt();//user enters what character they want
        if (choice != 1 && choice != 2) { //checks for valid entry
          System.out.println("Please enter a valid number.");
          choice = 0;
        }
      }
    } catch(InputMismatchException err){ //if user enters non-int value
      System.out.println("\nYou are given the old man since you can't enter a number.\n");
      choice = 1;
    }
    return choice;
  }

    //user enters their name for fun
  private static String init_name(){
    String user = ""; //initializes to 'space' to keep compiler happy
    Scanner in = new Scanner(System.in); //initializes Scanner for user entry
    System.out.println("What would you like your user name to be? ");
    if(in.hasNextLine()) //user entry
      user = in.nextLine();//sets to user entry
    //in.close();//closes scanner
    return user; //returns user entry
  }

    //This is strictly for testing the program
  private static int test_menu(character user){
    int entry = 0; //for testers input
    int tripwire = 0; //so the program closes after deletions
    while(entry != 4){
      System.out.println("\n\n**TESTING MENU**\n1) Display all (there's a lot)" +
          "\n2) Delete item (program will exit after testing)" +
          "\n3) Copy constructor\n4) Back to the dream");
      Scanner in = new Scanner(System.in); //initializes Scanner for user entry
      entry = in.nextInt();
      switch(entry){
        case 1: System.out.println("\nItems:" +
            user.display_all()); break; //displays all entries, not pretty
        case 2: test_delete(user, in); ++tripwire; break; //lets tester delete certain nodes
        case 3: test_copy(user); break; //test copy constructor
        case 4: break;
      }
    }
    return tripwire;
  }

    //this is strictly for testing the delete function
  private static void test_delete(character user, Scanner in){
    int entry = 0;
    while(entry != 5){
    System.out.println("**Only select each once, second time will have no effect**" +
        "\n1) Delete root\n2) Delete leaf\n" +
        "3) Delete in midst 1(right and left only, with no children)\n4) Delete in midst 2" +
        "(right and left, with children)\n5) Back to menu");
    entry = in.nextInt();
    switch(entry){ //the nodes which will be deleted
      case 1: user.delete(500); break; //delete root node, which is the more difficult node
      case 2: user.delete(770); break; //delete leaf, the easy stuff
      case 3: user.delete(870); break; //delete node with one leaf on each side
      case 4: user.delete(800); break; //deletes node with several children on one side
      case 5: break;
      }
    }
  }

    //strictly for testing copy constructor for entire character path/tree
  private static void test_copy(character user){
    System.out.println("\n\n***COPY CONSTRUCTOR -- DISPLAY ALL***\n");
    character copy_user = user;
    copy_user.display_all();
  }

  //Once complete, lets tester inspect stack
  private static void test_stack(character user) throws NullPointerException {
    int entry = 0;
    while(entry != 3){
      System.out.println("\n\n**TESTING MENU FOR STACK**\n1) Display stack" +
          "\n2) Pop stack with display\n3) Exit program");
      Scanner in = new Scanner(System.in);
      entry = in.nextInt();
      switch(entry){
        case 1: int total = user.list.display_all();
          if(total != 0)
            System.out.println("\nTotal items: " + total);
          else
            System.out.println("\nNothing on stack");
          break;

        case 2: decision temp = user.list.pop();
          if(temp != null) {
            System.out.println("\nPopped item: ");
            temp.display(); break;
          }else
            System.out.println("\nNothing on stack"); break;

      }
    }
  }
}

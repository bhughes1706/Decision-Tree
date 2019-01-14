/*
  Base class for the user types.
  holds life points, name of character, and accumulated items
 */
import java.io.IOException;

abstract class character extends tree{
  private static int life;
  private String name;
  private static int item;

  character(){life = 0; name = null; item = 0;}

  character(int input, String in, int items, String file) throws IOException {
    super(file); life = input; name = in; item = 0;
  }

  character(character old){name = old.name;} //copy constructor

    //reduces life points by 10
  static int reduce_life() { life-=10; return life;}

    //prints updated health points
  static void print_life() {
    System.out.println("Your life is now at: " + life);
  }

    //prints health points
  void display_health(){
    System.out.print(life);
  }

    //display_all is only for testing purposes
  abstract int display_all();

    //adds an item to the characters cache
  static void add_item(){
    ++item;
  }

    //returns the number of items user has
  static boolean item_check(){
    if(item > 1)
      return true;
    return false;
  }

    //delete item for testing
  void display_history(){
    System.out.println("\n\nItems: " + list.display_all());
  }
}

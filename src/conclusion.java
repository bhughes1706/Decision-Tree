/* This is the final class that the user will encounter if they get through all the
   story. It tests whether the user has collected all the items, and pushes out the
   appropriate message. */

class conclusion extends decision {
  private String output; //description of situation

  conclusion() {
    output = null;
  }

  conclusion(String input, String dec, int loc) {
    output = input;
    option = dec;
    location = loc;
  }

  conclusion(conclusion old) {
    this.output = old.output;
  }

  public void display() {
    System.out.println(output);
    System.out.println(option);
  }

  public int action() {
    if(character.item_check()) {
      System.out.println(output);
      //write out
      return -2;
    }
    else {
      System.out.println(option);
      System.out.println("You become lost in the dream forever. Try again.");
      return -1;
    }
  }
}
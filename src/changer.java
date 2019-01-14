/* This class is derived from decision.
   Begins as a choice class, then changes to a jump class after the user
   enters it once. This is so an item picked up isn't magically available
   again. It also adds the item to the characters inventory */

class changer extends decision{
  private int change; //where the class should jump to, only used after tripwire is ++
  private String output; //description of thing, situation -- changes after one entrance to class
  private int tripwire = 0; //this is used to change the class, user entered once

  changer(){output = null; change = 0;}

  changer(String input, String dec, int loc, int changeling){
    output = input;
    option = dec;
    location = loc;
    change = changeling;
  }

  changer(changer old) {
    this.output = old.output;
    this.change = old.change;
    this.tripwire = old.tripwire;
    this.option = old.option;
  }

  public void display(){
    System.out.println(output);
    System.out.println(option);
  }

  //change occurs in action method. The tripwire controls flow.
  public int action() {
    if (tripwire == 0) {
      ++tripwire;
      character.add_item();
      return 0;
    }
    option = "You've already done this.";
    output = null;
    System.out.println(option);
    return change;
  }
}

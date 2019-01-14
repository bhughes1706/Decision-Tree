/* This class is derived from decision class.
   Ends the dream due to an unfortunate event.
   Accomplished by returning -1 in action method
 */

class end extends decision {
  private String output; //output for why the game is ending 

  end(){
    output = null;
  }

  end(String input, int loc){
    output = input;
    location = loc;
  }

  end(end old) {this.output = old.output;}

  public void display(){
    System.out.println(output);
    System.out.println(option);
  }

  public int action(){
    System.out.println(output);
    System.out.println("You're now lost inside your dream\nTry again.\n");
    return -1;
  }
}

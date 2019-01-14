/* This class is derived from decision.
   Has no action, just gives user options for traversal, returns 0 in action
 */

class choice extends decision {
  private String output; //description of situation

  choice() {output = null;}

  choice(String input, String dec, int loc){
    output = input;
    option = dec;
    location = loc;
  }

  choice(choice old){this.output = old.output;}

    //display function with virtual base
  public void display(){
    System.out.println(output);
    System.out.println(option);
  }

    //performs action - virtual base
  public int action(){ return 0;}
}

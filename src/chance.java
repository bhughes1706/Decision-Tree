/* This class is derived from decision class.
   This class rewards the user's curiosity with a drop in health.
   Can be repeatedly done until character is 'dead'
 */

class chance extends decision{
  private int change; //which node to jump to after performs action

  chance(){
    change = 0; option = null; location = 0;
  }

  chance(String dec, int loc, int changer){
    change = changer;
    option = dec;
    location = loc;
  }

  chance(chance old){ this.change = old.change;}

  public void display(){
    System.out.println(change);
    System.out.println(option);
  }

  public int action(){
    System.out.println(option);
    if(character.reduce_life() == 0){
      System.out.println("\nYou done died. Try again.\n");
      return -1;}
    else
      character.print_life();
    return change;
  }
}

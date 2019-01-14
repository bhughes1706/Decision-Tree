/*  This class is derived from decision class.
    Provides a jump from current node to another through returned variable change.
    Used for most leafs on binary tree
 */

class jump extends decision {
  private int change; //jumps to node after action performed

  jump(){change = 0;}
  jump(String dec, int loc, int changer){
    change = changer;
    option = dec;
    location = loc;
  }
  jump(jump old) {this.change = old.change;}
  public void display(){
    System.out.println(change);
    System.out.println(option);
  }
  public int action(){
    System.out.println(option);
    return change;}
}

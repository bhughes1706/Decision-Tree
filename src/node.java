/*
  Tree class contains node class.
  Holds one decision class object.
 */

class node {
  node right; //right node in binary tree
  node left; //left in tree
  decision object; //decision object

  node(){ right = null; left = null; object = null;}

  node(node old) {
    right = new node(old.right);
    left = new node(old.left);
    object = old.object;
  } //copy constructor

    //dynamically builds tree with various decision objects
  void add(String input, String out, int loc, int type, int change) {
      switch (type) {
        case 1: object = new chance(out, loc, change);break;
        case 2: object = new jump(out, loc, change);break;
        case 3: object = new end(input, loc); break;
        case 4: object = new choice(input, out, loc);break;
        case 5: object = new changer(input, out, loc, change); break;
        case 6: object = new conclusion(input, out, loc); break;
      }
  }

    //adds the appropriate node when creating tree
  void add_node(String input, String out, int loc, int type, int change) throws NullPointerException {
    if(this.object.direction(loc) >= 0) //if node being added is smaller
    {
      if(left == null) { //if no left node
        left = new node();
        left.add(input, out, loc, type, change);
      }else //if left exists
        left.add_node(input, out, loc, type, change);
    }else{ //if node being added is larger
      if(right == null) {
        right = new node(); //if no right node
        right.add(input, out, loc, type, change);
      }else //if right exists
        right.add_node(input, out, loc, type, change);
    }
  }

  //displays every node's contents. For testing only.
  int display_all() {
    int count = 1;
    object.display();
    if(left != null && left.object != null)
      count+=left.display_all();
    if(right != null && right.object != null)
      count+=right.display_all();
    return count;
  }
}

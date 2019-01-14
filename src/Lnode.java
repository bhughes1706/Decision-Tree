/*
  Nodes for linked list. This is an array of decisions with pointer to
  next empty position. Array is size 5.
 */

class Lnode {
  private decision[] array; //holds decisions of user input
  Lnode next; //next node in list
  final private int size = 5; //final size
  static private int pointer = 0; //points to next open position in array

  Lnode() {
    array = new decision[size];
    next = null;
  } //default constructor

  Lnode(decision input) {
    array = new decision[size];
    array[pointer] = input;
    next = null;
    ++pointer;
  } //with decision input

  Lnode(Lnode old) {
    array = new decision[size];
    for (int i = 0; i < size; ++i) {
      if (old.array[i] != null) {
        this.array[i] = old.array[i];
        ++pointer;
      }
    }
    if (old.next != null) {
      next = new Lnode(old.next);
      pointer = 0;
    } else next = null;
  } //copy constructor

  boolean push(decision object) {
    array[pointer] = object;
    ++pointer;
    if(pointer == size){
      pointer = 0;
      return true;
    }
    return false;
  } // pushes new decision on stack

  int display(){
    return display(pointer);
  } //display wrapper

  int display(int ptr) {
    int count = 0;
    for(int i = ptr - 1; i >= 0; --i){
      array[i].display();
      ++count;
    }
    return count;
  } //display with known pointer size

  protected int get_size() {return size;} //return

  decision pop() {
    if (pointer == 0) {
      pointer = size;
      return null;
    }
    else {
      --pointer;
      decision temp = array[pointer];
      array[pointer] = null;
      return temp;
    }
  }
}
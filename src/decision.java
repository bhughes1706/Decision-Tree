/*
  Abstract base class for all decision classes
  holds the location for addition to tree
  and for printing option string for user entry
 */

abstract class decision {
  String option; //provides questions for next traversal
  int location; //which node in tree it should occupy

  decision(){option = null;}

  decision(String input){ option = input; }

  decision(decision old) {this.option = old.option;} //copy

  int direction(int loc){ return location - loc;} // returns what direction node needs to be added

  public abstract void display(); //pure virtual fx - display for derived classes

  public abstract int action(); //pure virtual fx - performs action
}

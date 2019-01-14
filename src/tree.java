/*
  Tree class, base class for character
  Holds root node for entry into tree
  and is where the dream is loaded from.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

class tree {
  node root; //package private
  linked_list list; //program five - creates stack

  tree(){root = null; list = null;} //default constructor

  tree(tree old) {
    this.root = new node(old.root);
    this.list = new linked_list(old.list);
  } //copy constructor

  tree(String file) throws IOException {
    root = null;
    list = new linked_list();
    load_dream(file); //loads external data file
    } //constructor with file path

    //provides path for entry through created menu -- user.enter_dream
  int enter_dream() throws NullPointerException{
    int action = dream(); //loads first iteration
    while(action > 0){ //checks for additional needed iterations
      action = find_dream(root, action);
    }
    return action;//return final result
  }

    //loads external data file, called from constructor with file path
  private void load_dream(String file) throws IOException, NullPointerException {
    String input;//result of action
    String out;//question for next action
    int loc; //for proper binary tree entry
    int type; //1-chance 2-jump 3-end 4-choice 5-changer
    int changeling; //for jump to different node
    BufferedReader buff = new BufferedReader(new FileReader(file)); //for reading file

    input = buff.readLine(); //first arg - EOF check
    while(input != null) {
      out = buff.readLine(); //second arg
      loc = Integer.parseInt(buff.readLine()); //third arg
      type = Integer.parseInt(buff.readLine()); //fourth arg
      changeling = Integer.parseInt(buff.readLine()); //fifth arg
      if (root == null) {
        root = new node();
        root.add(input, out, loc, type, changeling);
      } else
        root.add_node(input, out, loc, type, changeling);
      input = buff.readLine(); //first arg - EOF check
    }
  }

    //wrapper function for delete fx
  void delete(int location){
    delete(location, root);
  }

    //deletes one node ** only for testing, never used
  private void delete(int location, node root) {
    if(root == null) //Case 1 ** no root
      return;
    int loc = root.object.direction(location);
    if(loc == 0){
      if(root.right == null && root.left == null){ //Case 2 ** leaf node
        root.object = null; root = null;}
      else if(root.right == null) //Case 3 **
        root = root.left;
      else if(root.left == null && root.right.left == null)
        root = root.right;
      else{
        node current = root.right;
        node previous = root;
        if(current.left != null) {
          {
          while (current.left != null) {
            previous = current;
            current = current.left;
          }
          if(current.right != null){
            current.object = null;
            current = current.right;}
          else
            previous.left = null;
          }
          root.object = current.object;
        }
        else{
          if(current.right != null){
            root.object = current.object;
            root.right = current.right;}
          else{
            root.object = current.object;
            root.right = null;}
        }
      }
    }
    else if(loc > 0)
      delete(location, root.left);
    else
      delete(location, root.right);
  }

    //wrapper function for dream
  int dream(){ return dream(root);}

    //experiences one node in the dream tree
  private int dream(node root) {
    Scanner in = new Scanner(System.in); //initializes Scanner for user entry
    root.object.display();//displays decision object
    int entry = 0;
    try{
      while(entry == 0) {
      entry = in.nextInt();//sets to user entry
      if (entry != 2 && entry != 1){
        System.out.println("\nPlease enter a 1 or 2.");
        entry = 0;}
      }
      //this catches a char entry and gives user random number. that'll teach em.
    } catch (InputMismatchException err){
      System.out.println("\nYou've been given a random action since you can't enter a number.\n");
      Random rand = new Random();
      entry = rand.nextInt(2) + 1;
    }
    int action = 50; //sets to root node value, shouldn't ever be used
    list.add(root);

    switch(entry){ //where the user wants to go next
      case 1: action = root.left.object.action(); //performs action in chosen object
        break;
      case 2: action = root.right.object.action(); //action in right node
    }

    if(action == -1) //** CASE 1 ** ends dream sequence unsuccessfully
      return 0;
    else if(action == -2) //** CASE 2 ** ends dream sequence successfully
      return -1;
    else if(action == 0){ //** CASE 3 ** next node is right or left
      switch(entry){
        case 1: return dream(root.left);
        case 2: return dream(root.right);
      }
    }
    else{ //** CASE 4 ** next node is elsewhere in tree (jumps)
      return action;
    }
    return 0;
  }

    //finds the next dream node for user to experience
  private int find_dream(node root, int action){
    int loc = root.object.direction(action);
    if(loc == 0) //found needed node
      return dream(root); //enter that node object's dream sequence
    else if(loc > 0)
      return find_dream(root.left, action);
    else
      return find_dream(root.right, action);
  }
}

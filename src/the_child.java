/*
  One of user options for character choice
  Main difference is number of items (2) and
  life points (40), as well as an alternate load file
 */
import java.io.*;

class the_child extends character {
  the_child() throws IOException{
    super(30, "McNoName", 2, "src/the_child.txt");
  } //default constructor with no name input
  the_child(String in) throws IOException {
    super(30, in, 2, "src/the_child.txt");
  } //constructor with name input
  int display_all(){
    return root.display_all();
  } //for testing
}

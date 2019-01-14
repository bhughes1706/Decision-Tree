/*
  One of user options for character choice
  Main difference is number of items (2) and
  life points (50), as well as an alternate load file
 */
import java.io.*;

class old_man extends character {
  old_man() throws IOException{
    super(50, "NoName", 2, "src/old_man.txt");
  } //default constructor
  old_man(String in) throws IOException {
    super(50, in, 2, "src/old_man.txt");
  } //constructor with name input
  int display_all() {
    return root.display_all();
  } //display all for testing
}

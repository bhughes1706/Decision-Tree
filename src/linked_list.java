// The stack of user decisions as they progress through the program

class linked_list {
  private Lnode head; //head of stack

  linked_list(){ head = new Lnode(); } //default constructor

  linked_list(decision decision){
    head = new Lnode(decision);
  }

    //copy constructor
  linked_list(linked_list old){
    head = new Lnode(old.head);
  }

  void add(node root) {
    if(head.push(root.object)){ //if adds and array is full
      Lnode temp = new Lnode(); //new node
      temp.next = head; //adds new node to beginning
      head = temp; //resets head
    }
  } //pushes decision object to stack

    //displays all, and returns item count
  int display_all()throws NullPointerException {
    int count = 0;
    if(head != null) //if there is a list
      count = head.display(); //display head
    else return 0;
    Lnode current = head; //create temp node reference
    while(current.next != null){ //if there is more to display
      count+=current.next.display(head.get_size());
      current = current.next;
    }
    return count; //returns how many items displayed
  }

    //pops one decision off stack and returns that decision
  decision pop(){
    if(head == null) //if nothing in list
      return null;
    decision temp1 = head.pop(); //returns decision if node isn't empty
    if(temp1 == null && head.next != null){ //if node is empty and more next
      Lnode temp = head.next;
      head = null;
      head = temp;
      temp1 = head.pop(); //pops again with next node as head
    }
    else if(temp1 == null) //if head is empty and nothing and next is null
      head = null;
    return temp1; //returns decision
  }
}

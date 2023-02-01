/**
 * Stack class initializes a Stack of State objects as a double-
 * ended, singly linked list. Only States with a death rate no
 * less than 70 and up to 250 are inserted into the list. When
 * displaying the Stack, a print method is called recursively.
 * 
 * @author Miranda Weathers N01482572
 * @version 10/17/2021
 */

public class Stack {
	
	/**
	 * Link class initializes new Links for the Stack.
	 */
	public class Link {
		
		public State linkState;
		public Link next;
		
		/**
		 * Constructor creates new Link from given State.
		 * @param ls
		 */
		public Link(State ls) {
			linkState = ls;
		}//Link() constructor
		
	}//Link class
	
	private Link first;
	private Link last;
	
	/**
	 * Constructor for Stack linked list.
	 */
	public Stack() {
		setFirst(null);
		last = null;
	}//Stack() (linked list) constructor
	
	/**
	 * Adds new link to Stack from given State.
	 * @param State s
	 */
	public void push(State s) {
		Link newLink = new Link(s);
		if(isEmpty()) //if empty list,
			last = newLink; //this link will be last
		newLink.next = getFirst();
		setFirst(newLink);
	}//push()
	
	/**
	 * Removes and returns State from Stack.
	 * @return State temp
	 */
	public State pop() {
		State temp = getFirst().linkState;
		if(getFirst().next == null) //if only one item,
			last = null; //update last to null
		setFirst(getFirst().next); //update first (will be null if first.next=null)
		return temp;
	}//pop()
	
	/**
	 * Recursively prints State info in top-to-bottom Stack order.
	 * @param Link l
	 */
	public void printStack(Link l) {
		if(!isEmpty() && l!=null) {
			State curr = l.linkState;
			System.out.printf("%-15s%-14d%-14.1f%-14.6f%-14.2f%-14.2f\n",
				curr.getName(),
				curr.getMhi(),
				curr.getVcr(),
				curr.getCfr(),
				curr.getCaseRate(),
				curr.getDeathRate()
			);
			printStack(l.next);
		}//if
	}//printStack()
	
	/**
	 * Determines if Stack is empty.
	 * @return true if empty; false otherwise
	 */
	public boolean isEmpty() {
		return (getFirst()==null && last==null);
	}//isEmpty()

	/**
	 * Returns first link in Stack list.
	 * @return Link first
	 */
	public Link getFirst() {
		return first;
	}//getFirst()

	/**
	 * Sets first link in Stack list.
	 * @param Link first
	 */
	public void setFirst(Link first) {
		this.first = first;
	}//setFirst()
	
}//Stack class

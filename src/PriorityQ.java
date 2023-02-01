/**
 * PriorityQ implements a Priority Queue of state objects using 
 * a sorted double-ended, doubly-linked list. Insertion takes
 * O(N) time, with higher priority items at the head of the list.
 * Lower death rate means higher priority in the list.
 * <p>
 * When user chooses to print the Priority Queue, a formatted
 * table of data for each State in the PQ is displayed.
 * The user may also choose to delete States within a chosen
 * interval.
 * 
 * @author Miranda Weathers N01482572
 * @version 10/17/2021
 */

public class PriorityQ {

	
	/**
	 * Link class initializes new Links for the Priority Queue.
	 */
	public class Link {
		
		public State linkState;
		public Link next;
		
		public Link(State ls) {
			linkState = ls;
		}//Link() constructor
		
	}//Link class
	
	private Link first;
	private Link last;
	
	/**
	 * PriorityQ constructor.
	 */
	public PriorityQ() {
		setFirst(null);
		last = null;
	}//PriorityQ()
	
	/**
	 * Inserts State into Doubly-Linked Priority Queue according to 
	 * death rate; lower death rate, higher priority.
	 * @param State s
	 */
	public void insert(State s) {
		Link newLink = new Link(s);
		Link previous = null;
		Link current = first;
		
		while(current != null && s.getDeathRate() > current.linkState.getDeathRate()) {
			previous = current;
			current = current.next;
		}//while
		
		if(previous == null) {
			first = newLink;
		} else {
			previous.next = newLink;
		}//if..else comparison
		
		newLink.next = current;
	}//insert()
	
	/**
	 * Removes highest priority State in Priority Queue.
	 * @return Link temp
	 */
	public Link remove() {
		Link temp = first;
		if(first.next == null)
			last = null;
		first = first.next;
		return temp;
	}//remove()
	
	/**
	 * Recursively prints State info in Priority Queue
	 * from highest to lowest priority.
	 * @param Link l
	 */
	public void printPQ(Link l) {
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
			printPQ(l.next);
		}//if
	}//printPQ()
	
	/**
	 * Deletes from PQ state objects with CFR belonging to input interval.
	 * Removes state from PQ with CFR between x and y inclusive.
	 * Returns true if anything found and deleted, false otherwise.
	 * @param x - first interval value from user
	 * @param y - second interval value from user
	 */
	public boolean intervalDelete(int x, int y) {
		Link current = first;
		Link previous = first;
		
		while(current.linkState.getDeathRate() < x || current.linkState.getDeathRate() > y) {
			if(current.next == null) {
				return false;
			} else {
				previous = current;
				current = current.next;
			}//if..else
		}//while != interval
		
			while(current != null && current.linkState.getDeathRate() >= x && current.linkState.getDeathRate() <= y) {
				if(current == first) {
					first = first.next;
				} else {
					previous.next = current.next;
					current = current.next;
					//continue;
				}//if..else
			}//while == interval
		
		
		return true;
	}//intervalDelete()
	
	/**
	 * Determines if Priority Queue is empty.
	 * @return true if empty; false otherwise
	 */	
	public boolean isEmpty() {
		return (getFirst()==null);
	}//isEmpty()

	/**
	 * Gets first Link in Priority Queue list.
	 * @return Link first
	 */
	public Link getFirst() {
		return first;
	}//getFirst()
	
	/**
	 * Sets first Link in Priority Queue list.
	 * @param Link first
	 */
	public void setFirst(Link first) {
		this.first = first;
	}//setFirst()
	
}//PriorityQ

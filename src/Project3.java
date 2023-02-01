import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * COP 3530: Project 3 - Linked lists
 * <p>
 * This program reads in a CSV file from the user containing data relating
 * to US states' population and COVID-19 statistics, which is used to create 
 * a collection of State objects. Both a Stack and Priority Queue are
 * initialized. States with a COVID-19 death rate of 70 up to 250 are added
 * to the Stack; then, they are removed from the Stack and added to the 
 * Priority Queue in sorted order based on death rate, ascending.
 * <p>
 * The user enters the filename, the Stack is displayed, the Priority Queue
 * is displayed, and then a menu is repeatedly prompted to the user with
 * the options to remove certain states from the PQ, print the PQ, and exit.
 * When the user chooses to remove states from the PQ, they are prompted to
 * enter an interval of integers representing the death rate of States to be
 * removed. User input is validated to match data type and correct order.
 * 
 * @author Miranda Weathers N01482572
 * @version 10/17/2021
 */

public class Project3 {

	/**
	 * Instantiates Stack, Priority Queue, and State objects
	 * based on file contents provided by user-supplied file path. 
	 * Prompts menu to user with options to delete interval of
	 * States from PQ, print PQ, and exit.
	 * @param args
	 */
	public static void main(String[] args) {
		
		Stack stateStack = new Stack();
		PriorityQ statePQ = new PriorityQ();
		
		//read in states from csv
		System.out.print("Enter the file name: ");
		Scanner scan = new Scanner(System.in);
		String fileName = scan.nextLine();
		
		//check if file exists
		Scanner readFile = null;
		try {
			readFile = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("\nFile not found.");
			System.exit(1);
		}//end try/catch
		
		readFile.nextLine(); //skip CSV row 1 (labels)
		readFile.useDelimiter(",|\n");
		
		while(readFile.hasNext()) { //as long as file has more rows of data,
			State s = new State 	//create State object from file
				(
					readFile.next(), 		//name
					readFile.next(), 		//capitol
					readFile.next(), 		//region
					readFile.nextInt(), 	//house seats
					readFile.nextInt(), 	//population
					readFile.nextInt(), 	//COVID cases
					readFile.nextInt(), 	//COVID deaths
					readFile.nextInt(), 	//median household income
					readFile.nextDouble() 	//violent crime rate
				);
			if((s.getDeathRate() >= 70.0) && (s.getDeathRate() < 250.0)) {
				stateStack.push(s);
			}
		}//end while
		
		System.out.println("\nStack contents:");
		tableLabels();
		stateStack.printStack(stateStack.getFirst());
		
		while(!stateStack.isEmpty())
			statePQ.insert(stateStack.pop());
		
		System.out.println("\nPriority Queue contents:");
		tableLabels();
		statePQ.printPQ(statePQ.getFirst());
		
		//display interactive menu to user
		boolean flag = true; //repeat menu (true) or quit (false)
		int choice = 0, first, second;
		Scanner menuIn = new Scanner(System.in);
		
		while(flag == true) {
			
			System.out.println("\n1. Enter a DR interval for deletions.");
			System.out.println("2. Print the priority queue.");
			System.out.println("3. Exit program.");
			System.out.print("\nEnter the number of your choice: ");
			
			//check if input matches type int
			if(menuIn.hasNextInt()) {
				choice = menuIn.nextInt();
				menuIn.nextLine();
			} else {
				System.out.println("\nPlease enter 1, 2, or 3.");
				menuIn.nextLine();
				continue;
			}//end if else
			
			//menu functions
			switch(choice) {
			
			case 1: //Enter DR interval
				//collect values from user
				System.out.print("\nEnter 2 integers separated by a space, smaller first: ");
				if(scan.hasNextInt()) { //first #
					first = scan.nextInt();
					if(scan.hasNextInt()) { //second #
						second = scan.nextInt();
						if(first <= second) { //valid interval
							//System.out.println("yay");
							if(statePQ.intervalDelete(first, second) == true)
								System.out.println("\nStates of priority queue with DR in ["+first+","+second+"] are deleted.");
							else
								System.out.println("\nNo states found with DR in ["+first+","+second+"].");
						} else { //invalid interval
							System.out.println("Make sure first <= second.");
							scan.nextLine();
							//break;
						}//first <= second if..else
					} else { //second term invalid
						System.out.println("\nInvalid interval. Try again.");
						scan.nextLine();
						//break;
					}//second # if..else
				} else { //first term invalid
					System.out.println("\nInvalid interval. Try again.");
					scan.nextLine();
					//break;
				}//first # if..else
				break;
				
			case 2: //Print PQ
				System.out.println("\nPriority Queue contents:");
				tableLabels();
				statePQ.printPQ(statePQ.getFirst());
				break;
				
			case 3: //Exit
				System.out.println("\nGoodbye!");
				flag = false;
				break;
				
			default: //choice # out of range
				System.out.println("\nPlease enter 1, 2, or 3.");
				break;
				
			}//switch(choice)
			
		}//while() menu

		//close scanners
		menuIn.close();
		scan.close();
		
	}//main()
	
	/**
	 * tableLabels() prints the top row of labels for printing
	 * stack and priority queue contents.
	 */
	public static void tableLabels() {
		System.out.println("\nName           MHI           VCR           CFR           Case Rate     Death Rate");
		System.out.println("---------------------------------------------------------------------------------");
	}//tableLabels()

}//Project3

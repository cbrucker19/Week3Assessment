import java.util.List;
import java.util.Scanner;

import controller.ListBookHelper;
import model.ListBooks;
/**
 * @author cyrusbrucker - cbrucker19
 * CIS175 - Spring 2022
 * Feb 3, 2022
 */
public class StartProgramInterface {

	static Scanner in = new Scanner(System.in);
	static ListBookHelper lbh = new ListBookHelper();
	

	private static void addAnItem() {
		// TODO Auto-generated method stub
		System.out.print("Enter a Book: ");
		String books = in.nextLine();
		System.out.print("Enter an Class: ");
		String classes = in.nextLine();
		ListBooks toAdd = new ListBooks(books, classes);
		//add to listitem helper 
		lbh.insertItem(toAdd);
	}

	private static void deleteAnItem() {
		// TODO Auto-generated method stub
		System.out.print("Enter the book to delete: ");
		String books = in.nextLine();
		System.out.print("Enter the class to delete: ");
		String classes = in.nextLine();
		
		ListBooks toDelete = new ListBooks(books, classes);
		lbh.deleteItem(toDelete);
		//call and delete it 
		
	}

	private static void editAnItem() {
		// TODO Auto-generated method stub
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by Book");
		System.out.println("2 : Search by Class");
		int searchBy = in.nextInt();
		in.nextLine();
		List<ListBooks> foundBooks;
		if (searchBy == 1) {
			System.out.print("Enter the textbook: ");
			String bookName = in.nextLine();
			foundBooks = lbh.searchForItemByBook(bookName);
		} else {
			System.out.print("Enter the class: ");
			String className = in.nextLine();
			foundBooks = lbh.searchForItemByClass(className);
			

		}

		if (!foundBooks.isEmpty()) {
			System.out.println("Found Results.");
			for (ListBooks l : foundBooks) {
				System.out.println(l.getId() + " : " + l.toString());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			ListBooks toEdit = lbh.searchForListBookById(idToEdit);
			System.out.println("Retrieved " + toEdit.getBooks() + " from " + toEdit.getClass());
			System.out.println("1 : Update Class");
			System.out.println("2 : Update Book");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New Class: ");
				String newClass = in.nextLine();
				toEdit.setClasses(newClass);
			} else if (update == 2) {
				System.out.print("New TextBook: ");
				String newBook = in.nextLine();
				toEdit.setBooks(newBook);
			}

			lbh.updateListBooks(toEdit);

		} else {
			System.out.println("---- No results found");
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runMenu();

	}

	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("--- Welcome the new Semester! ---");
		while (goAgain) {
			System.out.println("*  Select an item:");
			System.out.println("*  1 -- Add an class");
			System.out.println("*  2 -- Edit an class");
			System.out.println("*  3 -- Delete an class");
			System.out.println("*  4 -- View the list");
			System.out.println("*  5 -- Exit the program");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addAnItem();
			} else if (selection == 2) {
				editAnItem();
			} else if (selection == 3) {
				deleteAnItem();
			} else if (selection == 4) {
				viewTheList();
			} else {
				lbh.cleanUp();
				System.out.println("   Goodbye!   ");
				goAgain = false;
			}

		}

	}

	private static void viewTheList() {
		// TODO Auto-generated method stub
		List<ListBooks> allItems = lbh.showAllItems();
		for(ListBooks singleItem : allItems) {
			System.out.println(singleItem.returnBookDetails());
		}

	}
}

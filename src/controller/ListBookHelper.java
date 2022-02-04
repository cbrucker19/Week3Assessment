package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ListBooks;
/**
 * @author cyrusbrucker - cbrucker19
 * CIS175 - Spring 2022
 * Feb 3, 2022
 */
public class ListBookHelper {

	//Creating a global instance of entitymanagerfactory
		static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ClassMaterials");
		
		//Creating method to accept ListItem to add
		
		public void insertItem(ListBooks li) {
			EntityManager em = emfactory.createEntityManager();
			em.getTransaction().begin();
			em.persist(li);
			em.getTransaction().commit();
			em.close();
		}
		
		public List<ListBooks> showAllItems(){
			EntityManager em = emfactory.createEntityManager();
			List<ListBooks>allItems = em.createQuery("SELECT i FROM ListBooks i").getResultList();
			return allItems; 
		}
		
		public void deleteItem(ListBooks toDelete) {
			EntityManager em = emfactory.createEntityManager();
			em.getTransaction().begin();
			TypedQuery<ListBooks>typedQuery = em.createQuery("select li from ListBooks li where li.books = :selectedBook and li.classes = :selectedClass", ListBooks.class);
			//Substitute parameter with actual data from the toDelete item
			//swithced around
			typedQuery.setParameter("selectedBook", toDelete.getBooks());
			typedQuery.setParameter("selectedClass", toDelete.getClass());
			
			//we only want on result
			typedQuery.setMaxResults(1);
			
			//get the result and save it into a new list item
			ListBooks result = typedQuery.getSingleResult();
			
			//remove it 
			em.remove(result);
			em.getTransaction().commit();
			em.close();	
		}
		
		
		
		
		
			//Search for Item by Store
		public List<ListBooks> searchForItemByBook(String bookName){
			EntityManager em = emfactory.createEntityManager();
			em.getTransaction().begin();
			TypedQuery<ListBooks> typedQuery = em.createQuery("select li from ListBooks li where li.books = :selectedBook", ListBooks.class);
			typedQuery.setParameter("selectedBook", bookName);
			List<ListBooks> foundBooks = typedQuery.getResultList();
			em.close();
			return foundBooks; 
		}
		//Search for Item by Items
		public List<ListBooks> searchForItemByClass(String className){
			EntityManager em = emfactory.createEntityManager();
			em.getTransaction().begin();
			TypedQuery<ListBooks> typedQuery = em.createQuery("select li from ListBooks li where li.classes = :selectedClass", ListBooks.class);
			typedQuery.setParameter("selectedClass", className);
			List<ListBooks> foundBooks = typedQuery.getResultList();
			em.close();
			return foundBooks; 
		}
		
		
		
		public ListBooks searchForListBookById(int idToEdit) {
			EntityManager em = emfactory.createEntityManager();
			em.getTransaction().begin();
			ListBooks found = em.find(ListBooks.class, idToEdit);
			em.close();
			return found; 
			
		}
		
		
		
		
		
		
		
		
		
		
		public void updateListBooks(ListBooks toEdit) {
			EntityManager em = emfactory.createEntityManager();
			em.getTransaction().begin();
			em.merge(toEdit);
			em.getTransaction().commit();
			em.close();
		}
		
		public void cleanUp() {
			emfactory.close();
		}
}

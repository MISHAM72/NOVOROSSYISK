
import java.util.ArrayList;
import java.util.List;

public class Library {
  public static void main (String[] args) {
	//------------------------------------------------------

	 class Book {
	  final String title;
	  final String author;
	  final String isbn;

	  public Book (String title, String author, String isbn) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
	  }

	  public String getTitle () {
		return title;
	  }

	  public String getAuthor () {
		return author;
	  }

	}
//------------------------------------------------------------
// Author.java (Модуль "Автор")
	class Author {
	  final String firstName;
	  final String lastName;

	  public Author (String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	  }

	  public String getFullName () {
		return firstName + " " + lastName;
	  }
	}
	//-----------------------------------------------------
	class Library1 {
	  final List <Book> books = new ArrayList <> ();

	  public void addBook (Book book) {
		books.add (book);
	  }

	  public Book findBookByTitle (String title) {
		for (Book book : books) {
		  if (book.getTitle ().equalsIgnoreCase (title)) {
			return book;
		  }
		}
		return null;
	  }
	}
//------------------------------------------------------------------
	 class User {
	  final String name;
	  final int userId;

	  public User (String name, int userId) {
		this.name = name;
		this.userId = userId;
	  }

	  public String getName () {
		return name;
	  }
	}
//--------------------------------------------------------------------
	Author tolstoy = new Author("Лев", "Толстой");
	Author dostoevsky = new Author("Федор", "Достоевский");

	// Создаем книги
	Book warAndPeace = new Book("Война и мир", tolstoy.getFullName (), "1234567890");
	Book crimeAndPunishment = new Book("Преступление и наказание", dostoevsky.getFullName (), "0987654321");

	// Создаем библиотеку
	Library1 library = new Library1 ();
	library.addBook(warAndPeace);
	library.addBook(crimeAndPunishment);

	// Ищем книгу
	Book foundBook = library.findBookByTitle("Война и мир");
	if (foundBook != null) {
	  System.out.println("Найдена книга: " + foundBook.getTitle() + " автор: " + foundBook.getAuthor());
	} else {
	  System.out.println("Книга не найдена");
	}

	// Создаем пользователя
	User user = new User("Иван Иванов", 123);
	System.out.println("Имя пользователя: " + user.getName());

  }
}


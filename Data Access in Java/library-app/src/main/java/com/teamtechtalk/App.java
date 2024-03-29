package com.teamtechtalk;

import com.teamtechtalk.model.Book;
import com.teamtechtalk.repository.BookDao;
import com.teamtechtalk.repository.Dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Dao<Book> bookDao = new BookDao();

        List<Book> books = bookDao.findAll();

        for(Book book: books){
            System.out.println("id: "+ book.getId());
            System.out.println("Title: "+ book.getTitle());
            System.out.println("Rating: "+ book.getRating());
        }

//        Optional<Book> optBook = bookDao.findById(1);
//        if(optBook.isPresent()){
//            Book book = optBook.get();
//
//            System.out.println("id: "+ book.getId());
//            System.out.println("Title: "+ book.getTitle());
//
//            book.setTitle("Effective Java: Second Edition");
//
//          //  bookDao.update(book);
//        }
//
//        Book newBook = new Book();
//        newBook.setTitle("The River Why");
//        newBook = bookDao.create(newBook);
//
//        System.out.println("id: "+ newBook.getId());
//        System.out.println("Title: "+ newBook.getTitle());
//
//        int numDel = bookDao.delete(newBook);
//
//        System.out.println("Number of records deleted: " + numDel);

//        books = bookDao.findAll();
//
//        List<Book> updatedEntries = books.stream()
//                .peek(b -> b.setRating(5))
//                .collect(Collectors.toList());
//
//        bookDao.update(updatedEntries);


    }
}

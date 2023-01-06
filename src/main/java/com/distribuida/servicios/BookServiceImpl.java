package com.distribuida.servicios;

import com.distribuida.config.DbConfig;
import com.distribuida.db.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class BookServiceImpl implements BookService{

    @Inject
    private DbConfig dbConfig;

    public Book findById (Integer id){
        Book ret = new Book();

        ret.setId(id);
        ret.setIsbn("isbn"+id);
        ret.setTitle("title"+id);
        ret.setAuthor("author"+id);
        ret.setPrice(id*30.24);
        return ret;
    }

    public List<Book> findAll(){
        Jdbi jdbi= dbConfig.init();
        try (Handle handle = jdbi.open()) {

            return handle.createQuery("SELECT * FROM \"books\" ORDER BY id ASC")
                    .mapToBean(Book.class)
                    .list();
        } catch (Exception e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void findAll1(Book book){
        Jdbi jdbi= dbConfig.init();
        Book ret1 = new Book();
        try (Handle handle = jdbi.open()) {

            String isbn = book.getIsbn();
            String author =book.getAuthor();
            String title =book.getTitle();
            Double price =book.getPrice();
            int count = handle.createUpdate("INSERT INTO \"books\" (\"isbn\", \"author\", \"title\",\"price\") VALUES(:isbn, :author, :title, :price)")
                    .bind("isbn", isbn)
                    .bind("author", author)
                    .bind("title", title)
                    .bind("price", price)
                    .execute();


        } catch (Exception e){
            e.printStackTrace();

        }

    }

    public void actualizar(Book book, Integer id){
        Jdbi jdbi= dbConfig.init();
        try (Handle handle = jdbi.open()) {
            //Integer id = book.getId();
            String isbn = book.getIsbn();
            String author =book.getAuthor();
            String title =book.getTitle();
            Double price =book.getPrice();

            int count = handle.createUpdate("update books set isbn = :isbn, author = :author , title = :title , price = :price where id = :id")
                    .bind("id", id)
                    .bind("isbn", isbn)
                    .bind("author", author)
                    .bind("title", title)
                    .bind("price", price)
                    .execute();

        } catch (Exception e){
            e.printStackTrace();
        }
        //return null;
    }

       public void delete(Integer id){
        Jdbi jdbi= dbConfig.init();
        try (Handle handle = jdbi.open()) {

             handle.createUpdate("DELETE FROM books WHERE id = :id")
                    .bind("id", id)
                    .execute();

        } catch (Exception e){
            e.printStackTrace();

        }
        //return null;
    }


    @Override
    public void insert(Book book) {
    }

    @Override
    public void update(Book book) {

    }


}

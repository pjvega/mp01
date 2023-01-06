package com.distribuida.servicios;

import com.distribuida.db.Book;

import java.util.List;

public interface BookService {
    Book findById(Integer id);
    List<Book> findAll();
     public void insert(Book book);
    public void update(Book book);
    void findAll1(Book book);
    void actualizar(Book book, Integer id);
    void delete (Integer id);

}
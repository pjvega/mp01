package com.distribuida.rest;

import com.distribuida.config.DbConfig;
import com.distribuida.db.Book;
import com.distribuida.servicios.BookService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Collections;
import java.util.List;

@ApplicationScoped
@Path("/books")
public class BookRest {
    @Inject private BookService bookService;

    @Inject private DbConfig dbConfig;

    /**
     * GET          /books{id}    buscar 1
     * GET          /books        listar todos
     * POST         /books        insertar/crear
     * PU/PATCH     /books{id}    actualizar
     * DELETE       /books{id}    eliminar
     */

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book findById(@PathParam("id") Integer  id){
        dbConfig.test();
        return bookService.findById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> findAll(){
        return bookService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void insert(Book book){ bookService.findAll1(book);

    }
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathParam("id")Integer id, Book book){
        bookService.actualizar(book, id);
    }
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") Integer id){
        bookService.delete(id);
    }


}

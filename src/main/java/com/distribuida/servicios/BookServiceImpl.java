package com.distribuida.servicios;

import com.distribuida.config.DbConfig;
import com.distribuida.db.Book;
import com.distribuida.rest.BookMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import org.jdbi.v3.core.Handle;

@ApplicationScoped
public class BookServiceImpl implements BookService{

    @Inject
    private DbConfig conexion;

    private List<Book> libros = new ArrayList<>();

    public Book findById(Integer id){
        Handle handle = conexion.pool().open();
        Book busc = handle.select("SELECT * FROM books where id =?",id).map(new BookMapper()).one();
        return busc;
    }

    public List<Book> findAll(){
        libros = null;

        try {
            Handle listar = conexion.pool().open();
            libros =listar.createQuery("SELECT * FROM books").mapToBean(Book.class).list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return libros;
    }

    @Override
    public void delete(Integer id) {
        Book eliminar = new Book();
        Handle eli = conexion.pool().open();
        eliminar =eli.createQuery("DELETE FROM books WHERE id =?;").bind(0, id).mapToBean(Book.class).findOnly();
    }

    @Override
    public void insert(Book book) {

    }

    @Override
    public void update(Integer id, Book book) {
        Handle actu = conexion.pool().open();
        actu.createUpdate("UPDATE libros SET isbn=:isbn, title=:title, author=:author, price=:price WHERE id=:id").bind("id",id)
                .bind("isbn", book.getIsbn()).bind("title", book.getTitle())
                .bind("author", book.getAuthor()).bind("price", book.getPrice()).execute();
    }

}

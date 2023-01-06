package com.distribuida.rest;

import com.distribuida.db.Book;
import org.jdbi.v3.core.statement.StatementContext;

import org.jdbi.v3.core.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper  implements RowMapper<Book> {
    @Override
    public Book map(ResultSet rs, StatementContext ctx) throws SQLException{
        return new Book(rs.getInt("id"), rs.getString("isbn"), rs.getString("title"), rs.getString("author"), rs.getDouble("price"));
    }
}

package com.trassos.bookstore.dtos;

import com.trassos.bookstore.domain.Book;

import java.io.Serializable;
import java.util.Objects;

public class BookDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String title;

    public BookDTO() {
    super();
    }

    public BookDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookDTO)) return false;
        BookDTO bookDTO = (BookDTO) o;
        return getId().equals(bookDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

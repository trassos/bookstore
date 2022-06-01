package com.trassos.bookstore.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Book  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Campo TÍTULO é requerido.")
    @Length(min = 3, max=100, message = "Campo TÍTULO deve ter entre 3 a 100 caracteres.")
    private String title;
    @NotEmpty(message = "Campo NOME DO AUTOR é requerido.")
    @Length(min = 3, max=100, message = "Campo  NOME DO AUTOR deve ter entre 3 a 100 caracteres.")
    private String author_name;
    @NotEmpty(message = "Campo TEXTO é requerido.")
    @Length(min = 3, max=100, message = "Campo  TEXTO deve ter entre 3 a 100 caracteres.")
    private String text;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Book() {
    }

    public Book(Long id, String title, String author_name, String text, Category category) {
        this.id = id;
        this.title = title;
        this.author_name = author_name;
        this.text = text;
        this.category = category;
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

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(getId(), book.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}


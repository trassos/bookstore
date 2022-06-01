package com.trassos.bookstore.dtos;

import com.trassos.bookstore.domain.Category;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CategoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    @NotEmpty(message = "Campo NOME é requerido.")
    @Length(min = 3, max=100, message = "Campo NOME deve ter entre 3 a 100 caracteres.")
    private String name;
    @NotEmpty(message = "Campo DESCRIÇÃO é requerido.")
    @Length(min = 3, max=200, message = "Campo DESCRIÇÃO deve ter entre 3 a 200 caracteres.")
    private String description;

    public CategoryDTO() {
        super();
    }

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

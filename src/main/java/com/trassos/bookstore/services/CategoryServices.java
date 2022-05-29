package com.trassos.bookstore.services;

import com.trassos.bookstore.domain.Category;
import com.trassos.bookstore.exceptions.ObjectNotFoundException;
import com.trassos.bookstore.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServices {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category findById(Long id) {
        Optional<Category> cat = categoryRepository.findById(id);
        return cat.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado id: "+id+ ", Tipo: "+ Category.class.getName()));
    }

    //Como tratar nulos aqui?
    public List<Category> findAll() {
        List<Category> catList = categoryRepository.findAll();
        return catList;
    }
}

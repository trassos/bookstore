package com.trassos.bookstore.services;

import com.trassos.bookstore.domain.Category;
import com.trassos.bookstore.dtos.CategoryDTO;
import com.trassos.bookstore.exceptions.ObjectNotFoundException;
import com.trassos.bookstore.repositories.CategoryRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
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

    public Category create(Category category) {
        category.setId(null);
        return categoryRepository.save(category);
    }


    public Category update(Long id, CategoryDTO categoryDTO) {
        Category category = findById(id);
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return categoryRepository.save(category);
    }


    public void delete(Long id) {
        findById(id);
        try {
            categoryRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new com.trassos.bookstore.exceptions.DataIntegrityViolationException(
                    "Categoria não pode ser deletada! Possui livros associados.");
        }

    }
}

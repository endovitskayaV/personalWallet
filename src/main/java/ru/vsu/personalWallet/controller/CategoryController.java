package ru.vsu.personalWallet.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.vsu.personalWallet.domain.dto.CategoryDto;
import ru.vsu.personalWallet.domain.dto.TransactionDto;
import ru.vsu.personalWallet.service.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;
    private Gson gson;

    @Autowired
    CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    @RequestMapping(value="delete", method = RequestMethod.DELETE)
    public boolean delete(long id){
        categoryService.delete(id);
        return true;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public boolean add(CategoryDto categoryDto){
        categoryService.save(categoryDto);
        return true;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public boolean edit(CategoryDto categoryDto){
        categoryService.save(categoryDto);
        return true;
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(long id){
        return gson.toJson(categoryService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public String getAll(){
        return gson.toJson(categoryService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public String getById(long id){
        return gson.toJson(categoryService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public String getByName(String name){
        return gson.toJson(categoryService.findByName(name));
    }
}

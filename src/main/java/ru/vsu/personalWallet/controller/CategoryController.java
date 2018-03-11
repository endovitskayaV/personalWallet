package ru.vsu.personalWallet.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.personalWallet.HttpResponse.HttpResponse;
import ru.vsu.personalWallet.domain.dto.CategoryDto;
import ru.vsu.personalWallet.service.CategoryService;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public boolean delete(String id) {
        return categoryService.delete(id);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public boolean add(@RequestBody CategoryDto categoryDto) {
        return categoryService.add(categoryDto);
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public boolean edit(@RequestBody CategoryDto categoryDto) {
        return categoryService.edit(categoryDto);
    }

    private ResponseEntity getCategoryDtoOrCode404(String id) {
        if (categoryService.findById(id) == null) {
            HttpHeaders httpHeader = new HttpHeaders();
            httpHeader.setConnection("close");
            return new ResponseEntity<>(
                    new HttpResponse()
                            .setTimestamp(Instant.now().getEpochSecond())
                            .setStatus(404)
                            .setError("Not found")
                            .setMessage("Category with id=" + id + " not found")
                            .setPath("/categories"),
                    httpHeader,
                    HttpStatus.NOT_FOUND);
        } else return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, params = {"id"})
    public ResponseEntity getById(String id) {
        return getCategoryDtoOrCode404(id);
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public ResponseEntity edit(String id) {
        return getCategoryDtoOrCode404(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CategoryDto> getAll() {
        return categoryService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public List<CategoryDto> getByName(String name) {
        return categoryService.findByName(name);
    }
}

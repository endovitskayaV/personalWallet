package ru.vsu.personalWallet.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.personalWallet.domain.dto.CategoryDto;
import ru.vsu.personalWallet.service.CategoryService;
import ru.vsu.personalWallet.util.HttpResponse;

import java.time.Instant;
import java.util.List;

import static ru.vsu.personalWallet.util.Constant.USER_ID_HEADER;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public ResponseEntity delete(long id, @RequestHeader(USER_ID_HEADER) long userId) {
        if (categoryService.delete(id, userId)) return ResponseEntity.noContent().build();
        else {
            HttpHeaders httpHeader = new HttpHeaders();
            httpHeader.setConnection("close");
            return new ResponseEntity<>(
                    new HttpResponse()
                            .setTimestamp(Instant.now().getEpochSecond())
                            .setStatus(404)
                            .setError("Not found")
                            .setMessage("Category with id=" + id + " not found")
                            .setPath("/categories/delete"),
                    httpHeader,
                    HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "add", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity add(@RequestBody CategoryDto categoryDto, @RequestHeader(USER_ID_HEADER) long userId) {
        categoryDto.setUserId(userId);
        return new ResponseEntity<>(categoryService.add(categoryDto),HttpStatus.OK);
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity edit(@RequestBody CategoryDto categoryDto, @RequestHeader(USER_ID_HEADER) long userId) {
        categoryDto.setUserId(userId);
        if (categoryService.edit(categoryDto)) return ResponseEntity.noContent().build();
        else {
            HttpHeaders httpHeader = new HttpHeaders();
            httpHeader.setConnection("close");
            return new ResponseEntity<>(
                    new HttpResponse()
                            .setTimestamp(Instant.now().getEpochSecond())
                            .setStatus(404)
                            .setError("Not found")
                            .setMessage("Category with id=" + categoryDto.getId() + " not found")
                            .setPath("/categories/edit"),
                    httpHeader,
                    HttpStatus.NOT_FOUND);
        }
    }

    private ResponseEntity getCategoryDtoOrCode404(long id, long userId, String path) {
        if (categoryService.findByIdAndUserId(id, userId) == null) {
            HttpHeaders httpHeader = new HttpHeaders();
            httpHeader.setConnection("close");
            return new ResponseEntity<>(
                    new HttpResponse()
                            .setTimestamp(Instant.now().getEpochSecond())
                            .setStatus(404)
                            .setError("Not found")
                            .setMessage("Category with id=" + id + " not found")
                            .setPath("/categories"+path),
                    httpHeader,
                    HttpStatus.NOT_FOUND);
        } else return new ResponseEntity<>(categoryService.findByIdAndUserId(id, userId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"id"})
    public ResponseEntity getById(long id, @RequestHeader(USER_ID_HEADER) long userId) {
        return getCategoryDtoOrCode404(id, userId, "");
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public ResponseEntity edit(long id, @RequestHeader(USER_ID_HEADER) long userId) {
        return getCategoryDtoOrCode404(id, userId, "/edit");
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAll(@RequestHeader(USER_ID_HEADER) long userId) {
        List<CategoryDto> categoryDtoList =categoryService.findAllByUserId(userId);
        if (categoryDtoList.size() == 0)
            return ResponseEntity.noContent().build();
        return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public ResponseEntity getByName(String name, @RequestHeader(USER_ID_HEADER) long userId) {
        List<CategoryDto> categoryDtoList = categoryService.findByNameAndUserId(name, userId);
        if (categoryDtoList.size() == 0)
            return ResponseEntity.noContent().build();
        return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);
    }
}

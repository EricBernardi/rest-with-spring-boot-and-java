package br.com.eric.controllers;

import br.com.eric.model.Phone;
import br.com.eric.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/phone")
public class PhoneController {

    @Autowired
    private PhoneService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Phone> getAll(){
        return service.findAll();
    }

}

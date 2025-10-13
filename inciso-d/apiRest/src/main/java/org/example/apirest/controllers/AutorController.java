package org.example.apirest.controllers;


import org.example.apirest.entities.Autor;
import org.example.apirest.services.AutorServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/autores")
public class AutorController extends BaseControllerImpl<Autor, AutorServiceImpl>{
}

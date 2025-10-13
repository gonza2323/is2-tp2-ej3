package org.example.apirest.controllers;

import org.example.apirest.entities.Libro;
import org.example.apirest.services.LibroServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/libros")
public class LibroController extends BaseControllerImpl<Libro, LibroServiceImpl> {
}


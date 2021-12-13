package ru.gb.java9_78.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.gb.java9_78.persist.Student;
import ru.gb.java9_78.persist.StudentRepository;

import java.util.List;

@RequestMapping("/student")
@RestController
public class StudentRestController {
    private final StudentRepository repository;

    @Autowired
    public StudentRestController(StudentRepository repository){
        this.repository = repository;
    }

    @GetMapping("/all")
    public List<Student> findAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}/id")
    public Student findById(@PathVariable("id") Long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Student not found"));
    }

    @Transactional
    @PostMapping
    public Student create(@RequestBody Student student){
        if (student.getId() != null) {
            throw new BadRequestException();
        }
        return repository.save(student);
    }

    @Transactional
    @PutMapping
    public Student update(@RequestBody Student student){
        if (student.getId() == null) {
            throw new BadRequestException();
        }
        return repository.save(student);
    }

    @Transactional
    @DeleteMapping("/{id}/id")
    public void delete(@PathVariable("id") Long id){
        repository.deleteById(id);
    }

    @ExceptionHandler
    public ResponseEntity<Void> handlerNotFoundException(NotFoundException ex){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> handlerBadRequestException(BadRequestException ex){
        return ResponseEntity.badRequest().build();
    }




}

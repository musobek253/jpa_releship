package uz.pdp.jpa_releship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.jpa_releship.dto.ApiResponse;
import uz.pdp.jpa_releship.dto.StudentDto;
import uz.pdp.jpa_releship.entity.Student;
import uz.pdp.jpa_releship.repositary.StudentRepozitary;
import uz.pdp.jpa_releship.service.StudentService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentRepozitary studentRepozitary;
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentRepozitary studentRepozitary, StudentService studentService) {
        this.studentRepozitary = studentRepozitary;
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public List<Student> getStudent(){
         return studentRepozitary.findAll();
    }
    @GetMapping("/{id}")
    public Student getByIdStudent(@PathVariable Integer id){
        Optional<Student> optionalStudent = studentRepozitary.findById(id);
        return optionalStudent.orElseGet(Student::new);
    }

    @PostMapping("/add")
    public ApiResponse addStudents(@RequestBody StudentDto studentDto){
       return studentService.addStudent(studentDto);
    }
    @PutMapping("/{id}")
    public ApiResponse editStudent(@PathVariable Integer id,@RequestBody StudentDto studentDto){
        return  studentService.editStudent(id, studentDto);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deletedStudentById(@PathVariable Integer id){
        studentRepozitary.deleteById(id);
       return new ApiResponse("Successfully deleted",true);
    }




}

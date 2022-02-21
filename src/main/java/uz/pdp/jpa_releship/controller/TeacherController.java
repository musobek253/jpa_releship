package uz.pdp.jpa_releship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.jpa_releship.dto.ApiResponse;
import uz.pdp.jpa_releship.dto.TeacherDTO;
import uz.pdp.jpa_releship.entity.Teacher;
import uz.pdp.jpa_releship.repositary.TeacherRepository;
import uz.pdp.jpa_releship.service.TeacherService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @Autowired
    TeacherRepository teacherRepository;

    @PostMapping
    public ApiResponse add(@RequestBody TeacherDTO teacherDTO) {
        return teacherService.addSubject(teacherDTO);
    }

    @GetMapping("/all")
    private List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @GetMapping("/one/{id}")
    private Teacher getOne(@PathVariable Integer id) {
        Optional<Teacher> byId = teacherRepository.findById(id);
        return byId.get();
    }

    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Integer id, @RequestBody TeacherDTO teacherDTO) {
        return teacherService.editSubject(id, teacherDTO);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        teacherRepository.deleteById(id);
        return new ApiResponse("Deleted successfully", true);
    }
}
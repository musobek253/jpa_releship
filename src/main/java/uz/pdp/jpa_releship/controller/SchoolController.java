package uz.pdp.jpa_releship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.jpa_releship.dto.ApiResponse;
import uz.pdp.jpa_releship.entity.School;
import uz.pdp.jpa_releship.repositary.SchoolRepository;
import uz.pdp.jpa_releship.service.SchoolService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    SchoolService schoolService;
    @Autowired
    SchoolRepository schoolRepository;
    @PostMapping
    public ApiResponse add(@RequestBody School school){
        return schoolService.addSchool(school);
    }
    @GetMapping("/all")
    private List<School> getAll(){
        return schoolRepository.findAll();
    }
    @GetMapping("/one/{id}")
    private School getOne(@PathVariable Integer id){
        Optional<School> schoolOptional = schoolRepository.findById(id);
        return schoolOptional.orElseGet(School::new);
    }
    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Integer id, @RequestBody School school){
        return schoolService.editSchool(id, school);
    }
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        schoolRepository.deleteById(id);
        return new ApiResponse("Deleted successfully",true);
    }

}

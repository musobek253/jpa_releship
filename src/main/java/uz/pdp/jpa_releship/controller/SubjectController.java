package uz.pdp.jpa_releship.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import uz.pdp.jpa_releship.dto.ApiResponse;
import uz.pdp.jpa_releship.entity.Subject;
import uz.pdp.jpa_releship.repositary.SubjectRepository;
import uz.pdp.jpa_releship.service.SubjectService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subject")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;
    private final SubjectRepository subjectRepository;

    @PostMapping
    public ApiResponse add(@RequestBody Subject subject) {
        return subjectService.addSubject(subject);
    }

    @GetMapping("/all")
    private List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    @GetMapping("/one/{id}")
    private Subject getOne(@PathVariable Integer id) {
        Optional<Subject> byId = subjectRepository.findById(id);
        return byId.get();
    }

    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Integer id, @RequestBody Subject subject) {
        return subjectService.editSubject(id, subject);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        subjectRepository.deleteById(id);
        return new ApiResponse("Deleted successfully", true);
    }

}

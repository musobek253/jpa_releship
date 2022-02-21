package uz.pdp.jpa_releship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.jpa_releship.dto.ApiResponse;
import uz.pdp.jpa_releship.dto.MarkDTO;
import uz.pdp.jpa_releship.entity.Mark;
import uz.pdp.jpa_releship.repositary.MarkRepozitary;
import uz.pdp.jpa_releship.service.MarkService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mark")
public class MarkCantroller {
    private final MarkRepozitary markRepozitary;
    private final MarkService markService;

    @Autowired
    public MarkCantroller(MarkRepozitary markRepozitary, MarkService markService) {
        this.markRepozitary = markRepozitary;
        this.markService = markService;
    }

    @GetMapping("/all")
    public List<Mark> getMark(){
        return markRepozitary.findAll();
    }
     @GetMapping("/{id}")
    public Mark getByIdMark(@PathVariable Integer id){
         Optional<Mark> optionalMark = markRepozitary.findById(id);
        return optionalMark.orElseGet(Mark::new);
     }
     @PostMapping("/add")
    public ApiResponse addmark(@RequestBody MarkDTO markDTO){
        return markService.AddMark(markDTO);
     }
     @PutMapping("/{id}")
    public ApiResponse editMark(@PathVariable Integer id,@RequestBody MarkDTO markDTO){
        return markService.editMark(id, markDTO);
     }
     @DeleteMapping("/{id}")
    public ApiResponse deletedmark(@PathVariable Integer id){
        markRepozitary.deleteById(id);
        return new ApiResponse("successfully deleted",true);
     }
}

package uz.pdp.jpa_releship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.jpa_releship.dto.ApiResponse;
import uz.pdp.jpa_releship.dto.GroupsDTO;
import uz.pdp.jpa_releship.entity.Groups;
import uz.pdp.jpa_releship.repositary.GroupsRepository;
import uz.pdp.jpa_releship.service.GroupsService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/groups")
public class GroupsController {

     private  final GroupsService groupsService;
     private  final GroupsRepository groupsRepository;

    @Autowired
    public GroupsController(GroupsService groupsService, GroupsRepository groupsRepository) {
        this.groupsService = groupsService;
        this.groupsRepository = groupsRepository;
    }


    @PostMapping("/add")
    public ApiResponse add(@RequestBody GroupsDTO groupsDTO){
        return groupsService.addGroups(groupsDTO);
    }
    @GetMapping("/all")
    public List<Groups> getAllGroups(){
         return  groupsRepository.findAll();
    }
    @PutMapping("/{id}")
    public ApiResponse editGroups(@PathVariable Integer id,@RequestBody GroupsDTO groupsDTO){
         return groupsService.editGroups(id, groupsDTO);
    }
    @GetMapping("/{id}")
    public Groups getById(@PathVariable Integer id){
        Optional<Groups> optionalGroups = groupsRepository.findById(id);
        return optionalGroups.orElseGet(Groups::new);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deletedByIdGroups(@PathVariable Integer id){
        groupsRepository.deleteById(id);
        return new ApiResponse("successfully deletd",true);
    }
}

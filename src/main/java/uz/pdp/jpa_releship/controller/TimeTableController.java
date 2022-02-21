package uz.pdp.jpa_releship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.jpa_releship.dto.ApiResponse;
import uz.pdp.jpa_releship.dto.TeacherDTO;
import uz.pdp.jpa_releship.dto.TimeTableDTO;
import uz.pdp.jpa_releship.entity.TimeTable;
import uz.pdp.jpa_releship.repositary.TimeTableRepozitary;
import uz.pdp.jpa_releship.service.TimeTableService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jadval")
public class TimeTableController {
    private final TimeTableRepozitary timeTableRepozitary;
    private final TimeTableService timeTableService;

    public TimeTableController(TimeTableRepozitary timeTableRepozitary, TimeTableService timeTableService) {
        this.timeTableRepozitary = timeTableRepozitary;
        this.timeTableService = timeTableService;
    }

    @GetMapping("/all")
    public List<TimeTable> getTimeTable(){
        return timeTableRepozitary.findAll();
    }

    @GetMapping("/{id}")
    public TimeTable getByTimeTable(@PathVariable Integer id){
        Optional<TimeTable> optionalTimeTable = timeTableRepozitary.findById(id);
        return optionalTimeTable.orElseGet(TimeTable::new);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deletedTimetable(@PathVariable Integer id){
        timeTableRepozitary.deleteById(id);
        return new ApiResponse("Successfulley deleted",false);
    }
    @PostMapping("/add")
    public ApiResponse addTimeTable(@RequestBody TimeTableDTO timeTableDTO){
        return timeTableService.addTimeTable(timeTableDTO);
    }
    @PutMapping("/{id}")
    public ApiResponse editTimeTable(@PathVariable Integer id,@RequestBody TimeTableDTO timeTableDTO){
        return timeTableService.editTimeTable(id, timeTableDTO);
    }
}

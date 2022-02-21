package uz.pdp.jpa_releship.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.jpa_releship.dto.ApiResponse;
import uz.pdp.jpa_releship.dto.TimeTableDTO;
import uz.pdp.jpa_releship.entity.TimeTable;
import uz.pdp.jpa_releship.repositary.GroupsRepository;
import uz.pdp.jpa_releship.repositary.TeacherRepository;
import uz.pdp.jpa_releship.repositary.TimeTableRepozitary;

import java.util.Optional;

@Service
public class TimeTableService {

    private final TimeTableRepozitary timeTableRepozitary;
    private final TeacherRepository teacherRepository;
    private final GroupsRepository groupsRepository;
    @Autowired
    public TimeTableService(TimeTableRepozitary timeTableRepozitary, TeacherRepository teacherRepository, GroupsRepository groupsRepository) {
        this.timeTableRepozitary = timeTableRepozitary;
        this.teacherRepository = teacherRepository;
        this.groupsRepository = groupsRepository;
    }

    public ApiResponse addTimeTable( TimeTableDTO timeTableDTO){
        if (!teacherRepository.findById(timeTableDTO.getTeacherId()).isPresent())
            return new ApiResponse("Teacher Not found",false);
        if (!groupsRepository.findById(timeTableDTO.getGroupsId()).isPresent())
            return new ApiResponse("Groups not fount",false);
        TimeTable timeTable = new TimeTable();
        timeTable.setGroups(groupsRepository.findById(timeTableDTO.getGroupsId()).get());
        timeTable.setTeacher(teacherRepository.findById(timeTableDTO.getTeacherId()).get());
        timeTable.setStartTime(timeTableDTO.getStartTime());
        timeTable.setEndTime(timeTableDTO.getEndTime());
        timeTableRepozitary.save(timeTable);
        return new ApiResponse("Successfully Added",true);
    }

    public ApiResponse editTimeTable(Integer id,TimeTableDTO timeTableDTO){
        Optional<TimeTable> tableOptional = timeTableRepozitary.findById(id);
        TimeTable timeTable = tableOptional.get();
        if (!tableOptional.isPresent())
            return new ApiResponse("TimeTable not Found", false);
        if (!teacherRepository.findById(timeTableDTO.getTeacherId()).isPresent())
            return new ApiResponse("Teacher not found",false);
        if (!groupsRepository.findById(timeTableDTO.getGroupsId()).isPresent())
            return new ApiResponse("Groups not found",false);
        timeTable.setStartTime(timeTableDTO.getStartTime());
        timeTable.setEndTime(timeTableDTO.getEndTime());
        timeTable.setTeacher(teacherRepository.findById(timeTableDTO.getTeacherId()).get());
        timeTable.setGroups(groupsRepository.findById(timeTableDTO.getGroupsId()).get());
        return new ApiResponse("Successfully edited", true);
    }

}

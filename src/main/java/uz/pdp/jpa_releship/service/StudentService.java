package uz.pdp.jpa_releship.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.jpa_releship.dto.ApiResponse;
import uz.pdp.jpa_releship.dto.StudentDto;
import uz.pdp.jpa_releship.entity.Student;
import uz.pdp.jpa_releship.repositary.GroupsRepository;
import uz.pdp.jpa_releship.repositary.StudentRepozitary;

import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepozitary studentRepozitary;
    private final GroupsRepository groupsRepository;

    @Autowired
    public StudentService(StudentRepozitary studentRepozitary, GroupsRepository groupsRepository) {
        this.studentRepozitary = studentRepozitary;
        this.groupsRepository = groupsRepository;
    }

    public ApiResponse addStudent(@RequestBody StudentDto studentDto){

        Student student = new Student();
        if (studentRepozitary.existsByPhoneNumber(studentDto.getPhoneNumber()))
            return new ApiResponse("Already exist PhoneNumber", false);
        if (!groupsRepository.findById(studentDto.getGroupsId()).isPresent())
            return new ApiResponse("Groups Not Found",false);
        student.setFrist_name(studentDto.getFristName());
        student.setLastName(studentDto.getLastName());
        student.setPhoneNumber(studentDto.getPhoneNumber());
        student.setGroups(groupsRepository.findById(studentDto.getGroupsId()).get());
        studentRepozitary.save(student);
        return  new ApiResponse("Successfully Added",true);
    }

    public  ApiResponse editStudent(Integer id,StudentDto studentDto){
        Optional<Student> optionalStudent = studentRepozitary.findById(id);
        Student student = optionalStudent.get();
        if (!optionalStudent.isPresent())
            return new ApiResponse("Student Not Found", false);
        if (studentRepozitary.existsByPhoneNumber(studentDto.getPhoneNumber()))
            return new ApiResponse("Already exsit PhoneNumber",false);
        if (!groupsRepository.findById(studentDto.getGroupsId()).isPresent())
            return new ApiResponse("Groups not fount",false);
        student.setPhoneNumber(studentDto.getPhoneNumber());
        student.setLastName(studentDto.getLastName());
        student.setFrist_name(studentDto.getFristName());
        student.setGroups(groupsRepository.findById(studentDto.getGroupsId()).get());
        return new ApiResponse("Successfully edited",true);
    }
}

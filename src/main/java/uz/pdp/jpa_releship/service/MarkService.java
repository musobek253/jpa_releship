package uz.pdp.jpa_releship.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.jpa_releship.dto.ApiResponse;
import uz.pdp.jpa_releship.dto.MarkDTO;
import uz.pdp.jpa_releship.entity.Mark;
import uz.pdp.jpa_releship.repositary.MarkRepozitary;
import uz.pdp.jpa_releship.repositary.StudentRepozitary;
import uz.pdp.jpa_releship.repositary.TeacherRepository;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MarkService {

    private final MarkRepozitary markRepozitary;
    private final StudentRepozitary studentRepozitary;
    private  final TeacherRepository teacherRepository;
    @Autowired
    public MarkService(MarkRepozitary markRepozitary, StudentRepozitary studentRepozitary, TeacherRepository teacherRepository) {
        this.markRepozitary = markRepozitary;
        this.studentRepozitary = studentRepozitary;
        this.teacherRepository = teacherRepository;
    }

    public ApiResponse AddMark(MarkDTO markDTO){
        Mark mark = new Mark();
        if(!teacherRepository.findById(markDTO.getTeacherId()).isPresent())
            return new ApiResponse("Teacher Not found",false);
        if (!studentRepozitary.findById(markDTO.getStudentId()).isPresent())
            return new ApiResponse("Student not found",false);
        mark.setDate(markDTO.getDate());
        mark.setStudent(studentRepozitary.findById(markDTO.getStudentId()).get());
        mark.setTeacher(teacherRepository.findById(markDTO.getTeacherId()).get());
        mark.setValue(markDTO.getValue());
        markRepozitary.save(mark);
        return new ApiResponse("Successfully Add", true);
    }

    public  ApiResponse editMark(Integer id, MarkDTO markDTO){


        Optional<Mark> markOptional = markRepozitary.findById(id);
        Mark mark = markOptional.get();
        if (!markOptional.isPresent())
            return new ApiResponse("Mark not found",false);
        if(!teacherRepository.findById(markDTO.getTeacherId()).isPresent())
            return new ApiResponse("Teacher Not found",false);
        if (!studentRepozitary.findById(markDTO.getStudentId()).isPresent())
            return new ApiResponse("Student not found",false);
        mark.setDate(markDTO.getDate());
        mark.setValue(markDTO.getValue());
        mark.setTeacher(teacherRepository.findById(markDTO.getTeacherId()).get());
        mark.setStudent(studentRepozitary.findById(markDTO.getStudentId()).get());
        markRepozitary.save(mark);
        return new ApiResponse("Successfuly edited",true);
    }
}

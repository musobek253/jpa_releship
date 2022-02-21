package uz.pdp.jpa_releship.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.jpa_releship.dto.ApiResponse;
import uz.pdp.jpa_releship.dto.TeacherDTO;
import uz.pdp.jpa_releship.entity.Teacher;
import uz.pdp.jpa_releship.repositary.SubjectRepository;
import uz.pdp.jpa_releship.repositary.TeacherRepository;

import java.util.Optional;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    @Autowired
    public TeacherService(SubjectRepository subjectRepository, TeacherRepository teacherRepository) {
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
    }

    public ApiResponse addSubject(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        if (teacherRepository.existsByName(teacherDTO.getName()))
            return new ApiResponse("Already exist", false);
        if (!subjectRepository.findById(teacherDTO.getSubjectId()).isPresent())
            return new ApiResponse("Subject not found", false);

        teacher.setName(teacherDTO.getName());
        teacher.setSubject(subjectRepository.findById(teacherDTO.getSubjectId()).get());
        teacherRepository.save(teacher);
        return new ApiResponse("Added succes", true);
    }

    public ApiResponse editSubject(Integer id, TeacherDTO teacherDTO) {
        Optional<Teacher> byId = teacherRepository.findById(id);
        Teacher teacher1 = byId.get();
        if (!byId.isPresent())
            return new ApiResponse("Not found", false);
        if (subjectRepository.existsByName(teacher1.getName()))
            return new ApiResponse("Already exist", false);
        teacher1.setName(teacherDTO.getName());
        teacher1.setSubject(subjectRepository.findById(teacherDTO.getSubjectId()).get());
        teacherRepository.save(teacher1);
        return new ApiResponse("edit Successfully", true);
    }
}

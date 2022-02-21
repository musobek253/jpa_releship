package uz.pdp.jpa_releship.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.jpa_releship.dto.ApiResponse;
import uz.pdp.jpa_releship.dto.GroupsDTO;
import uz.pdp.jpa_releship.entity.Groups;
import uz.pdp.jpa_releship.repositary.GroupsRepository;
import uz.pdp.jpa_releship.repositary.SchoolRepository;
import uz.pdp.jpa_releship.repositary.SubjectRepository;
import uz.pdp.jpa_releship.repositary.TeacherRepository;

import java.util.Optional;


@Service
public class GroupsService {

    private final SchoolRepository schoolRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final GroupsRepository groupsRepository;

    @Autowired
    public GroupsService(SchoolRepository schoolRepository, TeacherRepository teacherRepository, SubjectRepository subjectRepository, GroupsRepository groupsRepository) {
        this.schoolRepository = schoolRepository;
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
        this.groupsRepository = groupsRepository;
    }
    public ApiResponse addGroups(GroupsDTO groupsDTO){
        Groups groups = new Groups();
        if (groupsRepository.existsByName(groupsDTO.getName()))
            return new ApiResponse("Already exist", false);
        if (!schoolRepository.findById(groupsDTO.getSchoolId()).isPresent())
            return new ApiResponse("School not found",false);
        if (!teacherRepository.findById(groupsDTO.getTeacherId()).isPresent())
            return new ApiResponse("Teacher not found",false);

        if (!subjectRepository.existsByIdIn(groupsDTO.getSubjectsIds())) {
            return new ApiResponse("Subjects not found", false);
        } else {
            groups.setName(groupsDTO.getName());
            groups.setSchool(schoolRepository.getById(groupsDTO.getSchoolId()));
            groups.setTeacher(teacherRepository.getById(groupsDTO.getTeacherId()));

            groups.setSubjectList(subjectRepository.findAllByIdIn(groupsDTO.getSubjectsIds()));
            groupsRepository.save(groups);
            return new ApiResponse("Added succes", true);
        }
    }

    public ApiResponse editGroups(Integer id, GroupsDTO groupsDTO){
        Optional<Groups> optionalGroups = groupsRepository.findById(id);
        if (!optionalGroups.isPresent())
            return new ApiResponse("Not found",false);
        if (groupsRepository.existsByName(groupsDTO.getName()))
            return new ApiResponse("Already exist",false);
        if (!subjectRepository.existsByIdIn(groupsDTO.getSubjectsIds()))
            return  new ApiResponse("Subject not faound",false);
        if (!schoolRepository.findById(groupsDTO.getSchoolId()).isPresent())
            return new ApiResponse("School not found",false);
        if (!teacherRepository.findById(groupsDTO.getTeacherId()).isPresent())
            return new ApiResponse("Teacher not found",false);
        Groups groups = optionalGroups.get();
        groups.setSubjectList(subjectRepository.findAllByIdIn(groupsDTO.getSubjectsIds()));
        groups.setSchool(schoolRepository.findById(groupsDTO.getSchoolId()).get());
        groups.setTeacher(teacherRepository.findById(groupsDTO.getTeacherId()).get());
        groups.setName(groupsDTO.getName());
        groupsRepository.save(groups);
        return new ApiResponse("Successfully edited",true);
    }
}
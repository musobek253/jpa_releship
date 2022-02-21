package uz.pdp.jpa_releship.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.jpa_releship.dto.ApiResponse;
import uz.pdp.jpa_releship.entity.Subject;
import uz.pdp.jpa_releship.repositary.SubjectRepository;

import java.util.Optional;

@Service
public class SubjectService {
    final
    SubjectRepository subjectRepository;
    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public ApiResponse addSubject(Subject subject){
        Subject subject1 = new Subject();
        if (subjectRepository.existsByName(subject.getName()))
            return new ApiResponse("Already exist",false);
        subject1.setName(subject.getName());
        subjectRepository.save(subject1);
        return new ApiResponse("Added succes",true);
    }

    public ApiResponse editSubject(Integer id,Subject subject){
        Optional<Subject> byId = subjectRepository.findById(id);
        Subject subject1 = byId.get();
        if (!byId.isPresent())
            return new ApiResponse("Not found",false);
        if (subjectRepository.existsByName(subject.getName()))
            return new ApiResponse("Already exist",false);
        subject1.setName(subject.getName());
        subjectRepository.save(subject1);
        return new ApiResponse("Added Successfully",true);
    }
}

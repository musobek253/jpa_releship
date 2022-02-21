package uz.pdp.jpa_releship.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.jpa_releship.dto.ApiResponse;
import uz.pdp.jpa_releship.entity.School;
import uz.pdp.jpa_releship.repositary.SchoolRepository;

import java.util.Optional;
@Service
public class SchoolService {
    @Autowired
    SchoolRepository schoolRepository;

    public ApiResponse addSchool(School school){
        School school1=new School();
        if (schoolRepository.existsByName(school.getName()))
            return new ApiResponse("Already exist",false);
        school1.setName(school.getName());
        schoolRepository.save(school1);
        return new ApiResponse("Added succes",true);
    }

    public ApiResponse editSchool(Integer id,School school){
        Optional<School> schoolOptional = schoolRepository.findById(id);
        School school1 = schoolOptional.get();
        if (schoolRepository.existsByName(school.getName()))
            return new ApiResponse("Already exist",false);
        school1.setName(school.getName());
        schoolRepository.save(school1);
        return new ApiResponse("Added Successfully",true);
    }
}

package com.WebApps.Benchmark.Service;

import com.WebApps.Benchmark.DTO.BreakageDTO;
import com.WebApps.Benchmark.Mapper.BreakageMapper;
import com.WebApps.Benchmark.Model.Breakage;
import com.WebApps.Benchmark.Repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BreakageService {

    private final BreakageRepository breakageRepository;
    private final TestCaseVersionRepository testCaseVersionRepository;
    private final AppReleaseRepository appReleaseRepository;
    private final BreakageReasonRepository breakageReasonRepository;
    private final LocatingMethodRepository locatingMethodRepository;
    public BreakageService(BreakageRepository breakageRepository, TestCaseVersionRepository testCaseVersionRepository, AppReleaseRepository appReleaseRepository, BreakageReasonRepository breakageReasonRepository, LocatingMethodRepository locatingMethodRepository) {
        this.breakageRepository = breakageRepository;
        this.testCaseVersionRepository = testCaseVersionRepository;
        this.appReleaseRepository = appReleaseRepository;
        this.breakageReasonRepository = breakageReasonRepository;
        this.locatingMethodRepository = locatingMethodRepository;
    }

    public List<BreakageDTO> findAll(){
        List<Breakage> breakages = breakageRepository.findAll();
        return breakages.stream()
                .map(BreakageMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BreakageDTO findById(int id){
        Breakage breakage = breakageRepository.getReferenceById(id);
        return BreakageMapper.toDTO(breakage);
    }

    public BreakageDTO save(BreakageDTO breakageDTO) {
        Breakage breakage = new Breakage();
        breakage.setTaxonomyDescription(breakageDTO.getTaxonomyDescription());
        breakage.setAppRelease(appReleaseRepository.getReferenceById(breakageDTO.getAppRelease()));
        breakage.setTestCaseVersion(testCaseVersionRepository.getReferenceById(breakageDTO.getTestCaseVersion()));
        breakage.setBreakageReason(breakageReasonRepository.getReferenceById(breakageDTO.getBreakageReasonID()));
        if (breakageDTO.getLocatingMethodID() != 0) {
            breakage.setLocatingMethod(locatingMethodRepository.getReferenceById(breakageDTO.getLocatingMethodID()));
        }
        breakageRepository.save(breakage);

        breakageDTO.setId(breakage.getId());
        return breakageDTO;
    }

}

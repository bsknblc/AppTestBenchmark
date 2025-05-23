package com.WebApps.Benchmark.Service;

import com.WebApps.Benchmark.DTO.RepairDTO;
import com.WebApps.Benchmark.Mapper.RepairMapper;
import com.WebApps.Benchmark.Model.Repair;
import com.WebApps.Benchmark.Repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepairService {

    private final RepairRepository repairRepository;
    private final BreakageRepository breakageRepository;
    private final RepairExplanationRepository repairExplanationRepository;
    public RepairService(RepairRepository repairRepository, BreakageRepository breakageRepository, RepairExplanationRepository repairExplanationRepository) {
        this.repairRepository = repairRepository;
        this.breakageRepository = breakageRepository;
        this.repairExplanationRepository = repairExplanationRepository;
    }

    public List<RepairDTO> findAll(){
        List<Repair> repairs = repairRepository.findAll();
        return repairs.stream()
                .map(RepairMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RepairDTO findById(int id){
        Repair repair = repairRepository.getReferenceById(id);
        return RepairMapper.toDTO(repair);
    }

    public List<RepairDTO> findByBreakageId(int breakageId){
        List<Repair> repairs = repairRepository.findByBreakage_Id(breakageId);
        return repairs.stream()
                .map(RepairMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RepairDTO save(RepairDTO repairDTO) {
        Repair repair = new Repair();
        repair.setBreakage(breakageRepository.getReferenceById(repairDTO.getBreakageId()));
        repair.setCommitHash(repairDTO.getCommitHash());
        repair.setRepairExplanations(repairDTO.getRepairExplanations().stream()
                .map(dto -> repairExplanationRepository.getReferenceById(dto.getId()))
                .collect(Collectors.toList()));
        repairRepository.save(repair);

        repairDTO.setId(repair.getId());
        return repairDTO;
    }

}



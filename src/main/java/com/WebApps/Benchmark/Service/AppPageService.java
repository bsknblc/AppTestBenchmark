package com.WebApps.Benchmark.Service;

import com.WebApps.Benchmark.DTO.AppPageDTO;
import com.WebApps.Benchmark.Model.AppPage;
import com.WebApps.Benchmark.Model.Application;
import com.WebApps.Benchmark.Repository.AppPageRepository;
import com.WebApps.Benchmark.Repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppPageService {
    @Autowired
    AppPageRepository appPageRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    public List<AppPageDTO> findAll(){
        List<AppPage> appPages = appPageRepository.findAll();
        List<AppPageDTO> appPageDTOs = new ArrayList<>();
        return appPageDTOs;
    }

    public AppPageDTO findById(int id){
        AppPage appPage = appPageRepository.getReferenceById(id);
        return new AppPageDTO();
    }

    public AppPage save(AppPage applicationPage) {
        Application app = applicationRepository.findById(applicationPage.getApplication().getId())
                .orElseThrow(() -> new RuntimeException("Application not found"));

        AppPage appPage = new AppPage(applicationPage.getPageName(),app);

        return appPageRepository.save(appPage);
    }

}

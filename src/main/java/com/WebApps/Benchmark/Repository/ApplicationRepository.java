package com.WebApps.Benchmark.Repository;

import com.WebApps.Benchmark.Model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    public Application getReferenceById(int id);
}

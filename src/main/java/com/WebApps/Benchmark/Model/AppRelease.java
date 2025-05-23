package com.WebApps.Benchmark.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_APP_RELEASE")
public class AppRelease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID")
    private int id;

    @Column(name = "RELEASE_NAME")
    @NotNull
    private String releaseName;

    @OneToMany(mappedBy = "appRelease", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Breakage> breakages = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)
    @NotNull
    private Application application;

    public AppRelease(){}

    public AppRelease(String releaseName, Application application) {
        this.releaseName = releaseName;
        this.application = application;
    }

    public int getId() {
        return id;
    }

    public String getReleaseName() {
        return releaseName;
    }

    public void setReleaseName(String releaseName) {
        this.releaseName = releaseName;
    }

    public List<Breakage> getBreakages() {
        return breakages;
    }

    public void setBreakages(List<Breakage> breakages) {
        this.breakages = breakages;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}

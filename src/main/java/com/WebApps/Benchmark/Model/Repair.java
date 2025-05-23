package com.WebApps.Benchmark.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_REPAIR")
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "breakage_id", nullable = false)
    @NotNull
    private Breakage breakage;

    @Column(name = "COMMIT_HASH")
    @NotNull
    private String commitHash;

    @ManyToMany
    @JoinTable(
            name = "REPAIR_EXPLANATION",
            joinColumns = @JoinColumn(name = "repair_id"),
            inverseJoinColumns = @JoinColumn(name = "repair_explanation_id")
    )
    private List<RepairExplanation> repairExplanations = new ArrayList<>();

    public Repair() {}

    public Repair(Breakage breakage, String commitHash, List<RepairExplanation> repairExplanations) {
        this.breakage = breakage;
        this.commitHash = commitHash;
        this.repairExplanations = repairExplanations;
    }

    public int getId() {
        return id;
    }

    public Breakage getBreakage() {
        return breakage;
    }

    public void setBreakage(Breakage breakage) {
        this.breakage = breakage;
    }

    public String getCommitHash() {
        return commitHash;
    }

    public void setCommitHash(String commitHash) {
        this.commitHash = commitHash;
    }

    public List<RepairExplanation> getRepairExplanations() {
        return repairExplanations;
    }

    public void setRepairExplanations(List<RepairExplanation> repairExplanations) {
        this.repairExplanations = repairExplanations;
    }
}

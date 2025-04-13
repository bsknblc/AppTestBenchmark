package com.WebApps.Benchmark.DTO;

import com.WebApps.Benchmark.Model.TestCase;

import java.util.ArrayList;
import java.util.List;

public class TestSuiteDTO {

    private int id;
    private String testSuiteName;
    private List<TestCaseDTO> testCases = new ArrayList<>();

    public TestSuiteDTO() {}

    public TestSuiteDTO(int id, String testSuiteName, List<TestCaseDTO> testCases) {
        this.id = id;
        this.testSuiteName = testSuiteName;
        this.testCases = testCases;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestSuiteName() {
        return testSuiteName;
    }

    public void setTestSuiteName(String testSuiteName) {
        this.testSuiteName = testSuiteName;
    }

    public List<TestCaseDTO> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCaseDTO> testCases) {
        this.testCases = testCases;
    }
}

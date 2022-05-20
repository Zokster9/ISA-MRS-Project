package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.Report;
import com.example.projectmrsisa.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public Report save(Report report) {
        return reportRepository.save(report);
    }
}

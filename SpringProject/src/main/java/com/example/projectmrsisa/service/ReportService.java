package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.Report;
import com.example.projectmrsisa.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public Report save(Report report) {
        return reportRepository.save(report);
    }

    public List<Report> findNegativeUnansweredReports(){
        return reportRepository.findNegativeUnansweredReports();
    }

    public Report findReportById(Integer id){
        return reportRepository.findReportById(id);
    }

    public void setReportAnswered(Integer id){
        reportRepository.setReportAnswered(id);
    }

    public void setReportPenalized(Integer id){
        reportRepository.setReportPenalized(id);
    }
}

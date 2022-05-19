package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.Complaint;
import com.example.projectmrsisa.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    public List<Complaint> findUnansweredComplaints(){
        return complaintRepository.findUnansweredComplaint();
    }
}

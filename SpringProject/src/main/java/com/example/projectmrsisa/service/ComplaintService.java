package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.Complaint;
import com.example.projectmrsisa.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.QueryHint;
import java.util.List;

@Transactional(readOnly = true)
@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Transactional(readOnly = false)
    public Complaint save(Complaint complaint) {
        return complaintRepository.save(complaint);
    }

    public List<Complaint> findUnansweredComplaints(){
        return complaintRepository.findUnansweredComplaints();
    }

    @Transactional(readOnly = false)
    public void updateComplaintStatus(Integer id){
        complaintRepository.updateComplaintStatus(id);
    }

    @Transactional(readOnly = false)
    public void updateComplaintResponse(String response, Integer id){
        complaintRepository.updateComplaintResponse(response, id);
    }

    public Complaint findComplaintById(Integer id){
        return complaintRepository.findComplaintById(id);
    }

    @Transactional(readOnly = false)
    public Complaint findComplaintByReservationId(Integer id) {
        return complaintRepository.findComplaintByReservationId(id);
    }
}

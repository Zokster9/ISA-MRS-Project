package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.Complaint;
import com.example.projectmrsisa.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Service;

import javax.persistence.QueryHint;
import java.util.List;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    public Complaint save(Complaint complaint) {
        return complaintRepository.save(complaint);
    }

    public List<Complaint> findUnansweredComplaints(){
        return complaintRepository.findUnansweredComplaints();
    }

    public void updateComplaintStatus(Integer id){
        complaintRepository.updateComplaintStatus(id);
    }

    public void updateComplaintResponse(String response, Integer id){
        complaintRepository.updateComplaintResponse(response, id);
    }

    public Complaint findComplaintById(Integer id){
        return complaintRepository.findComplaintById(id);
    }
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    public Complaint findComplaintByReservationId(Integer id) {
        return complaintRepository.findComplaintByReservationId(id);
    }
}

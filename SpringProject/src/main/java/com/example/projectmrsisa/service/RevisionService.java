package com.example.projectmrsisa.service;


import com.example.projectmrsisa.model.Revision;
import com.example.projectmrsisa.repository.RevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevisionService {
    @Autowired
    private RevisionRepository revisionRepository;

    public List<Revision> findUnansweredRevisions(){
        return revisionRepository.findUnansweredRevisions();
    }

    public Revision findRevisionById(Integer id){
        return revisionRepository.findRevisionById(id);
    }

    public void updateRevisionAnsweredStatus(Integer id){
        revisionRepository.updateRevisionAnsweredStatus(id);
    }

    public void updateRevisionApprovedStatus(Integer id){
        revisionRepository.updateRevisionApprovedStatus(id);
    }

    public Revision save(Revision revision) {
        return revisionRepository.save(revision);
    }
}

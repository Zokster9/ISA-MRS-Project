package com.example.projectmrsisa.service;


import com.example.projectmrsisa.dto.ServiceAverageRatingDTO;
import com.example.projectmrsisa.model.Revision;
import com.example.projectmrsisa.model.User;
import com.example.projectmrsisa.repository.RevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.QueryHint;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RevisionService {
    @Autowired
    private RevisionRepository revisionRepository;

    public List<Revision> findUnansweredRevisions(){
        return revisionRepository.findUnansweredRevisions();
    }

    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
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

    public List<ServiceAverageRatingDTO> getAverageRatingForUserServices(User user) {
        Map<String, Double> sum = new HashMap<>();
        Map<String, Integer> count = new HashMap<>();
        Map<String, List<String>> servicePictures = new HashMap<>();
        List<Revision> revisions = revisionRepository.findRevisionForServiceOwner(user.getId());
        for (Revision revision: revisions) {
            String serviceName = revision.getReservation().getService().getName();
            if (!sum.containsKey(serviceName)) {
                sum.put(serviceName, revision.getRating().getServiceRating());
                count.put(serviceName, 1);
                servicePictures.put(serviceName, new ArrayList<>(revision.getReservation().getService().getPictures()));
            }else {
                sum.put(serviceName, sum.get(serviceName) + revision.getRating().getServiceRating());
                count.put(serviceName, count.get(serviceName) + 1);
            }
        }
        return countAverageRatingForService(sum, count, servicePictures);
    }

    public double getAverageRatingForService(int serviceId) {
        List<Revision> revisions = revisionRepository.findRevisionForService(serviceId);
        int num_of_revisions = revisions.size();
        if (num_of_revisions == 0) {
            return 0;
        }
        double rating = 0;
        for (Revision revision : revisions) {
            rating += revision.getRating().getServiceRating();
        }
        DecimalFormat df = new DecimalFormat("0.00");
        double averageRating = rating / num_of_revisions;
        return Double.parseDouble(df.format(averageRating));
    }

    public double getAverageRatingForServiceOwner(int ownerId) {
        List<Revision> revisions = revisionRepository.findRevisionForServiceOwner(ownerId);
        int num_of_revisions = revisions.size();
        if (num_of_revisions == 0) {
            return 0;
        }
        double rating = 0;
        for (Revision revision : revisions) {
            rating += revision.getRating().getUserRating();
        }
        DecimalFormat df = new DecimalFormat("0.00");
        double averageRating = rating / num_of_revisions;
        return Double.parseDouble(df.format(averageRating));
    }

    public Revision findClientsRevisionForReservation(int reservationId, int clientId) {
        return revisionRepository.findClientsRevisionForReservation(reservationId, clientId);
    }

    private List<ServiceAverageRatingDTO> countAverageRatingForService(Map<String, Double> sum, Map<String, Integer> count, Map<String, List<String>> servicePictures) {
        List<ServiceAverageRatingDTO> averageRatingDTOS = new ArrayList<>();
        int id = 1;
        for (String serviceName: sum.keySet()) {
            averageRatingDTOS.add(new ServiceAverageRatingDTO(id, serviceName, sum.get(serviceName)/count.get(serviceName), servicePictures.get(serviceName)));
            id++;
        }
        return averageRatingDTOS;
    }
}

package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.Action;
import com.example.projectmrsisa.model.Retreat;
import com.example.projectmrsisa.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class ActionService {

    @Autowired
    private ActionRepository actionRepository;

    @Transactional(readOnly = false)
    public Action addAction(Action action) {
        action.getService().setNumberOfReservations(action.getService().getNumberOfReservations() + 1);
        return actionRepository.save(action);
    }

    public List<Action> getActionsByServiceAndCurrentDate(int serviceId) {
        return actionRepository.getActionsByServiceAndCurrentDate(serviceId, new Date());
    }

    public boolean actionAlreadyExists(Set<Action> actions, Date fromDate, Date toDate) {
        for (Action action: actions) {
            if (fromDate.compareTo(action.getDateFrom()) == 0) {
                return false;
            }else if (fromDate.compareTo(action.getDateFrom()) < 0) {
                if (toDate.compareTo(action.getDateFrom()) > 0) return false;
            }else {
                if (fromDate.compareTo(action.getDateTo()) < 0) return false;
            }
        }
        return true;
    }
}

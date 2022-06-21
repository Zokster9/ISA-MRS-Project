package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.Action;
import com.example.projectmrsisa.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
        Calendar date = new GregorianCalendar();
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        Date today = date.getTime();
        return actionRepository.getActionsByServiceAndCurrentDate(serviceId, today);
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

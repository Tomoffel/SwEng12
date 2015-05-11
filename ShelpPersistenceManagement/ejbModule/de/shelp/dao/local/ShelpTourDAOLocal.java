package de.shelp.dao.local;

import java.util.Calendar;

import de.shelp.entities.Location;
import de.shelp.entities.Tour;
import de.shelp.entities.User;
import de.shelp.enums.ApprovalStatus;

public interface ShelpTourDAOLocal {

    public Tour createTour(Tour tour, User user);

    public void search(ApprovalStatus approvalStatus, Location location, Calendar startTime, Calendar endTime);

    public void searchNear(ApprovalStatus approvalStatus, Location location, Calendar startTime, Calendar endTime);

}

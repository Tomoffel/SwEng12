package de.shelp.dao.local;

import java.util.Calendar;
import java.util.List;

import de.shelp.entities.Location;
import de.shelp.entities.Tour;
import de.shelp.entities.User;
import de.shelp.enums.ApprovalStatus;

public interface ShelpTourDAOLocal {

    public Tour createTour(Tour tour, User user);

    public List<Tour> search(ApprovalStatus approvalStatus, Location location, Calendar startTime, Calendar endTime, User currentUser);

    public List<Tour> searchNear(ApprovalStatus approvalStatus, Location location, Calendar startTime, Calendar endTime, User currentUser);

    public Tour getTour(int tourId);

    public void cancleTour(Tour tour);

}

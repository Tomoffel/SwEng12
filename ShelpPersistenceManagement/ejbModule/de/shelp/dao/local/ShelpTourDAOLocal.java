package de.shelp.dao.local;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import de.shelp.entities.ApprovalStatus;
import de.shelp.entities.Capacity;
import de.shelp.entities.DeliveryCondition;
import de.shelp.entities.Location;
import de.shelp.entities.PaymentCondition;
import de.shelp.entities.Tour;
import de.shelp.entities.User;

@Local
public interface ShelpTourDAOLocal {

    public Tour createTour(Tour tour, User user);

    public List<Tour> search(ApprovalStatus approvalStatus, Location location, Capacity capacity, Date startDate, Date endDate, User currentUser);

    public List<Tour> searchNear(ApprovalStatus approvalStatus, Location location, Capacity capacity, Date startTime, Date endTime, User currentUser);

    public Tour getTour(long tourId);

    public void cancleTour(Tour tour);

    public Location getLocation(long locationId);

    public ApprovalStatus getApprovalStatus(int approvalStatusId);

    public Capacity getCapacity(int capacityId);

    public PaymentCondition getPaymentCondition(int paymentConditionId);

    public DeliveryCondition getDeliveryCondition(int deliveryConditionId);

	public void saveTour(Tour tour);

}

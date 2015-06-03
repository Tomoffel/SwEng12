package de.shelp.dao.local;

import java.util.List;

import javax.ejb.Local;

import de.shelp.entities.ApprovalStatus;
import de.shelp.entities.Capacity;
import de.shelp.entities.DeliveryCondition;
import de.shelp.entities.Location;
import de.shelp.entities.PaymentCondition;

@Local
public interface ShelpStateDAOLocal {

    List<Location> getLocations();

    List<ApprovalStatus> getApprovalStates();

    List<Capacity> getCapacities();

    List<DeliveryCondition> getDeliveryConditions();

    List<PaymentCondition> getPaymentConditions();

}

package de.shelp.util;

import javax.ejb.Stateless;

import de.shelp.dto.state.ApprovalStatusTO;
import de.shelp.dto.state.CapacityTO;
import de.shelp.dto.state.DeliveryConditionTO;
import de.shelp.dto.state.LocationTO;
import de.shelp.dto.state.PaymentConditionTO;
import de.shelp.entities.ApprovalStatus;
import de.shelp.entities.Capacity;
import de.shelp.entities.DeliveryCondition;
import de.shelp.entities.Location;
import de.shelp.entities.PaymentCondition;

@Stateless
public class StateDtoAssembler {

    public LocationTO makeDTO(Location location) {
	LocationTO dto = new LocationTO();

	dto.setId(location.getId());
	dto.setDescription(location.getDescription());
	dto.setZipcode(location.getZipcode());

	return dto;
    }

    public ApprovalStatusTO makeDTO(ApprovalStatus approvalStatus) {
	ApprovalStatusTO dto = new ApprovalStatusTO();
	dto.setDescription(approvalStatus.getDescription());
	dto.setId(approvalStatus.getId());
	return dto;
    }

    public CapacityTO makeDTO(Capacity capacity) {
	CapacityTO dto = new CapacityTO();
	dto.setDescription(capacity.getDescription());
	dto.setId(capacity.getId());
	return dto;
    }

    public DeliveryConditionTO makeDTO(DeliveryCondition condition) {
	DeliveryConditionTO dto = new DeliveryConditionTO();
	dto.setDescription(condition.getDescription());
	dto.setId(condition.getId());
	return dto;
    }

    public PaymentConditionTO makeDTO(PaymentCondition condition) {
	PaymentConditionTO dto = new PaymentConditionTO();
	dto.setDescription(condition.getDescription());
	dto.setId(condition.getId());
	return dto;
    }

}

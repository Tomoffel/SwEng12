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

/**
 * Klasse zur Umwandlung von State-Objekten in transportfähige Objekte.
 * 
 * @author Thomas Sennekamp
 *
 */

@Stateless
public class StateDtoAssembler {

	/**
	 * Methode zum Umwandeln des Objektes
	 * 
	 * @param location
	 * @return Umgewandelte LocationTransferObjekt
	 */
	public LocationTO makeDTO(Location location) {
		LocationTO dto = new LocationTO();

		dto.setId(location.getId());
		dto.setDescription(location.getDescription());
		dto.setZipcode(location.getZipcode());

		return dto;
	}

	/**
	 * Methode um Freigabestadien in Transportfähige Objekte umzuwandeln
	 * @param approvalStatus
	 * @return Umgewandelte Freigabestadie
	 */
	public ApprovalStatusTO makeDTO(ApprovalStatus approvalStatus) {
		ApprovalStatusTO dto = new ApprovalStatusTO();
		dto.setDescription(approvalStatus.getDescription());
		dto.setId(approvalStatus.getId());
		return dto;
	}

	
	/** 
	 *  Methode zum Umwandeln des Objektes
	 * @param capacity
	 * @return Umgewandelts Objekt
	 */
	public CapacityTO makeDTO(Capacity capacity) {
		CapacityTO dto = new CapacityTO();
		dto.setDescription(capacity.getDescription());
		dto.setId(capacity.getId());
		return dto;
	}

	/**
	 *  Methode zum Umwandeln des Objektes
	 * @param condition
	 * @return Umgewandelts Objekt
	 */
	public DeliveryConditionTO makeDTO(DeliveryCondition condition) {
		DeliveryConditionTO dto = new DeliveryConditionTO();
		dto.setDescription(condition.getDescription());
		dto.setId(condition.getId());
		return dto;
	}

	/**
	 *  Methode zum Umwandeln des Objektes
	 * @param condition
	 * @return Umgewandelts Objekt
	 */
	public PaymentConditionTO makeDTO(PaymentCondition condition) {
		PaymentConditionTO dto = new PaymentConditionTO();
		dto.setDescription(condition.getDescription());
		dto.setId(condition.getId());
		return dto;
	}

}

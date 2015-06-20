package de.shelp.util;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.shelp.dto.request.RequestTO;
import de.shelp.dto.request.WishlistItemTO;
import de.shelp.entities.Request;
import de.shelp.entities.WishlistItem;

/**
 * Klasse zur Umwandlung von RequestObjekten in transportfähige Objekte.
 * @author Thomas Sennekamp
 *
 */
@Stateless
public class RequestDtoAssembler {

	/**
	 * EJB zur Erzeugung von DataTransferObjects
	 */
	@EJB
	private UserDtoAssembler userDtoAssembler;

	/**
	 * EJB zur Erzeugung von DataTransferObjects
	 */
	@EJB
	private TourDtoAssembler tourDtoAssembler;

	/**
	 * Methode zum Umwandeln des Objektes
	 * @param request
	 * @return Umgewandelte RequestTransferObjekt
	 */
	public RequestTO makeDTO(Request request) {
		RequestTO dto = new RequestTO();

		dto.setId(request.getId());
		dto.setNotice(request.getNotice());
		dto.setSourceUser(userDtoAssembler.makeDTO(request.getSourceUser()));
		dto.setTargetUser(userDtoAssembler.makeDTO(request.getTargetUser()));
		dto.setTour(tourDtoAssembler.makeDTO(request.getTour()));
		dto.setUpdated(request.isUpdated());
		dto.setWishes(makeDTO(request.getWishes()));
		dto.setStatus(request.getStatus());

		return dto;
	}
/**
 * Methode zum Umwandeln der Liste
 * @param wishes
 * @return Umgewandelte Liste
 */
	public List<WishlistItemTO> makeDTO(List<WishlistItem> wishes) {

		List<WishlistItemTO> dto = new ArrayList<>();

		for (WishlistItem wishlistItem : wishes) {
			WishlistItemTO wishlistItemTO = new WishlistItemTO();
			wishlistItemTO.setId(wishlistItem.getId());
			wishlistItemTO.setChecked(wishlistItem.isChecked());
			wishlistItemTO.setText(wishlistItem.getText());
			dto.add(wishlistItemTO);
		}
		return dto;
	}

}

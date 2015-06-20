package de.shelp.dto.tour;

import de.shelp.dto.ReturnCodeResponse;

/**
 * Klasse repr�sentiert die R�ckgabe f�r eine Fahrt ({@link TourTO}). Erbt von
 * {@link ReturnCodeResponse}.
 * 
 * @author Jos Werner
 *
 */
public class TourResponse extends ReturnCodeResponse {

    private static final long serialVersionUID = 4333669660990735973L;
    private TourTO tour;

    public TourTO getTour() {
	return tour;
    }

    public void setTour(TourTO tour) {
	this.tour = tour;
    }

}

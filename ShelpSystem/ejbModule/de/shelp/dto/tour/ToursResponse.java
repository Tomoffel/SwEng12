package de.shelp.dto.tour;

import java.util.List;

import de.shelp.dto.ReturnCodeResponse;

/**
 * Klasse repräsentiert die Rückgabe für die Fahrten ({@link TourTO}). Erbt von
 * {@link ReturnCodeResponse}.
 * 
 * @author Jos Werner
 *
 */
public class ToursResponse extends ReturnCodeResponse {

    private static final long serialVersionUID = 8555735319879744070L;

    private List<TourTO> tours;

    public List<TourTO> getTours() {
	return tours;
    }

    public void setTours(List<TourTO> tours) {
	this.tours = tours;
    }

}

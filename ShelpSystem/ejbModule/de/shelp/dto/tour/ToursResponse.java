package de.shelp.dto.tour;

import java.util.List;

import de.shelp.dto.ReturnCodeResponse;

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

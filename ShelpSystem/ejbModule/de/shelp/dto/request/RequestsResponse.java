package de.shelp.dto.request;

import java.util.List;

import de.shelp.dto.ReturnCodeResponse;
import de.shelp.dto.tour.TourTO;

public class RequestsResponse extends ReturnCodeResponse {

    private static final long serialVersionUID = 5957637236176457984L;

    private TourTO tour;
    private List<RequestTO> requests;

    public List<RequestTO> getRequests() {
	return requests;
    }

    public void setRequests(List<RequestTO> requests) {
	this.requests = requests;
    }

    public TourTO getTour() {
	return tour;
    }

    public void setTour(TourTO tour) {
	this.tour = tour;
    }

}

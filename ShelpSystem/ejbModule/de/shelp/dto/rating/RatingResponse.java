package de.shelp.dto.rating;

import java.util.List;

import de.shelp.dto.ReturnCodeResponse;

public class RatingResponse extends ReturnCodeResponse {

    private static final long serialVersionUID = 8974472260940989428L;
    private List<RatingTO> ratingsTO;

    public List<RatingTO> getRatings() {
	return ratingsTO;
    }

    public void setRatings(List<RatingTO> ratingsTO) {
	this.ratingsTO = ratingsTO;
    }

}
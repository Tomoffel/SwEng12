package de.shelp.dto.rating;

import java.util.List;

import de.shelp.dto.ReturnCodeResponse;

/**
 * Klasse repräsentiert die Rückgabe für mehrere Bewertungen ({@link RatingTO}) in einer Liste.
 * Erbt von {@link ReturnCodeResponse}.
 * 
 * @author Thomas Sennekamp
 *
 */
public class RatingsResponse extends ReturnCodeResponse {

    private static final long serialVersionUID = 8974472260940989428L;
    private List<RatingTO> ratingsTO;

    public List<RatingTO> getRatings() {
	return ratingsTO;
    }

    public void setRatings(List<RatingTO> ratingsTO) {
	this.ratingsTO = ratingsTO;
    }

}
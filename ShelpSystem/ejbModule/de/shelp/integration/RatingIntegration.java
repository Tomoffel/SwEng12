package de.shelp.integration;

import javax.ejb.Stateless;
import javax.jws.WebService;

import org.jboss.logging.Logger;
import org.jboss.ws.api.annotation.WebContext;

import de.shelp.dto.ReturnCodeResponse;
import de.shelp.dto.rating.RatingResponse;
import de.shelp.dto.rating.RatingTO;

@WebService
@WebContext(contextRoot = "/shelp")
@Stateless
public class RatingIntegration {
	
    private static final Logger LOGGER = Logger.getLogger(RatingIntegration.class);
	

    public RatingResponse getRatings(String userName) {
    	RatingResponse response = new RatingResponse();
    	
    	//logik für Ratingsuche
    	
    	return response;
    }
    
    public ReturnCodeResponse createRating(RatingTO rating, int sessionId) {
    	ReturnCodeResponse response = new ReturnCodeResponse();
    	
    	//logik für Ratingerstellung
    	return response;
    }
}

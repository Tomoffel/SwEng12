package de.shelp.integration;

import java.util.Calendar;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import org.jboss.logging.Logger;
import org.jboss.ws.api.annotation.WebContext;

import de.shelp.dao.local.ShelpTourDAOLocal;
import de.shelp.dao.local.ShelpUserDAOLocal;
import de.shelp.dto.ReturnCodeResponse;
import de.shelp.dto.state.LocationTO;
import de.shelp.dto.tour.TourTO;
import de.shelp.dto.tour.ToursResponse;
import de.shelp.entities.Location;
import de.shelp.entities.ShelpSession;
import de.shelp.entities.Tour;
import de.shelp.enums.ApprovalStatus;
import de.shelp.enums.ReturnCode;
import de.shelp.exception.ShelpException;
import de.shelp.exception.TourNotValidException;
import de.shelp.util.TourDtoAssembler;

@WebService
@WebContext(contextRoot = "/shelp")
@Stateless
public class TourIntegration {

    private static final Logger LOGGER = Logger.getLogger(TourIntegration.class);

    /**
     * EJB zur Abfrage von Datensätzen Referenz auf die EJB wird per Dependency
     * Injection gefüllt.
     */
    @EJB(beanName = "ShelpTourDAO", beanInterface = ShelpTourDAOLocal.class)
    private ShelpTourDAOLocal tourDao;

    /**
     * EJB zur Abfrage von Datensätzen Referenz auf die EJB wird per Dependency
     * Injection gefüllt.
     */
    @EJB(beanName = "ShelpUserDAO", beanInterface = ShelpUserDAOLocal.class)
    private ShelpUserDAOLocal userDao;

    /**
     * EJB zur Erzeugung von DataTransferObjects
     */
    @EJB
    private TourDtoAssembler dtoAssembler;

    public ReturnCodeResponse createTour(TourTO tourTO, String sessionId) {
	ReturnCodeResponse response = new ReturnCodeResponse();
	try {
	    if (!tourTO.isValid()) {
		LOGGER.warn("Es sind nicht alle Felder der Fahrt gefüllt.");
		throw new TourNotValidException(ReturnCode.ERROR, "Es sind nicht alle Felder der Fahrt gefüllt.");
	    }

	    ShelpSession session = userDao.getSession(sessionId);

	    Tour tour = dtoAssembler.makeDAO(tourTO);
	    tour = tourDao.createTour(tour, session.getUser());

	    LOGGER.info("Fahrt wurde erstellt " + tour.getId());
	} catch (ShelpException ex) {
	    response.setReturnCode(ex.getErrorCode());
	    response.setMessage(ex.getMessage());
	}

	return response;
    }

    public ToursResponse searchTour(ApprovalStatus approvalStatus, LocationTO locationTO, Calendar startTime, Calendar endTime, boolean directSearch) {
	ToursResponse response = new ToursResponse();

	Location location = dtoAssembler.makeDAO(locationTO);

	if (directSearch) {
	    tourDao.search(approvalStatus, location, startTime, endTime);
	} else {
	    tourDao.searchNear(approvalStatus, location, startTime, endTime);
	}

	return response;
    }
}

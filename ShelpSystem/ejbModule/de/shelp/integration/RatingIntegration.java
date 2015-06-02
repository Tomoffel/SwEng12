package de.shelp.integration;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import org.jboss.logging.Logger;
import org.jboss.ws.api.annotation.WebContext;

import de.shelp.dao.local.ShelpRatingDAOLocal;
import de.shelp.dao.local.ShelpUserDAOLocal;
import de.shelp.dto.ReturnCodeResponse;
import de.shelp.dto.rating.RatingResponse;
import de.shelp.dto.rating.RatingTO;
import de.shelp.entities.Rating;
import de.shelp.entities.ShelpSession;
import de.shelp.entities.User;
import de.shelp.enums.ReturnCode;
import de.shelp.exception.ShelpException;
import de.shelp.exception.UserNotExistEcxeption;
import de.shelp.util.RatingDtoAssembler;

@WebService
@WebContext(contextRoot = "/shelp")
@Stateless
public class RatingIntegration {

	/**
	 * EJB zur Abfrage von Datensätzen Referenz auf die EJB wird per Dependency
	 * Injection gefüllt.
	 */
	@EJB(beanName = "ShelpRatingDAO", beanInterface = ShelpRatingDAOLocal.class)
	private ShelpRatingDAOLocal daoRating;

	/**
	 * EJB zur Abfrage von Datensätzen Referenz auf die EJB wird per Dependency
	 * Injection gefüllt.
	 */
	@EJB(beanName = "ShelpUserDAO", beanInterface = ShelpUserDAOLocal.class)
	private ShelpUserDAOLocal daoUser;

	/**
	 * EJB zur Erzeugung von DataTransferObjects
	 */
	@EJB
	private RatingDtoAssembler dtoAssembler;

	private static final Logger LOGGER = Logger
			.getLogger(RatingIntegration.class);

	public RatingResponse getRatings(String userName) {
		RatingResponse response = new RatingResponse();

		User user = daoUser.findUserByName(userName);
		try {
			if (user == null) {
				LOGGER.info("User " + userName + "existiert nicht.");
				throw new UserNotExistEcxeption(ReturnCode.ERROR, "User "
						+ userName + "existiert nicht.");
			}

			
			List<Rating> ratings = user.getRatings();

			List<RatingTO> ratingsTO = new ArrayList<RatingTO>();
			for (Rating r : ratings) {
				ratingsTO.add(dtoAssembler.makeDTO(r));
			}

			response.setRatings(ratingsTO);

			LOGGER.info("Es wurden" + ratingsTO.size()
					+ " Ratings für den Benutzer " + userName + " gefunden.");
		} catch (ShelpException e) {
			response.setReturnCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		}
		return response;

	}

	public ReturnCodeResponse createRating(String targetUserId, int rating,
			String notice, int sessionId) {
		ReturnCodeResponse response = new ReturnCodeResponse();

		ShelpSession session = daoUser.getSession(sessionId);

		User targetUser = daoUser.findUserByName(targetUserId);
		try {
			if (targetUser == null) {
				LOGGER.info("TargetUser existiert nicht.");
				throw new UserNotExistEcxeption(ReturnCode.ERROR,
						"TargetUser existiert nicht.");
			}
				if (session == null) {
					LOGGER.info("Session-Id existiert nicht.");
					throw new UserNotExistEcxeption(ReturnCode.ERROR,
							"Session-Id existiert nicht.");
				}
			

		
			// TODO: Check Range of Ratingvalue (int or float)

			Rating newRating = new Rating();
			newRating.setTargetUser(targetUser);
			newRating.setSourceUser(session.getUser());
			newRating.setRating(rating);
			newRating.setNotice(notice);

			daoRating.createRating(newRating);

		} catch (ShelpException e) {
			response.setReturnCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		}

		return response;
	}
}

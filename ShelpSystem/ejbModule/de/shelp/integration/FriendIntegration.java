package de.shelp.integration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import org.jboss.logging.Logger;
import org.jboss.ws.api.annotation.WebContext;

import de.shelp.dao.local.ShelpFriendDAOLocal;
import de.shelp.dao.local.ShelpUserDAOLocal;
import de.shelp.dto.ReturnCodeResponse;
import de.shelp.dto.friend.FriendsResponse;
import de.shelp.dto.friend.FriendshipTO;
import de.shelp.entities.Friendship;
import de.shelp.entities.ShelpSession;
import de.shelp.entities.User;
import de.shelp.enums.FriendshipStatus;
import de.shelp.enums.ReturnCode;
import de.shelp.exception.ShelpException;
import de.shelp.util.FriendDtoAssembler;
import de.shelp.util.ShelpHelper;

/**
 * Webservice der alle notwendigen Methoden zur Freundesverwaltung zur Verfügung
 * stellt. Über die Schnittstellen können Freundeschaften akzeptiert werden
 * {@link acceptFriendship(int, int)}, Freundschaften abgelehnt werden {@link
 * deniedFriendship(int, int)}, Freundschaften gelöscht werden {@link
 * deleteFriendship(int, int)}, Freundschaften geändert werden {@link
 * changeFriendShip(int, FriendshipStatus, int)}, Freunde hinzugefügt werden
 * {@link addFriend(int, String) }, alle Freunde abgerufen werden {@link
 * getFriends(int)}, Jeder Schritt wird über die Logausgabe {@link #LOGGER} dokumentiert.
 * Außerdem werden alle Entitäten vor der Rückgabe in Data Transfer Objekte
 * umgewandelt.
 * 
 * 
 * @author Thomas Sennekamp
 *
 */
@WebService
@WebContext(contextRoot = "/shelp")
@Stateless
public class FriendIntegration {

	private static final Logger LOGGER = Logger
			.getLogger(FriendIntegration.class);

	/**
	 * EJB zur Abfrage von Datensätzen Referenz auf die EJB wird per Dependency
	 * Injection gefüllt.
	 */
	@EJB(beanName = "ShelpUserDAO", beanInterface = ShelpUserDAOLocal.class)
	private ShelpUserDAOLocal daoUser;

	/**
	 * EJB zur Abfrage von Datensätzen Referenz auf die EJB wird per Dependency
	 * Injection gefüllt.
	 */
	@EJB(beanName = "ShelpFriendDAO", beanInterface = ShelpFriendDAOLocal.class)
	private ShelpFriendDAOLocal daoFriend;

	/**
	 * EJB zur Erzeugung von DataTransferObjects
	 */
	@EJB
	private FriendDtoAssembler dtoAssembler;

	@EJB
	private ShelpHelper helper;

	/**
	 * EJB zur Beauftragung zum Versenden von E-Mails
	 */
	@EJB
	private MailRequesterBean mailRequester;

	/**
	 * Schnittstelle, die genutzt werden kann um alle Freunde abzurufen.
	 * Zunächst wird die aktuelle Session auf gültigkeit überprüft, anschließend
	 * wird das Userobjekt geholt und alle existierende Freunde abgerufen und
	 * zurückgegeben.
	 * 
	 * @param sessionId
	 *            SessionId (muss aktuell angemeldet sein)
	 * @return einen {@link ReturnCodeResponse} der entweder den Status OK oder
	 *         ERROR + Fehlernachricht beinhaltet
	 */
	public FriendsResponse getFriends(int sessionId) {
		FriendsResponse response = new FriendsResponse();

		try {
			ShelpSession session = helper.checkSession(sessionId, daoUser);

			User user = session.getUser();

			List<Friendship> friends = user.getFriendships();

			List<FriendshipTO> friendsTO = new ArrayList<FriendshipTO>();
			for (Friendship f : friends) {
				friendsTO.add(dtoAssembler.makeDTO(f));
			}

			response.setFriends(friendsTO);
			LOGGER.info(friendsTO.size()
					+ " Freundschaften wurde zur SessionId " + sessionId
					+ " gefunden.");
		} catch (ShelpException e) {
			response.setReturnCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		}

		return response;

	}

	/**
	 * Schnittstelle die genutzt werden kann um Freundschaften zu akzeptieren.
	 * Status der Freundschaft wird mit Hilfe der Methode
	 * {@link #changeFriendShip(int, FriendshipStatus, int)} auf "Acccept"
	 * gesetzt.
	 * 
	 * @param friendshipId
	 *            ID der Freundschaft
	 * @param sessionId
	 *            Aktuelle SessionID
	 * @return einen {@link FriendShip} mit dem geänderten Freundschafsstatus
	 */
	public ReturnCodeResponse acceptFriendship(int friendshipId, int sessionId) {
		return changeFriendShip(friendshipId, FriendshipStatus.ACCEPT,
				sessionId);
	}

	/**
	 * Schnittstelle, die genutzt werden kann um Freundschaften zu beenden.
	 * Status der Freundschaft {@link Friendship} wird mit Hilfe der Methode changeFriendShip
	 * {@link #changeFriendShip(int, FriendshipStatus, int)} auf "DENIED"
	 * gesetzt.
	 * 
	 * @param friendshipId
	 *            ID der Freundschaft
	 * @param sessionId
	 *            * Aktuelle SessionID
	 * @return einen {@link FriendShip} mit dem geänderten Freundschafsstatus
	 */
	public ReturnCodeResponse deniedFriendship(int friendshipId, int sessionId) {
		return changeFriendShip(friendshipId, FriendshipStatus.DENIED,
				sessionId);
	}

	/**
	 * Schnittstelle, die genutzt werden kann um eine Freundschaft zu löschen.
	 * Zunächst wir die Freundschaft {@link Friendship} auf gültigkeit geprüft und im Erfolgsfall
	 * die Freundschaft gelöscht.
	 * 
	 * @param friendshipId
	 *            ID der Freundschaft
	 * @param sessionId
	 *            Aktuelle SessionID
	 * @return einen {@link ReturnCodeResponse} der entweder den Status OK oder
	 *         ERROR + Fehlernachricht beinhaltet
	 */
	public ReturnCodeResponse deleteFriendship(int friendshipId, int sessionId) {
		ReturnCodeResponse response = new ReturnCodeResponse();
		try {
			Friendship friendship = checkFriendship(sessionId, friendshipId,
					false);

			daoFriend.deleteFriendship(friendship);
			LOGGER.info("Die Freundschaft " + friendshipId + " wurde gelöscht.");
		} catch (ShelpException e) {
			response.setReturnCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		}
		return response;
	}

	/**
	 * Interne Methode die zur Änderung einer Freundschaft {@link Friendship} genutzt werden kann.
	 * Zunächst wird die Freundschaft auf Gültigkeit überprüft. Anschließend
	 * wird der Status und das Datum aktualisiert und abschließend in der
	 * Datenbank gespeichert.
	 * 
	 * @param friendshipId
	 *            ID der Freundschaft
	 * @param status
	 *            Neuer Status der Friendship
	 * @param sessionId
	 *            Aktuelle SessionID
	 * @return einen {@link ReturnCodeResponse} der entweder den Status OK oder
	 *         ERROR + Fehlernachricht beinhaltet
	 */
	private ReturnCodeResponse changeFriendShip(int friendshipId,
			FriendshipStatus status, int sessionId) {
		ReturnCodeResponse response = new ReturnCodeResponse();

		try {
			Friendship friendship = checkFriendship(sessionId, friendshipId,
					true);
			friendship.setStatus(status);
			friendship.setChangeOn(new Date());
			daoFriend.saveFriendship(friendship);

			LOGGER.info("Der Status der Freundschaft " + friendshipId
					+ " wurde auf  " + status + " gewechselt.");

		} catch (ShelpException e) {
			response.setReturnCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		}

		return response;
	}

	/**
	 * Schnitttstelle, die genutzt werden kann um einen Freund hinzuzufügen.
	 * Zunächst wird die Session {@link ShelpSession} auf Gültigkeit überprüft, anschließend
	 * überprüft, dass sich ein Benutzer {@link User} nicht selbst als Freund hinzufügen
	 * kann. Weiterhin wird überpüft, dass die Freundschaft zwischen den
	 * Benutzern nicht bereits existiert. Im Erfolgsfall wird die Freundschaft
	 * hinzugefügt, im Fehlerfall wird weiter geprüft, warum die Freundschaft
	 * nicht erstellt werden kann und entsprechende Meldung {@link ShelpException} zurückgegeben.
	 * 
	 * @param sessionId
	 *            Aktuelle SessionID
	 * @param friendId
	 *            BenutzerId des anfragenden Benutzers
	 * @return einen {@link ReturnCodeResponse} der entweder den Status OK oder
	 *         ERROR + Fehlernachricht beinhaltet
	 * @throws ShelpException
	 *             wird im Fehlerfall geworfen
	 */
	public ReturnCodeResponse addFriend(int sessionId, String friendId) {
		ReturnCodeResponse response = new ReturnCodeResponse();

		try {
			ShelpSession session = helper.checkSession(sessionId, daoUser);
			User targetUser = helper.checkUser(friendId, daoUser);
			if (targetUser.equals(session.getUser())) {
				LOGGER.info("Man kann nicht mit sich selbst befreundet sein "
						+ session.getUser());
				throw new ShelpException(ReturnCode.ERROR,
						"Man kann nicht mit sich selbst befreundet sein "
								+ session.getUser());
			}

			Friendship friendship = new Friendship();
			friendship.setInitiatorUser(session.getUser());
			friendship.setRecipientUser(targetUser);

			friendship.setStatus(FriendshipStatus.ASKED);

			Friendship dbFriendship = daoFriend.findFriendshipById(friendship
					.getFriendshipHash());
			if (dbFriendship == null) {
				friendship.setId(friendship.getFriendshipHash());
				friendship.setChangeOn(new Date());
				daoFriend.saveFriendship(friendship);

				LOGGER.info("Zwischen " + session.getUser() + " und "
						+ targetUser
						+ " wurde eine Freunschaftsanfrage erstellt.");
			} else {

				if (dbFriendship.getStatus().equals(FriendshipStatus.ASKED)) {
					LOGGER.info("Anfrage wurde schon gestellt zwischen "
							+ session.getUser() + " und " + targetUser + ".");
					throw new ShelpException(ReturnCode.ERROR,
							"Anfrage wurde schon gestellt zwischen "
									+ session.getUser() + " und " + targetUser
									+ ".");
				} else if (dbFriendship.getStatus().equals(
						FriendshipStatus.DENIED)) {
					LOGGER.info("Anfrage wurde schon abgelehnt zwischen "
							+ session.getUser() + " und " + targetUser + ".");
					throw new ShelpException(ReturnCode.ERROR,
							"Anfrage wurde schon abgelehnt zwischen "
									+ session.getUser() + " und " + targetUser
									+ ".");
				} else if (dbFriendship.getStatus().equals(
						FriendshipStatus.ACCEPT)) {
					LOGGER.info("Anfrage wurde schon angenommen zwischen "
							+ session.getUser() + " und " + targetUser + ".");
					throw new ShelpException(ReturnCode.ERROR,
							"Anfrage wurde schon angenommen zwischen "
									+ session.getUser() + " und " + targetUser
									+ ".");
				}
			}

		} catch (ShelpException e) {
			response.setReturnCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		}
		return response;
	}

	/**
	 * Interne Methode zur Überprüfung einer Freundschaft {@link Friendship}. Zunächst wird die
	 * Session auf Gültigkeit überprüft. Weiterhin wird überprüft, ob die
	 * Freundschaft {@link Friendship} bereits existiert und gültig ist. Bei
	 * Existenz bzw. Ungültikeit wird eine entsprechender Fehler
	 * {@link ShelpException} geworfen. Anschließend wird überprüft, ob der
	 * aktuelle Benutzer der Empfänger der Freundschaftsanfrage bzw. an der
	 * Freundschaft beteiligt ist. {@link Friendship} ist. Im Fehlerfall wird
	 * der Status PERMISSION_DENIED {@link FriendshipStatus} zurückgegeben. Nach erfolgreichem
	 * Durchlaufer aller Tests und Überprüfungen wird abschließend eine Email an
	 * die Benuter der beteiligten Freunschaftsanfrage gesendet.
	 * 
	 * @param sessionId
	 *            * Aktuelle SessionID
	 * @param friendshipId
	 *            FriendShipID
	 * @param checkChange
	 *            Steuerparameter zur internen Methode
	 * @return einen {@link Friendship}
	 * @throws ShelpException
	 *             wenn ein Fehler aufgetreten ist.
	 */
	private Friendship checkFriendship(int sessionId, int friendshipId,
			boolean checkChange) throws ShelpException {

		ShelpSession session = helper.checkSession(sessionId, daoUser);

		Friendship friendship = daoFriend.findFriendshipById(friendshipId);
		if (friendship == null) {
			LOGGER.info("Freundschaft existiert nicht!");
			throw new ShelpException(ReturnCode.ERROR,
					"Freundschaft existiert nicht!");
		}
		if (checkChange) {
			if (!friendship.getRecipientUser().equals(session.getUser())) {
				LOGGER.warn("Zugriff verweigert. Anfragende Session "
						+ session.getId()
						+ " ist nicht der Empfänger der Freundschaftsanfrage "
						+ friendshipId + "!");
				throw new ShelpException(
						ReturnCode.PERMISSION_DENIED,
						"Zugriff verweigert. Anfragende Session "
								+ session.getId()
								+ " ist nicht der Empfänger der Freundschaftsanfrage "
								+ friendshipId + "!");
			} else if (!friendship.getStatus().equals(FriendshipStatus.ASKED)) {
				LOGGER.warn("Freundschaftsanfrage wurde schon angenommen/abgelehnt.");
				throw new ShelpException(ReturnCode.ERROR,
						"Freundschaftsanfrage wurde schon angenommen/abgelehnt.");
			}
		} else if (!friendship.getInitiatorUser().equals(session.getUser())
				&& !friendship.getRecipientUser().equals(session.getUser())) {
			LOGGER.warn("Zugriff verweigert. Anfragende Session "
					+ session.getId() + " ist nicht an der Freundschaft "
					+ friendshipId + " beteiligt!");
			throw new ShelpException(ReturnCode.PERMISSION_DENIED,
					"Zugriff verweigert. Anfragende Session " + session.getId()
							+ " ist nicht an der Freundschaft " + friendshipId
							+ " beteiligt!");
		}

		String logMessage = "Freundschaft " + friendship
				+ " wurde verändern. Status ist nun " + friendship.getStatus();
		LOGGER.info(logMessage);
		mailRequester.printLetter(logMessage, friendship.getRecipientUser()
				.getEmail());
		mailRequester.printLetter(logMessage, friendship.getInitiatorUser()
				.getEmail());
		return friendship;

	}

}

package de.shelp.integration;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import org.jboss.logging.Logger;
import org.jboss.ws.api.annotation.WebContext;

import de.shelp.dao.local.ShelpStateDAOLocal;
import de.shelp.dto.state.AllListResponse;
import de.shelp.dto.state.ApprovalStatusResponse;
import de.shelp.dto.state.ApprovalStatusTO;
import de.shelp.dto.state.CapacitiesResponse;
import de.shelp.dto.state.CapacityTO;
import de.shelp.dto.state.DeliveryConditionResponse;
import de.shelp.dto.state.DeliveryConditionTO;
import de.shelp.dto.state.LocationResponse;
import de.shelp.dto.state.LocationTO;
import de.shelp.dto.state.PaymentConditionTO;
import de.shelp.dto.state.PaymentConditionsResponse;
import de.shelp.entities.ApprovalStatus;
import de.shelp.entities.Capacity;
import de.shelp.entities.DeliveryCondition;
import de.shelp.entities.Location;
import de.shelp.entities.PaymentCondition;
import de.shelp.util.StateDtoAssembler;

/**
 * Webservice der alle nötigen Methoden zur Listenabfrage bereitstellt. Über die
 * Schnittstelle können die Liefermethoden {@link #getDeliveryConditions()},
 * Bezahlmethoden {@link #getPaymentConditions()}, Orte {@link #getLocations()},
 * Kapazitäten {@link #getCapacities()}, Freigabestatusse
 * {@link #getApprovalStatus()} und alle Listen {@link #getAllLists()} abgefragt
 * werden. <br>
 *
 * Jeder Schritt wird über die Logausgabe dokumentiert. Außerdem werden alle
 * Entitäten vor der Rückgabe in Data Transfer Objekte umgewandelt.
 * 
 * @author Jos Werner
 *
 */
@WebService
@WebContext(contextRoot = "/shelp")
@Stateless
public class StateIntegration {

    private static final Logger LOGGER = Logger
	    .getLogger(StateIntegration.class);

    /**
     * EJB zur Abfrage von Datensätzen der Statusse. Referenz auf die EJB wird
     * per Dependency Injection gefüllt.
     */
    @EJB(beanName = "ShelpStateDAO", beanInterface = ShelpStateDAOLocal.class)
    private ShelpStateDAOLocal dao;

    /**
     * EJB zur Erzeugung von DataTransferObjects von Statussen
     */
    @EJB
    private StateDtoAssembler dtoAssembler;

    /**
     * Webservice um die in der Datenbank hinterlegten Freigabestatussen (
     * {@link ApprovalStatus}) abzufragen. Standardmäßig sind "Nur Freunde" und
     * "Alle" hinterlegt.
     * 
     * @return einen {@link ApprovalStatusResponse} mit den verfügbaren
     *         Freigabestatussen
     */
    public ApprovalStatusResponse getApprovalStatus() {
	ApprovalStatusResponse response = new ApprovalStatusResponse();
	LOGGER.info("Get approval status");
	response.setStates(getAllApprovalStatesTO());
	return response;
    }

    /**
     * Webservice um die in der Datenbank hinterlegten Orte ({@link Location})
     * abzufragen.
     * 
     * @return einen {@link LocationResponse} mit den verfügbaren Orten
     */
    public LocationResponse getLocations() {
	LocationResponse response = new LocationResponse();
	LOGGER.info("Get all locations");
	response.setLocations(getAllLocationsTO());

	return response;
    }

    /**
     * Webservice um die in der Datenbank hinterlegten Kapazitäten (
     * {@link Capacity}) abzufragen. Standardmäßig sind "Großer Kofferraum",
     * "Mittlerer Kofferraum" und "Kleiner Kofferraum" hinterlegt.
     * 
     * @return einen {@link CapacitiesResponse} mit den verfügbaren Kapazitäten
     */
    public CapacitiesResponse getCapacities() {
	CapacitiesResponse response = new CapacitiesResponse();
	LOGGER.info("Get capacities");
	response.setCapacities(getAllCapacitiesTO());
	return response;
    }

    /**
     * Interne Methode um alle Kapazitäten ({@link Capacity}) aus der Datenbank
     * zu holen und in DTO-Objekt umzuwandeln.
     * 
     * @return Liste mit den DTO-Objekten der Kapazitäten ({@link CapacityTO})
     */
    private List<CapacityTO> getAllCapacitiesTO() {
	List<Capacity> capacities = dao.getCapacities();

	List<CapacityTO> capacitiesTO = new ArrayList<CapacityTO>();
	for (Capacity capacity : capacities) {
	    capacitiesTO.add(dtoAssembler.makeDTO(capacity));
	}

	return capacitiesTO;
    }

    /**
     * Webservice um die in der Datenbank hinterlegten Bezahlmethoden (
     * {@link PaymentCondition}) abzufragen. Standardmäßig sind "PayPal",
     * "Barzahlung" und "Vorkasse" hinterlegt.
     * 
     * @return einen {@link PaymentConditionsResponse} mit den verfügbaren
     *         Bezahlmethoden
     */
    public PaymentConditionsResponse getPaymentConditions() {
	PaymentConditionsResponse response = new PaymentConditionsResponse();
	LOGGER.info("Get payment conditions");
	response.setConditions(getAllPaymentConditionsTO());
	return response;
    }

    /**
     * Interne Methode um alle Bezahlbedingungen ({@link PaymentCondition}) aus
     * der Datenbank zu holen und in DTO-Objekt umzuwandeln.
     * 
     * @return Liste mit den DTO-Objekten der Bezahlbedingungen (
     *         {@link PaymentConditionTO})
     */
    private List<PaymentConditionTO> getAllPaymentConditionsTO() {
	List<PaymentCondition> conditions = dao.getPaymentConditions();

	List<PaymentConditionTO> conditionsTO = new ArrayList<PaymentConditionTO>();
	for (PaymentCondition condition : conditions) {
	    conditionsTO.add(dtoAssembler.makeDTO(condition));
	}

	return conditionsTO;
    }

    /**
     * Webservice um die in der Datenbank hinterlegten Liefermethoden (
     * {@link DeliveryCondition}) abzufragen. Standardmäßig sind "Abholen", und
     * "Bringen" hinterlegt.
     * 
     * @return einen {@link DeliveryConditionResponse} mit den verfügbaren
     *         Liefermethoden
     */
    public DeliveryConditionResponse getDeliveryConditions() {
	DeliveryConditionResponse response = new DeliveryConditionResponse();
	LOGGER.info("Get delivery conditions");
	response.setConditions(getAllDeliveryConditionsTO());
	return response;
    }

    /**
     * Interne Methode um alle Liefermethoden ({@link DeliveryCondition}) aus
     * der Datenbank zu holen und in DTO-Objekt umzuwandeln.
     * 
     * @return Liste mit den DTO-Objekten der Liefermethoden (
     *         {@link DeliveryConditionTO})
     */
    private List<DeliveryConditionTO> getAllDeliveryConditionsTO() {
	List<DeliveryCondition> conditions = dao.getDeliveryConditions();

	List<DeliveryConditionTO> conditionsTO = new ArrayList<DeliveryConditionTO>();
	for (DeliveryCondition condition : conditions) {
	    conditionsTO.add(dtoAssembler.makeDTO(condition));
	}

	return conditionsTO;
    }

    /**
     * Schnittstelle um alle hinterlegten Listen abzufragen.
     * 
     * @return einen {@link AllListResponse} mit allen Listen (Orte,
     *         Freigabestatusse, Kapazitäten, Bezahlbedingungen,
     *         Lieferbedingungen)
     */
    public AllListResponse getAllLists() {
	LOGGER.info("Get all lists");

	AllListResponse response = new AllListResponse();
	response.setLocations(getAllLocationsTO());
	response.setStates(getAllApprovalStatesTO());
	response.setCapacities(getAllCapacitiesTO());
	response.setDeliveryConditions(getAllDeliveryConditionsTO());
	response.setPaymentConditions(getAllPaymentConditionsTO());

	return response;
    }

    /**
     * Interne Methode um alle Orte ({@link Location}) aus der Datenbank zu
     * holen und in DTO-Objekt umzuwandeln.
     * 
     * @return Liste mit den DTO-Objekten der Orte ({@link LocationTO})
     */
    private List<LocationTO> getAllLocationsTO() {
	List<Location> locations = dao.getLocations();

	List<LocationTO> locationsTO = new ArrayList<LocationTO>();
	for (Location location : locations) {
	    locationsTO.add(dtoAssembler.makeDTO(location));
	}

	return locationsTO;
    }

    /**
     * Interne Methode um alle Freigabestatusse ({@link ApprovalStatus}) aus der
     * Datenbank zu holen und in DTO-Objekt umzuwandeln.
     * 
     * @return Liste mit den DTO-Objekten der Freigabestatusse (
     *         {@link ApprovalStatusTO})
     */
    private List<ApprovalStatusTO> getAllApprovalStatesTO() {
	List<ApprovalStatus> approvalStates = dao.getApprovalStates();

	List<ApprovalStatusTO> approvalStatesTO = new ArrayList<ApprovalStatusTO>();
	for (ApprovalStatus approvalStatus : approvalStates) {
	    approvalStatesTO.add(dtoAssembler.makeDTO(approvalStatus));
	}

	return approvalStatesTO;
    }
}

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
import de.shelp.dto.state.CapacitiesResponse;
import de.shelp.dto.state.DeliveryConditionResponse;
import de.shelp.dto.state.LocationResponse;
import de.shelp.dto.state.LocationTO;
import de.shelp.dto.state.PaymentConditionsResponse;
import de.shelp.entities.Location;
import de.shelp.util.StateDtoAssembler;

@WebService
@WebContext(contextRoot = "/shelp")
@Stateless
public class StateIntegration {

    private static final Logger LOGGER = Logger.getLogger(StateIntegration.class);

    /**
     * EJB zur Abfrage von Datensätzen Referenz auf die EJB wird per Dependency
     * Injection gefüllt.
     */
    @EJB(beanName = "ShelpStateDAO", beanInterface = ShelpStateDAOLocal.class)
    private ShelpStateDAOLocal dao;

    /**
     * EJB zur Erzeugung von DataTransferObjects
     */
    @EJB
    private StateDtoAssembler dtoAssembler;

    public ApprovalStatusResponse getApprovalStatus() {
	LOGGER.info("Get approval status");
	return new ApprovalStatusResponse();
    }

    public LocationResponse getLocations() {
	LocationResponse response = new LocationResponse();
	LOGGER.info("Get all locations");
	response.setLocations(getAllLocationsTO());

	return response;
    }

    public CapacitiesResponse getCapacities() {
	LOGGER.info("Get capacities");
	return new CapacitiesResponse();
    }

    public PaymentConditionsResponse getPaymentConditions() {
	LOGGER.info("Get payment conditions");
	return new PaymentConditionsResponse();
    }

    public DeliveryConditionResponse getDeliveryConditions() {
	LOGGER.info("Get delivery conditions");
	return new DeliveryConditionResponse();
    }

    public AllListResponse getAllLists() {
	LOGGER.info("Get all lists");

	AllListResponse response = new AllListResponse();
	response.setLocations(getAllLocationsTO());

	return response;
    }

    private List<LocationTO> getAllLocationsTO() {
	List<Location> locations = dao.getLocations();

	List<LocationTO> locationsTO = new ArrayList<LocationTO>();
	for (Location location : locations) {
	    locationsTO.add(dtoAssembler.makeDTO(location));
	}

	return locationsTO;
    }
}

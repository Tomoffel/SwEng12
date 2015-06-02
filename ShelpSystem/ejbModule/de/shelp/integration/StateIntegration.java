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

@WebService
@WebContext(contextRoot = "/shelp")
@Stateless
public class StateIntegration {

    private static final Logger LOGGER = Logger
	    .getLogger(StateIntegration.class);

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
	ApprovalStatusResponse response = new ApprovalStatusResponse();
	LOGGER.info("Get approval status");
	response.setStates(getAllApprovalStatesTO());
	return response;
    }

    public LocationResponse getLocations() {
	LocationResponse response = new LocationResponse();
	LOGGER.info("Get all locations");
	response.setLocations(getAllLocationsTO());

	return response;
    }

    public CapacitiesResponse getCapacities() {
	CapacitiesResponse response = new CapacitiesResponse();
	LOGGER.info("Get capacities");
	response.setCapacities(getAllCapacitiesTO());
	return response;
    }

    private List<CapacityTO> getAllCapacitiesTO() {
	List<Capacity> capacities = dao.getCapacities();

	List<CapacityTO> capacitiesTO = new ArrayList<CapacityTO>();
	for (Capacity capacity : capacities) {
	    capacitiesTO.add(dtoAssembler.makeDTO(capacity));
	}

	return capacitiesTO;
    }

    public PaymentConditionsResponse getPaymentConditions() {
	PaymentConditionsResponse response = new PaymentConditionsResponse();
	LOGGER.info("Get payment conditions");
	response.setConditions(getAllPaymentConditionsTO());
	return response;
    }

    private List<PaymentConditionTO> getAllPaymentConditionsTO() {
	List<PaymentCondition> conditions = dao.getPaymentConditions();

	List<PaymentConditionTO> conditionsTO = new ArrayList<PaymentConditionTO>();
	for (PaymentCondition condition : conditions) {
	    conditionsTO.add(dtoAssembler.makeDTO(condition));
	}

	return conditionsTO;
    }

    public DeliveryConditionResponse getDeliveryConditions() {
	DeliveryConditionResponse response = new DeliveryConditionResponse();
	LOGGER.info("Get delivery conditions");
	response.setConditions(getAllDeliveryConditionsTO());
	return response;
    }

    private List<DeliveryConditionTO> getAllDeliveryConditionsTO() {
	List<DeliveryCondition> conditions = dao.getDeliveryConditions();

	List<DeliveryConditionTO> conditionsTO = new ArrayList<DeliveryConditionTO>();
	for (DeliveryCondition condition : conditions) {
	    conditionsTO.add(dtoAssembler.makeDTO(condition));
	}

	return conditionsTO;
    }

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

    private List<LocationTO> getAllLocationsTO() {
	List<Location> locations = dao.getLocations();

	List<LocationTO> locationsTO = new ArrayList<LocationTO>();
	for (Location location : locations) {
	    locationsTO.add(dtoAssembler.makeDTO(location));
	}

	return locationsTO;
    }

    private List<ApprovalStatusTO> getAllApprovalStatesTO() {
	List<ApprovalStatus> approvalStates = dao.getApprovalStates();

	List<ApprovalStatusTO> approvalStatesTO = new ArrayList<ApprovalStatusTO>();
	for (ApprovalStatus approvalStatus : approvalStates) {
	    approvalStatesTO.add(dtoAssembler.makeDTO(approvalStatus));
	}

	return approvalStatesTO;
    }
}

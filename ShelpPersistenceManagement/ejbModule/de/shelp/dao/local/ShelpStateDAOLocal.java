package de.shelp.dao.local;

import java.util.List;

import javax.ejb.Local;

import de.shelp.entities.ApprovalStatus;
import de.shelp.entities.Capacity;
import de.shelp.entities.DeliveryCondition;
import de.shelp.entities.Location;
import de.shelp.entities.PaymentCondition;

/**
 * Interface das vorgibt welche Methoden für die datenbankseitige
 * Listenverwaltung benötigt werden.
 * 
 * @author Jos Werner
 *
 */
@Local
public interface ShelpStateDAOLocal {

    /**
     * Gibt alle in der Datenbank hinterlegten Orte ({@link Location}) zurück.
     * 
     * @return Liste von Orten
     */
    public List<Location> getLocations();

    /**
     * Gibt alle in der Datenbank hinterlegten Freigabestatussen (
     * {@link ApprovalStatus}) zurück.
     * 
     * @return Liste von Freigabestatussen
     */
    public List<ApprovalStatus> getApprovalStates();

    /**
     * Gibt alle in der Datenbank hinterlegten Kapazitäten ({@link Capacity})
     * zurück.
     * 
     * @return Liste von Kapazitäten
     */
    public List<Capacity> getCapacities();

    /**
     * Gibt alle in der Datenbank hinterlegten Lieferbedingungen (
     * {@link DeliveryCondition}) zurück.
     * 
     * @return Liste von Lieferbedingungen
     */
    public List<DeliveryCondition> getDeliveryConditions();

    /**
     * Gibt alle in der Datenbank hinterlegten Bezahlbedingungen (
     * {@link PaymentCondition}) zurück.
     * 
     * @return Liste von Bezahlbedingungen
     */
    public List<PaymentCondition> getPaymentConditions();

    /**
     * Sucht nach einem Ort ({@link Location}) in der Datenbank
     * 
     * @param locationId
     *            - Id des zu suchenden Ortes
     * @return den gefunden Ort oder null
     */
    public Location getLocation(long locationId);

    /**
     * Sucht nach einem Freigabestatus ({@link ApprovalStatus}) in der Datenbank
     * 
     * @param approvalStatusId
     *            - Id des zu suchenden Freigabestatusses
     * @return den gefunden Freigabestatus oder null
     */
    public ApprovalStatus getApprovalStatus(int approvalStatusId);

    /**
     * Sucht nach einer Kapazität ({@link Capacity}) in der Datenbank
     * 
     * @param capacityId
     *            - Id der zu suchenden Kapazität
     * @return die gefunden Kapazität oder null
     */
    public Capacity getCapacity(int capacityId);

    /**
     * Sucht nach einer Bezahlmethode ({@link PaymentCondition}) in der
     * Datenbank
     * 
     * @param paymentConditionId
     *            - Id der zu suchenden Bezahlmethode
     * @return die gefunden Bezahlmethode oder null
     */
    public PaymentCondition getPaymentCondition(int paymentConditionId);

    /**
     * Sucht nach einer Liefermethode ({@link DeliveryCondition}) in der
     * Datenbank
     * 
     * @param deliveryConditionId
     *            - Id der zu suchenden Liefermethode
     * @return die gefunden Liefermethode oder null
     */
    public DeliveryCondition getDeliveryCondition(int deliveryConditionId);

}

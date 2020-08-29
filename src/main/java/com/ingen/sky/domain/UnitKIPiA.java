package com.ingen.sky.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A UnitKIPiA.
 */
@Entity
@Table(name = "unit_ki_pi_a")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UnitKIPiA implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 3)
    @Column(name = "title", nullable = false)
    private String title;

    @Size(min = 3)
    @Column(name = "description")
    private String description;

    @Column(name = "system_rsupaz")
    private Boolean systemRSUPAZ;

    @Column(name = "root_plan_presence")
    private Boolean rootPlanPresence;

    @Column(name = "need_project_order")
    private Boolean needProjectOrder;

    @Column(name = "need_project_calculation")
    private Boolean needProjectCalculation;

    @Column(name = "need_electrical_heating")
    private Boolean needElectricalHeating;

    @Column(name = "number_ai")
    private Integer numberAI;

    @Column(name = "number_ao")
    private Integer numberAO;

    @Column(name = "number_di")
    private Integer numberDI;

    @Column(name = "number_do")
    private Integer numberDO;

    @Column(name = "basic_labor_input")
    private Double basicLaborInput;

    @ManyToOne
    @JsonIgnoreProperties(value = "unitKIPiAS", allowSetters = true)
    private KIPWiringDiagramType kipWiringDiagramType;

    @ManyToOne
    @JsonIgnoreProperties(value = "unitKIPiAS", allowSetters = true)
    private MeasurementParameter meashurmentParameter;

    @ManyToMany(mappedBy = "unitKIPiAS")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<UnitKIPiAGroup> unitKIPiAGroups = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public UnitKIPiA title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public UnitKIPiA description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isSystemRSUPAZ() {
        return systemRSUPAZ;
    }

    public UnitKIPiA systemRSUPAZ(Boolean systemRSUPAZ) {
        this.systemRSUPAZ = systemRSUPAZ;
        return this;
    }

    public void setSystemRSUPAZ(Boolean systemRSUPAZ) {
        this.systemRSUPAZ = systemRSUPAZ;
    }

    public Boolean isRootPlanPresence() {
        return rootPlanPresence;
    }

    public UnitKIPiA rootPlanPresence(Boolean rootPlanPresence) {
        this.rootPlanPresence = rootPlanPresence;
        return this;
    }

    public void setRootPlanPresence(Boolean rootPlanPresence) {
        this.rootPlanPresence = rootPlanPresence;
    }

    public Boolean isNeedProjectOrder() {
        return needProjectOrder;
    }

    public UnitKIPiA needProjectOrder(Boolean needProjectOrder) {
        this.needProjectOrder = needProjectOrder;
        return this;
    }

    public void setNeedProjectOrder(Boolean needProjectOrder) {
        this.needProjectOrder = needProjectOrder;
    }

    public Boolean isNeedProjectCalculation() {
        return needProjectCalculation;
    }

    public UnitKIPiA needProjectCalculation(Boolean needProjectCalculation) {
        this.needProjectCalculation = needProjectCalculation;
        return this;
    }

    public void setNeedProjectCalculation(Boolean needProjectCalculation) {
        this.needProjectCalculation = needProjectCalculation;
    }

    public Boolean isNeedElectricalHeating() {
        return needElectricalHeating;
    }

    public UnitKIPiA needElectricalHeating(Boolean needElectricalHeating) {
        this.needElectricalHeating = needElectricalHeating;
        return this;
    }

    public void setNeedElectricalHeating(Boolean needElectricalHeating) {
        this.needElectricalHeating = needElectricalHeating;
    }

    public Integer getNumberAI() {
        return numberAI;
    }

    public UnitKIPiA numberAI(Integer numberAI) {
        this.numberAI = numberAI;
        return this;
    }

    public void setNumberAI(Integer numberAI) {
        this.numberAI = numberAI;
    }

    public Integer getNumberAO() {
        return numberAO;
    }

    public UnitKIPiA numberAO(Integer numberAO) {
        this.numberAO = numberAO;
        return this;
    }

    public void setNumberAO(Integer numberAO) {
        this.numberAO = numberAO;
    }

    public Integer getNumberDI() {
        return numberDI;
    }

    public UnitKIPiA numberDI(Integer numberDI) {
        this.numberDI = numberDI;
        return this;
    }

    public void setNumberDI(Integer numberDI) {
        this.numberDI = numberDI;
    }

    public Integer getNumberDO() {
        return numberDO;
    }

    public UnitKIPiA numberDO(Integer numberDO) {
        this.numberDO = numberDO;
        return this;
    }

    public void setNumberDO(Integer numberDO) {
        this.numberDO = numberDO;
    }

    public Double getBasicLaborInput() {
        return basicLaborInput;
    }

    public UnitKIPiA basicLaborInput(Double basicLaborInput) {
        this.basicLaborInput = basicLaborInput;
        return this;
    }

    public void setBasicLaborInput(Double basicLaborInput) {
        this.basicLaborInput = basicLaborInput;
    }

    public KIPWiringDiagramType getKipWiringDiagramType() {
        return kipWiringDiagramType;
    }

    public UnitKIPiA kipWiringDiagramType(KIPWiringDiagramType kIPWiringDiagramType) {
        this.kipWiringDiagramType = kIPWiringDiagramType;
        return this;
    }

    public void setKipWiringDiagramType(KIPWiringDiagramType kIPWiringDiagramType) {
        this.kipWiringDiagramType = kIPWiringDiagramType;
    }

    public MeasurementParameter getMeashurmentParameter() {
        return meashurmentParameter;
    }

    public UnitKIPiA meashurmentParameter(MeasurementParameter measurementParameter) {
        this.meashurmentParameter = measurementParameter;
        return this;
    }

    public void setMeashurmentParameter(MeasurementParameter measurementParameter) {
        this.meashurmentParameter = measurementParameter;
    }

    public Set<UnitKIPiAGroup> getUnitKIPiAGroups() {
        return unitKIPiAGroups;
    }

    public UnitKIPiA unitKIPiAGroups(Set<UnitKIPiAGroup> unitKIPiAGroups) {
        this.unitKIPiAGroups = unitKIPiAGroups;
        return this;
    }

    public UnitKIPiA addUnitKIPiAGroup(UnitKIPiAGroup unitKIPiAGroup) {
        this.unitKIPiAGroups.add(unitKIPiAGroup);
        unitKIPiAGroup.getUnitKIPiAS().add(this);
        return this;
    }

    public UnitKIPiA removeUnitKIPiAGroup(UnitKIPiAGroup unitKIPiAGroup) {
        this.unitKIPiAGroups.remove(unitKIPiAGroup);
        unitKIPiAGroup.getUnitKIPiAS().remove(this);
        return this;
    }

    public void setUnitKIPiAGroups(Set<UnitKIPiAGroup> unitKIPiAGroups) {
        this.unitKIPiAGroups = unitKIPiAGroups;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UnitKIPiA)) {
            return false;
        }
        return id != null && id.equals(((UnitKIPiA) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UnitKIPiA{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", systemRSUPAZ='" + isSystemRSUPAZ() + "'" +
            ", rootPlanPresence='" + isRootPlanPresence() + "'" +
            ", needProjectOrder='" + isNeedProjectOrder() + "'" +
            ", needProjectCalculation='" + isNeedProjectCalculation() + "'" +
            ", needElectricalHeating='" + isNeedElectricalHeating() + "'" +
            ", numberAI=" + getNumberAI() +
            ", numberAO=" + getNumberAO() +
            ", numberDI=" + getNumberDI() +
            ", numberDO=" + getNumberDO() +
            ", basicLaborInput=" + getBasicLaborInput() +
            "}";
    }
}

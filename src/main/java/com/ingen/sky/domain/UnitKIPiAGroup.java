package com.ingen.sky.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A UnitKIPiAGroup.
 */
@Entity
@Table(name = "unit_ki_pi_a_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UnitKIPiAGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "unit_ki_pi_a_group_unitkipia",
               joinColumns = @JoinColumn(name = "unitkipiagroup_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "unitkipia_id", referencedColumnName = "id"))
    private Set<UnitKIPiA> unitKIPiAS = new HashSet<>();

    @ManyToMany(mappedBy = "unitKIPiAGroups")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<Tender> tenders = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public UnitKIPiAGroup quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Set<UnitKIPiA> getUnitKIPiAS() {
        return unitKIPiAS;
    }

    public UnitKIPiAGroup unitKIPiAS(Set<UnitKIPiA> unitKIPiAS) {
        this.unitKIPiAS = unitKIPiAS;
        return this;
    }

    public UnitKIPiAGroup addUnitKIPiA(UnitKIPiA unitKIPiA) {
        this.unitKIPiAS.add(unitKIPiA);
        unitKIPiA.getUnitKIPiAGroups().add(this);
        return this;
    }

    public UnitKIPiAGroup removeUnitKIPiA(UnitKIPiA unitKIPiA) {
        this.unitKIPiAS.remove(unitKIPiA);
        unitKIPiA.getUnitKIPiAGroups().remove(this);
        return this;
    }

    public void setUnitKIPiAS(Set<UnitKIPiA> unitKIPiAS) {
        this.unitKIPiAS = unitKIPiAS;
    }

    public Set<Tender> getTenders() {
        return tenders;
    }

    public UnitKIPiAGroup tenders(Set<Tender> tenders) {
        this.tenders = tenders;
        return this;
    }

    public UnitKIPiAGroup addTender(Tender tender) {
        this.tenders.add(tender);
        tender.getUnitKIPiAGroups().add(this);
        return this;
    }

    public UnitKIPiAGroup removeTender(Tender tender) {
        this.tenders.remove(tender);
        tender.getUnitKIPiAGroups().remove(this);
        return this;
    }

    public void setTenders(Set<Tender> tenders) {
        this.tenders = tenders;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UnitKIPiAGroup)) {
            return false;
        }
        return id != null && id.equals(((UnitKIPiAGroup) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UnitKIPiAGroup{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            "}";
    }
}

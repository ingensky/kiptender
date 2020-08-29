package com.ingen.sky.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A InstallationKIPiAGroup.
 */
@Entity
@Table(name = "installation_ki_pi_a_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InstallationKIPiAGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "installation_ki_pi_a_group_installationkipia",
               joinColumns = @JoinColumn(name = "installationkipiagroup_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "installationkipia_id", referencedColumnName = "id"))
    private Set<InstallationKIPiA> installationKIPiAS = new HashSet<>();

    @ManyToMany(mappedBy = "installationKIPiAGroups")
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

    public InstallationKIPiAGroup quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Set<InstallationKIPiA> getInstallationKIPiAS() {
        return installationKIPiAS;
    }

    public InstallationKIPiAGroup installationKIPiAS(Set<InstallationKIPiA> installationKIPiAS) {
        this.installationKIPiAS = installationKIPiAS;
        return this;
    }

    public InstallationKIPiAGroup addInstallationKIPiA(InstallationKIPiA installationKIPiA) {
        this.installationKIPiAS.add(installationKIPiA);
        installationKIPiA.getInstallationKIPiAGroups().add(this);
        return this;
    }

    public InstallationKIPiAGroup removeInstallationKIPiA(InstallationKIPiA installationKIPiA) {
        this.installationKIPiAS.remove(installationKIPiA);
        installationKIPiA.getInstallationKIPiAGroups().remove(this);
        return this;
    }

    public void setInstallationKIPiAS(Set<InstallationKIPiA> installationKIPiAS) {
        this.installationKIPiAS = installationKIPiAS;
    }

    public Set<Tender> getTenders() {
        return tenders;
    }

    public InstallationKIPiAGroup tenders(Set<Tender> tenders) {
        this.tenders = tenders;
        return this;
    }

    public InstallationKIPiAGroup addTender(Tender tender) {
        this.tenders.add(tender);
        tender.getInstallationKIPiAGroups().add(this);
        return this;
    }

    public InstallationKIPiAGroup removeTender(Tender tender) {
        this.tenders.remove(tender);
        tender.getInstallationKIPiAGroups().remove(this);
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
        if (!(o instanceof InstallationKIPiAGroup)) {
            return false;
        }
        return id != null && id.equals(((InstallationKIPiAGroup) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InstallationKIPiAGroup{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            "}";
    }
}

package com.ingen.sky.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A InstallationKIPiA.
 */
@Entity
@Table(name = "installation_ki_pi_a")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InstallationKIPiA implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 3)
    @Column(name = "title", nullable = false)
    private String title;

    @ManyToMany(mappedBy = "installationKIPiAS")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<InstallationKIPiAGroup> installationKIPiAGroups = new HashSet<>();

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

    public InstallationKIPiA title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<InstallationKIPiAGroup> getInstallationKIPiAGroups() {
        return installationKIPiAGroups;
    }

    public InstallationKIPiA installationKIPiAGroups(Set<InstallationKIPiAGroup> installationKIPiAGroups) {
        this.installationKIPiAGroups = installationKIPiAGroups;
        return this;
    }

    public InstallationKIPiA addInstallationKIPiAGroup(InstallationKIPiAGroup installationKIPiAGroup) {
        this.installationKIPiAGroups.add(installationKIPiAGroup);
        installationKIPiAGroup.getInstallationKIPiAS().add(this);
        return this;
    }

    public InstallationKIPiA removeInstallationKIPiAGroup(InstallationKIPiAGroup installationKIPiAGroup) {
        this.installationKIPiAGroups.remove(installationKIPiAGroup);
        installationKIPiAGroup.getInstallationKIPiAS().remove(this);
        return this;
    }

    public void setInstallationKIPiAGroups(Set<InstallationKIPiAGroup> installationKIPiAGroups) {
        this.installationKIPiAGroups = installationKIPiAGroups;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InstallationKIPiA)) {
            return false;
        }
        return id != null && id.equals(((InstallationKIPiA) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InstallationKIPiA{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            "}";
    }
}

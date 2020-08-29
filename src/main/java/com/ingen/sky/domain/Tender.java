package com.ingen.sky.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Tender.
 */
@Entity
@Table(name = "tender")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Tender implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "number")
    private String number;

    @NotNull
    @Size(min = 3)
    @Column(name = "title", nullable = false)
    private String title;

    @Size(min = 3)
    @Column(name = "description")
    private String description;

    @Column(name = "location")
    private String location;

    @Column(name = "worktrip")
    private Boolean worktrip;

    @Column(name = "need_sid_stage")
    private Boolean needSIDStage;

    @Column(name = "need_otr_stage")
    private Boolean needOTRStage;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "tender_project_mark",
               joinColumns = @JoinColumn(name = "tender_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "project_mark_id", referencedColumnName = "id"))
    private Set<ProjectMark> projectMarks = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "tender_unitkipiagroup",
               joinColumns = @JoinColumn(name = "tender_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "unitkipiagroup_id", referencedColumnName = "id"))
    private Set<UnitKIPiAGroup> unitKIPiAGroups = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "tender_installationkipiagroup",
               joinColumns = @JoinColumn(name = "tender_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "installationkipiagroup_id", referencedColumnName = "id"))
    private Set<InstallationKIPiAGroup> installationKIPiAGroups = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "tenders", allowSetters = true)
    private Client client;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public Tender number(String number) {
        this.number = number;
        return this;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public Tender title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public Tender description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public Tender location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean isWorktrip() {
        return worktrip;
    }

    public Tender worktrip(Boolean worktrip) {
        this.worktrip = worktrip;
        return this;
    }

    public void setWorktrip(Boolean worktrip) {
        this.worktrip = worktrip;
    }

    public Boolean isNeedSIDStage() {
        return needSIDStage;
    }

    public Tender needSIDStage(Boolean needSIDStage) {
        this.needSIDStage = needSIDStage;
        return this;
    }

    public void setNeedSIDStage(Boolean needSIDStage) {
        this.needSIDStage = needSIDStage;
    }

    public Boolean isNeedOTRStage() {
        return needOTRStage;
    }

    public Tender needOTRStage(Boolean needOTRStage) {
        this.needOTRStage = needOTRStage;
        return this;
    }

    public void setNeedOTRStage(Boolean needOTRStage) {
        this.needOTRStage = needOTRStage;
    }

    public Set<ProjectMark> getProjectMarks() {
        return projectMarks;
    }

    public Tender projectMarks(Set<ProjectMark> projectMarks) {
        this.projectMarks = projectMarks;
        return this;
    }

    public Tender addProjectMark(ProjectMark projectMark) {
        this.projectMarks.add(projectMark);
        projectMark.getTenders().add(this);
        return this;
    }

    public Tender removeProjectMark(ProjectMark projectMark) {
        this.projectMarks.remove(projectMark);
        projectMark.getTenders().remove(this);
        return this;
    }

    public void setProjectMarks(Set<ProjectMark> projectMarks) {
        this.projectMarks = projectMarks;
    }

    public Set<UnitKIPiAGroup> getUnitKIPiAGroups() {
        return unitKIPiAGroups;
    }

    public Tender unitKIPiAGroups(Set<UnitKIPiAGroup> unitKIPiAGroups) {
        this.unitKIPiAGroups = unitKIPiAGroups;
        return this;
    }

    public Tender addUnitKIPiAGroup(UnitKIPiAGroup unitKIPiAGroup) {
        this.unitKIPiAGroups.add(unitKIPiAGroup);
        unitKIPiAGroup.getTenders().add(this);
        return this;
    }

    public Tender removeUnitKIPiAGroup(UnitKIPiAGroup unitKIPiAGroup) {
        this.unitKIPiAGroups.remove(unitKIPiAGroup);
        unitKIPiAGroup.getTenders().remove(this);
        return this;
    }

    public void setUnitKIPiAGroups(Set<UnitKIPiAGroup> unitKIPiAGroups) {
        this.unitKIPiAGroups = unitKIPiAGroups;
    }

    public Set<InstallationKIPiAGroup> getInstallationKIPiAGroups() {
        return installationKIPiAGroups;
    }

    public Tender installationKIPiAGroups(Set<InstallationKIPiAGroup> installationKIPiAGroups) {
        this.installationKIPiAGroups = installationKIPiAGroups;
        return this;
    }

    public Tender addInstallationKIPiAGroup(InstallationKIPiAGroup installationKIPiAGroup) {
        this.installationKIPiAGroups.add(installationKIPiAGroup);
        installationKIPiAGroup.getTenders().add(this);
        return this;
    }

    public Tender removeInstallationKIPiAGroup(InstallationKIPiAGroup installationKIPiAGroup) {
        this.installationKIPiAGroups.remove(installationKIPiAGroup);
        installationKIPiAGroup.getTenders().remove(this);
        return this;
    }

    public void setInstallationKIPiAGroups(Set<InstallationKIPiAGroup> installationKIPiAGroups) {
        this.installationKIPiAGroups = installationKIPiAGroups;
    }

    public Client getClient() {
        return client;
    }

    public Tender client(Client client) {
        this.client = client;
        return this;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tender)) {
            return false;
        }
        return id != null && id.equals(((Tender) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Tender{" +
            "id=" + getId() +
            ", number='" + getNumber() + "'" +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", location='" + getLocation() + "'" +
            ", worktrip='" + isWorktrip() + "'" +
            ", needSIDStage='" + isNeedSIDStage() + "'" +
            ", needOTRStage='" + isNeedOTRStage() + "'" +
            "}";
    }
}

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
 * A ProjectMark.
 */
@Entity
@Table(name = "project_mark")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProjectMark implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 3)
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Size(min = 2)
    @Column(name = "mark", nullable = false)
    private String mark;

    @ManyToMany(mappedBy = "projectMarks")
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

    public String getTitle() {
        return title;
    }

    public ProjectMark title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMark() {
        return mark;
    }

    public ProjectMark mark(String mark) {
        this.mark = mark;
        return this;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Set<Tender> getTenders() {
        return tenders;
    }

    public ProjectMark tenders(Set<Tender> tenders) {
        this.tenders = tenders;
        return this;
    }

    public ProjectMark addTender(Tender tender) {
        this.tenders.add(tender);
        tender.getProjectMarks().add(this);
        return this;
    }

    public ProjectMark removeTender(Tender tender) {
        this.tenders.remove(tender);
        tender.getProjectMarks().remove(this);
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
        if (!(o instanceof ProjectMark)) {
            return false;
        }
        return id != null && id.equals(((ProjectMark) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectMark{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", mark='" + getMark() + "'" +
            "}";
    }
}

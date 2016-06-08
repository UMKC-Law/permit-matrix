package edu.umkc.permitme.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import edu.umkc.permitme.domain.enumeration.RightOfWayType;

/**
 * A RightOfWay.
 */
@Entity
@Table(name = "right_of_way")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RightOfWay implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "right_of_way_type")
    private RightOfWayType rightOfWayType;

    @Column(name = "closure_boundaries")
    private String closureBoundaries;

    @Column(name = "proposed_detour")
    private String proposedDetour;

    @Column(name = "right_of_way_name")
    private String rightOfWayName;

    @Column(name = "closure_duration_days")
    private Integer closureDurationDays;

    @ManyToOne
    private Project project;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RightOfWayType getRightOfWayType() {
        return rightOfWayType;
    }

    public void setRightOfWayType(RightOfWayType rightOfWayType) {
        this.rightOfWayType = rightOfWayType;
    }

    public String getClosureBoundaries() {
        return closureBoundaries;
    }

    public void setClosureBoundaries(String closureBoundaries) {
        this.closureBoundaries = closureBoundaries;
    }

    public String getProposedDetour() {
        return proposedDetour;
    }

    public void setProposedDetour(String proposedDetour) {
        this.proposedDetour = proposedDetour;
    }

    public String getRightOfWayName() {
        return rightOfWayName;
    }

    public void setRightOfWayName(String rightOfWayName) {
        this.rightOfWayName = rightOfWayName;
    }

    public Integer getClosureDurationDays() {
        return closureDurationDays;
    }

    public void setClosureDurationDays(Integer closureDurationDays) {
        this.closureDurationDays = closureDurationDays;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RightOfWay rightOfWay = (RightOfWay) o;
        if(rightOfWay.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, rightOfWay.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "RightOfWay{" +
            "id=" + id +
            ", rightOfWayType='" + rightOfWayType + "'" +
            ", closureBoundaries='" + closureBoundaries + "'" +
            ", proposedDetour='" + proposedDetour + "'" +
            ", rightOfWayName='" + rightOfWayName + "'" +
            ", closureDurationDays='" + closureDurationDays + "'" +
            '}';
    }
}

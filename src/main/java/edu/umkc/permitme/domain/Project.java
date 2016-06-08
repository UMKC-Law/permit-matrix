package edu.umkc.permitme.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import edu.umkc.permitme.domain.enumeration.ProjectStatus;

import edu.umkc.permitme.domain.enumeration.RepairLocation;

import edu.umkc.permitme.domain.enumeration.MeterLocation;

import edu.umkc.permitme.domain.enumeration.PlumbingType;

import edu.umkc.permitme.domain.enumeration.WorkSiteType;

/**
 * A Project.
 */
@Entity
@Table(name = "project")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_description")
    private String projectDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "project_status")
    private ProjectStatus projectStatus;

    @Column(name = "added_date")
    private LocalDate addedDate;

    @Column(name = "submitted_date")
    private LocalDate submittedDate;

    @Column(name = "approved_date")
    private LocalDate approvedDate;

    @Column(name = "is_work_on_existing_plumbing")
    private Boolean isWorkOnExistingPlumbing;

    @Enumerated(EnumType.STRING)
    @Column(name = "repair_location")
    private RepairLocation repairLocation;

    @Column(name = "custom_repair_location")
    private String customRepairLocation;

    @Enumerated(EnumType.STRING)
    @Column(name = "meter_location")
    private MeterLocation meterLocation;

    @Column(name = "is_new_line")
    private Boolean isNewLine;

    @Enumerated(EnumType.STRING)
    @Column(name = "plumbing_type")
    private PlumbingType plumbingType;

    @Column(name = "custom_plumbing_type")
    private String customPlumbingType;

    @Column(name = "is_connection_available_from_property")
    private Boolean isConnectionAvailableFromProperty;

    @Column(name = "is_connection_ready_for_hookup")
    private Boolean isConnectionReadyForHookup;

    @Column(name = "has_master_sewer_approval_letter")
    private Boolean hasMasterSewerApprovalLetter;

    @Column(name = "plat_subdivision")
    private String platSubdivision;

    @Column(name = "has_assessment_or_lien")
    private Boolean hasAssessmentOrLien;

    @Column(name = "is_assessment_or_lien_paid")
    private Boolean isAssessmentOrLienPaid;

    @Enumerated(EnumType.STRING)
    @Column(name = "work_site_type")
    private WorkSiteType workSiteType;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "includes_excavation")
    private Boolean includesExcavation;

    @Column(name = "excavation_description")
    private String excavationDescription;

    @Column(name = "excavation_permit_number")
    private String excavationPermitNumber;

    @Column(name = "is_new_construction")
    private Boolean isNewConstruction;

    @Column(name = "new_construction_building_permit_number")
    private String newConstructionBuildingPermitNumber;

    @Column(name = "zoning_district")
    private String zoningDistrict;

    @Column(name = "occ_group")
    private String occGroup;

    @Column(name = "construction_type")
    private String constructionType;

    @Column(name = "structural_class")
    private String structuralClass;

    @Column(name = "has_site_plan")
    private Boolean hasSitePlan;

    @Column(name = "control_number")
    private String controlNumber;

    @Column(name = "total_floors")
    private Integer totalFloors;

    @Column(name = "gross_building_area", precision=10, scale=2)
    private BigDecimal grossBuildingArea;

    @Column(name = "total_dwelling_units")
    private Integer totalDwellingUnits;

    @Column(name = "service_line_location")
    private String serviceLineLocation;

    @Column(name = "existing_sewer_material_description")
    private String existingSewerMaterialDescription;

    @Column(name = "connection_type")
    private String connectionType;

    @Column(name = "service_line_size")
    private String serviceLineSize;

    @Column(name = "sewer_main_size")
    private String sewerMainSize;

    @Column(name = "uses_right_of_way")
    private Boolean usesRightOfWay;

    @ManyToOne
    private Contractor contractor;

    @OneToMany(mappedBy = "project")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RightOfWay> rightOfWays = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    public LocalDate getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDate addedDate) {
        this.addedDate = addedDate;
    }

    public LocalDate getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(LocalDate submittedDate) {
        this.submittedDate = submittedDate;
    }

    public LocalDate getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(LocalDate approvedDate) {
        this.approvedDate = approvedDate;
    }

    public Boolean isIsWorkOnExistingPlumbing() {
        return isWorkOnExistingPlumbing;
    }

    public void setIsWorkOnExistingPlumbing(Boolean isWorkOnExistingPlumbing) {
        this.isWorkOnExistingPlumbing = isWorkOnExistingPlumbing;
    }

    public RepairLocation getRepairLocation() {
        return repairLocation;
    }

    public void setRepairLocation(RepairLocation repairLocation) {
        this.repairLocation = repairLocation;
    }

    public String getCustomRepairLocation() {
        return customRepairLocation;
    }

    public void setCustomRepairLocation(String customRepairLocation) {
        this.customRepairLocation = customRepairLocation;
    }

    public MeterLocation getMeterLocation() {
        return meterLocation;
    }

    public void setMeterLocation(MeterLocation meterLocation) {
        this.meterLocation = meterLocation;
    }

    public Boolean isIsNewLine() {
        return isNewLine;
    }

    public void setIsNewLine(Boolean isNewLine) {
        this.isNewLine = isNewLine;
    }

    public PlumbingType getPlumbingType() {
        return plumbingType;
    }

    public void setPlumbingType(PlumbingType plumbingType) {
        this.plumbingType = plumbingType;
    }

    public String getCustomPlumbingType() {
        return customPlumbingType;
    }

    public void setCustomPlumbingType(String customPlumbingType) {
        this.customPlumbingType = customPlumbingType;
    }

    public Boolean isIsConnectionAvailableFromProperty() {
        return isConnectionAvailableFromProperty;
    }

    public void setIsConnectionAvailableFromProperty(Boolean isConnectionAvailableFromProperty) {
        this.isConnectionAvailableFromProperty = isConnectionAvailableFromProperty;
    }

    public Boolean isIsConnectionReadyForHookup() {
        return isConnectionReadyForHookup;
    }

    public void setIsConnectionReadyForHookup(Boolean isConnectionReadyForHookup) {
        this.isConnectionReadyForHookup = isConnectionReadyForHookup;
    }

    public Boolean isHasMasterSewerApprovalLetter() {
        return hasMasterSewerApprovalLetter;
    }

    public void setHasMasterSewerApprovalLetter(Boolean hasMasterSewerApprovalLetter) {
        this.hasMasterSewerApprovalLetter = hasMasterSewerApprovalLetter;
    }

    public String getPlatSubdivision() {
        return platSubdivision;
    }

    public void setPlatSubdivision(String platSubdivision) {
        this.platSubdivision = platSubdivision;
    }

    public Boolean isHasAssessmentOrLien() {
        return hasAssessmentOrLien;
    }

    public void setHasAssessmentOrLien(Boolean hasAssessmentOrLien) {
        this.hasAssessmentOrLien = hasAssessmentOrLien;
    }

    public Boolean isIsAssessmentOrLienPaid() {
        return isAssessmentOrLienPaid;
    }

    public void setIsAssessmentOrLienPaid(Boolean isAssessmentOrLienPaid) {
        this.isAssessmentOrLienPaid = isAssessmentOrLienPaid;
    }

    public WorkSiteType getWorkSiteType() {
        return workSiteType;
    }

    public void setWorkSiteType(WorkSiteType workSiteType) {
        this.workSiteType = workSiteType;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean isIncludesExcavation() {
        return includesExcavation;
    }

    public void setIncludesExcavation(Boolean includesExcavation) {
        this.includesExcavation = includesExcavation;
    }

    public String getExcavationDescription() {
        return excavationDescription;
    }

    public void setExcavationDescription(String excavationDescription) {
        this.excavationDescription = excavationDescription;
    }

    public String getExcavationPermitNumber() {
        return excavationPermitNumber;
    }

    public void setExcavationPermitNumber(String excavationPermitNumber) {
        this.excavationPermitNumber = excavationPermitNumber;
    }

    public Boolean isIsNewConstruction() {
        return isNewConstruction;
    }

    public void setIsNewConstruction(Boolean isNewConstruction) {
        this.isNewConstruction = isNewConstruction;
    }

    public String getNewConstructionBuildingPermitNumber() {
        return newConstructionBuildingPermitNumber;
    }

    public void setNewConstructionBuildingPermitNumber(String newConstructionBuildingPermitNumber) {
        this.newConstructionBuildingPermitNumber = newConstructionBuildingPermitNumber;
    }

    public String getZoningDistrict() {
        return zoningDistrict;
    }

    public void setZoningDistrict(String zoningDistrict) {
        this.zoningDistrict = zoningDistrict;
    }

    public String getOccGroup() {
        return occGroup;
    }

    public void setOccGroup(String occGroup) {
        this.occGroup = occGroup;
    }

    public String getConstructionType() {
        return constructionType;
    }

    public void setConstructionType(String constructionType) {
        this.constructionType = constructionType;
    }

    public String getStructuralClass() {
        return structuralClass;
    }

    public void setStructuralClass(String structuralClass) {
        this.structuralClass = structuralClass;
    }

    public Boolean isHasSitePlan() {
        return hasSitePlan;
    }

    public void setHasSitePlan(Boolean hasSitePlan) {
        this.hasSitePlan = hasSitePlan;
    }

    public String getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(String controlNumber) {
        this.controlNumber = controlNumber;
    }

    public Integer getTotalFloors() {
        return totalFloors;
    }

    public void setTotalFloors(Integer totalFloors) {
        this.totalFloors = totalFloors;
    }

    public BigDecimal getGrossBuildingArea() {
        return grossBuildingArea;
    }

    public void setGrossBuildingArea(BigDecimal grossBuildingArea) {
        this.grossBuildingArea = grossBuildingArea;
    }

    public Integer getTotalDwellingUnits() {
        return totalDwellingUnits;
    }

    public void setTotalDwellingUnits(Integer totalDwellingUnits) {
        this.totalDwellingUnits = totalDwellingUnits;
    }

    public String getServiceLineLocation() {
        return serviceLineLocation;
    }

    public void setServiceLineLocation(String serviceLineLocation) {
        this.serviceLineLocation = serviceLineLocation;
    }

    public String getExistingSewerMaterialDescription() {
        return existingSewerMaterialDescription;
    }

    public void setExistingSewerMaterialDescription(String existingSewerMaterialDescription) {
        this.existingSewerMaterialDescription = existingSewerMaterialDescription;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    public String getServiceLineSize() {
        return serviceLineSize;
    }

    public void setServiceLineSize(String serviceLineSize) {
        this.serviceLineSize = serviceLineSize;
    }

    public String getSewerMainSize() {
        return sewerMainSize;
    }

    public void setSewerMainSize(String sewerMainSize) {
        this.sewerMainSize = sewerMainSize;
    }

    public Boolean isUsesRightOfWay() {
        return usesRightOfWay;
    }

    public void setUsesRightOfWay(Boolean usesRightOfWay) {
        this.usesRightOfWay = usesRightOfWay;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public Set<RightOfWay> getRightOfWays() {
        return rightOfWays;
    }

    public void setRightOfWays(Set<RightOfWay> rightOfWays) {
        this.rightOfWays = rightOfWays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Project project = (Project) o;
        if(project.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Project{" +
            "id=" + id +
            ", projectName='" + projectName + "'" +
            ", projectDescription='" + projectDescription + "'" +
            ", projectStatus='" + projectStatus + "'" +
            ", addedDate='" + addedDate + "'" +
            ", submittedDate='" + submittedDate + "'" +
            ", approvedDate='" + approvedDate + "'" +
            ", isWorkOnExistingPlumbing='" + isWorkOnExistingPlumbing + "'" +
            ", repairLocation='" + repairLocation + "'" +
            ", customRepairLocation='" + customRepairLocation + "'" +
            ", meterLocation='" + meterLocation + "'" +
            ", isNewLine='" + isNewLine + "'" +
            ", plumbingType='" + plumbingType + "'" +
            ", customPlumbingType='" + customPlumbingType + "'" +
            ", isConnectionAvailableFromProperty='" + isConnectionAvailableFromProperty + "'" +
            ", isConnectionReadyForHookup='" + isConnectionReadyForHookup + "'" +
            ", hasMasterSewerApprovalLetter='" + hasMasterSewerApprovalLetter + "'" +
            ", platSubdivision='" + platSubdivision + "'" +
            ", hasAssessmentOrLien='" + hasAssessmentOrLien + "'" +
            ", isAssessmentOrLienPaid='" + isAssessmentOrLienPaid + "'" +
            ", workSiteType='" + workSiteType + "'" +
            ", streetAddress='" + streetAddress + "'" +
            ", zipCode='" + zipCode + "'" +
            ", city='" + city + "'" +
            ", state='" + state + "'" +
            ", includesExcavation='" + includesExcavation + "'" +
            ", excavationDescription='" + excavationDescription + "'" +
            ", excavationPermitNumber='" + excavationPermitNumber + "'" +
            ", isNewConstruction='" + isNewConstruction + "'" +
            ", newConstructionBuildingPermitNumber='" + newConstructionBuildingPermitNumber + "'" +
            ", zoningDistrict='" + zoningDistrict + "'" +
            ", occGroup='" + occGroup + "'" +
            ", constructionType='" + constructionType + "'" +
            ", structuralClass='" + structuralClass + "'" +
            ", hasSitePlan='" + hasSitePlan + "'" +
            ", controlNumber='" + controlNumber + "'" +
            ", totalFloors='" + totalFloors + "'" +
            ", grossBuildingArea='" + grossBuildingArea + "'" +
            ", totalDwellingUnits='" + totalDwellingUnits + "'" +
            ", serviceLineLocation='" + serviceLineLocation + "'" +
            ", existingSewerMaterialDescription='" + existingSewerMaterialDescription + "'" +
            ", connectionType='" + connectionType + "'" +
            ", serviceLineSize='" + serviceLineSize + "'" +
            ", sewerMainSize='" + sewerMainSize + "'" +
            ", usesRightOfWay='" + usesRightOfWay + "'" +
            '}';
    }
}

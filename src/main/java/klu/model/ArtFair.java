package klu.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.util.Date;

@Entity
@Table(name = "artfair")
public class ArtFair 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tourid", nullable = false)
    private Long tourid;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "tdescription", length = 500)
    private String tdescription;

    @Column(name = "startdate", nullable = false)
    private String startDate;

    @Column(name = "enddate", nullable = false)
    private String endDate;

    @Column(name = "location", nullable = false, length = 200)
    private String location;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "entryfee")
    private Double entryFee;

    @Column(name = "theme", length = 100)
    private String theme;
    
    @Column(name = "timage",nullable=false)
    private String timage;
    
    
    @Column(name = "curator_id", nullable = false) // Add this line
    private String curatorId;

   
    public Long getTourid() {
        return tourid;
    }

    public void setTourid(Long tourid) {
        this.tourid = tourid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTdescription() {
        return tdescription;
    }

    public void setTdescription(String tdescription) {
        this.tdescription = tdescription;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String string) {
        this.startDate = string;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String string) {
        this.endDate = string;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Double getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(Double entryFee) {
        this.entryFee = entryFee;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

	public String getTimage() {
		return timage;
	}

	public void setTimage(String timage) {
		this.timage = timage;
	}

	public String getCuratorId() {
		return curatorId;
	}

	public void setCuratorId(String curatorId) {
		this.curatorId = curatorId;
	}

	

    
}

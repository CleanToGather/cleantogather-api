package com.yellowstone.cleantogather.api.marker;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Marker {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String address;
    private Long coord_x;
    private Long coord_y;
    private LocalDateTime markedDateTime;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marker marker = (Marker) o;
        return Objects.equals(id, marker.id) &&
        		Objects.equals(address, marker.address) &&
                Objects.equals(coord_x, marker.coord_x) &&
                Objects.equals(coord_y, marker.coord_y) &&
                Objects.equals(markedDateTime, marker.markedDateTime);
    }
    
    public Marker () {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCoord_x() {
		return coord_x;
	}

	public void setCoord_x(Long coord_x) {
		this.coord_x = coord_x;
	}

	public Long getCoord_y() {
		return coord_y;
	}

	public void setCoord_y(Long coord_y) {
		this.coord_y = coord_y;
	}

	public LocalDateTime getMarkedDateTime() {
		return markedDateTime;
	}

	public void setMarkedDateTime(LocalDateTime markedDateTime) {
		this.markedDateTime = markedDateTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
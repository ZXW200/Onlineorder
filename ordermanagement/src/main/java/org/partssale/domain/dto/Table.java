package org.partssale.domain.dto;


import org.partssale.utils.TableStatus;

import java.io.Serializable;

public class Table implements Serializable {
    private Long id;
    private Integer number;
    private Integer seatingCapacity;
    private TableStatus status; // Changed to use the enum

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(Integer seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public TableStatus getStatus() {
        return status;
    }

    public void setStatus(TableStatus status) {
        this.status = status;
    }
}


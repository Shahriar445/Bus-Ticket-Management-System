/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ticketmanagementsystem;

import java.util.Date;

/**
 *
 *            @author Shipon
 */
public class busData {

    private final Integer busId;
    private final String location;
    private final String status;
    private final Double price;
    private final Date date;

    public busData(Integer busId, String location, String status, Double price, Date date) {
        this.busId = busId;
        this.location = location;
        this.status = status;
        this.price = price;
        this.date = date;
    }

    public Integer getBusId() {
        return busId;
    }

    /**
     *
     * @return
     */
    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    /**
     *
     * @return
     */
    public Double getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }
}

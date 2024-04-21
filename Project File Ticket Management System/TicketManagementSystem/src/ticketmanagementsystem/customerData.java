/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ticketmanagementsystem;

import java.util.Date;

/**
 *
 * @author Shipon
 */
public class customerData {
    private Integer customerNum;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNum;
    private Integer busId;
    private String location;
    private String type;
    private Integer seatNum;
    private Double total;
    private Date date;
    public customerData(Integer customerNum, String firstName,String lastName,String gender,String phoneNum,Integer busId,String location,String type,Integer seatNum,Double total,Date date)
    {
        this.customerNum=customerNum;
        this.firstName=firstName;
        this.lastName=lastName;
        this.gender=gender;
        this.phoneNum=phoneNum;
        this.seatNum=seatNum;
        this.total=total;
        this.type=type;
        this.busId=busId;
        this.location=location;
        this.date=date;
    
    }
    public Integer getCustomerNum()
    {
        return customerNum;
    }
    
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getGender(){
        return gender;
    }
    public String getPhoneNum(){
        return phoneNum;
    }
    public Integer getBusId(){
        return busId;
    }
    public String getLocation(){
        return location;
    }
    public String getType(){
        return type;
    }
   
    public Double getTotal(){
        return total;
    }
    public Date getDate(){
        return date;
    }
    
}

package de.regis24.persistence.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * Created by vbourdine on 22.09.2015.
 */

@Entity(name="customer_address")
public class Address {
	
    @Id
    @GeneratedValue//(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String address;
    
    @Temporal(TemporalType.DATE)
	@Column(unique = true, nullable = false, length = 10)
    private Date date;   

	public Address(String address) {
		super();
		this.address = address;
	}

	public Address(String address, Customer customer) {
		super();
		this.address = address;
		this.customer = customer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "Address [id=" + id + ", address=" + address + "]";
	}
	
}

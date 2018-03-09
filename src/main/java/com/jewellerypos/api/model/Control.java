package com.jewellerypos.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CONTROL")
public class Control implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SNO")
	private long sno;
	
	@Column(name = "PURCHASE_NO")
	private long purchaseBillno;
	
	@Column(name = "SALE_NO")
	private long saleBillNo;

}

package com.example.shahar.ex3_mt;

/**
 * Created by eliran on 25/12/17.
 */

public class Supplier {
    Supplier() {}

    public Supplier(String supplierName, String supplierContact) {
        this.supplierName = supplierName;
        this.supplierContact = supplierContact;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierContact() {
        return supplierContact;
    }

    public void setSupplierContact(String supplierContact) {
        this.supplierContact = supplierContact;
    }
    private String supplierName;
    private String supplierContact;
}

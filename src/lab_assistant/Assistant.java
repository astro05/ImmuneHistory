/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_assistant;

import immunehistory.labib_constants.Constants;

/**
 *
 * @author labib
 */
public class Assistant {
    
    String assistant_name;
    String mobile;
    String degree;
    String hospital;
    String email;
    String address;
    String zipcode;
    String password;
    
    public Assistant(){
        ;
    }
    
    public Assistant(String name, String mobile, String degree, String hospital, String email, String address, String zipcode, String password) {
        this.assistant_name = name;
        this.mobile = mobile;
        this.degree = degree;
        this.hospital = hospital;
        this.email = email;
        this.address = address;
        this.zipcode = zipcode;
        this.password = password;
    }

    public String getAssistant_name() {
        return assistant_name;
    }

    public void setAssistant_name(String assistant_name) {
        this.assistant_name = assistant_name;
    }

    

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}

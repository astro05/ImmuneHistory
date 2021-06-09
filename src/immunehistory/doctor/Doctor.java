/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.doctor;

/**
 *
 * @author labib
 */
public class Doctor {
    
    private int doctor_id;
    private String doctor_name;
    private String mobile;
    private String degree;
    private String hospital;
    private String email;
    private String address;
    private String zipcode;
    private String category;
    private String password;
    
    public Doctor(){
        ;
    }
    
    public Doctor(int doctor_id, String doctor_name, String mobile, String degree, String hospital, String email, String address, String zipcode, String category, String password) {
        this.doctor_id = doctor_id;
        this.doctor_name = doctor_name;
        this.mobile = mobile;
        this.degree = degree;
        this.hospital = hospital;
        this.email = email;
        this.address = address;
        this.zipcode = zipcode;
        this.category = category;
        this.password = password;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    
    
    
    
}

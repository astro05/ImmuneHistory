/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.lab_test;

/**
 *
 * @author labib
 */
public class Lab_Test {
    
    private int test_id;
    private int assistant_id;
    private String ref_id;
    private int consult_id;
    private String test_name;
    private String report;
    private String test_center;
    private String date_of_test;
    private String date_of_result;
    
    public Lab_Test(){
        ;
    }
    
    public Lab_Test(int test_id, int assistant_id, String ref_id, int consult_id, String test_name, String report, String test_center, String date_of_test, String date_of_result) {
        this.test_id = test_id;
        this.assistant_id = assistant_id;
        this.ref_id = ref_id;
        this.consult_id = consult_id;
        this.test_name = test_name;
        this.report = report;
        this.test_center = test_center;
        this.date_of_test = date_of_test;
        this.date_of_result = date_of_result;
    }

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

    public int getAssistant_id() {
        return assistant_id;
    }

    public void setAssistant_id(int assistant_id) {
        this.assistant_id = assistant_id;
    }

    public String getRef_id() {
        return ref_id;
    }

    public void setRef_id(String ref_id) {
        this.ref_id = ref_id;
    }

    public int getConsult_id() {
        return consult_id;
    }

    public void setConsult_id(int consult_id) {
        this.consult_id = consult_id;
    }

    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getTest_center() {
        return test_center;
    }

    public void setTest_center(String test_center) {
        this.test_center = test_center;
    }

    public String getDate_of_test() {
        return date_of_test;
    }

    public void setDate_of_test(String date_of_test) {
        this.date_of_test = date_of_test;
    }

    public String getDate_of_result() {
        return date_of_result;
    }

    public void setDate_of_result(String date_of_result) {
        this.date_of_result = date_of_result;
    }
    
    
    
    
}

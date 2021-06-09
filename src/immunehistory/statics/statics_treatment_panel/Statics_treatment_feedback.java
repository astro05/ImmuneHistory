/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.statics.statics_treatment_panel;

/**
 *
 * @author joyultimates
 */
public class Statics_treatment_feedback {
    
    private String TID;
    private String CID;
    private String SuccessRate;
    private String CDRate;
    

    public Statics_treatment_feedback(String TID, String CID, String SuccessRate, String CDRate) {
        this.TID = TID;
        this.CID = CID;
        this.SuccessRate = SuccessRate;
        this.CDRate = CDRate;
    }
    
     
    /**
     * @return the TID
     */
    public String getTID() {
        return TID;
    }

    /**
     * @param TID the TID to set
     */
    public void setTID(String TID) {
        this.TID = TID;
    }

    /**
     * @return the CID
     */
    public String getCID() {
        return CID;
    }

    /**
     * @param CID the CID to set
     */
    public void setCID(String CID) {
        this.CID = CID;
    }

    /**
     * @return the SuccessRate
     */
    public String getSuccessRate() {
        return SuccessRate;
    }

    /**
     * @param SuccessRate the SuccessRate to set
     */
    public void setSuccessRate(String SuccessRate) {
        this.SuccessRate = SuccessRate;
    }

    /**
     * @return the CDRate
     */
    public String getCDRate() {
        return CDRate;
    }

    /**
     * @param CDRate the CDRate to set
     */
    public void setCDRate(String CDRate) {
        this.CDRate = CDRate;
    }

   
    
    
}

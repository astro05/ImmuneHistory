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
public class Statics_treatment_feedbackDependent {
    
        private String dTID;
    private String dCID;
    private String dSuccessRate;
    private String dCDRate;

    public Statics_treatment_feedbackDependent(String dTID, String dCID, String dSuccessRate, String dCDRate) {
        this.dTID = dTID;
        this.dCID = dCID;
        this.dSuccessRate = dSuccessRate;
        this.dCDRate = dCDRate;
    }

    /**
     * @return the dTID
     */
    public String getdTID() {
        return dTID;
    }

    /**
     * @param dTID the dTID to set
     */
    public void setdTID(String dTID) {
        this.dTID = dTID;
    }

    /**
     * @return the dCID
     */
    public String getdCID() {
        return dCID;
    }

    /**
     * @param dCID the dCID to set
     */
    public void setdCID(String dCID) {
        this.dCID = dCID;
    }

    /**
     * @return the dSuccessRate
     */
    public String getdSuccessRate() {
        return dSuccessRate;
    }

    /**
     * @param dSuccessRate the dSuccessRate to set
     */
    public void setdSuccessRate(String dSuccessRate) {
        this.dSuccessRate = dSuccessRate;
    }

    /**
     * @return the dCDRate
     */
    public String getdCDRate() {
        return dCDRate;
    }

    /**
     * @param dCDRate the dCDRate to set
     */
    public void setdCDRate(String dCDRate) {
        this.dCDRate = dCDRate;
    }

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.statics.statics_drug_panel;

/**
 *
 * @author joyultimates
 */
public class statics_drug_panel {
    
     private String Drugid;
     private String MedGenericName;
     private String MedBrandName;
     private String Applyonpatient;
     private String Note; 
     private String Action; 

    public statics_drug_panel(String Drugid, String MedGenericName, String MedBrandName, String Note) {
        this.Drugid = Drugid;
        this.MedGenericName = MedGenericName;
        this.MedBrandName = MedBrandName;
        this.Note = Note;
    }

    /**
     * @return the Drugid
     */
    public String getDrugid() {
        return Drugid;
    }

    /**
     * @param Drugid the Drugid to set
     */
    public void setDrugid(String Drugid) {
        this.Drugid = Drugid;
    }

    /**
     * @return the MedGenericName
     */
    public String getMedGenericName() {
        return MedGenericName;
    }

    /**
     * @param MedGenericName the MedGenericName to set
     */
    public void setMedGenericName(String MedGenericName) {
        this.MedGenericName = MedGenericName;
    }

    /**
     * @return the MedBrandName
     */
    public String getMedBrandName() {
        return MedBrandName;
    }

    /**
     * @param MedBrandName the MedBrandName to set
     */
    public void setMedBrandName(String MedBrandName) {
        this.MedBrandName = MedBrandName;
    }

    /**
 

    /**
     * @return the Note
     */
    public String getNote() {
        return Note;
    }

    /**
     * @param Note the Note to set
     */
    public void setNote(String Note) {
        this.Note = Note;
    }
     

    
    
}

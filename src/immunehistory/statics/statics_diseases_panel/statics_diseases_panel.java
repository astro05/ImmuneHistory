/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.statics.statics_diseases_panel;

/**
 *
 * @author joyultimates
 */
public class statics_diseases_panel {
    
     private String diseaseid;
     private String DiseaseName;
     private String DiseaseType;
     private String MinPeriod;
     private String MaxPeriod; 
     private String Symptom;
     private String Medicine;
     private String ReactiveMedicine;
     private String Action; 

    public statics_diseases_panel(String diseaseid, String DiseaseName, String DiseaseType, String MinPeriod, String MaxPeriod, String Symptom, String Medicine, String ReactiveMedicine) {
        this.diseaseid = diseaseid;
        this.DiseaseName = DiseaseName;
        this.DiseaseType = DiseaseType;
        this.MinPeriod = MinPeriod;
        this.MaxPeriod = MaxPeriod;
        this.Symptom = Symptom;
        this.Medicine = Medicine;
        this.ReactiveMedicine = ReactiveMedicine;
     
    }
    

    /**
     * @return the diseaseid
     */
    public String getDiseaseid() {
        return diseaseid;
    }

    /**
     * @param diseaseid the diseaseid to set
     */
    public void setDiseaseid(String diseaseid) {
        this.diseaseid = diseaseid;
    }

    /**
     * @return the DiseaseName
     */
    public String getDiseaseName() {
        return DiseaseName;
    }

    /**
     * @param DiseaseName the DiseaseName to set
     */
    public void setDiseaseName(String DiseaseName) {
        this.DiseaseName = DiseaseName;
    }

    /**
     * @return the DiseaseType
     */
    public String getDiseaseType() {
        return DiseaseType;
    }

    /**
     * @param DiseaseType the DiseaseType to set
     */
    public void setDiseaseType(String DiseaseType) {
        this.DiseaseType = DiseaseType;
    }

    /**
     * @return the MinPeriod
     */
    public String getMinPeriod() {
        return MinPeriod;
    }

    /**
     * @param MinPeriod the MinPeriod to set
     */
    public void setMinPeriod(String MinPeriod) {
        this.MinPeriod = MinPeriod;
    }

    /**
     * @return the MaxPeriod
     */
    public String getMaxPeriod() {
        return MaxPeriod;
    }

    /**
     * @param MaxPeriod the MaxPeriod to set
     */
    public void setMaxPeriod(String MaxPeriod) {
        this.MaxPeriod = MaxPeriod;
    }

    /**
     * @return the Symptom
     */
    public String getSymptom() {
        return Symptom;
    }

    /**
     * @param Symptom the Symptom to set
     */
    public void setSymptom(String Symptom) {
        this.Symptom = Symptom;
    }

    /**
     * @return the Medicine
     */
    public String getMedicine() {
        return Medicine;
    }

    /**
     * @param Medicine the Medicine to set
     */
    public void setMedicine(String Medicine) {
        this.Medicine = Medicine;
    }

    /**
     * @return the ReactiveMedicine
     */
    public String getReactiveMedicine() {
        return ReactiveMedicine;
    }

    /**
     * @param ReactiveMedicine the ReactiveMedicine to set
     */
    public void setReactiveMedicine(String ReactiveMedicine) {
        this.ReactiveMedicine = ReactiveMedicine;
    }

    /**
     * @return the Action
     */
    public String getAction() {
        return Action;
    }

    /**
     * @param Action the Action to set
     */
    public void setAction(String Action) {
        this.Action = Action;
    }



}

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
public class statics_disease_list {
    private String id;
    private String DiseaseName;
    private String DiseaseType;
    private String Action;
   

    public statics_disease_list(String id, String DiseaseName, String DiseaseType) {
        this.id = id;
        this.DiseaseName = DiseaseName;
        this.DiseaseType = DiseaseType;
    
    }
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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

   
    
}

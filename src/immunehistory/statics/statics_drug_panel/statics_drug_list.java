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
public class statics_drug_list {
    private String id;
    private String MedicineGenericName;
    private String MedicineType;
   private String Action;

    public statics_drug_list(String id, String MedicineGenericName) {
        this.id = id;
        this.MedicineGenericName = MedicineGenericName;
       
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
     * @return the MedicineGenericName
     */
    public String getMedicineGenericName() {
        return MedicineGenericName;
    }

    /**
     * @param MedicineGenericName the MedicineGenericName to set
     */
    public void setMedicineGenericName(String MedicineGenericName) {
        this.MedicineGenericName = MedicineGenericName;
    }

    /**
     * @return the MedicineType
     */
    public String getMedicineType() {
        return MedicineType;
    }

    /**
     * @param MedicineType the MedicineType to set
     */
    public void setMedicineType(String MedicineType) {
        this.MedicineType = MedicineType;
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

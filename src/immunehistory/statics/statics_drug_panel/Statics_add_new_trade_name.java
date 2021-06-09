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
public class Statics_add_new_trade_name {
    
     private String GenericId;
    private String GenericName;

    public Statics_add_new_trade_name(String GenericId, String GenericName) {
        this.GenericId = GenericId;
        this.GenericName = GenericName;
    }

    /**
     * @return the GenericId
     */
    public String getGenericId() {
        return GenericId;
    }

    /**
     * @param GenericId the GenericId to set
     */
    public void setGenericId(String GenericId) {
        this.GenericId = GenericId;
    }

    /**
     * @return the GenericName
     */
    public String getGenericName() {
        return GenericName;
    }

    /**
     * @param GenericName the GenericName to set
     */
    public void setGenericName(String GenericName) {
        this.GenericName = GenericName;
    }
   
    
    
    
}

package us.ttyl.chance.domain;



/**
 * TMaritalStatus generated by MyEclipse - Hibernate Tools
 */

public class TMaritalStatus  implements java.io.Serializable {


    // Fields    

     private Integer maritalStatusId;
     private String maritalStatusDesc;


    // Constructors

    /** default constructor */
    public TMaritalStatus() {
    }

    
    /** full constructor */
    public TMaritalStatus(String maritalStatusDesc) {
        this.maritalStatusDesc = maritalStatusDesc;
    }

   
    // Property accessors

    public Integer getMaritalStatusId() {
        return this.maritalStatusId;
    }
    
    public void setMaritalStatusId(Integer maritalStatusId) {
        this.maritalStatusId = maritalStatusId;
    }

    public String getMaritalStatusDesc() {
        return this.maritalStatusDesc;
    }
    
    public void setMaritalStatusDesc(String maritalStatusDesc) {
        this.maritalStatusDesc = maritalStatusDesc;
    }
   








}
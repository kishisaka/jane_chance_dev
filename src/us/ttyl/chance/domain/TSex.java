package us.ttyl.chance.domain;



/**
 * TSex generated by MyEclipse - Hibernate Tools
 */

public class TSex  implements java.io.Serializable {


    // Fields    

     private Integer sexId;
     private String sexDesc;


    // Constructors

    /** default constructor */
    public TSex() {
    }

    
    /** full constructor */
    public TSex(String sexDesc) {
        this.sexDesc = sexDesc;
    }

   
    // Property accessors

    public Integer getSexId() {
        return this.sexId;
    }
    
    public void setSexId(Integer sexId) {
        this.sexId = sexId;
    }

    public String getSexDesc() {
        return this.sexDesc;
    }
    
    public void setSexDesc(String sexDesc) {
        this.sexDesc = sexDesc;
    }
   








}
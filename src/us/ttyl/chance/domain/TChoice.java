package us.ttyl.chance.domain;



/**
 * TChoice generated by MyEclipse - Hibernate Tools
 */

public class TChoice  implements java.io.Serializable {


    // Fields    

     private Integer choiceId;
     private String choiceDesc;


    // Constructors

    /** default constructor */
    public TChoice() {
    }

    
    /** full constructor */
    public TChoice(String choiceDesc) {
        this.choiceDesc = choiceDesc;
    }

   
    // Property accessors

    public Integer getChoiceId() {
        return this.choiceId;
    }
    
    public void setChoiceId(Integer choiceId) {
        this.choiceId = choiceId;
    }

    public String getChoiceDesc() {
        return this.choiceDesc;
    }
    
    public void setChoiceDesc(String choiceDesc) {
        this.choiceDesc = choiceDesc;
    }
   








}
package us.ttyl.chance.domain;



/**
 * TIdealBodytypeId generated by MyEclipse - Hibernate Tools
 */

public class TIdealBodytypeId  implements java.io.Serializable {


    // Fields    

     private Integer userId;
     private Integer idealBodytypeId;
     private Integer id;


    // Constructors

    public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	/** default constructor */
    public TIdealBodytypeId() {
    }

    
    /** full constructor */
    public TIdealBodytypeId(Integer userId, Integer idealBodytypeId) {
        this.userId = userId;
        this.idealBodytypeId = idealBodytypeId;
    }

   
    // Property accessors

    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIdealBodytypeId() {
        return this.idealBodytypeId;
    }
    
    public void setIdealBodytypeId(Integer idealBodytypeId) {
        this.idealBodytypeId = idealBodytypeId;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof TIdealBodytypeId) ) return false;
		 TIdealBodytypeId castOther = ( TIdealBodytypeId ) other; 
         
		 return ( (this.getUserId()==castOther.getUserId()) || ( this.getUserId()!=null && castOther.getUserId()!=null && this.getUserId().equals(castOther.getUserId()) ) )
 && ( (this.getIdealBodytypeId()==castOther.getIdealBodytypeId()) || ( this.getIdealBodytypeId()!=null && castOther.getIdealBodytypeId()!=null && this.getIdealBodytypeId().equals(castOther.getIdealBodytypeId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUserId() == null ? 0 : this.getUserId().hashCode() );
         result = 37 * result + ( getIdealBodytypeId() == null ? 0 : this.getIdealBodytypeId().hashCode() );
         return result;
   }   





}
package us.ttyl.chance.domain;

public class Distance 
{
	Integer distanceId;
	Integer distanceAmt;
	Zipcode zipcodeIdTarget; 
	Zipcode zipcodeIdOrigin;
	
	public Integer getDistanceAmt() {		
		return distanceAmt;
	}
	
	public Integer getDistanceId() {
		return distanceId;
	}

	public void setDistanceId(Integer distanceId) {
		this.distanceId = distanceId;
	}

	public void setDistanceAmt(Integer distanceAmt) {
		this.distanceAmt = distanceAmt;
	}
	
	public Zipcode getZipcodeIdOrigin() {
		return zipcodeIdOrigin;
	}
	
	public void setZipcodeIdOrigin(Zipcode zipcodeIdOrigin) {
		this.zipcodeIdOrigin = zipcodeIdOrigin;
	}
	
	public Zipcode getZipcodeIdTarget() {
		return zipcodeIdTarget;
	}
	
	public void setZipcodeIdTarget(Zipcode zipcodeIdTarget) {
		this.zipcodeIdTarget = zipcodeIdTarget;
	}	
	
	public String toString()
	{
		return distanceId.intValue() + ":" + distanceAmt.intValue() + ":" + zipcodeIdTarget.getZipcode().intValue() + ":" + zipcodeIdOrigin.getZipcode().intValue(); 
	}
}

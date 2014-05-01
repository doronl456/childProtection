package com.androidhive.pushnotifications;

final public class localDB
{
	private static localDB instance = null;
	private final static Integer myMutex = new Integer(1);
	private double longtitudeOut;
	private double latitudeOut;
//	private double longtitudeIn;
//	private double latitudeIn;
//	private double speedlimit;
//	private String phoneOut;
//	private String phoneIn;
//	private String phoneSMS;
//	private String phoneSpeed;
//	private String key;
//	double radiusIn;
	double radiusOut;
//	private String searchName;
	private boolean stepOutFlag;
//	private boolean stepInFlag;
//	private boolean speedFlag;
//	private boolean smsFlag;
	private boolean isContorel;
	private String userName;
	private String userPassword;
	
//	public String getSearchName() 
//	{
//		return searchName;
//	}
//
//	public void setSearchName(String searchName) 
//	{
//		this.searchName = searchName;
//	}
//
//	public boolean isSmsFlag()
//	{
//		return smsFlag;
//	}
//
//	public void setSmsFlag(boolean smsFlag) {
//		this.smsFlag = smsFlag;
//	}
//
//	public boolean isSpeedFlag() {
//		return speedFlag;
//	}
//
//	public void setSpeedFlag(boolean speedFlag) {
//		this.speedFlag = speedFlag;
//	}
//
//	public boolean isStepInFlag() {
//		return stepInFlag;
//	}
//
//	public void setStepInFlag(boolean stepInFlag) {
//		this.stepInFlag = stepInFlag;
//	}
	
	public boolean isContorel() {
		return isContorel;
	}

	public void setContorel(boolean isContorel) {
		this.isContorel = isContorel;
	}

	public boolean isStepOutFlag() 
	{
		return stepOutFlag;
	}

	public void setStepOutFlag(boolean stepOutFlag) {
		
		this.stepOutFlag = stepOutFlag;
	}
		
	public double getLongtitudeOut() {
		return longtitudeOut;
	}

	public void setLongtitudeOut(double longtitudeOut) {
		this.longtitudeOut = longtitudeOut;
	}

	public double getLatitudeOut() {
		return latitudeOut;
	}

	public void setLatitudeOut(double latitudeOut) {
		this.latitudeOut = latitudeOut;
	}
//
//	public double getLongtitudeIn() {
//		return longtitudeIn;
//	}
//
//	public void setLongtitudeIn(double longtitudeIn) {
//		this.longtitudeIn = longtitudeIn;
//	}
//
//	public double getLatitudeIn() {
//		return latitudeIn;
//	}
//
//	public void setLatitudeIn(double latitudeIn) {
//		this.latitudeIn = latitudeIn;
//	}
//
//	public double getSpeedlimit() {
//		return speedlimit;
//	}
//
//	public void setSpeedlimit(double speedlimit) {
//		this.speedlimit = speedlimit;
//	}
//
//	public String getPhoneOut() {
//		return phoneOut;
//	}
//
//	public void setPhoneOut(String phoneOut) {
//		this.phoneOut = phoneOut;
//	}
//
//	public String getPhoneIn() {
//		return phoneIn;
//	}
//
//	public void setPhoneIn(String phoneIn) {
//		this.phoneIn = phoneIn;
//	}
//
//	public String getPhoneSMS() {
//		return phoneSMS;
//	}
//
//	public void setPhoneSMS(String phoneSMS) {
//		this.phoneSMS = phoneSMS;
//	}
//
//	public String getPhoneSpeed() {
//		return phoneSpeed;
//	}
//
//	public void setPhoneSpeed(String phoneSpeed) {
//		this.phoneSpeed = phoneSpeed;
//	}
//
//	public String getKey() {
//		return key;
//	}
//
//	public void setKey(String key) {
//		this.key = key;
//	}
//
//	public double getRadiusIn() {
//		return radiusIn;
//	}
//
//	public void setRadiusIn(double radiusIn) {
//		this.radiusIn = radiusIn;
//	}

	public double getRadiusOut() {
		return radiusOut;
	}

	public void setRadiusOut(double radiusOut) {
		this.radiusOut = radiusOut;
	}
	
	private localDB()
	{
//		this.searchName="";
//		this.radiusIn=0;
		this.radiusOut=0;
//		this.phoneIn="";
//		this.phoneOut="";
		this.isContorel=false;
		
	}
	public static localDB getInstance() 
	{
		if (instance == null)
		{
			synchronized (myMutex)
			{
				if (instance==null)
				{
					
					instance=new localDB();
				}
			}
		}
		return instance;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}

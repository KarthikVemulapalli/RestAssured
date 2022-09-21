package POJO_DeSerialization;

public class RSGoogleOAuth2_Pojo {
	/* Refer ComplexJsonParse_PojoClass.json file in 'src/test/resources/JsonFiles' path.
	 * Create private variables for all the API Parameters
	 */
	
	/*
	 * Follow the chain ComplexJson_Pojo -> Courses -> (WebAutomation, Api, Mobile)
	 */
	
	private String instructor;
	private String url;
	private String services;
	private String expertise;
	private Courses courses;     //As Courses is a nested Json, we need to create a class for it.
	private String linkedIn;
	
	
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public Courses getCourses() {
		return courses;
	}
	public void setCourses(Courses courses) {
		this.courses = courses;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	
}

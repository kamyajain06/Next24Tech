// Experience.java
public class Experience {
    private String jobTitle;
    private String company;
    private String startDate;
    private String endDate;
    private String responsibilities;

    public Experience(String jobTitle, String company, String startDate, String endDate, String responsibilities) {
        this.jobTitle = jobTitle;
        this.company = company;
        this.startDate = startDate;
        this.endDate = endDate;
        this.responsibilities = responsibilities;
    }

    // Getters
    public String getJobTitle() { return jobTitle; }
    public String getCompany() { return company; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
    public String getResponsibilities() { return responsibilities; }
}

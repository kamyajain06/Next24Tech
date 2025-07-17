// Education.java
public class Education {
    private String degree;
    private String institution;
    private String graduationYear;

    public Education(String degree, String institution, String graduationYear) {
        this.degree = degree;
        this.institution = institution;
        this.graduationYear = graduationYear;
    }

    // Getters
    public String getDegree() { return degree; }
    public String getInstitution() { return institution; }
    public String getGraduationYear() { return graduationYear; }
}

// Resume.java
import java.util.ArrayList;
import java.util.List;

public class Resume {
    private String fullName;
    private String email;
    private String phone;
    private String linkedIn;
    private String gitHub;
    private String summary;
    private List<Education> educationList;
    private List<Experience> experienceList;
    private List<String> skills;

    public Resume() {
        this.educationList = new ArrayList<Education>();
        this.experienceList = new ArrayList<Experience>();
        this.skills = new ArrayList<String>();
    }

    // --- Getters and Setters for Personal Information ---
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLinkedIn() {
        return linkedIn;
    }
    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getGitHub() {
        return gitHub;
    }
    public void setGitHub(String gitHub) {
        this.gitHub = gitHub;
    }

    // --- Getter and Setter for Summary ---
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }

    // --- Methods for Education List ---
    public List<Education> getEducationList() {
        return educationList;
    }
    public void addEducation(Education education) {
        this.educationList.add(education);
    }

    // --- Methods for Experience List ---
    public List<Experience> getExperienceList() {
        return experienceList;
    }
    public void addExperience(Experience experience) {
        this.experienceList.add(experience);
    }

    // --- Methods for Skills List ---
    public List<String> getSkills() {
        return skills;
    }
    public void addSkill(String skill) {
        this.skills.add(skill);
    }

    // --- Method to generate HTML content for the resume ---
    public String toHtmlString() {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n");
        html.append("<html lang=\"en\">\n");
        html.append("<head>\n");
        html.append("    <meta charset=\"UTF-8\">\n");
        html.append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
        html.append("    <title>").append(fullName).append("'s Resume</title>\n");
        html.append("    <link href=\"https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600;700&display=swap\" rel=\"stylesheet\">\n"); // Google Font: Inter
        html.append("    <style>\n");
        html.append("        body {\n");
        html.append("            font-family: 'Inter', sans-serif;\n");
        html.append("            line-height: 1.6;\n");
        html.append("            margin: 0;\n");
        html.append("            padding: 20px;\n");
        html.append("            background-color: #f4f7f6;\n"); /* Light grey background */
        html.append("            color: #333;\n");
        html.append("            display: flex;\n");
        html.append("            justify-content: center;\n");
        html.append("        }\n");
        html.append("        .container {\n");
        html.append("            width: 100%;\n");
        html.append("            max-width: 800px;\n");
        html.append("            background-color: #ffffff;\n"); /* White resume background */
        html.append("            padding: 40px;\n");
        html.append("            border-radius: 10px;\n");
        html.append("            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);\n");
        html.append("        }\n");
        html.append("        h1, h2, h3 {\n");
        html.append("            color: #2c3e50;\n"); /* Dark blue headings */
        html.append("            margin-top: 20px;\n");
        html.append("            margin-bottom: 10px;\n");
        html.append("        }\n");
        html.append("        h1 {\n");
        html.append("            text-align: center;\n");
        html.append("            font-size: 2.5em;\n");
        html.append("            margin-bottom: 5px;\n");
        html.append("        }\n");
        html.append("        .contact-info {\n");
        html.append("            text-align: center;\n");
        html.append("            margin-bottom: 30px;\n");
        html.append("            color: #555;\n");
        html.append("            font-size: 0.9em;\n");
        html.append("        }\n");
        html.append("        .contact-info span {\n");
        html.append("            margin: 0 10px;\n");
        html.append("        }\n");
        html.append("        .section {\n");
        html.append("            margin-bottom: 30px;\n");
        html.append("            padding-bottom: 20px;\n");
        html.append("            border-bottom: 1px solid #eee;\n");
        html.append("        }\n");
        html.append("        .section:last-child {\n");
        html.append("            border-bottom: none;\n");
        html.append("            margin-bottom: 0;\n");
        html.append("            padding-bottom: 0;\n");
        html.append("        }\n");
        html.append("        .experience-item, .education-item {\n");
        html.append("            margin-bottom: 15px;\n");
        html.append("        }\n");
        html.append("        .experience-title, .education-degree {\n");
        html.append("            font-weight: 600;\n");
        html.append("            color: #34495e;\n"); /* Slightly lighter dark blue */
        html.append("        }\n");
        html.append("        .company-name, .institution-name {\n");
        html.append("            font-style: italic;\n");
        html.append("            color: #7f8c8d;\n"); /* Grey */
        html.append("        }\n");
        html.append("        .duration {\n");
        html.append("            float: right;\n");
        html.append("            color: #7f8c8d;\n");
        html.append("        }\n");
        html.append("        ul {\n");
        html.append("            list-style-type: disc;\n");
        html.append("            margin-left: 20px;\n");
        html.append("            padding-left: 0;\n");
        html.append("        }\n");
        html.append("        ul li {\n");
        html.append("            margin-bottom: 5px;\n");
        html.append("        }\n");
        html.append("        .skills-list {\n");
        html.append("            display: flex;\n");
        html.append("            flex-wrap: wrap;\n");
        html.append("            gap: 8px;\n");
        html.append("        }\n");
        html.append("        .skill-tag {\n");
        html.append("            background-color: #ecf0f1;\n"); /* Light grey skill tag */
            html.append("            color: #2c3e50;\n");
        html.append("            padding: 5px 12px;\n");
        html.append("            border-radius: 5px;\n");
        html.append("            font-size: 0.9em;\n");
        html.append("        }\n");
        html.append("    </style>\n");
        html.append("</head>\n");
        html.append("<body>\n");
        html.append("    <div class=\"container\">\n");

        // Personal Info
        html.append("        <h1>").append(fullName).append("</h1>\n");
        html.append("        <div class=\"contact-info\">\n");
        html.append("            <span>").append(phone).append("</span> |\n");
        html.append("            <span>").append(email).append("</span>\n");
        if (linkedIn != null && !linkedIn.equalsIgnoreCase("N/A")) {
            html.append("            | <span>LinkedIn: <a href=\"").append(linkedIn).append("\" target=\"_blank\">").append(linkedIn).append("</a></span>\n");
        }
        if (gitHub != null && !gitHub.equalsIgnoreCase("N/A")) {
            html.append("            | <span>GitHub: <a href=\"").append(gitHub).append("\" target=\"_blank\">").append(gitHub).append("</a></span>\n");
        }
        html.append("        </div>\n");

        // Summary
        if (summary != null && !summary.isEmpty()) {
            html.append("        <div class=\"section\">\n");
            html.append("            <h2>Summary</h2>\n");
            html.append("            <p>").append(summary.replace("\n", "<br>")).append("</p>\n"); // Replace newlines with <br> for HTML
            html.append("        </div>\n");
        }

        // Education
        if (!educationList.isEmpty()) {
            html.append("        <div class=\"section\">\n");
            html.append("            <h2>Education</h2>\n");
            for (Education edu : educationList) {
                html.append("            <div class=\"education-item\">\n");
                html.append("                <span class=\"education-degree\">").append(edu.getDegree()).append("</span>, ");
                html.append("                <span class=\"institution-name\">").append(edu.getInstitution()).append("</span>\n");
                html.append("                <span class=\"duration\">").append(edu.getGraduationYear()).append("</span>\n");
                html.append("            </div>\n");
            }
            html.append("        </div>\n");
        }

        // Experience
        if (!experienceList.isEmpty()) {
            html.append("        <div class=\"section\">\n");
            html.append("            <h2>Work Experience</h2>\n");
            for (Experience exp : experienceList) {
                html.append("            <div class=\"experience-item\">\n");
                html.append("                <span class=\"experience-title\">").append(exp.getJobTitle()).append("</span> at ");
                html.append("                <span class=\"company-name\">").append(exp.getCompany()).append("</span>\n");
                html.append("                <span class=\"duration\">").append(exp.getStartDate()).append(" - ").append(exp.getEndDate()).append("</span>\n");
                html.append("                <ul>\n");
                // Split responsibilities by newline and create list items
                for (String resp : exp.getResponsibilities().split("\n")) {
                    if (!resp.trim().isEmpty()) {
                        html.append("                    <li>").append(resp.replaceFirst("^- ", "")).append("</li>\n"); // Remove leading '- ' if present
                    }
                }
                html.append("                </ul>\n");
                html.append("            </div>\n");
            }
            html.append("        </div>\n");
        }

        // Skills
        if (!skills.isEmpty()) {
            html.append("        <div class=\"section\">\n");
            html.append("            <h2>Skills</h2>\n");
            html.append("            <div class=\"skills-list\">\n");
            for (String skill : skills) {
                html.append("                <span class=\"skill-tag\">").append(skill).append("</span>\n");
            }
            html.append("            </div>\n");
            html.append("        </div>\n");
        }

        html.append("    </div>\n");
        html.append("</body>\n");
        html.append("</html>\n");

        return html.toString();
    }

    // The displayResume method is no longer used for console output, but kept for reference
    // if you ever want to revert to console-only display.
    public void displayResume() {
        System.out.println("==================================================");
        System.out.println("                " + fullName.toUpperCase());
        System.out.println("==================================================");
        System.out.println("Phone: " + phone + " | Email: " + email);
        if (linkedIn != null && !linkedIn.equalsIgnoreCase("N/A")) {
            System.out.println("LinkedIn: " + linkedIn);
        }
        if (gitHub != null && !gitHub.equalsIgnoreCase("N/A")) {
            System.out.println("GitHub: " + gitHub);
        }
        System.out.println("\n--- Summary ---");
        System.out.println(summary);

        System.out.println("\n--- Education ---");
        if (educationList.isEmpty()) {
            System.out.println("No education details provided.");
        } else {
            for (Education edu : educationList) {
                System.out.println("- " + edu.getDegree() + " from " + edu.getInstitution() + ", " + edu.getGraduationYear());
            }
        }

        System.out.println("\n--- Work Experience ---");
        if (experienceList.isEmpty()) {
            System.out.println("No work experience details provided.");
        } else {
            for (Experience exp : experienceList) {
                System.out.println("\nJob Title: " + exp.getJobTitle());
                System.out.println("Company: " + exp.getCompany());
                System.out.println("Duration: " + exp.getStartDate() + " - " + exp.getEndDate());
                System.out.println("Responsibilities:");
                System.out.println(exp.getResponsibilities());
            }
        }

        System.out.println("\n--- Skills ---");
        if (skills.isEmpty()) {
            System.out.println("No skills provided.");
        } else {
            StringBuilder skillsBuilder = new StringBuilder();
            for (int i = 0; i < skills.size(); i++) {
                skillsBuilder.append(skills.get(i));
                if (i < skills.size() - 1) {
                    skillsBuilder.append(", ");
                }
            }
            System.out.println(skillsBuilder.toString());
        }
    }
}

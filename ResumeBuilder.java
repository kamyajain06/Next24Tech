import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Desktop; // Required to open files in default browser
import java.util.Scanner;

public class ResumeBuilder {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Resume resume = new Resume(); // Create an instance of the Resume class

        System.out.println("Welcome to the Java Console Resume Builder! 🚀");
        System.out.println("Let's build your professional resume step by step.");

        // Personal Information
        System.out.println("\n--- Personal Information ---");
        System.out.print("Enter your Full Name: ");
        resume.setFullName(scanner.nextLine());

        System.out.print("Enter your Email Address: ");
        resume.setEmail(scanner.nextLine());

        System.out.print("Enter your Phone Number: ");
        resume.setPhone(scanner.nextLine());

        System.out.print("Enter your LinkedIn Profile URL (or N/A): ");
        resume.setLinkedIn(scanner.nextLine());

        System.out.print("Enter your GitHub Profile URL (or N/A): ");
        resume.setGitHub(scanner.nextLine());

        // Summary/Objective
        System.out.println("\n--- Summary/Objective ---");
        System.out.println("Enter a brief professional summary or career objective (enter an empty line to finish):");
        StringBuilder summary = new StringBuilder();
        String line;
        while (true) {
            line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }
            summary.append(line).append("\n");
        }
        resume.setSummary(summary.toString().trim());

        // Education
        System.out.println("\n--- Education ---");
        System.out.print("How many educational experiences do you want to add? ");
        int numEducation = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numEducation; i++) {
            System.out.println("\nEducation #" + (i + 1));
            System.out.print("Degree/Qualification: ");
            String degree = scanner.nextLine();
            System.out.print("University/Institution: ");
            String institution = scanner.nextLine();
            System.out.print("Graduation Year (e.g., 2023): ");
            String year = scanner.nextLine();
            resume.addEducation(new Education(degree, institution, year));
        }

        // Experience
        System.out.println("\n--- Work Experience ---");
        System.out.print("How many work experiences do you want to add? ");
        int numExperience = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numExperience; i++) {
            System.out.println("\nExperience #" + (i + 1));
            System.out.print("Job Title: ");
            String title = scanner.nextLine();
            System.out.print("Company: ");
            String company = scanner.nextLine();
            System.out.print("Start Date (e.g., Jan 2020): ");
            String startDate = scanner.nextLine();
            System.out.print("End Date (e.g., Dec 2022 or Present): ");
            String endDate = scanner.nextLine();
            System.out.println("Responsibilities/Achievements (enter an empty line to finish each point):");
            StringBuilder responsibilities = new StringBuilder();
            String respLine;
            while (true) {
                respLine = scanner.nextLine();
                if (respLine.isEmpty()) {
                    break;
                }
                responsibilities.append("- ").append(respLine).append("\n");
            }
            resume.addExperience(new Experience(title, company, startDate, endDate, responsibilities.toString().trim()));
        }

        // Skills
        System.out.println("\n--- Skills ---");
        System.out.println("Enter your skills, separated by commas (e.g., Java, Python, SQL, Agile):");
        String skillsInput = scanner.nextLine();
        for (String skill : skillsInput.split(",")) {
            resume.addSkill(skill.trim());
        }

        scanner.close(); // Close the scanner to release system resources

        // --- Generate and Display HTML Resume ---
        System.out.println("\n\n--- Generating HTML Resume ---");
        String htmlContent = resume.toHtmlString(); // Get HTML content from Resume object
        File htmlFile = new File("resume.html"); // Define the output file name

        FileWriter writer = null; // Declare FileWriter outside try block for finally access
        try {
            writer = new FileWriter(htmlFile);
            writer.write(htmlContent); // Write HTML content to the file
            System.out.println("Resume saved to: " + htmlFile.getAbsolutePath());

            // Attempt to open the HTML file in the default web browser
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(htmlFile.toURI());
                System.out.println("Opening resume in your default browser...");
            } else {
                System.out.println("Desktop operations not supported. Please open 'resume.html' manually in your browser.");
            }

        } catch (IOException e) { // Catch block for handling potential IO errors
            System.err.println("Error writing or opening HTML file: " + e.getMessage());
        } finally {
            // Ensure the writer is closed even if an exception occurs
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.err.println("Error closing FileWriter: " + e.getMessage());
                }
            }
        }

        System.out.println("\n------------------------------------");
        System.out.println("Thank you for using the Resume Builder!");
    }
}

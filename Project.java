
package project;

import java.util.*;

class Node {
    Job job;
    Node next;

    public Node(Job job) {
        this.job = job;
        this.next = null;
    }
}

class Stack {
    private Node top;

    public Stack() {
        top = null;
    }
 
    // Push job to the stack
    public void push(Job job) {
        Node newNode = new Node(job);
        newNode.next = top;
        top = newNode;
    }

    // Pop job from the stack
    public Job pop() {
        if (top == null) {
            System.out.println("Stack is empty.");
            return null;
        }
        Job job = top.job;
        top = top.next;
        return job;
    }

    // Peek the top job of the stack
    public Job peek() {
        return (top == null) ? null : top.job;
    }

    // Check if stack is empty
    public boolean isEmpty() {
        return top == null;
    }
}



//      professional 




class Professional {
    String name, profession, contactDetails, password;
    int experience;
    boolean mentorshipAvailable;

    public Professional(String name, String profession, int experience, String contactDetails, String password) {
        this.name = name;
        this.profession = profession;
        this.experience = experience;
        this.contactDetails = contactDetails;
        this.password = password;
        this.mentorshipAvailable = false;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nProfession: " + profession + "\nExperience: " + experience + " years\nContact: " + contactDetails + 
               "\nMentorship Available: " + (mentorshipAvailable ? "Yes" : "No");
    }
}

 class Job {
    String title, company, description, skills;
    int salary;

    public Job(String title, String company, String description, String skills, int salary) {
        this.title = title;
        this.company = company;
        this.description = description;
        this.skills = skills;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Job Title: " + title + "\nCompany: " + company + "\nDescription: " + description +
               "\nRequired Skills: " + skills + "\nSalary: " + salary;
    }
}



//                   student



class Student {
    String name, email, password;
    Stack viewedJobsStack = new Stack();
    Stack appliedJobsStack = new Stack();
    List<Professional> mentors = new ArrayList<>();

    public Student(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}

public class Project {
    static HashMap<String, Student> studentsMap = new HashMap<>();
    static HashMap<String, Professional> professionalsMap = new HashMap<>();
    static ArrayList<Job> allJobs = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nCareer Guidance System");
            System.out.println("1. Professional (Admin)");
            System.out.println("2. Student");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    professionalMenu();
                    break;
                case 2:
                    studentMenu();
                    break;
                case 3:
                    System.out.println("Exiting system...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    
    
    // Professional/Admin Menu
    
    
    public static void professionalMenu() {
        while (true) {
            System.out.println("\nProfessional (Admin) Menu");
            System.out.println("1. Sign-Up");
            System.out.println("2. Login");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    signUpProfessional();
                    break;
                case 2:
                    loginProfessional();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    
    
    public static void signUpProfessional() {
        System.out.println("\nSign-Up (Professional)");
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter your profession: ");
        String profession = sc.nextLine();
        System.out.print("Enter your experience (in years): ");
        int experience = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Enter your contact details: ");
        String contactDetails = sc.nextLine();
        System.out.print("Set a password: ");
        String password = sc.nextLine();

        professionalsMap.put(name, new Professional(name, profession, experience, contactDetails, password));
        System.out.println("Sign-Up successful! You can now log in.");
    }

    
    public static void loginProfessional() {
        System.out.println("\nLogin (Professional)");
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        Professional loggedInProfessional = professionalsMap.get(name);
        if (loggedInProfessional != null && loggedInProfessional.password.equals(password)) {
            System.out.println("Login successful! Welcome, " + loggedInProfessional.name + ".");
            adminOptions(loggedInProfessional);
        } else {
            System.out.println("Invalid name or password. Please try again.");
        }
    }

    
    public static void adminOptions(Professional loggedInProfessional) {
        while (true) {
            System.out.println("\nAdmin/Professional Options");
            System.out.println("1. Post a Job");
            System.out.println("2. View All Jobs");
            System.out.println("3. Update a Job");
            System.out.println("4. Delete a Job");
            System.out.println("5. Update Availability for Mentorship");
            System.out.println("6. Exit to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    postJob(loggedInProfessional);
                    break;
                case 2:
                    viewJobs();
                    break;
                case 3:
                    updateJob();
                    break;
                case 4:
                    deleteJob();
                    break;
                case 5:
                    updateMentorshipAvailability(loggedInProfessional);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    
    // 1.post job 
    
    
    public static void postJob(Professional loggedInProfessional) {
        System.out.print("Enter job title: ");
        String title = sc.nextLine();
        System.out.print("Enter company name: ");
        String company = sc.nextLine();
        System.out.print("Enter job description: ");
        String description = sc.nextLine();
        System.out.print("Enter required skills: ");
        String skills = sc.nextLine();
        System.out.print("Enter salary: ");
        int salary = sc.nextInt();
        sc.nextLine(); // Consume newline

        allJobs.add(new Job(title, company, description, skills, salary));
        System.out.println("Job posted successfully!");
    }

    
    // 2.view job
    
    public static void viewJobs() {
        if (allJobs.isEmpty()) {
            System.out.println("No jobs available.");
        } else {
            System.out.println("All Jobs:");
            for (int i = 0; i < allJobs.size(); i++) {
                System.out.println((i + 1) + ". " + allJobs.get(i));
                System.out.println("-----------------------");
            }
        }
    }

    
    //  3.update job
    
    public static void updateJob() {
        viewJobs();
        if (!allJobs.isEmpty()) {
            System.out.print("Enter the job number to update: ");
            int jobNumber = sc.nextInt();
            sc.nextLine(); // Consume newline
            if (jobNumber > 0 && jobNumber <= allJobs.size()) {
                Job job = allJobs.get(jobNumber - 1);
                System.out.print("Enter new job title: ");
                job.title = sc.nextLine();
                System.out.print("Enter new company name: ");
                job.company = sc.nextLine();
                System.out.print("Enter new job description: ");
                job.description = sc.nextLine();
                System.out.print("Enter new required skills: ");
                job.skills = sc.nextLine();
                System.out.print("Enter new salary: ");
                job.salary = sc.nextInt();
                sc.nextLine(); // Consume newline
                System.out.println("Job updated successfully!");
            } else {
                System.out.println("Invalid job number.");
            }
        }
    }

    // 4.delete 
    
    
    public static void deleteJob() {
        viewJobs();
        if (!allJobs.isEmpty()) {
            System.out.print("Enter the job number to delete: ");
            int jobNumber = sc.nextInt();
            sc.nextLine(); // Consume newline
            if (jobNumber > 0 && jobNumber <= allJobs.size()) {
                allJobs.remove(jobNumber - 1);
                System.out.println("Job deleted successfully!");
            } else {
                System.out.println("Invalid job number.");
            }
        }
    }

    
    // 5.mentorship
    
    
    public static void updateMentorshipAvailability(Professional loggedInProfessional) {
        System.out.print("Would you like to enable mentorship? (yes/no): ");
        String response = sc.nextLine();
        loggedInProfessional.mentorshipAvailable = response.equalsIgnoreCase("yes");
        System.out.println("Mentorship availability updated.");
    }

    
    
    
    
   
    // Student Menu
    
    
    public static void studentMenu() {
        while (true) {
            System.out.println("\nStudent Menu");
            System.out.println("1. Sign-Up");
            System.out.println("2. Login");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    signUpStudent();
                    break;
                case 2:
                    loginStudent();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void signUpStudent() {
        System.out.println("\nSign-Up (Student)");
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter your email: ");
        String email = sc.nextLine();
        System.out.print("Set a password: ");
        String password = sc.nextLine();

        studentsMap.put(email, new Student(name, email, password));
        System.out.println("Sign-Up successful! You can now log in.");
    }

    public static void loginStudent() {
        System.out.println("\nLogin (Student)");
        System.out.print("Enter your email: ");
        String email = sc.nextLine();
        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        Student loggedInStudent = studentsMap.get(email);
        if (loggedInStudent != null && loggedInStudent.password.equals(password)) {
            System.out.println("Login successful! Welcome, " + loggedInStudent.name + ".");
            studentOptions(loggedInStudent);
        } else {
            System.out.println("Invalid email or password. Please try again.");
        }
    }

    public static void studentOptions(Student loggedInStudent) {
        while (true) {
            System.out.println("\nStudent Options");
            System.out.println("1. Explore Career Paths");
            System.out.println("2. Take a Test");
            System.out.println("3. Mentorship");
            System.out.println("4. Apply for Job");
            System.out.println("5. Exit to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    exploreCareers();
                    break;
                    
                case 2:
                    takeTest();
                    break;
                    
                case 3:
             
                    mentorship(loggedInStudent);
                    break;
                    
                case 4:
                    applyForJob(); 
                    break;
                    
                case 5:
                    return;
                    
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void exploreCareers() {
        Scanner sc = new Scanner(System.in); // Adding the Scanner instance
        System.out.println("\nExploring Career Paths:");
        System.out.println("1. Software Development");
        System.out.println("2. Graphic Design");
        System.out.println("3. Data Science");
        System.out.println("4. Cybersecurity");
        System.out.println("5. Artificial Intelligence");
        System.out.println("6. UI/UX Design");
        System.out.println("7. Game Development");
        System.out.println("8. Cloud Computing");
        System.out.println("9. Digital Marketing");
        System.out.println("10. Blockchain");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.println("Software Development: Requires skills in programming languages like Java, C++, and Python.");
                break;
            case 2:
                System.out.println("Graphic Design: Requires skills in Adobe Photoshop, Illustrator, and creativity.");
                break;
            case 3:
                System.out.println("Data Science: Requires skills in Python, R, and data analysis.");
                break;
            case 4:
                System.out.println("Cybersecurity: Requires knowledge of network security, encryption, and ethical hacking.");
                break;
            case 5:
                System.out.println("Artificial Intelligence: Requires knowledge of machine learning algorithms, Python, and data science.");
                break;
            case 6:
                System.out.println("UI/UX Design: Requires skills in user research, wireframing, prototyping, and tools like Sketch and Figma.");
                break;
            case 7:
                System.out.println("Game Development: Requires skills in game engines like Unity or Unreal Engine, and programming languages like C++ and C#.");
                break;
            case 8:
                System.out.println("Cloud Computing: Requires knowledge of cloud services like AWS, Azure, or Google Cloud, and skills in virtualization and networking.");
                break;
            case 9:
                System.out.println("Digital Marketing: Requires skills in SEO, content creation, social media marketing, and tools like Google Analytics.");
                break;
            case 10:
                System.out.println("Blockchain: Requires knowledge of blockchain technology, smart contracts, and programming languages like Solidity and Ethereum.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }


    public static void takeTest() {
        System.out.println("\nTake Test:");
        System.out.println("Select your field of interest by entering the corresponding number:");
        System.out.println("1. Software Development");
        System.out.println("2. Graphic Design");
        System.out.println("3. Data Science");
        System.out.println("4. Cybersecurity");
        System.out.println("5. Artificial Intelligence");
        System.out.println("6. UI/UX Design");
        System.out.println("7. Game Development");
        System.out.println("8. Cloud Computing");
        System.out.println("9. Digital Marketing");
        System.out.println("10. Blockchain");
        System.out.print("Enter your choice: ");
        int field = sc.nextInt();
        sc.nextLine(); // Consume newline

        int skillScore = 0, techScore = 0;

        if (field == 1) {
            // Software Development
            System.out.println("Part 1: Rate your skills from 1 to 5 for the following areas:");
            System.out.print("Problem Solving: ");
            skillScore += sc.nextInt();
            System.out.print("Coding: ");
            skillScore += sc.nextInt();
            System.out.print("Analytical Thinking: ");
            skillScore += sc.nextInt();
            sc.nextLine(); // Consume newline

            System.out.println("Part 2: Answer the technical questions for Software Development:");
            System.out.println("1. What does OOP stand for?");
            System.out.println("   1. Object-Oriented Programming");
            System.out.println("   2. Open Online Platform");
            System.out.println("   3. Order of Operation");
            System.out.print("Enter your answer (1, 2, or 3): ");
            int userAnswer = sc.nextInt(); // Get user input
            sc.nextLine(); // Consume newline

            if (userAnswer == 1) {
                techScore += 5; // Increment score if correct
                System.out.println("Correct! +5 points.");
            } else {
                System.out.println("Incorrect. The correct answer is 1 (Object-Oriented Programming).");
          }
          
            // Consume newline
            System.out.println("2.Which language is primarily used for Android app development?");
            System.out.println("   1. Kotlin");
            System.out.println("   2. Python");
            System.out.println("   3. Ruby");
            System.out.print("Enter your answer (1, 2, or 3): ");
            userAnswer = sc.nextInt(); // Get user input
            sc.nextLine(); // Consume newline

            if (userAnswer == 1) {
                techScore += 5; // Increment score if correct
                System.out.println("Correct! +5 points.");
            } else {
                System.out.println("Incorrect. The correct answer is 1 (Object-Oriented Programming).");
}
        } else if (field == 2) {
            // Graphic Design
            System.out.println("Part 1: Rate your skills from 1 to 5 for the following areas:");
            System.out.print("Creativity: ");
            skillScore += sc.nextInt();
            System.out.print("Design Tools (e.g., Adobe Suite): ");
            skillScore += sc.nextInt();
            System.out.print("Communication: ");
            skillScore += sc.nextInt();
            sc.nextLine(); // Consume newline

            System.out.println("Part 2: Answer the technical questions for Graphic Design:");
            System.out.println("1. What is the primary purpose of graphic design?");
            System.out.println("   1. To communicate visually");
            System.out.println("   2. To write code");
            System.out.println("   3. To analyze data");
            System.out.print("Enter your answer (1, 2, or 3): ");
            int userAnswer = sc.nextInt(); // Get user input
            sc.nextLine(); // Consume newline

            if (userAnswer == 1) {
                techScore += 5; // Increment score if correct
                System.out.println("Correct! +5 points.");
            } else {
                System.out.println("Incorrect. The correct answer is 1 (Object-Oriented Programming).");
                } 
            // Consume newline
            System.out.println("2.Which software is commonly used for vector graphic design?");
            System.out.println("   1. Adobe Photoshop");
            System.out.println("   2. Adobe Illustrator");
            System.out.println("   3. Microsoft Paint");
            System.out.print("Enter your answer (1, 2, or 3): ");
            userAnswer = sc.nextInt(); // Get user input
            sc.nextLine(); // Consume newline

            if (userAnswer == 1) {
                techScore += 5; // Increment score if correct
                System.out.println("Correct! +5 points.");
            } else {
                System.out.println("Incorrect. The correct answer is 1 (Object-Oriented Programming).");
}
        } else if (field == 3) {
            // Data Science
            System.out.println("Part 1: Rate your skills from 1 to 5 for the following areas:");
            System.out.print("Statistics: ");
            skillScore += sc.nextInt();
            System.out.print("Data Manipulation (e.g., Excel, Python): ");
            skillScore += sc.nextInt();
            System.out.print("Visualization: ");
            skillScore += sc.nextInt();
            sc.nextLine(); // Consume newline

            System.out.println("Part 2: Answer the technical questions for Data Science:");
            System.out.println("1. What is a common library for data manipulation in Python?");
            System.out.println("   1. NumPy");
            System.out.println("   2. TensorFlow");
            System.out.println("   3. Flask");
            System.out.print("Enter your answer (1, 2, or 3): ");
            int userAnswer = sc.nextInt(); // Get user input
            sc.nextLine(); // Consume newline

            if (userAnswer == 1) {
                techScore += 5; // Increment score if correct
                System.out.println("Correct! +5 points.");
            } else {
                System.out.println("Incorrect. The correct answer is 1 (Object-Oriented Programming).");
} // Consume newline
            System.out.println("2.In data science, what does the term 'EDA' stand for?");
            System.out.println("   1. Essential Data Analysis");
            System.out.println("   2. Elementary Data Assessment");
            System.out.println("   3. Enhanced Data Analytics");
            System.out.print("Enter your answer (1, 2, or 3): ");
            userAnswer = sc.nextInt(); // Get user input
            sc.nextLine(); // Consume newline

            if (userAnswer == 1) {
                techScore += 5; // Increment score if correct
                System.out.println("Correct! +5 points.");
            } else {
                System.out.println("Incorrect. The correct answer is 1 (Object-Oriented Programming).");}
        } else if (field == 4) {
            // Cybersecurity
            System.out.println("Part 1: Rate your skills from 1 to 5 for the following areas:");
            System.out.print("Network Security: ");
            skillScore += sc.nextInt();
            System.out.print("Cryptography: ");
            skillScore += sc.nextInt();
            System.out.print("Incident Response: ");
            skillScore += sc.nextInt();
            sc.nextLine(); // Consume newline

            System.out.println("Part 2: Answer the technical questions for Cybersecurity:");
            System.out.println("1. What does SSL stand for?");
            System.out.println("   1. Secure Sockets Layer");
            System.out.println("   2. Security Software Layer");
            System.out.println("   3. Secured Server Logic");
            System.out.print("Enter your answer (1, 2, or 3): ");
            int userAnswer = sc.nextInt(); // Get user input
            sc.nextLine(); // Consume newline

            if (userAnswer == 1) {
                techScore += 5; // Increment score if correct
                System.out.println("Correct! +5 points.");
            } else {
                System.out.println("Incorrect. The correct answer is 1 (Object-Oriented Programming).");
}// Consume newline
            
            System.out.println("2.Which of the following is a method used to protect data from unauthorized access?");
            System.out.println("   1. Encryption");
            System.out.println("   2. Debugging");
            System.out.println("   3. Spamming");
            System.out.print("Enter your answer (1, 2, or 3): ");
            userAnswer = sc.nextInt(); // Get user input
            sc.nextLine(); // Consume newline

            if (userAnswer == 1) {
                techScore += 5; // Increment score if correct
                System.out.println("Correct! +5 points.");
            } else {
                System.out.println("Incorrect. The correct answer is 1 (Object-Oriented Programming).");}
        } else if (field == 5) {
            // Artificial Intelligence
            System.out.println("Part 1: Rate your skills from 1 to 5 for the following areas:");
            System.out.print("Machine Learning: ");
            skillScore += sc.nextInt();
            System.out.print("Deep Learning: ");
            skillScore += sc.nextInt();
            System.out.print("AI Tools (e.g., TensorFlow): ");
            skillScore += sc.nextInt();
            sc.nextLine(); // Consume newline

            System.out.println("Part 2: Answer the technical questions for Artificial Intelligence:");
            System.out.println("1. What is supervised learning?");
            System.out.println("   1. Learning with labeled data");
            System.out.println("   2. Learning without data");
            System.out.println("   3. Learning with unlabeled data");
            System.out.print("Enter your answer (1, 2, or 3): ");
            int userAnswer = sc.nextInt(); // Get user input
            sc.nextLine(); // Consume newline

            if (userAnswer == 1) {
                techScore += 5; // Increment score if correct
                System.out.println("Correct! +5 points.");
            } else {
                System.out.println("Incorrect. The correct answer is 1 (Object-Oriented Programming).");
} // Consume newline
            
            System.out.println("2.Which AI technique is used to make decisions based on probabilities and uncertainties?");
            System.out.println("   1. Neural Networks");
            System.out.println("   2. Genetic Algorithms");
            System.out.println("   3. Bayesian Networks");
            System.out.print("Enter your answer (1, 2, or 3): ");
             userAnswer = sc.nextInt(); // Get user input
            sc.nextLine(); // Consume newline

            if (userAnswer == 1) {
                techScore += 5; // Increment score if correct
                System.out.println("Correct! +5 points.");
            } else {
                System.out.println("Incorrect. The correct answer is 1 (Object-Oriented Programming).");
}
           

        }else if(field == 6) {
            // UI/UX Design
            System.out.println("Part 1: Rate your skills from 1 to 5 for the following areas:");
            System.out.print("User Research: ");
            skillScore += sc.nextInt();
            System.out.print("Wireframing: ");
            skillScore += sc.nextInt();
            System.out.print("Prototyping: ");
            skillScore += sc.nextInt();
            sc.nextLine(); // Consume newline

            System.out.println("Part 2: Answer the technical questions for UI/UX Design:");
            System.out.println("1. What is a wireframe?");
            System.out.println("   1. A basic visual guide for content layout");
            System.out.println("   2. A finished high-fidelity design");
            System.out.println("   3. A detailed user persona");
            System.out.print("Enter your answer (1, 2, or 3): ");
            int userAnswer = sc.nextInt(); // Get user input
            sc.nextLine(); // Consume newline

            if (userAnswer == 1) {
                techScore += 5; // Increment score if correct
                System.out.println("Correct! +5 points.");
            } else {
                System.out.println("Incorrect. The correct answer is 1 (Object-Oriented Programming).");
} // Consume newline

            System.out.println("2. Which tool is commonly used for prototyping?");
            System.out.println("   1. Adobe XD");
            System.out.println("   2. Blender");
            System.out.println("   3. PyCharm");
            System.out.print("Enter your answer (1, 2, or 3): ");
            userAnswer = sc.nextInt(); // Get user input
            sc.nextLine(); // Consume newline

            if (userAnswer == 1) {
                techScore += 5; // Increment score if correct
                System.out.println("Correct! +5 points.");
            } else {
                System.out.println("Incorrect. The correct answer is 1 (Object-Oriented Programming).");} // Consume newline
        } else if (field == 7) {
            // Game Development
            System.out.println("Part 1: Rate your skills from 1 to 5 for the following areas:");
            System.out.print("Game Mechanics: ");
            skillScore += sc.nextInt();
            System.out.print("Programming (e.g., C#, Unity, Unreal Engine): ");
            skillScore += sc.nextInt();
            System.out.print("Creativity: ");
            skillScore += sc.nextInt();
            sc.nextLine(); // Consume newline

            System.out.println("Part 2: Answer the technical questions for Game Development:");
            System.out.println("1. What is a game engine?");
            System.out.println("   1. A software framework for creating video games");
            System.out.println("   2. A system to handle game art and graphics");
            System.out.println("   3. A platform for social media sharing");
            System.out.print("Enter your answer (1, 2, or 3): ");
            int userAnswer = sc.nextInt(); // Get user input
            sc.nextLine(); // Consume newline

            if (userAnswer == 1) {
                techScore += 5; // Increment score if correct
                System.out.println("Correct! +5 points.");
            } else {
                System.out.println("Incorrect. The correct answer is 1 (Object-Oriented Programming).");
} // Consume newline

            System.out.println("2. Which game engine is known for its use in 3D games?");
            System.out.println("   1. Unity");
            System.out.println("   2. Notepad++");
            System.out.println("   3. Photoshop");
            System.out.print("Enter your answer (1, 2, or 3): ");
            userAnswer = sc.nextInt(); // Get user input
            sc.nextLine(); // Consume newline

            if (userAnswer == 1) {
                techScore += 5; // Increment score if correct
                System.out.println("Correct! +5 points.");
            } else {
                System.out.println("Incorrect. The correct answer is 1 (Object-Oriented Programming).");
} // Consume newline
        } else if (field == 8) {
            // Cloud Computing
            System.out.println("Part 1: Rate your skills from 1 to 5 for the following areas:");
            System.out.print("Cloud Service Providers (AWS, Azure, etc.): ");
            skillScore += sc.nextInt();
            System.out.print("Cloud Architecture: ");
            skillScore += sc.nextInt();
            System.out.print("Networking: ");
            skillScore += sc.nextInt();
            sc.nextLine(); // Consume newline

            System.out.println("Part 2: Answer the technical questions for Cloud Computing:");
            System.out.println("1. What is AWS?");
            System.out.println("   1. Amazon Web Services");
            System.out.println("   2. A new software tool for graphic design");
            System.out.println("   3. A type of mobile phone");
            System.out.print("Enter your answer (1, 2, or 3): ");
            int userAnswer = sc.nextInt(); // Get user input
            sc.nextLine(); // Consume newline

            if (userAnswer == 1) {
                techScore += 5; // Increment score if correct
                System.out.println("Correct! +5 points.");
            } else {
                System.out.println("Incorrect. The correct answer is 1 (Object-Oriented Programming).");
} // Consume newline

            System.out.println("2. What is the purpose of load balancing?");
            System.out.println("   1. Distribute traffic across multiple servers");
            System.out.println("   2. Monitor CPU usage on a server");
            System.out.println("   3. Design database schemas");
            System.out.print("Enter your answer (1, 2, or 3): ");
            userAnswer = sc.nextInt(); // Get user input
            sc.nextLine(); // Consume newline

            if (userAnswer == 1) {
                techScore += 5; // Increment score if correct
                System.out.println("Correct! +5 points.");
            } else {
                System.out.println("Incorrect. The correct answer is 1 (Object-Oriented Programming).");
} // Consume newline
        } else if (field == 9) {
            // Digital Marketing
            System.out.println("Part 1: Rate your skills from 1 to 5 for the following areas:");
            System.out.print("SEO (Search Engine Optimization): ");
            skillScore += sc.nextInt();
            System.out.print("Content Marketing: ");
            skillScore += sc.nextInt();
            System.out.print("Social Media Marketing: ");
            skillScore += sc.nextInt();
            sc.nextLine(); // Consume newline

            System.out.println("Part 2: Answer the technical questions for Digital Marketing:");
            System.out.println("1. What does SEO stand for?");
            System.out.println("   1. Search Engine Optimization");
            System.out.println("   2. Social Engagement Optimization");
            System.out.println("   3. Software Evaluation Optimization");
            System.out.print("Enter your answer (1, 2, or 3): ");
            int userAnswer = sc.nextInt(); // Get user input
            sc.nextLine(); // Consume newline

            if (userAnswer == 1) {
                techScore += 5; // Increment score if correct
                System.out.println("Correct! +5 points.");
            } else {
                System.out.println("Incorrect. The correct answer is 1 (Object-Oriented Programming).");
} // Consume newline

            System.out.println("2. Which platform is commonly used for advertising campaigns?");
            System.out.println("   1. Google Ads");
            System.out.println("   2. Spotify");
            System.out.println("   3. GitHub");
            System.out.print("Enter your answer (1, 2, or 3): ");
            userAnswer = sc.nextInt(); // Get user input
            sc.nextLine(); // Consume newline

            if (userAnswer == 1) {
                techScore += 5; // Increment score if correct
                System.out.println("Correct! +5 points.");
            } else {
                System.out.println("Incorrect. The correct answer is 1 (Object-Oriented Programming).");
} // Consume newline
        } else if (field == 10) {
            // Blockchain
            System.out.println("Part 1: Rate your skills from 1 to 5 for the following areas:");
            System.out.print("Cryptocurrency Understanding: ");
            skillScore += sc.nextInt();
            System.out.print("Blockchain Development: ");
            skillScore += sc.nextInt();
            System.out.print("Security Protocols: ");
            skillScore += sc.nextInt();
            sc.nextLine(); // Consume newline

            System.out.println("Part 2: Answer the technical questions for Blockchain:");
            System.out.println("1. What is a blockchain?");
            System.out.println("   1. A decentralized ledger of transactions");
            System.out.println("   2. A centralized database");
            System.out.println("   3. A programming language");
            System.out.print("Enter your answer (1, 2, or 3): ");
            int userAnswer = sc.nextInt(); // Get user input
            sc.nextLine(); // Consume newline

            if (userAnswer == 1) {
                techScore += 5; // Increment score if correct
                System.out.println("Correct! +5 points.");
            } else {
                System.out.println("Incorrect. The correct answer is 1 (Object-Oriented Programming).");
                } // Consume newline

            System.out.println("2. What does the term 'mining' refer to in blockchain?");
            System.out.println("   1. Solving complex mathematical problems to validate transactions");
            System.out.println("   2. Mining resources for hardware");
            System.out.println("   3. Developing blockchain software");
            System.out.print("Enter your answer (1, 2, or 3): ");
            userAnswer = sc.nextInt(); // Get user input
            sc.nextLine(); // Consume newline

            if (userAnswer == 1) {
                techScore += 5; // Increment score if correct
                System.out.println("Correct! +5 points.");
            } else {
                System.out.println("Incorrect. The correct answer is 1 (Object-Oriented Programming).");
} // Consume newline
        }
        int totalScore = skillScore + techScore;
        System.out.println("\nYour skill score: " + skillScore);
        System.out.println("Your technical score: " + techScore);
        System.out.println("Total score for your field of interest: " + totalScore);

        if (totalScore >= 20) {
            System.out.println("Congratulations! You are a good fit for this field.");
        } else {
            System.out.println("You may need improvement in this field. Consider enhancing your skills.");
        }
    }

    public static void applyForJob() {
        if (allJobs.isEmpty()) {
            System.out.println("No jobs available at the moment.");
            return;
        }

        System.out.println("Available Jobs:");
        int jobIndex = 1;
        for (Job job : allJobs) {
            System.out.println("\nJob #" + jobIndex);
            System.out.println(job.toString());
            jobIndex++;
        }

        System.out.print("\nEnter the job number you want to apply for (or 0 to exit): ");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume newline

        if (choice > 0 && choice <= allJobs.size()) {
            System.out.println("You have applied for the following job:");
            System.out.println(allJobs.get(choice - 1).toString());
        } else if (choice == 0) {
            System.out.println("Exiting to the main menu...");
        } else {
            System.out.println("Invalid choice! Please try again.");
        }
    }
    
    public static void mentorship(Student student) {
        System.out.println("\nMentorship Opportunities:");
        for (Professional professional : professionalsMap.values()) {
            if (professional.mentorshipAvailable) {
                System.out.println(professional);
                System.out.println("---------------------------");
                student.mentors.add(professional); // Adding to student's mentors list
            }
        }
        if (student.mentors.isEmpty()) {
            System.out.println("No mentors available at the moment.");
        }
    }
}
/*
   OUTPUT :-

Career Guidance System
1. Professional (Admin)
2. Student
3. Exit
Enter your choice: 1

Professional (Admin) Menu
1. Sign-Up
2. Login
3. Back to Main Menu
Enter your choice: 1

Sign-Up (Professional)
Enter your name: Om
Enter your profession: Manager
Enter your experience (in years): 5
Enter your contact details: 2345127892
Set a password: om@123
Sign-Up successful! You can now log in.

Professional (Admin) Menu
1. Sign-Up
2. Login
3. Back to Main Menu
Enter your choice: 2

Login (Professional)
Enter your name: Om
Enter your password: om@123
Login successful! Welcome, Om.

Admin/Professional Options
1. Post a Job
2. View All Jobs
3. Update a Job
4. Delete a Job
5. Update Availability for Mentorship
6. Exit to Main Menu
Enter your choice: 1
Enter job title: ABC
Enter company name: Amdocs
Enter job description: kk
Enter required skills: Java,Html,Css,Python,React
Enter salary: 40000
Job posted successfully!

Admin/Professional Options
1. Post a Job
2. View All Jobs
3. Update a Job
4. Delete a Job
5. Update Availability for Mentorship
6. Exit to Main Menu
Enter your choice: 1
Enter job title: XYZ
Enter company name: BNY
Enter job description: www
Enter required skills: JavaScript,Node.js,Django.Java Swing
Enter salary: 35000
Job posted successfully!

Admin/Professional Options
1. Post a Job
2. View All Jobs
3. Update a Job
4. Delete a Job
5. Update Availability for Mentorship
6. Exit to Main Menu
Enter your choice: 2
All Jobs:
1. Job Title: ABC
Company: Amdocs
Description: kk
Required Skills: Java,Html,Css,Python,React
Salary: 40000
-----------------------
2. Job Title: XYZ
Company: BNY
Description: www
Required Skills: JavaScript,Node.js,Django.Java Swing
Salary: 35000
-----------------------

Admin/Professional Options
1. Post a Job
2. View All Jobs
3. Update a Job
4. Delete a Job
5. Update Availability for Mentorship
6. Exit to Main Menu
Enter your choice: 3
All Jobs:
1. Job Title: ABC
Company: Amdocs
Description: kk
Required Skills: Java,Html,Css,Python,React
Salary: 40000
-----------------------
2. Job Title: XYZ
Company: BNY
Description: www
Required Skills: JavaScript,Node.js,Django.Java Swing
Salary: 35000
-----------------------
Enter the job number to update: 1
Enter new job title: Administrative Assistant
Enter new company name: Amdocs
Enter new job description: Full Time
Enter new required skills: Java,Python,Dsa,Html,Css
Enter new salary: 45000
Job updated successfully!

Admin/Professional Options
1. Post a Job
2. View All Jobs
3. Update a Job
4. Delete a Job
5. Update Availability for Mentorship
6. Exit to Main Menu
Enter your choice: 2
All Jobs:
1. Job Title: Administrative Assistant
Company: Amdocs
Description: Full Time
Required Skills: Java,Python,Dsa,Html,Css
Salary: 45000
-----------------------
2. Job Title: XYZ
Company: BNY
Description: www
Required Skills: JavaScript,Node.js,Django.Java Swing
Salary: 35000
-----------------------

Admin/Professional Options
1. Post a Job
2. View All Jobs
3. Update a Job
4. Delete a Job
5. Update Availability for Mentorship
6. Exit to Main Menu
Enter your choice: 4
All Jobs:
1. Job Title: Administrative Assistant
Company: Amdocs
Description: Full Time
Required Skills: Java,Python,Dsa,Html,Css
Salary: 45000
-----------------------
2. Job Title: XYZ
Company: BNY
Description: www
Required Skills: JavaScript,Node.js,Django.Java Swing
Salary: 35000
-----------------------
Enter the job number to delete: 2
Job deleted successfully!

Admin/Professional Options
1. Post a Job
2. View All Jobs
3. Update a Job
4. Delete a Job
5. Update Availability for Mentorship
6. Exit to Main Menu
Enter your choice: 5
Would you like to enable mentorship? (yes/no): yes
Mentorship availability updated.

Admin/Professional Options
1. Post a Job
2. View All Jobs
3. Update a Job
4. Delete a Job
5. Update Availability for Mentorship
6. Exit to Main Menu
Enter your choice: 6

Professional (Admin) Menu
1. Sign-Up
2. Login
3. Back to Main Menu
Enter your choice: 3

Career Guidance System
1. Professional (Admin)
2. Student
3. Exit
Enter your choice: 2

Student Menu
1. Sign-Up
2. Login
3. Back to Main Menu
Enter your choice: 1

Sign-Up (Student)
Enter your name: Yukta 
Enter your email: yukta@gmail.com
Set a password: yu@09
Sign-Up successful! You can now log in.

Student Menu
1. Sign-Up
2. Login
3. Back to Main Menu
Enter your choice: 2

Login (Student)
Enter your email: yukta@gmail.com
Enter your password: yu@09
Login successful! Welcome, Yukta .

Student Options
1. Explore Career Paths
2. Take a Test
3. Mentorship
4. Apply for Job
5. Exit to Main Menu
Enter your choice: 1

Exploring Career Paths:
1. Software Development
2. Graphic Design
3. Data Science
4. Cybersecurity
5. Artificial Intelligence
6. UI/UX Design
7. Game Development
8. Cloud Computing
9. Digital Marketing
10. Blockchain
Enter your choice: 3
Data Science: Requires skills in Python, R, and data analysis.

Student Options
1. Explore Career Paths
2. Take a Test
3. Mentorship
4. Apply for Job
5. Exit to Main Menu
Enter your choice: 2

Take Test:
Select your field of interest by entering the corresponding number:
1. Software Development
2. Graphic Design
3. Data Science
4. Cybersecurity
5. Artificial Intelligence
6. UI/UX Design
7. Game Development
8. Cloud Computing
9. Digital Marketing
10. Blockchain
Enter your choice: 2
Part 1: Rate your skills from 1 to 5 for the following areas:
Creativity: 4
Design Tools (e.g., Adobe Suite): 5
Communication: 5
Part 2: Answer the technical questions for Graphic Design:
1. What is the primary purpose of graphic design?
   1. To communicate visually
   2. To write code
   3. To analyze data
Enter your answer (1, 2, or 3): 1
Correct! +5 points.
2.Which software is commonly used for vector graphic design?
   1. Adobe Photoshop
   2. Adobe Illustrator
   3. Microsoft Paint
Enter your answer (1, 2, or 3): 1
Correct! +5 points.

Your skill score: 14
Your technical score: 10
Total score for your field of interest: 24
Congratulations! You are a good fit for this field.

Student Options
1. Explore Career Paths
2. Take a Test
3. Mentorship
4. Apply for Job
5. Exit to Main Menu
Enter your choice: 3

Mentorship Opportunities:
Name: Om
Profession: Manager
Experience: 5 years
Contact: 2345127892
Mentorship Available: Yes
---------------------------

Student Options
1. Explore Career Paths
2. Take a Test
3. Mentorship
4. Apply for Job
5. Exit to Main Menu
Enter your choice: 4
Available Jobs:

Job #1
Job Title: Administrative Assistant
Company: Amdocs
Description: Full Time
Required Skills: Java,Python,Dsa,Html,Css
Salary: 45000

Enter the job number you want to apply for (or 0 to exit): 1
You have applied for the following job:
Job Title: Administrative Assistant
Company: Amdocs
Description: Full Time
Required Skills: Java,Python,Dsa,Html,Css
Salary: 45000

Student Options
1. Explore Career Paths
2. Take a Test
3. Mentorship
4. Apply for Job
5. Exit to Main Menu
Enter your choice: 5

Student Menu
1. Sign-Up
2. Login
3. Back to Main Menu
Enter your choice: 3

Career Guidance System
1. Professional (Admin)
2. Student
3. Exit
Enter your choice: 3
Exiting system...

 */

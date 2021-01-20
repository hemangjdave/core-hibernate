package com.techrevolution.hibernate.onetoone;

import com.techrevolution.hibernate.util.RepositoryOperations;

public class CreateInstructor {
    public static void main(String[] args) {
        InstructorDetails instructorDetails = new InstructorDetails(
                "www.youtube.com/shiva.dave", "programming"
        );

        Instructor instructor = new Instructor(
                "Shiva", "Dave", "shiva.dave@gmail.com"
        );

        instructor.setInstructorDetails(instructorDetails);

        RepositoryOperations<Instructor> repositoryOperations
                = new RepositoryOperations<>();

        if (repositoryOperations.save(instructor)) {
            System.out.println("Successfully saved the instructor...");
        } else
            System.out.println("Error occurred...");
    }
}

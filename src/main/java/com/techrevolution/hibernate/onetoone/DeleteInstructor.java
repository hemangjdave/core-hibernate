package com.techrevolution.hibernate.onetoone;

import com.techrevolution.hibernate.util.RepositoryOperations;

public class DeleteInstructor {
    public static void main(String[] args) {
        RepositoryOperations<Instructor> repositoryOperations = new RepositoryOperations<>();
        if (repositoryOperations.deleteById(Instructor.class, 2)) {
            System.out.println("Successfully deleted the instructor.");
        } else
            System.out.println("Error occurred");
    }
}

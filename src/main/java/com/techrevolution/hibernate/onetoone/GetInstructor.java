package com.techrevolution.hibernate.onetoone;

import com.techrevolution.hibernate.util.RepositoryOperations;

public class GetInstructor {
    public static void main(String[] args) {
        RepositoryOperations<Instructor> operations
                = new RepositoryOperations<>();

        Instructor instructor = operations.getEntity(3);
        System.out.println(instructor);
        System.out.println(instructor.getInstructorDetails());
    }
}

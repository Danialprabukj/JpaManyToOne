package com.kgisl.JpaManyToOne;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        // Get the EntityManagerFactory from the JPA configuration
        EntityManagerFactory emFactory = JpaConfig.getEntityManagerFactory();
        
        // Create an entity manager
        EntityManager entityManager = emFactory.createEntityManager();
        
        // Create a department
        Department department = new Department();
        department.setId(1L);
        department.setName("IT");
        
        // Create employees
        Employee employee1 = new Employee();
        employee1.setId(1L);
        employee1.setName("John Doe");
        employee1.setDepartment(department);
        
        Employee employee2 = new Employee();
        employee2.setId(2L);
        employee2.setName("Jane Smith");
        employee2.setDepartment(department);
        
        // Persist the department and employees
        entityManager.getTransaction().begin();
        entityManager.persist(department);
        entityManager.persist(employee1);
        entityManager.persist(employee2);
        entityManager.getTransaction().commit();
        
        // Retrieve all employees for a department
        List<Employee> employees = entityManager.createQuery(
                "SELECT e FROM Employee e WHERE e.department = :department", Employee.class)
                .setParameter("department", department)
                .getResultList();
        
        System.out.println("Employees in the " + department.getName() + " department:");
        for (Employee employee : employees) {
            System.out.println(employee.getName());
        }
        
        // Close the entity manager and factory
        entityManager.close();
        emFactory.close();
    }
}

package com.cts;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class DepartmentDAO {
    private EntityManager entityManager;

   
    public DepartmentDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    
    public void createDepartmentWithEmployees(Department department, Set<Employee> employees) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            department.setEmployees(employees);  
            entityManager.persist(department);  
            transaction.commit();
            System.out.println("Department created with ID: " + department.getId());
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback(); 
            }
            throw e;
        }
    }
    
    public Department findDepartment(Long departmentId) {
        return entityManager.find(Department.class, departmentId);  // Find the department by its ID
    }
    
    public void updateDepartment(Department department) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(department); 
            transaction.commit();
            System.out.println("Department updated: " + department.getName());
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();  
            }
            throw e;
        }
    }
    
    public void deleteDepartment(Long departmentId) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Department department = entityManager.find(Department.class, departmentId);  // Find the department by ID
            if (department != null) {
                entityManager.remove(department);  // Remove the department (employees will be deleted due to cascade)
                System.out.println("Department deleted with ID: " + departmentId);
            } else {
                System.out.println("Department not found with ID: " + departmentId);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();  
            throw e;
        }
    }


    
}}
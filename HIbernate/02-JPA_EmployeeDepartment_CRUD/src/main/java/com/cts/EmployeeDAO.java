package com.cts;import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class EmployeeDAO {
    private EntityManager entityManager;

   
    public EmployeeDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

  
    public void createEmployee(Employee employee) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(employee);  
            transaction.commit();
            System.out.println("Employee created with ID: " + employee.getId());
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();  
            }
            throw e;
        }
    }
    
    public Employee findEmployee(Long employeeId) {
        return entityManager.find(Employee.class, employeeId);  
    }
    
    public void updateEmployee(Employee employee) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(employee);  
            transaction.commit();
            System.out.println("Employee updated: " + employee.getFirstName() + " " + employee.getLastName());
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();  
            }
            throw e;
        }
    }
    
    public void deleteEmployee(Long employeeId) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Employee employee = entityManager.find(Employee.class, employeeId); 
            if (employee != null) {
                entityManager.remove(employee);  
                System.out.println("Employee deleted with ID: " + employeeId);
            } else {
                System.out.println("Employee not found with ID: " + employeeId);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();  
            }
            throw e;
        }
    }


}

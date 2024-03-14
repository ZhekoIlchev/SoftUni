package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entities.Department;
import org.example.entities.Employee;

public class JPAExample {
    public static void main(String[] args) {
        /**
         * Създаване на EntityManager.
         */
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("softuni");
        EntityManager em = emf.createEntityManager();

        /**
         * Отваряне на транзакция и добавяне на записи в базата.
         */
        em.getTransaction().begin();

        Department departmentOne = new Department();
        departmentOne.setName("Software Engineering");

        Department departmentTwo = new Department();
        departmentTwo.setName("QA");

        em.persist(departmentOne);
        em.persist(departmentTwo);

        Employee employeeOne = new Employee();
        employeeOne.setName("Boyan");
        employeeOne.setDepartment(departmentOne);

        Employee employeeTwo = new Employee();
        employeeTwo.setName("Presiyana");
        employeeTwo.setDepartment(departmentTwo);

        em.persist(employeeOne);
        em.persist(employeeTwo);

        em.getTransaction().commit();
    }
}
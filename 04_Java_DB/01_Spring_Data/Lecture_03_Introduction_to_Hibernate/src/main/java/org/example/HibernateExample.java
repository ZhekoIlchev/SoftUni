package org.example;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entities.City;
import org.example.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateExample {
    public static void main(String[] args) {
        /**
         * Configure Hibernate.
         */
        Configuration cfg = new Configuration()
                .configure();

        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        /**
         * Create object: Cities.
         */

        City cityPlovdiv = new City();
        cityPlovdiv.setName("Plovdiv");

        City citySofia = new City();
        citySofia.setName("Sofia");

        /**
         * Save objects in Database (Cities).
         */
        session.persist(cityPlovdiv);
        session.persist(citySofia);

        /**
         * Create object: Students.
         */
        Student student1 = new Student();
        student1.setFirstName("Boyan");
        student1.setLastName("Stoyanov");
        student1.setCity(cityPlovdiv);

        Student student2 = new Student();
        student2.setFirstName("Presiyana");
        student2.setLastName("Ivanova");
        student2.setCity(citySofia);

        /**
         * Save objects in Database (Students).
         */
        session.persist(student1);
        session.persist(student2);

        /**
         * Select all students from Sofia with JPQL.
         */
        TypedQuery<Student> query = session.createQuery("FROM Student s JOIN FETCH s.city c WHERE c.name = :cityName");
        query.setParameter("cityName", "Sofia");
        query.getResultList().forEach(
                s -> System.out.println(s)
        );

        /**
         * CriteriaBuilder example.
         */
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> root = cq.from(Student.class);

        cq.select(root).where(cb.gt(root.get("id"), 0));

        TypedQuery<Student> queryCB = session.createQuery(cq);
        queryCB.getResultList().forEach(s -> System.out.println(s));

        session.getTransaction().commit();
        session.close();
    }
}
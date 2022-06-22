import entities.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("school");

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Student firstStudent  = new Student("Teo", 23);
        entityManager.persist(firstStudent);

        Student secondStudent = new Student("Leo", 27);
        entityManager.persist(secondStudent);


        Student findFirst = entityManager.find(Student.class, 1L);
        entityManager.remove(findFirst);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
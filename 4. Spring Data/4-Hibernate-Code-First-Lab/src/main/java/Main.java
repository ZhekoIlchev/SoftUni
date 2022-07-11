import entities.shampoo.BasicIngredient;
import entities.shampoo.BasicLabel;
import entities.shampoo.BasicShampoo;
import entities.shampoo.ProductionBatch;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cfa-lab");

        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

//        Bike bike = new Bike();
//        Car car = new Car();
//
//        entityManager.persist(bike);
//        entityManager.persist(car);


        BasicLabel label = new BasicLabel("Black");
        ProductionBatch batch = new ProductionBatch(LocalDate.now());
        BasicShampoo shampoo = new BasicShampoo("Shauma", label, batch);

        BasicIngredient firstIngredient = new BasicIngredient(100, "B12");
        BasicIngredient secondIngredient = new BasicIngredient(30, "Violet");

        entityManager.persist(firstIngredient);
        entityManager.persist(secondIngredient);

        shampoo.addIngredient(firstIngredient);
        shampoo.addIngredient(secondIngredient);

        entityManager.persist(label);
        entityManager.persist(batch);
        entityManager.persist(shampoo);

        entityManager.getTransaction().commit();
    }
}
package org.example;

//import org.example.entity.tableperclass.Bike;
//import org.example.entity.tableperclass.Car;
//import org.example.entity.tableperclass.Vehicle;

//import org.example.entity.joined.Car;
//import org.example.entity.joined.Truck;
//import org.example.entity.joined.Vehicle;

import org.example.entity.relation.Ingredient;
import org.example.entity.relation.ProductionBatch;
import org.example.entity.relation.Shampoo;
import org.example.entity.relation.ShampooLabel;
//import org.example.entity.singletable.Car;
//import org.example.entity.singletable.Truck;
//import org.example.entity.singletable.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("softuni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        /**
         * Strategy: Table per class
         */
//        Vehicle car = new Car();
//        Vehicle bike = new Bike();
//
//        entityManager.persist(car);
//        entityManager.persist(bike);

        /**
         * Strategy: Joined
         */

//        Vehicle car = new Car(5);
//        Vehicle truck = new Truck(100, 200);
//
//        entityManager.persist(car);
//        entityManager.persist(truck);

        /**
         * Strategy: Single Table
         */

//        Vehicle car = new Car(5);
//        Vehicle truck = new Truck(100, 200);
//
//        entityManager.persist(car);
//        entityManager.persist(truck);

        /**
         * Relations: @OneToOne, @ManyToOne and @ManyToMany
         *
         * Label creation.
         */

        ShampooLabel labelForHair = new ShampooLabel();
        labelForHair.setName("For Hair");
        entityManager.persist(labelForHair);

        ShampooLabel labelForBody = new ShampooLabel();
        labelForBody.setName("For Body");
        entityManager.persist(labelForBody);

        ShampooLabel labelForSoftCare = new ShampooLabel();
        labelForSoftCare.setName("For Soft Care");
        entityManager.persist(labelForSoftCare);

        /**
         * Batch creation.
         */

        ProductionBatch batchOne = new ProductionBatch();
        batchOne.setCreatedOn(new Date());
        entityManager.persist(batchOne);

        ProductionBatch batchTwo = new ProductionBatch();
        batchTwo.setCreatedOn(new Date());
        entityManager.persist(batchTwo);

        /**
         * Ingredients creation.
         */

        Ingredient ingredientOne = new Ingredient();
        ingredientOne.setName("Mint");

        Ingredient ingredientTwo = new Ingredient();
        ingredientTwo.setName("Honey");

        Ingredient ingredientTree = new Ingredient();
        ingredientTree.setName("Sunflower");

        entityManager.persist(ingredientOne);
        entityManager.persist(ingredientTwo);
        entityManager.persist(ingredientTree);

        /**
         * Shampoo creation.
         */

        Shampoo shampooOne = new Shampoo();
        shampooOne.setLabel(labelForHair);
        shampooOne.setBatch(batchOne);
        shampooOne.getIngredients().add(ingredientOne);
        shampooOne.getIngredients().add(ingredientTwo);

        Shampoo shampooTwo = new Shampoo();
        shampooTwo.setLabel(labelForBody);
        shampooTwo.setBatch(batchOne);
        shampooTwo.getIngredients().add(ingredientTwo);
        shampooTwo.getIngredients().add(ingredientTree);

        Shampoo shampooThree = new Shampoo();
        shampooThree.setLabel(labelForSoftCare);
        shampooThree.setBatch(batchTwo);
        shampooThree.getIngredients().add(ingredientTree);
        shampooThree.getIngredients().add(ingredientOne);

        entityManager.persist(shampooOne);
        entityManager.persist(shampooTwo);
        entityManager.persist(shampooThree);

        entityManager.getTransaction().commit();
    }
}
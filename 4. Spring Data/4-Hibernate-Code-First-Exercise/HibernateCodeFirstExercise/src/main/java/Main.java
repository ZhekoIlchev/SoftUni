import entity.Product;
import entity.Sale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cfa");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
//        Sale sale = new Sale();
//        sale.setLocalDateTime(LocalDateTime.now());
//
//        Product product = new Product();
//        product.setName("Meat");
//        product.setPrice(BigDecimal.valueOf(10.37));
//        product.setQuantity(1);
//        product.getSales().add(sale);
//
//        sale.setProduct(product);
//
//        entityManager.persist(product);

//        Product findProduct = entityManager.find(Product.class, 4L);
//        entityManager.remove(findProduct);

        entityManager.getTransaction().commit();
    }
}
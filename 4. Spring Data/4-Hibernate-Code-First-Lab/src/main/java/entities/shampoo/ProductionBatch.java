package entities.shampoo;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "batches")
public class ProductionBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "created_at")
    private LocalDate createdAt;

    /**
     * 2. Many-To-One (Bidirectional)
     * This is Many-To-One (Bidirectional) Relationship. Here we should use One-To-Many Relationship - One batch to many shampoos.
     * mappedBy = "batch" -> Field in entity BasicShampoo.
     * targetEntity = BasicShampoo.class -> Entity for the mapping.
     */
    @OneToMany(mappedBy = "batch", targetEntity = BasicShampoo.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<BasicShampoo> shampoos;

    public ProductionBatch() {
    }

    public ProductionBatch(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Set<BasicShampoo> getShampoos() {
        return shampoos;
    }

    public void setShampoos(Set<BasicShampoo> shampoos) {
        this.shampoos = shampoos;
    }
}
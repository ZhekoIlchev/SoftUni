package entities.shampoo;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shampoos")
public class BasicShampoo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    /**
     * 1. One-To-One (Unidirectional)
     * This is One-To-One (Unidirectional) Relationship. One shampoo with one label.
     * name = "label_id" -> Column name in table shampoos.
     * referencedColumnName = "id" -> Column name in table labels/Field in entity BasicLabel.
     */
    @OneToOne(optional = false)
    @JoinColumn(name = "label_id", referencedColumnName = "id")
    private BasicLabel label;

    /**
     * 2. Many-To-One (Unidirectional)
     * This is Many-To-One (Unidirectional) Relationship. Many shampoos in one batch.
     * name = "batch_id" -> Column name in table shampoos.
     * referencedColumnName = "id" -> Column name in table batches/Field in entity ProductionBatch.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "batch_id", referencedColumnName = "id")
    private ProductionBatch batch;

    /**
     * 3. Many-To-Many (Unidirectional)
     * This is Many-To-Many (Unidirectional) Relationship. Many shampoos with many ingredients.
     * name = "shampoos_ingredients" -> Mapping table.
     * name = "shampoo_id" -> Column name in table "shampoos_ingredients".
     * referencedColumnName = "id" -> Column name in table shampoos/Field in entity BasicShampoo.
     * name = "ingredient_id" -> Column name in table "shampoos_ingredients".
     * referencedColumnName = "id" -> Column name in table ingredients/Field in entity BasicIngredient.
     */
    @ManyToMany
    @JoinTable(name = "shampoos_ingredients",
            joinColumns = @JoinColumn(name = "shampoo_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
    private Set<BasicIngredient> ingredients;

    public BasicShampoo() {
    }

    public BasicShampoo(String name, BasicLabel label, ProductionBatch batch) {
        this.name = name;
        this.label = label;
        this.batch = batch;
        this.ingredients = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BasicLabel getLabel() {
        return label;
    }

    public void setLabel(BasicLabel label) {
        this.label = label;
    }

    public ProductionBatch getBatch() {
        return batch;
    }

    public void setBatch(ProductionBatch batch) {
        this.batch = batch;
    }

    public Set<BasicIngredient> getIngredients() {
        return Collections.unmodifiableSet(this.ingredients);
    }

    public void addIngredient(BasicIngredient ingredient) {
        this.ingredients.add(ingredient);
    }
}
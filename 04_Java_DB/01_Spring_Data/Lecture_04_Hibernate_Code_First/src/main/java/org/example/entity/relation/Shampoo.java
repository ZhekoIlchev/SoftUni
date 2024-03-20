package org.example.entity.relation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shampoos")
public class Shampoo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * @OneToOne Property: optional = false, означава, че не може да създадем шампоан, който няма етикет и е Runtime evaluation.
     * NB! Важно е да го има, за да се създаде ключ, който да бъде уникален. При липса на това property, колоната няма
     * да е уникална/да съдържа уникални стойности.
     * @JoinColumn Property: name показва как ще се казва колоната на foreign key-я.
     * Property: referencedColumnName показва, към кое поле от другата таблица ще реферира foreign key-я.
     * <p>
     * Когато няма релация и от другата страна е само Unidirectional. Имаме достъп до етикетите, само през шампоаните.
     * В случай, че искаме да дотъпваме до шампоаните през етикетите е необходимо да се създаде релация и от страната на
     * етикиетите - Bidirectional.
     */
    @OneToOne(optional = false)
    @JoinColumn(name = "label_id", referencedColumnName = "id")
    private ShampooLabel label;

    /**
     * @ManyToOne Property: optional = false, означава, че не може да създадем шампоан, който няма партида и е Runtime evaluation.
     * @JoinColumn Property: name показва как ще се казва колоната на foreign key-я.
     * Property: referencedColumnName показва, към кое поле от другата таблица ще реферира foreign key-я.
     * <p>
     * Когато няма релация и от другата страна е само Unidirectional. Имаме достъп до партидите, само през шампоаните.
     * В случай, че искаме да дотъпваме до шампоаните през партидите е необходимо да се създаде релация и от страната на
     * партидите - Bidirectional.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "batch_id", referencedColumnName = "id")
    private ProductionBatch batch;

    /**
     * @ManyToMany
     * @JoinTable Property: name - как ще се казва новата таблица.
     * <p>
     * joinColumns - разглежда се от страната на класа в който правим Unidirectional relation-а/Shampoos.
     * inverseJoinColumns - разглежда се от страната на класа към който ще правим референцията/Ingredients.
     * <p>
     * Unidirectional relation.
     */
    @ManyToMany
    @JoinTable(name = "shampoos_ingredients",
            joinColumns = @JoinColumn(name = "shampoo_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
    private Set<Ingredient> ingredients = new HashSet<>();

    public Shampoo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShampooLabel getLabel() {
        return label;
    }

    public void setLabel(ShampooLabel label) {
        this.label = label;
    }

    public ProductionBatch getBatch() {
        return batch;
    }

    public void setBatch(ProductionBatch batch) {
        this.batch = batch;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
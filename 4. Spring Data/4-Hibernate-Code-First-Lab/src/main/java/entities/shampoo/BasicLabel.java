package entities.shampoo;

import javax.persistence.*;

@Entity
@Table(name = "labels")
public class BasicLabel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String color;

    /**
     * 1. One-To-One (Bidirectional)
     * This is One-To-One (Bidirectional) Relationship. One label with one shampoo.
     * mappedBy = "label" -> Field in entity BasicShampoo.
     * targetEntity = BasicShampoo.class -> Entity for the mapping.
     */
    @OneToOne(mappedBy = "label", targetEntity = BasicShampoo.class)
    private BasicShampoo shampoo;

    public BasicLabel() {}

    public BasicLabel(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BasicShampoo getShampoo() {
        return shampoo;
    }

    public void setShampoo(BasicShampoo shampoo) {
        this.shampoo = shampoo;
    }
}
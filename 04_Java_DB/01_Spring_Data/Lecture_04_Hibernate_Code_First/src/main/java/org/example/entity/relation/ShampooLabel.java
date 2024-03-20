package org.example.entity.relation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "labels")
public class ShampooLabel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    /**
     * По този начин създаваме Bidirectional relation.
     * <p>
     * Property: mapperdBy - показва към кое поле от класа Shampoo ще се свърже.
     * Property: targetEntity - не е задължително, когато типа на полето е същия като типа на targetEntity-то.
     * Възможно е да обаче полето да не е от тип Shampoo, а от някой по-базов клас. При този случай е добре
     * да използваме targetEntity property-то.
     */
    @OneToOne(mappedBy = "label", targetEntity = Shampoo.class)
    private Shampoo shampoo;

    public ShampooLabel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shampoo getShampoo() {
        return shampoo;
    }

    public void setShampoo(Shampoo shampoo) {
        this.shampoo = shampoo;
    }
}
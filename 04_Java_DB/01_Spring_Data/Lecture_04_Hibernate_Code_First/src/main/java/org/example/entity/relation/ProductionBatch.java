package org.example.entity.relation;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "batches")
public class ProductionBatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createdOn;

    /**
     * По този начин създаваме Bidirectional relation.
     * <p>
     * Property: mapperdBy - показва към кое поле от класа Shampoo ще се свърже.
     * Property: targetEntity - не е задължително, когато типа на полето е същия като типа на targetEntity-то.
     * Възможно е да обаче полето да не е от тип Shampoo, а от някой по-базов клас. При този случай е добре
     * да използваме targetEntity property-то.
     */
    @OneToMany(mappedBy = "batch", targetEntity = Shampoo.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Shampoo> shampoos;

    public ProductionBatch() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Set<Shampoo> getShampoos() {
        return shampoos;
    }

    public void setShampoos(Set<Shampoo> shampoos) {
        this.shampoos = shampoos;
    }
}
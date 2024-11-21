package com.example.pms.purchase;

import com.example.pms.item.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name="box")
    private int box;

    @Column(name="units_per_box")
    private int unitsPerBox;

    public Purchase(int box, int unitsPerBox, Item item) {
        this.box = box;
        this.unitsPerBox = unitsPerBox;
        this.item = item;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="item_id",referencedColumnName = "id")
    private Item item;

    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    public Purchase(int box, int unitsPerBox, Item item, LocalDateTime localDateTime) {
        this.box = box;
        this.item=item;
        this.unitsPerBox=unitsPerBox;
        this.purchaseDate = localDateTime;
    }

}

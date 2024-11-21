package com.example.pms.purchaseDetails;

import com.example.pms.purchase.Purchase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="box_serial_number")
    private String boxSerialNumber;

    @Column(name="unit_per_box_serial_number")
    private String unitSerialNumber;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="purchase_id",referencedColumnName = "id" )
    private Purchase purchase;

}

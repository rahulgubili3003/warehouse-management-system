package com.demo.warehousemanagementsystem.entity;

import com.demo.warehousemanagementsystem.enums.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "product")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false, name = "id")
    private Long id;

    @Column(unique = true, nullable = false, name = "product_id")
    private Long productId;

    @Column(nullable = false, name = "product_name")
    private String productName;

    @Column(nullable = false, name = "available_units", columnDefinition = "INTEGER DEFAULT 0")
    private Integer availableUnits;

    @Column(name = "category")
    @Enumerated(EnumType.ORDINAL)
    private Category category;

    @Column(nullable = false, name = "is_deleted")
    private Boolean isDeleted;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(updatable = false, name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;
}

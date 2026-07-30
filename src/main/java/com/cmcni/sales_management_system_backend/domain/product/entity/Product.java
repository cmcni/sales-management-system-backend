package com.cmcni.sales_management_system_backend.domain.product.entity;

import com.cmcni.sales_management_system_backend.common.entity.BaseTimeEntity;
import com.cmcni.sales_management_system_backend.domain.product_category.entity.ProductCategory;
import com.cmcni.sales_management_system_backend.domain.product_model.entity.ProductModel;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false, comment = "제품 모델")
    private ProductModel model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false, comment = "가장 하위 카테고리 참조")
    private ProductCategory category;

    @Column(comment = "제품명")
    private String name;

    @Column(comment = "비고")
    private String note;

    @Column(comment = "권장 판매 단가")
    private Integer recommendedSellingPrice;

    public Product() {}

    public Product(ProductModel model, ProductCategory category, String name, String note, Integer recommendedSellingPrice) {
        this.model = model;
        this.category = category;
        this.name = name;
        this.note = note;
        this.recommendedSellingPrice = recommendedSellingPrice;
    }
}

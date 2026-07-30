package com.cmcni.sales_management_system_backend.domain.product_category.entity;

import com.cmcni.sales_management_system_backend.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class ProductCategory extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(comment = "카테고리명")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", comment = "상위 카테고리 ID")
    private ProductCategory parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sortOrder ASC")
    private List<ProductCategory> children = new ArrayList<>();

    // 트리 탐색/정렬 최적화용 컬럼
    @Column(comment = "카테고리 깊이")
    private Integer depth = 0;

    @Column(comment = "같은 레벨에서의 정렬 순서")
    private Integer sortOrder = 0;

    @Column(comment = "사용 중 여부")
    private Boolean isActive = true;

    public ProductCategory() {}

    // 루트 카테고리 생성용
    public ProductCategory(String name) {
        this.name = name;
        this.parent = null;
        this.depth = 0;
        this.sortOrder = 0;
        this.isActive = true;
    }

    // 하위 카테고리 생성용
    public ProductCategory(String name, ProductCategory parent) {
        this.name = name;
        this.parent = parent;
        this.depth = parent.getDepth() + 1;
        this.sortOrder = 0;
        this.isActive = true;

        parent.getChildren().add(this);
    }

    public boolean isRoot() {
        return parent == null;
    }
}

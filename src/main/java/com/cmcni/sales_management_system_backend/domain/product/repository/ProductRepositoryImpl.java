package com.cmcni.sales_management_system_backend.domain.product.repository;

import com.cmcni.sales_management_system_backend.domain.product.entity.Product;
import com.cmcni.sales_management_system_backend.domain.product.service.request.ProductSearchRequest;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.cmcni.sales_management_system_backend.domain.product.entity.QProduct.product;
import static com.cmcni.sales_management_system_backend.domain.product_category.entity.QProductCategory.productCategory;
import static com.cmcni.sales_management_system_backend.domain.product_model.entity.QProductModel.productModel;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Product> search(ProductSearchRequest productSearchRequest, Pageable pageable) {

        // 조회 쿼리 : 목록 표시에 필요한 model/category를 fetch join으로 함께 가져온다.
        List<Product> content = queryFactory
                .selectFrom(product)
                .leftJoin(product.model, productModel).fetchJoin()
                .leftJoin(product.category, productCategory).fetchJoin()
                .where(
                        categoryIdEq(productSearchRequest.getProductCategoryId()),
                        modelIdEq(productSearchRequest.getProductModelId()),
                        modelNameContains(productSearchRequest.getProductModelName()),
                        recommendedSellingPriceEq(productSearchRequest.getProductRecommendedSellingPrice())
                )
                .orderBy(toOrderSpecifiers(pageable.getSort()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 카운트 쿼리 : fetch join 없이 동일한 조건으로 전체 건수만 별도 조회한다.
        Long totalCount = queryFactory
                .select(product.count())
                .from(product)
                .leftJoin(product.model, productModel)
                .leftJoin(product.category, productCategory)
                .where(
                        categoryIdEq(productSearchRequest.getProductCategoryId()),
                        modelIdEq(productSearchRequest.getProductModelId()),
                        modelNameContains(productSearchRequest.getProductModelName()),
                        recommendedSellingPriceEq(productSearchRequest.getProductRecommendedSellingPrice())
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, totalCount == null ? 0 : totalCount);
    }

    private BooleanExpression categoryIdEq(Long categoryId) {
        return categoryId == null ? null : productCategory.id.eq(categoryId);
    }

    private BooleanExpression modelIdEq(Long modelId) {
        return modelId == null ? null : productModel.id.eq(modelId);
    }

    private BooleanExpression modelNameContains(String modelName) {
        return StringUtils.hasText(modelName) ? productModel.name.contains(modelName) : null;
    }

    private BooleanExpression recommendedSellingPriceEq(Integer recommendedSellingPrice) {
        return recommendedSellingPrice == null ? null : product.recommendedSellingPrice.eq(recommendedSellingPrice);
    }

    // Pageable에 담겨 온 정렬 조건을 QueryDSL의 OrderSpecifier로 변환한다. 지원하지 않는 프로퍼티나 미지정 시 id desc로 정렬.
    private OrderSpecifier<?>[] toOrderSpecifiers(Sort sort) {
        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();

        for (Sort.Order order : sort) {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            switch (order.getProperty()) {
                case "name" -> orderSpecifiers.add(new OrderSpecifier<>(direction, product.name));
                case "recommendedSellingPrice" -> orderSpecifiers.add(new OrderSpecifier<>(direction, product.recommendedSellingPrice));
                case "createdAt" -> orderSpecifiers.add(new OrderSpecifier<>(direction, product.createdAt));
                case "id" -> orderSpecifiers.add(new OrderSpecifier<>(direction, product.id));
                default -> { }
            }
        }

        return orderSpecifiers.isEmpty()
                ? new OrderSpecifier<?>[]{ product.id.desc() }
                : orderSpecifiers.toArray(new OrderSpecifier<?>[0]);
    }
}

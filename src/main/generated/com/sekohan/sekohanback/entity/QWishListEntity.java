package com.sekohan.sekohanback.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWishListEntity is a Querydsl query type for WishListEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWishListEntity extends EntityPathBase<WishListEntity> {

    private static final long serialVersionUID = 932783631L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWishListEntity wishListEntity = new QWishListEntity("wishListEntity");

    public final DateTimePath<java.time.LocalDateTime> localDateTime = createDateTime("localDateTime", java.time.LocalDateTime.class);

    public final QProductEntity productEntity;

    public final QUserEntity userEntity;

    public final NumberPath<Long> wishListId = createNumber("wishListId", Long.class);

    public QWishListEntity(String variable) {
        this(WishListEntity.class, forVariable(variable), INITS);
    }

    public QWishListEntity(Path<? extends WishListEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWishListEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWishListEntity(PathMetadata metadata, PathInits inits) {
        this(WishListEntity.class, metadata, inits);
    }

    public QWishListEntity(Class<? extends WishListEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productEntity = inits.isInitialized("productEntity") ? new QProductEntity(forProperty("productEntity"), inits.get("productEntity")) : null;
        this.userEntity = inits.isInitialized("userEntity") ? new QUserEntity(forProperty("userEntity")) : null;
    }

}


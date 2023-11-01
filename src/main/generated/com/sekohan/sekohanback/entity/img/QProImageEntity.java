package com.sekohan.sekohanback.entity.img;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProImageEntity is a Querydsl query type for ProImageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProImageEntity extends EntityPathBase<ProImageEntity> {

    private static final long serialVersionUID = 67605027L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProImageEntity proImageEntity = new QProImageEntity("proImageEntity");

    public final StringPath path = createString("path");

    public final com.sekohan.sekohanback.entity.QProductEntity productEntity;

    public final NumberPath<Long> proImgId = createNumber("proImgId", Long.class);

    public QProImageEntity(String variable) {
        this(ProImageEntity.class, forVariable(variable), INITS);
    }

    public QProImageEntity(Path<? extends ProImageEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProImageEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProImageEntity(PathMetadata metadata, PathInits inits) {
        this(ProImageEntity.class, metadata, inits);
    }

    public QProImageEntity(Class<? extends ProImageEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productEntity = inits.isInitialized("productEntity") ? new com.sekohan.sekohanback.entity.QProductEntity(forProperty("productEntity"), inits.get("productEntity")) : null;
    }

}


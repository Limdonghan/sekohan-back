package com.sekohan.sekohanback.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QServiceEntity is a Querydsl query type for ServiceEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QServiceEntity extends EntityPathBase<ServiceEntity> {

    private static final long serialVersionUID = -255822287L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QServiceEntity serviceEntity = new QServiceEntity("serviceEntity");

    public final QProductEntity productEntity;

    public final NumberPath<Long> serviceId = createNumber("serviceId", Long.class);

    public final QUserEntity userEntity;

    public QServiceEntity(String variable) {
        this(ServiceEntity.class, forVariable(variable), INITS);
    }

    public QServiceEntity(Path<? extends ServiceEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QServiceEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QServiceEntity(PathMetadata metadata, PathInits inits) {
        this(ServiceEntity.class, metadata, inits);
    }

    public QServiceEntity(Class<? extends ServiceEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productEntity = inits.isInitialized("productEntity") ? new QProductEntity(forProperty("productEntity"), inits.get("productEntity")) : null;
        this.userEntity = inits.isInitialized("userEntity") ? new QUserEntity(forProperty("userEntity")) : null;
    }

}

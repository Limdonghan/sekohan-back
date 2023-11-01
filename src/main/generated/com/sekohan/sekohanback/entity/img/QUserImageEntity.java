package com.sekohan.sekohanback.entity.img;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserImageEntity is a Querydsl query type for UserImageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserImageEntity extends EntityPathBase<UserImageEntity> {

    private static final long serialVersionUID = 978826401L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserImageEntity userImageEntity = new QUserImageEntity("userImageEntity");

    public final StringPath path = createString("path");

    public final NumberPath<Long> uImgId = createNumber("uImgId", Long.class);

    public final com.sekohan.sekohanback.entity.QUserEntity user;

    public QUserImageEntity(String variable) {
        this(UserImageEntity.class, forVariable(variable), INITS);
    }

    public QUserImageEntity(Path<? extends UserImageEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserImageEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserImageEntity(PathMetadata metadata, PathInits inits) {
        this(UserImageEntity.class, metadata, inits);
    }

    public QUserImageEntity(Class<? extends UserImageEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.sekohan.sekohanback.entity.QUserEntity(forProperty("user")) : null;
    }

}


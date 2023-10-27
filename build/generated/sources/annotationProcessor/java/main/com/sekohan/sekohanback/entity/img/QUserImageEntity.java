package com.sekohan.sekohanback.entity.img;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserImageEntity is a Querydsl query type for UserImageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserImageEntity extends EntityPathBase<UserImageEntity> {

    private static final long serialVersionUID = 978826401L;

    public static final QUserImageEntity userImageEntity = new QUserImageEntity("userImageEntity");

    public final StringPath path = createString("path");

    public final NumberPath<Long> uImgId = createNumber("uImgId", Long.class);

    public QUserImageEntity(String variable) {
        super(UserImageEntity.class, forVariable(variable));
    }

    public QUserImageEntity(Path<? extends UserImageEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserImageEntity(PathMetadata metadata) {
        super(UserImageEntity.class, metadata);
    }

}


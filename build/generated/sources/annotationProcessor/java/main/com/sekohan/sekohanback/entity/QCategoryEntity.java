package com.sekohan.sekohanback.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCategoryEntity is a Querydsl query type for CategoryEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategoryEntity extends EntityPathBase<CategoryEntity> {

    private static final long serialVersionUID = 1160266344L;

    public static final QCategoryEntity categoryEntity = new QCategoryEntity("categoryEntity");

    public final StringPath catClass = createString("catClass");

    public final StringPath catDetailClass = createString("catDetailClass");

    public final NumberPath<Integer> catDetailLev = createNumber("catDetailLev", Integer.class);

    public final NumberPath<Integer> catDetailParentLev = createNumber("catDetailParentLev", Integer.class);

    public final NumberPath<Long> catId = createNumber("catId", Long.class);

    public final NumberPath<Integer> catLev = createNumber("catLev", Integer.class);

    public final NumberPath<Integer> catParentLev = createNumber("catParentLev", Integer.class);

    public final StringPath groupId = createString("groupId");

    public QCategoryEntity(String variable) {
        super(CategoryEntity.class, forVariable(variable));
    }

    public QCategoryEntity(Path<? extends CategoryEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategoryEntity(PathMetadata metadata) {
        super(CategoryEntity.class, metadata);
    }

}


package com.sekohan.sekohanback.entity.admin;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEventEntity is a Querydsl query type for EventEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventEntity extends EntityPathBase<EventEntity> {

    private static final long serialVersionUID = -430882377L;

    public static final QEventEntity eventEntity = new QEventEntity("eventEntity");

    public final NumberPath<Long> eId = createNumber("eId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> localDateTime = createDateTime("localDateTime", java.time.LocalDateTime.class);

    public final StringPath name = createString("name");

    public final StringPath path = createString("path");

    public QEventEntity(String variable) {
        super(EventEntity.class, forVariable(variable));
    }

    public QEventEntity(Path<? extends EventEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEventEntity(PathMetadata metadata) {
        super(EventEntity.class, metadata);
    }

}


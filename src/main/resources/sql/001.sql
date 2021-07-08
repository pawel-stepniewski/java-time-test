create table if not exists sample_resources (
    ordering                        bigserial not null primary key,
    resource_id                     varchar not null unique,
    external_resource_id            varchar not null unique,
    key                             varchar not null unique,
    value                           varchar not null unique,
    created_timestamp               timestamp with time zone not null unique
);
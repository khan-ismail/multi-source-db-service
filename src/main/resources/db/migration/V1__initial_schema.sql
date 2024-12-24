CREATE TABLE agent_queue_mappings
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    agent_id   BIGINT NULL,
    queue_id   BIGINT NULL,
    CONSTRAINT pk_agent_queue_mappings PRIMARY KEY (id)
);

CREATE TABLE agent_queues
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_at    datetime NULL,
    updated_at    datetime NULL,
    queue_name    VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    CONSTRAINT pk_agent_queues PRIMARY KEY (id)
);

CREATE TABLE agents
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    name       VARCHAR(255) NULL,
    skills     VARCHAR(255) NULL,
    `role`     VARCHAR(255) NULL,
    status     VARCHAR(255) NULL,
    CONSTRAINT pk_agents PRIMARY KEY (id)
);

CREATE TABLE analyses
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    created_at     datetime NULL,
    updated_at     datetime NULL,
    interaction_id BIGINT NULL,
    CONSTRAINT pk_analyses PRIMARY KEY (id)
);

CREATE TABLE connection_config
(
    id                   BIGINT AUTO_INCREMENT NOT NULL,
    mysql                JSON         NOT NULL,
    api_domain           VARCHAR(50)  NOT NULL,
    xmpp_domain          VARCHAR(200) NOT NULL,
    signal_domain        VARCHAR(150) NULL,
    xmpp_config          JSON         NOT NULL,
    license_key          VARCHAR(200) NOT NULL,
    storage              VARCHAR(6)   NOT NULL,
    user_max_sessions    INT          NOT NULL,
    aws_details          JSON         NOT NULL,
    oci_details          JSON         NOT NULL,
    android_push_details JSON NULL,
    ios_push_details     JSON NULL,
    web_push_details     JSON NULL,
    minio_details        JSON NULL,
    email_config         JSON NULL,
    CONSTRAINT pk_connection_config PRIMARY KEY (id)
);

CREATE TABLE current_state
(
    tenant_id VARCHAR(255) NOT NULL,
    data      VARCHAR(255) NULL,
    CONSTRAINT pk_currentstate PRIMARY KEY (tenant_id)
);

CREATE TABLE customer
(
    customer_id BIGINT NOT NULL,
    name        VARCHAR(255) NULL,
    CONSTRAINT pk_customer PRIMARY KEY (customer_id)
);

CREATE TABLE interaction_agent_mappings
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    created_at     datetime NULL,
    updated_at     datetime NULL,
    interaction_id BIGINT NULL,
    agent_id       BIGINT NULL,
    join_time      datetime NULL,
    leave_time     datetime NULL,
    CONSTRAINT pk_interaction_agent_mappings PRIMARY KEY (id)
);

CREATE TABLE interaction_logs
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    created_at     datetime NULL,
    updated_at     datetime NULL,
    interaction_id BIGINT NULL,
    transcript     TEXT NULL,
    recording_url  VARCHAR(255) NULL,
    CONSTRAINT pk_interaction_logs PRIMARY KEY (id)
);

CREATE TABLE interactions
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    created_at        datetime NULL,
    updated_at        datetime NULL,
    type              VARCHAR(255) NULL,
    start_time        datetime NULL,
    end_time          datetime NULL,
    status            VARCHAR(255) NULL,
    customer_feedback VARCHAR(255) NULL,
    CONSTRAINT pk_interactions PRIMARY KEY (id)
);

CREATE TABLE product
(
    product_id BIGINT NOT NULL,
    name       VARCHAR(255) NULL,
    CONSTRAINT pk_product PRIMARY KEY (product_id)
);

CREATE TABLE service_agent_queue_mappings
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    service_id BIGINT NULL,
    queue_id   BIGINT NULL,
    CONSTRAINT pk_service_agent_queue_mappings PRIMARY KEY (id)
);

CREATE TABLE services
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    created_at        datetime NULL,
    updated_at        datetime NULL,
    service_name      VARCHAR(255) NULL,
    `description`     VARCHAR(255) NULL,
    service_level     VARCHAR(255) NULL,
    parent_service_id BIGINT NULL,
    CONSTRAINT pk_services PRIMARY KEY (id)
);

ALTER TABLE analyses
    ADD CONSTRAINT uc_analyses_interaction UNIQUE (interaction_id);

ALTER TABLE connection_config
    ADD CONSTRAINT uc_connection_config_api_domain UNIQUE (api_domain);

ALTER TABLE interaction_logs
    ADD CONSTRAINT uc_interaction_logs_interaction UNIQUE (interaction_id);

ALTER TABLE agent_queue_mappings
    ADD CONSTRAINT FK_AGENT_QUEUE_MAPPINGS_ON_AGENT FOREIGN KEY (agent_id) REFERENCES agents (id);

ALTER TABLE agent_queue_mappings
    ADD CONSTRAINT FK_AGENT_QUEUE_MAPPINGS_ON_QUEUE FOREIGN KEY (queue_id) REFERENCES agent_queues (id);

ALTER TABLE analyses
    ADD CONSTRAINT FK_ANALYSES_ON_INTERACTION FOREIGN KEY (interaction_id) REFERENCES interactions (id);

ALTER TABLE interaction_agent_mappings
    ADD CONSTRAINT FK_INTERACTION_AGENT_MAPPINGS_ON_AGENT FOREIGN KEY (agent_id) REFERENCES agents (id);

ALTER TABLE interaction_agent_mappings
    ADD CONSTRAINT FK_INTERACTION_AGENT_MAPPINGS_ON_INTERACTION FOREIGN KEY (interaction_id) REFERENCES interactions (id);

ALTER TABLE interaction_logs
    ADD CONSTRAINT FK_INTERACTION_LOGS_ON_INTERACTION FOREIGN KEY (interaction_id) REFERENCES interactions (id);

ALTER TABLE services
    ADD CONSTRAINT FK_SERVICES_ON_PARENT_SERVICE FOREIGN KEY (parent_service_id) REFERENCES services (id);

ALTER TABLE service_agent_queue_mappings
    ADD CONSTRAINT FK_SERVICE_AGENT_QUEUE_MAPPINGS_ON_QUEUE FOREIGN KEY (queue_id) REFERENCES agent_queues (id);

ALTER TABLE service_agent_queue_mappings
    ADD CONSTRAINT FK_SERVICE_AGENT_QUEUE_MAPPINGS_ON_SERVICE FOREIGN KEY (service_id) REFERENCES services (id);
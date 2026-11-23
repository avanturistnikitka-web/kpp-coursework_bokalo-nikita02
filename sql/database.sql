CREATE DATABASE IF NOT EXISTS eco_incidents
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE eco_incidents;

CREATE TABLE IF NOT EXISTS locations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(32) NOT NULL UNIQUE,
    name VARCHAR(120) NOT NULL,
    region VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS incidents (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    location_id BIGINT NOT NULL,
    title VARCHAR(150) NOT NULL,
    description TEXT,
    state VARCHAR(20) NOT NULL DEFAULT 'REGISTERED',
    f1 DOUBLE NOT NULL DEFAULT 0.0,
    f2 DOUBLE NOT NULL DEFAULT 0.0,
    f3 DOUBLE NOT NULL DEFAULT 0.0,
    risk_index DOUBLE NOT NULL DEFAULT 0.0,
    CONSTRAINT fk_incidents_location
        FOREIGN KEY (location_id) REFERENCES locations(id)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT chk_incidents_state
        CHECK (state IN ('REGISTERED', 'VERIFIED', 'RESPONSE', 'CLOSED'))
);

CREATE TABLE IF NOT EXISTS observations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    incident_id BIGINT NOT NULL,
    metric_name VARCHAR(80) NOT NULL,
    value DOUBLE NOT NULL,
    threshold DOUBLE NOT NULL,
    duration_score DOUBLE NOT NULL,
    scale_score DOUBLE NOT NULL,
    CONSTRAINT fk_observations_incident
        FOREIGN KEY (incident_id) REFERENCES incidents(id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS response_actions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    incident_id BIGINT NOT NULL,
    action_type VARCHAR(100) NOT NULL,
    executor VARCHAR(100) NOT NULL,
    planned_date DATE NOT NULL,
    CONSTRAINT fk_actions_incident
        FOREIGN KEY (incident_id) REFERENCES incidents(id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

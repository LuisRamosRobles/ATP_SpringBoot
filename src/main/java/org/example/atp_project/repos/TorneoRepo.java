package org.example.atp_project.repos;

import org.example.atp_project.models.Torneo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TorneoRepo extends JpaRepository<Torneo, Long>, JpaSpecificationExecutor<Torneo> {
}

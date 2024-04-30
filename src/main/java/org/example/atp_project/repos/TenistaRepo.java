package org.example.atp_project.repos;

import org.example.atp_project.models.Tenista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TenistaRepo extends JpaRepository<Tenista, Long>, JpaSpecificationExecutor<Tenista> {
}

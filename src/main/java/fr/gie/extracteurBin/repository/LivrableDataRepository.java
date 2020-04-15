package fr.gie.extracteurBin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.gie.extracteurBin.model.LivrableData;

@Repository
public interface LivrableDataRepository extends JpaRepository<LivrableData, Long>{

}

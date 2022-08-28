package com.magageEquipment.ufrpe.repositorys;

import com.magageEquipment.ufrpe.entitys.Salas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SalasRepository extends JpaRepository<Salas, UUID> {
}
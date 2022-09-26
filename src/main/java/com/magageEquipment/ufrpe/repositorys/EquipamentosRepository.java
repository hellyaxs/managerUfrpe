package com.magageEquipment.ufrpe.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import com.magageEquipment.ufrpe.entitys.Equipamentos;


import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface EquipamentosRepository extends JpaRepository<Equipamentos, Long> {
}
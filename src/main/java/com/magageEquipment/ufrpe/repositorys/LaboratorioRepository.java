package com.magageEquipment.ufrpe.repositorys;

import com.magageEquipment.ufrpe.entitys.Laboratorios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LaboratorioRepository extends JpaRepository<Laboratorios, UUID> {
}
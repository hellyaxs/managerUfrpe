package com.magageEquipment.ufrpe.repositorys;

import com.magageEquipment.ufrpe.entitys.AluguelEquipamentos;
import com.magageEquipment.ufrpe.entitys.Equipamentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AluguelRepository extends JpaRepository<AluguelEquipamentos, UUID> {
}
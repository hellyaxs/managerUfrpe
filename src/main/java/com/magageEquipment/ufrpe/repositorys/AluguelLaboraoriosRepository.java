package com.magageEquipment.ufrpe.repositorys;

import com.magageEquipment.ufrpe.entitys.AluguelLaboraorios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AluguelLaboraoriosRepository extends JpaRepository<AluguelLaboraorios, Long> {
    List<AluguelLaboraorios> findByLaboratorios_IdOrderByNameAsc(Long id);


}
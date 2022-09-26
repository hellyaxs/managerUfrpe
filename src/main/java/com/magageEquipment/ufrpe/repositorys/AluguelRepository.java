package com.magageEquipment.ufrpe.repositorys;

import com.magageEquipment.ufrpe.entitys.AluguelEquipamentos;
import com.magageEquipment.ufrpe.entitys.Equipamentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface AluguelRepository extends JpaRepository<AluguelEquipamentos, Long> {

    @Query("select a from AluguelEquipamentos a where a.solicitacao = ?1")
    List<AluguelEquipamentos> findBySolicitation(LocalDateTime solicitacao);

    List<AluguelEquipamentos> findByEquipamento_Id(Long id);





}
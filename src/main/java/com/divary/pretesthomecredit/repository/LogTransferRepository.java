package com.divary.pretesthomecredit.repository;

import com.divary.pretesthomecredit.enums.LogTransferEnum;
import com.divary.pretesthomecredit.model.LogTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogTransferRepository extends JpaRepository<LogTransfer, String> {

    List<LogTransfer> findAllByUser_IdAndTimeBetweenAndType(String id, LocalDateTime startTime, LocalDateTime endTime, LogTransferEnum type);

}
package com.divary.pretesthomecredit.service.implementation;

import com.divary.pretesthomecredit.enums.LogTransferEnum;
import com.divary.pretesthomecredit.model.LogTransfer;
import com.divary.pretesthomecredit.model.User;
import com.divary.pretesthomecredit.repository.LogTransferRepository;
import com.divary.pretesthomecredit.service.LogTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogTransferImpl implements LogTransferService {

    @Autowired
    LogTransferRepository logTransferRepository;

    @Override
    public LogTransfer create(LogTransferEnum type, User user, long amount, LocalDateTime time) {

        LogTransfer logTransfer = new LogTransfer();

        logTransfer.setUser(user);
        logTransfer.setType(type);
        logTransfer.setAmount(amount);
        logTransfer.setTime(time);
        return logTransfer;

    }

    @Override
    public List<LogTransfer> findLogTransferToday(String id, String time) {

        LocalDate today = LocalDate.now();

        return findLogToday(id, today, LogTransferEnum.SENT);
    }

    public List<LogTransfer> findLogToday(String id, LocalDate time, LogTransferEnum type) {
        return logTransferRepository.findAllByUser_IdAndTimeBetweenAndType(id, LocalDateTime.parse(time + "T00:00:00.000"), LocalDateTime.parse(time + "T23:59:59.999"), type);
    }
}

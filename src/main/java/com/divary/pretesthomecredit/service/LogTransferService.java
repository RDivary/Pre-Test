package com.divary.pretesthomecredit.service;

import com.divary.pretesthomecredit.enums.LogTransferEnum;
import com.divary.pretesthomecredit.model.LogTransfer;
import com.divary.pretesthomecredit.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface LogTransferService {

    LogTransfer create(LogTransferEnum type, User user, long amount, LocalDateTime time);

    List<LogTransfer> findLogTransferToday(String id, String time);
}

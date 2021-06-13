package com.divary.pretesthomecredit.service;

import com.divary.pretesthomecredit.dto.request.AddBalanceRequest;
import com.divary.pretesthomecredit.dto.request.CreateUSerRequest;
import com.divary.pretesthomecredit.dto.request.TransferRequest;
import com.divary.pretesthomecredit.model.User;

public interface UserService {

    User save(User user);

    User createOrUpdate(CreateUSerRequest form, String id);

    User findById(String id, String messageError);

    User addBalance(String id, AddBalanceRequest form);

    User transfer(TransferRequest form, String id);
}

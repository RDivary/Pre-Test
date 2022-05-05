package com.divary.pretesthomecredit.service.implementation;

import com.divary.pretesthomecredit.dto.request.AddBalanceRequest;
import com.divary.pretesthomecredit.dto.request.CreateUSerRequest;
import com.divary.pretesthomecredit.dto.request.TransferRequest;
import com.divary.pretesthomecredit.enums.LogTransferEnum;
import com.divary.pretesthomecredit.exception.NotFoundException;
import com.divary.pretesthomecredit.exception.UnprocessableEntity;
import com.divary.pretesthomecredit.model.LogTransfer;
import com.divary.pretesthomecredit.model.User;
import com.divary.pretesthomecredit.repository.LogTransferRepository;
import com.divary.pretesthomecredit.repository.UserRepository;
import com.divary.pretesthomecredit.service.LogTransferService;
import com.divary.pretesthomecredit.service.UserService;
import com.divary.pretesthomecredit.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class UserImpl implements UserService {

//    @Autowired
    UserRepository userRepository;

//    @Autowired
    LogTransferService logTransferService;

//    @Autowired
    Random random;

//    @Autowired
//    LocalDateTime localDateTime;

    @Autowired
    public UserImpl(UserRepository userRepository, LogTransferService logTransferService, Random random) {
        this.userRepository = userRepository;
        this.logTransferService = logTransferService;
        this.random = random;
//        this.localDateTime = localDateTime;
    }

    @Override
    public User save(User user) {

        LocalDateTime now = LocalDateTime.now();

//        if (user.getCreateDate() == null) user.setCreateDate(now);
//        user.setUpdateDate(now);

        return userRepository.save(user);
    }

    @Override
    public User createOrUpdate(CreateUSerRequest form, String id) {

        User user;

        if (id == null) {
            user = new User();

            do {
                StringBuilder accountNumber = new StringBuilder();

                for(int i = 1; i < 8; i++){
                    accountNumber.append(random.nextInt(10));
                }

                user.setAccountNumber(accountNumber.toString());

            } while (checkDuplicateAccountNumber(user.getAccountNumber()));

        } else {
            user = findById(id, null);
        }

        user.setUsername(form.getUsername());
        user.setBalance(form.getBalance());

        System.err.println(user);
//        save(user);
        userRepository.save(user);
        System.err.println(user);
        System.err.println("===============================");
        return user;
    }

    @Override
    public User findById(String id, String messageError) {

        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(messageError != null && !messageError.isEmpty() ? messageError : "User"));
    }

    @Override
    public User addBalance(String id, AddBalanceRequest form) {

        User user = findById(id, null);

        user.setBalance(user.getBalance() + form.getAddBalanceAmount());

        LocalDateTime time = LocalDateTime.now();

        LogTransfer logTransferSent = logTransferService.create(LogTransferEnum.ADD_BALANCE, user, form.getAddBalanceAmount(), time);

        user.getLogTransfers().add(logTransferSent);

//        save(user);

        return save(user);

    }

    @Override
    public User transfer(TransferRequest form, String id) {

        User userSent = findById(id, "Sender");
        User userReceive = findByAccountNumber(form.getAccountNumberReceive(), "Receiver");

        if (id.equalsIgnoreCase(userReceive.getId())) throw new UnprocessableEntity(Constants.ERR_TRANSFER_YOURSELF);

        if (userSent.getBalance() < form.getAmount()) throw new UnprocessableEntity(Constants.ERR_BALANCE_INSUFFICIENT);
        if (form.getAmount() > 50000) throw new UnprocessableEntity(Constants.ERR_TRANSFER_BALANCE_LIMIT);

        LocalDateTime time = LocalDateTime.now();

        List<LogTransfer> logTransfers = logTransferService.findLogTransferToday(id, LocalDate.now().toString());

        long todaySent = logTransfers.stream()
                .reduce(0L, (subtotal, logTransfer) -> subtotal + logTransfer.getAmount(), Long::sum);

        if (todaySent >= 50000) throw new UnprocessableEntity(Constants.ERR_TRANSFER_REACH_LIMIT);

        if (todaySent + form.getAmount() > 50000)
            throw new UnprocessableEntity(Constants.ERR_TRANSFER_BALANCE_LIMIT);

        userSent.setBalance(userSent.getBalance() - form.getAmount());
        userReceive.setBalance(userReceive.getBalance() + form.getAmount());

        LogTransfer logTransferSent = logTransferService.create(LogTransferEnum.SENT, userSent, form.getAmount(), time);
        LogTransfer logTransferReceive = logTransferService.create(LogTransferEnum.RECEIVE, userReceive, form.getAmount(), time);

        userSent.getLogTransfers().add(logTransferSent);
        userReceive.getLogTransfers().add(logTransferReceive);

        save(userSent, time);
        save(userReceive, time);

        return userSent;
    }

    private User save(User user, LocalDateTime time) {

        if (user.getCreateDate() == null) user.setCreateDate(time);
        user.setUpdateDate(time);

        return userRepository.save(user);
    }

    private User findByAccountNumber(String accountNumber, String messageError){
        return userRepository.findByAccountNumberEquals(accountNumber).orElseThrow(() -> new NotFoundException(messageError != null && !messageError.isEmpty() ? messageError : "User"));
    }

    private boolean checkDuplicateAccountNumber(String accountNumber) {
        return userRepository.findByAccountNumberEquals(accountNumber).isPresent();
    }
}

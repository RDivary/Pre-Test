package com.divary.pretesthomecredit.controller;

import com.divary.pretesthomecredit.dto.request.AddBalanceRequest;
import com.divary.pretesthomecredit.dto.request.CreateUSerRequest;
import com.divary.pretesthomecredit.dto.request.TransferRequest;
import com.divary.pretesthomecredit.dto.response.AddBalanceResponse;
import com.divary.pretesthomecredit.dto.response.BaseResponse;
import com.divary.pretesthomecredit.dto.response.TransferResponse;
import com.divary.pretesthomecredit.dto.response.CreatedUserResponse;
import com.divary.pretesthomecredit.model.User;
import com.divary.pretesthomecredit.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @PostMapping()
    @ApiOperation("Create User")
    public ResponseEntity<BaseResponse<?>> createUser(
            @RequestBody CreateUSerRequest form
    ) {

        User user = userService.createOrUpdate(form, null);

        return super.getResponseCreated(new CreatedUserResponse().setId(user.getId()).build(), "User Created");
    }

    @PostMapping("/transfer")
    @ApiOperation("Transfer")
    public ResponseEntity<BaseResponse<?>> transfer(
            @RequestHeader(name = "id") String senderId,
            @RequestBody TransferRequest form
    ) {

        User user = userService.transfer(form, senderId);

        TransferResponse data = new TransferResponse()
                .setFrom(user.getAccountNumber())
                .setTo(form.getAccountNumberReceive())
                .setAmount(form.getAmount());

        return super.getResponseSuccess(data, "Transfer success");
    }

    @PostMapping("/add-balance")
    @ApiOperation("Transfer")
    public ResponseEntity<BaseResponse<?>> transfer(
            @RequestHeader(name = "id") String id,
            @RequestBody AddBalanceRequest form
    ) {

        User user = userService.addBalance(id, form);

        AddBalanceResponse data = new AddBalanceResponse()
                .setBalance(user.getBalance());

        return super.getResponseSuccess(data, "Your balance updated");
    }
}

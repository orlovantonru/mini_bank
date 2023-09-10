package com.minibank.demo.rest;

import com.minibank.demo.model.Account;
import com.minibank.demo.model.User;
import com.minibank.demo.service.AccountService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bank/v1/account/")
public class AccountRestControllerV1 {

    private AccountService accountService;

    public AccountRestControllerV1(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(@RequestBody Account account){
        return accountService.saveAccount(account);
    }

    @GetMapping
    public List<Account> getAllAccounts(){
        return accountService.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Account> getAccount(@PathVariable("id") Long accountId) {
        /* if (featire_flags_getuser.equals("1")) {*/
        if (accountId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Account account = this.accountService.findById(accountId);

        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(account, HttpStatus.OK);
    }
      /*  else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

    /*@GetMapping("{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable("id") long accountId){
        return accountService.findById(accountId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }*/


    @RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Account> updateAccount(@RequestBody @Valid Account account, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (account == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.accountService.saveAccount(account);

        return new ResponseEntity<>(account, headers, HttpStatus.OK);
    }

  /*  @PutMapping("{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable("id") long accountId,
                                                   @RequestBody Account account){
        return accountService.findById(accountId)
                .map(savedAccount -> {

                    savedAccount.setFirstName(account.getFirstName());
                    savedAccount.setLastName(account.getLastName());
                    savedAccount.setEmail(account.getEmail());

                    Account updatedEmployee = accountService.saveAccount(savedAccount);
                    return new ResponseEntity<>(updateAccount, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }*/

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable("id") long accountId){

        accountService.deleteById(accountId);

        return new ResponseEntity<String>("Account deleted successfully!.", HttpStatus.OK);

    }
}

package com.banking.accountservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.accountservice.dto.AccountDTO;
import com.banking.accountservice.service.AccountService;

@RestController
@RequestMapping("/api/account")
public class AccountController {

	@Autowired
	private AccountService service;

	@PostMapping
	public ResponseEntity<?> createAccount(@RequestBody AccountDTO dto) {
		service.createAccount(dto);
		return new ResponseEntity<>("Account created with:"+dto.getBalance(), HttpStatus.CREATED);
	}

	@GetMapping("{id}")
	public ResponseEntity<AccountDTO> getById(@PathVariable Long id) {
		return ResponseEntity.ok(service.getById(id));
	}
	
	@GetMapping("/number/{accountNumber}")
	public ResponseEntity<AccountDTO> getByAccountNumber(@PathVariable String accountNumber) {
	    return ResponseEntity.ok(service.getByAccountNumber(accountNumber));
	}

	@GetMapping
	public ResponseEntity<List<AccountDTO>> getAllAccounts() {
		return ResponseEntity.ok(service.getAllAccounts());
	}
	
	@PutMapping("debit/{accountNumber}")
	public ResponseEntity<AccountDTO> debitAmount(
			@PathVariable String accountNumber,
			@RequestParam double amount) {
		
		return ResponseEntity.ok(service.debitAmount(accountNumber, amount));

	}
	@PutMapping("credit/{accountNumber}")
	public ResponseEntity<AccountDTO> creditAmount(
			@PathVariable String accountNumber,
			@RequestParam double amount) {
		
		return ResponseEntity.ok(service.creditAmount(accountNumber, amount));

	}
}

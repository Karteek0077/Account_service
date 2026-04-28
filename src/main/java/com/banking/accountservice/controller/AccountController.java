package com.banking.accountservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.accountservice.dto.AccountDTO;
import com.banking.accountservice.service.AccountService;

@RestController
@RequestMapping("/api/account")
public class AccountController {

	@Autowired
	private AccountService service;

	@PostMapping
	public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO dto) {
		return ResponseEntity.ok(service.createAccount(dto));
	}

	@GetMapping("{id}")
	public ResponseEntity<AccountDTO> getById(@PathVariable Long id) {
		return ResponseEntity.ok(service.getById(id));
	}

	@GetMapping
	public ResponseEntity<List<AccountDTO>> getAllAccounts() {
		return ResponseEntity.ok(service.getAllAccounts());
	}
}

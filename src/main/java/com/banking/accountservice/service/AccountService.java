package com.banking.accountservice.service;

import java.util.List;
import java.util.stream.Collectors;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.accountservice.dto.AccountDTO;
import com.banking.accountservice.repository.AccountRepository;
import com.banking.accountservice.entity.Account;
import com.banking.accountservice.exception.AccountNotFoundException;
import com.banking.accountservice.exception.InsufficientBalanceException;


@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public AccountDTO createAccount(AccountDTO dto) {
		Account account = new Account();
		account.setAccountNumber(dto.getAccountNumber());
		account.setAccountHolderName(dto.getAccountHolderName());
		account.setAccountType(dto.getAccountType());
		account.setBalance(dto.getBalance());
		Account saved = accountRepository.save(account);
		return mapToDTO(saved);
	}

	public AccountDTO getById(Long id) {
		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found with id: "+id));
		return mapToDTO(account);
	}

	public List<AccountDTO> getAllAccounts() {
		return accountRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	private AccountDTO mapToDTO(Account account) {
		AccountDTO dto = new AccountDTO();
		dto.setId(account.getId());
		dto.setAccountHolderName(account.getAccountHolderName());
		dto.setAccountNumber(account.getAccountNumber());
		dto.setAccountType(account.getAccountType());
		dto.setBalance(account.getBalance());
		return dto;
	}

	public AccountDTO getByAccountNumber(String accountNumber) {
	    Account account = accountRepository.findByAccountNumber(accountNumber)
	            .orElseThrow(() -> new AccountNotFoundException("Account not found with: "+accountNumber));
	    return mapToDTO(account);
	}
	public AccountDTO debitAmount(String accountNumber, Double amount){
		
		Account account=accountRepository.findByAccountNumber(accountNumber).orElseThrow(
								()->new AccountNotFoundException("Account not found with :"+ accountNumber));
		
		if(amount>account.getBalance()) {
			throw new InsufficientBalanceException("InsufficientBalance! AvailableBalance :"+account.getBalance());
		}
		account.setBalance(account.getBalance()-amount);
		Account saved=accountRepository.save(account);
		return mapToDTO(saved);
	}
	
	public AccountDTO creditAmount(String accountNumber, Double amount) {
		Account account=accountRepository.findByAccountNumber(accountNumber).orElseThrow(()->
						new AccountNotFoundException("Account not found with number: "+accountNumber));
		account.setBalance(account.getBalance()+ amount);
		Account saved=accountRepository.save(account);
		return mapToDTO(saved);
	}
}

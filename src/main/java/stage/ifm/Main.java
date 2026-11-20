package stage.ifm;

import stage.ifm.model.BankAccount;
import stage.ifm.repository.AccountRepositoryImpl;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        AccountRepositoryImpl accountRepository = new AccountRepositoryImpl();
        accountRepository.populateData();
        List<BankAccount> bankAccounts = accountRepository.findAll();
        bankAccounts.forEach(System.out::println);
    }
}
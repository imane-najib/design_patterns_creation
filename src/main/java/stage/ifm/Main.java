package stage.ifm;

import stage.ifm.model.AccountType;
import stage.ifm.model.BankAccount;
import stage.ifm.repository.AccountRepositoryImpl;
import stage.ifm.util.JsonSerializer;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        JsonSerializer<BankAccount> bankAccountJsonSerializer = new JsonSerializer<BankAccount>() ;
        AccountRepositoryImpl accountRepository = AccountRepositoryImpl.getInstance();
        accountRepository.populateData();
        List<BankAccount> bankAccounts = accountRepository.findAll();
        bankAccounts.stream().map(bankAccountJsonSerializer::toJson).forEach(System.out::println);

        System.out.println("============================");
        BankAccount bankAccount = accountRepository.findById(1L).orElse(null);
        if (bankAccount != null) {
            System.out.println(bankAccountJsonSerializer.toJson(bankAccount));
        }

        System.out.println("============================");

        List<BankAccount> bankAccounts2 = accountRepository.searchAccount(new Predicate<BankAccount>() {
            @Override
            public boolean test(BankAccount bankAccount) {
                return bankAccount.getType().equals(AccountType.CURRENT_ACCOUNT);
            }
        });
        bankAccounts2.stream().map(bankAccountJsonSerializer::toJson).forEach(System.out::println);
    }
}
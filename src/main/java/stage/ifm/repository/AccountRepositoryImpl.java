package stage.ifm.repository;

import stage.ifm.model.AccountStatus;
import stage.ifm.model.AccountType;
import stage.ifm.model.BankAccount;
import stage.ifm.model.BankDirector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AccountRepositoryImpl implements AccountRepository {

    private static final AccountRepositoryImpl accountRepository;

    static {
        System.out.println("Singleton instanciation");
        accountRepository = new AccountRepositoryImpl();
    }
    private AccountRepositoryImpl() {}
    private Map<Long, BankAccount> bankAccountMap = new HashMap<>();
    private long accountsCount=0;
    @Override
    public BankAccount save(BankAccount bankAccount) {
        Long accountId = ++accountsCount;
        bankAccount.setAccountId(accountId);
        bankAccountMap.put(accountId, bankAccount);
        return bankAccount;
    }

    @Override
    public List<BankAccount> findAll() {
        return bankAccountMap.values().stream().toList();
    }

    @Override
    public Optional<BankAccount> findById(Long id) {
        BankAccount bankAccount = bankAccountMap.get(id);
        if(bankAccount == null) return Optional.empty();
        else return Optional.of(bankAccount);

    }

    @Override
    public List<BankAccount> searchAccount(Predicate<BankAccount> predicates) {

        return bankAccountMap.values().stream().filter(predicates).collect(Collectors.toList());
    }

    @Override
    public BankAccount update(BankAccount bankAccount) {
        bankAccountMap.put(bankAccount.getAccountId(), bankAccount);
        return bankAccount;
    }

    @Override
    public void deleteById(Long id) {
        bankAccountMap.remove(id);
    }
    
    public void populateData(){
        for (int i = 0; i < 10; i++) {
             BankAccount bankAccount = BankDirector.Accountsbuilder()
                     .balance(10000+Math.random()*900000)
                     .type(Math.random()>0.5? AccountType.SAVING_ACCOUNT:AccountType.CURRENT_ACCOUNT)
                     .status(Math.random()>0.5? AccountStatus.CREATED:AccountStatus.ACTIVATED)
                     .currency(Math.random()>0.5? "MAD":"USD")
                     .build();

             save(bankAccount);
        }
    }

    public static  AccountRepositoryImpl getInstance() {
        return accountRepository;
    }
}

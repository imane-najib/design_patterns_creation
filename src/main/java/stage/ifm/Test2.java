package stage.ifm;

import stage.ifm.model.AccountStatus;
import stage.ifm.model.AccountType;
import stage.ifm.model.BankAccount;
import stage.ifm.model.Customer;
import stage.ifm.util.JsonSerializer;

public class Test2 {
    public static void main(String[] args) throws CloneNotSupportedException {
        BankAccount account1 = new BankAccount();
        account1.setAccountId(1L);
        account1.setBalance(500);
        account1.setCurrency("MAD");
        account1.setType(AccountType.CURRENT_ACCOUNT);
        account1.setStatus(AccountStatus.ACTIVATED);
        account1.setCustomer(new Customer(1L,"Imane"));

        BankAccount account2 = account1.clone();

        JsonSerializer<BankAccount> jsonSerializer = new JsonSerializer<BankAccount>();
        System.out.println(jsonSerializer.toJson(account1));
        System.out.println(jsonSerializer.toJson(account2));

    }
}

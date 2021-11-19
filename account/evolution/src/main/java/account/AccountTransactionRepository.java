package account;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long> {


    List<AccountTransaction> findByAccountNumber(String accountNumber);

}

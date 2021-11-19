package account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Date;

@Configuration
class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(AccountRepository repository, AccountTransactionRepository accountTransactionRepository) {

		return args -> {


			log.info("Preloading " +
					repository.save
							(new Account(123L, "SgSavings123",
							"Savings", new Date("08/11/2018"), "SGD", new BigDecimal(2300.99))));

			log.info("Preloading " +
					repository.save
							(new Account(1234L, "SgSavings1234",
									"Savings", new Date("08/11/2018"), "SGD", new BigDecimal(2400.99))));


			log.info("Preloading " +
					accountTransactionRepository.save
							(new AccountTransaction(11L, "123", "a", new Date("08/11/2018"), "EUR",
									new BigDecimal(2401.99), new BigDecimal(0), "", "")));


			log.info("Preloading " +
					accountTransactionRepository.save
							(new AccountTransaction(12L, "123", "ab", new Date("08/11/2018"), "EUR",
									new BigDecimal(2401.99), new BigDecimal(0), "", "")));

			log.info("Preloading " +
					accountTransactionRepository.save
							(new AccountTransaction(13L, "123456", "abc", new Date("08/11/2018"), "EUR",
									new BigDecimal(2402.99), new BigDecimal(0), "", "")));

			log.info("Preloading " +
					accountTransactionRepository.save
							(new AccountTransaction(14L, "123", "abcd", new Date("08/11/2018"), "EUR",
									new BigDecimal(2401.99), new BigDecimal(0), "", "")));

			log.info("Preloading " +
					accountTransactionRepository.save
							(new AccountTransaction(15L, "123", "abcde", new Date("08/11/2018"), "EUR",
									new BigDecimal(2401.99), new BigDecimal(0), "", "")));

		};
	}
}

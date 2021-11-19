package account;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
class AccountTransactionController {

	private final AccountTransactionRepository AccountTransactionRepository;
	private final AccountTransactionModelAssembler assembler;

	AccountTransactionController(AccountTransactionRepository AccountTransactionRepository, AccountTransactionModelAssembler assembler) {

		this.AccountTransactionRepository = AccountTransactionRepository;
		this.assembler = assembler;
	}

	@GetMapping("/AccountTransactions")
	CollectionModel<EntityModel<AccountTransaction>> all() {

		List<EntityModel<AccountTransaction>> AccountTransactions = AccountTransactionRepository.
				findAll().stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());

		return CollectionModel.of(AccountTransactions,
				linkTo(methodOn(AccountTransactionController.class).all()).withSelfRel());
	}

	@GetMapping("/AccountTransactions/{accountNumber}")
	CollectionModel<EntityModel<AccountTransaction>> allWithAccountNumber(@PathVariable String accountNumber) {

			List<EntityModel<AccountTransaction>> AccountTransactions = AccountTransactionRepository.
					findByAccountNumber(accountNumber).stream()
					.map(assembler::toModel)
					.collect(Collectors.toList());

			return CollectionModel.of(AccountTransactions,
					linkTo(methodOn(AccountTransactionController.class).allWithAccountNumber(accountNumber)).withSelfRel());

	}

}

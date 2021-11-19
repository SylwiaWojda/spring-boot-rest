package account;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
class AccountController {

	private final AccountRepository repository;

	private final AccountModelAssembler assembler;

	AccountController(AccountRepository repository, AccountModelAssembler assembler) {

		this.repository = repository;
		this.assembler = assembler;
	}


	@GetMapping("/accounts")
	CollectionModel<EntityModel<Account>> all() {

		List<EntityModel<Account>> Accounts = repository.findAll().stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());

		return CollectionModel.of(Accounts, linkTo(methodOn(AccountController.class).all()).withSelfRel());
	}

	@PostMapping("/accounts")
	ResponseEntity<?> newAccount(@RequestBody Account newAccount) {

		EntityModel<Account> entityModel = assembler.toModel(repository.save(newAccount));

		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}


	@GetMapping("/accounts/{id}")
	EntityModel<Account> one(@PathVariable Long id) {

		Account account = repository.findById(id)
				.orElseThrow(() -> new AccountNotFoundException(id));

		return assembler.toModel(account);
	}




}

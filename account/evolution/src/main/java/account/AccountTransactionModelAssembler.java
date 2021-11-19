package account;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class AccountTransactionModelAssembler implements RepresentationModelAssembler<AccountTransaction, EntityModel<AccountTransaction>> {

	@Override
	public EntityModel<AccountTransaction> toModel(AccountTransaction accountTransaction) {

		return EntityModel.of(accountTransaction,
				linkTo(methodOn(AccountTransactionController.class).allWithAccountNumber(accountTransaction.getAccountNumber())).withRel("AccountTransaction"),
				linkTo(methodOn(AccountTransactionController.class).all()).withRel("AccountTransaction"));
	}

}

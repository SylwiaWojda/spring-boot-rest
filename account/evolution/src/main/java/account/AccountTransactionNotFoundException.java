package account;

class AccountTransactionNotFoundException extends RuntimeException {

	AccountTransactionNotFoundException(Long id) {
		super("Could not find AccountTransaction " + id);
	}
}

package account;

class AccountNotFoundException extends RuntimeException {

	AccountNotFoundException(Long id) {
		super("Could not find Account " + id);
	}
}

package com.baijia123.deadlock;

public class TestDeadLock {
	public class DollarAmount implements Comparable<DollarAmount> {
		private long amount;

		public DollarAmount(long amount) {
			super();
			this.amount = amount;
		}

		@Override
		public int compareTo(DollarAmount o) {
			// TODO Auto-generated method stub
			if (this.amount < o.amount) {
				return -1;
			} else if (this.amount > o.amount) {
				return 1;
			} else {
				return 0;
			}
		}

	}

	public class Account {
		public DollarAmount getBalance() {
			return new DollarAmount(3);
		}

		public void debit(DollarAmount o) {

		}

		public void credit(DollarAmount o) {

		}
	}

	private static final Object tieLock = new Object();

	public void transferMoney(final Account fromAcct, Account toAcct,
			final DollarAmount amount) throws Exception {
		class Helper {
			public void transfer() throws Exception {
				if (fromAcct.getBalance().compareTo(amount) < 0) {
					throw new Exception();
				} else {
					fromAcct.debit(amount);
					toAcct.credit(amount);
				}
			}
		}
		int fromHash = System.identityHashCode(fromAcct);
		int toHash = System.identityHashCode(toAcct);
		if (fromHash < toHash) {
			synchronized (fromAcct) {
				synchronized (toAcct) {
					new Helper().transfer();
				}
			}
		} else if (fromHash > toHash) {
			synchronized (toAcct) {
				synchronized (fromAcct) {
					new Helper().transfer();
				}
			}
		} else {
			synchronized (tieLock) {
				synchronized (fromAcct) {
					synchronized (toAcct) {
						new Helper().transfer();
					}
				}
			}
		}
	}
}

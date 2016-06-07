package com.baijia123.executor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TravelQuote {

	private final ExecutorService executor = Executors.newFixedThreadPool(5);

	public class TravelCompany {
		public TravelQuote solicitQuote(TravelInfo travelInfo) {
			return null;
		}
	}

	public class TravelInfo {

	}

	private class QuoteTask implements Callable<TravelQuote> {

		private final TravelCompany company;
		private final TravelInfo travelInfo;

		public QuoteTask(TravelCompany company, TravelInfo travelInfo) {
			this.company = company;
			this.travelInfo = travelInfo;
		}

		public TravelQuote getFailureQuote(Throwable t) {
			return null;
		}

		public TravelQuote getTimeOutQuote(Exception e) {
			return null;
		}

		@Override
		public TravelQuote call() throws Exception {
			// TODO Auto-generated method stub
			return company.solicitQuote(travelInfo);
		}

	}

	public List<TravelQuote> getRankedTravelQuotes(TravelInfo travleInfo,
			Set<TravelCompany> companies, Comparator<TravelQuote> ranking,
			long time, TimeUnit unit) throws InterruptedException {
		List<QuoteTask> tasks = new ArrayList<QuoteTask>();
		for (TravelCompany company : companies) {
			tasks.add(new QuoteTask(company, travleInfo));
		}

		List<Future<TravelQuote>> futures = executor.invokeAll(tasks, time,
				unit);

		List<TravelQuote> quotes = new ArrayList<TravelQuote>(tasks.size());
		Iterator<QuoteTask> taskIter = tasks.iterator();

		for (Future<TravelQuote> f : futures) {
			QuoteTask task = taskIter.next();
			try {
				quotes.add(f.get());
			} catch (ExecutionException e) {
				quotes.add(task.getFailureQuote(e.getCause()));
			} catch (CancellationException e) {
				quotes.add(task.getTimeOutQuote(e));
			}
		}
		Collections.sort(quotes, ranking);
		return quotes;

	}
}

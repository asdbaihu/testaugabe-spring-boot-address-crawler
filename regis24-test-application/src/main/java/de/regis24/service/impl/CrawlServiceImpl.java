package de.regis24.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import de.regis24.app.CrawlerTask;
import de.regis24.app.CustomerData;
import de.regis24.service.CrawlService;

/**
 * Created by vbourdine on 22.09.2015.
 */
@Component
public class CrawlServiceImpl implements CrawlService {

	private static final Logger LOG = LoggerFactory.getLogger(CrawlService.class);

	public List<CustomerData> crawl(List<CustomerData> data) {
		List<CustomerData> result = new ArrayList<CustomerData>();
		
		Executor exec = new ThreadPoolExecutor(3, 10, 1, TimeUnit.SECONDS,
				new LinkedBlockingQueue());
		
		CompletionService<CustomerData> ecs = 
				new ExecutorCompletionService<CustomerData>(exec);
		if (data != null) {

			Iterator<CustomerData> customerIter = data.iterator();

			while (customerIter.hasNext()) {
				ecs.submit(new CrawlerTask(customerIter.next()));
			}

			for (int i = 0; i < data.size(); i++) {
				try {
//					if(ecs.poll(1L, TimeUnit.SECONDS) != null 
//							&& ecs.poll(1L, TimeUnit.SECONDS).get() != null)
//						result.add(ecs.poll(1L, TimeUnit.SECONDS).get());
					Future<CustomerData> future = ecs.poll(2, TimeUnit.SECONDS);
					if (future != null && future.get() != null){
						result.add(future.get());
					}
				} catch (InterruptedException e) {
					LOG.error(e.getMessage());
					e.printStackTrace();
				} catch (ExecutionException e) {
					LOG.error("Could not obtain result cause - some ExecutionException - it's OK");
					// do nothing
					// e.printStackTrace();
				}
			}
			return result;

		}
		LOG.debug("Customer data is emply, will return null!");
		return null;
	}

}

package org.senro.persistence.ejb3;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class OpenTransactionInView extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		JtaTransactionManager manager = lookupTransactionManager();
		UserTransaction tx = manager.getUserTransaction();

		try {
			tx.begin();
			logger.info("Started JTA transaction...");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			filterChain.doFilter(request, response);
		}

		finally {
			try {
				if (tx.getStatus() == Status.STATUS_ACTIVE) {
					tx.commit();
					logger.info("Commited JTA transaction...");
				}
				else if (tx.getStatus() == Status.STATUS_MARKED_ROLLBACK) {
					tx.rollback();
					logger.info("Rolled back JTA transaction...");
				}
				else {
					throw new IllegalStateException("Transaction is in illegal state: "+tx.getStatus());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected JtaTransactionManager lookupTransactionManager() {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		JtaTransactionManager manager = (JtaTransactionManager) wac.getBean(getTransactionManagerBeanName());
		return manager;
	}

	protected String getTransactionManagerBeanName() {
		return "transactionManager";
	}
}

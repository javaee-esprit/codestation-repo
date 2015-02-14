package org.ug.cs.client.locator;

import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServiceLocator {

	private static ServiceLocator instance;

	public synchronized static ServiceLocator getInstance() {
		if (instance == null) {
			instance = new ServiceLocator();
		}
		return instance;
	}

	private ServiceLocator() {
		cache = new HashMap<>();
		try {
			context = new InitialContext();
		} catch (NamingException e) {
			throw new ServiceLocatorException(e);
		}
	}

	private Map<String, Object> cache;
	private Context context = null;

	public synchronized Object getProxy(String ear, String ejbModule,
			String serviceName, Class<?> clazz) {
		String jndiName = new StringBuilder().append(ear).append("/")
				.append(ejbModule).append("/").append(serviceName).append("!")
				.append(clazz.getCanonicalName()).toString();
		Object proxy = cache.get(jndiName);
		if (proxy == null) {
			try {
				proxy = context.lookup(jndiName);
			} catch (NamingException e) {
				throw new ServiceLocatorException(e);
			}
			cache.put(jndiName, proxy);
		}

		return proxy;
	}
}

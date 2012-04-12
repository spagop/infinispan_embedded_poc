/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc. and/or its affiliates,
 * and individual contributors as indicated by the @author tags.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 *
 * (C) 2012,
 * @author JBoss, by Red Hat.
 */

package org.jboss.gpse.quickstart.dgbean;

import javax.ejb.Stateful;

import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.CacheContainer;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.Cache;
import org.infinispan.eviction.EvictionStrategy;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * A Stateful EJB that starts a cache instance provides and interface for 
 * adding and removeing objects
 *
 * @author randy.thomas@redhat.com, 2012-04-11
 */
@Stateful
public class DataGridEJB
{
//	 Configuration config = new DefaultCacheManager()
//	.defineConfiguration("custom-cache", new ConfigurationBuilder()
//	.eviction().strategy(EvictionStrategy.LIRS).maxEntries(10)
//	.build());
//	
//	Cache<Object, Object> c = ((CacheContainer) config).getCache("custom-cache");
	
	@Resource(lookup="java:jboss/infinispan/agilaireCacheContainer")
  	private org.infinispan.manager.CacheContainer container;
  	private org.infinispan.Cache<String, Object> cache;

    @PostConstruct
    public void postContruct() {
		this.cache = this.container.getCache();
    }
	
    /**
     * This test method takes an object and stores in in the Data Grid
     *
     * @param key the identity to be used to store the object
     * @return the results of the store put command
     */
	public String storeItem(String key, Object item) {
		cache.put(key, key);
		return "Storing " + key;
	}
	
	/**
	 * Given the key to an item in the data grid retrieve
	 * and return the item
	 * 
	 * @parma key the identity of the item to be retrieved
	 * @return the item
	 */
	public Object getItem(String key)
	{
		return cache.get(key);
	}
}

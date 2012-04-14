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

import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.Cache;
import org.infinispan.eviction.EvictionStrategy;
import org.infinispan.remoting.transport.jgroups.JGroupsTransport;
import org.jboss.gpse.quickstart.perftest.InfinispanClientAbstract;

/**
 * A Stateful EJB that starts a cache instance provides and interface for 
 * adding and removeing objects
 *
 * @author randy.thomas@redhat.com, 2012-04-11
 */
@Stateful
public class DataGridEJB extends InfinispanClientAbstract
{
	private EmbeddedCacheManager manager;
	
	public DataGridEJB(){
		
		// attempted to use: globalJmxStatistics().cacheManagerName("AgilaireCacheManager")
		// changed it to
		// .globalJmxStatistics().disable()
		
		super();
        
		System.out.println("\n Building Global Config: ");
		GlobalConfiguration  globalcfg = new GlobalConfigurationBuilder().transport().clusterName("cluster1").transport(new JGroupsTransport())
		.addProperty("configurationFile", "jgroups-udp.xml").globalJmxStatistics().disable()
		.build();
		
		System.out.println("Building the configuration next ...");
		Configuration config = new ConfigurationBuilder()
		.clustering().cacheMode(CacheMode.DIST_SYNC).sync().replTimeout(20000L).hash().numOwners(2)
		.eviction().strategy(EvictionStrategy.LIRS).maxEntries(10).build();
		
		System.out.println("Creating the cache manager ...");
		manager = new DefaultCacheManager(globalcfg, config, true);
		cache = manager.getCache("agilaire-cache");
		cache.start();
		ready = true;
	}
}

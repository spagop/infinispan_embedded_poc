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
package org.jboss.as.quickstarts.ejbinwar.test;

import static org.junit.Assert.assertEquals;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.gpse.quickstart.dgbean.DataGridEJB;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * A simple test case using Arquillian to test an EJB.
 *
 * @author randy.thomas@redhat.com
 */
@RunWith(Arquillian.class)
public class DataGridEJBTest {
	
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar")
			.addClasses(DataGridEJB.class);
	}

	@EJB
	private DataGridEJB dataGridEJB;

	@Test
	public void testDataGrid() throws Exception {
		String name = "World!";
		assertEquals("Storing " + name, dataGridEJB.storeItem(name, null));
	}
}

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

package org.jboss.gpse.quickstart.ejb.controller;

import org.jboss.gpse.quickstart.dgbean.DataGridEJB;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * A simple managed bean that is used to invoke the DataGridEJB and store the response. 
 * The response is obtained byminvoking getMessage().
 *
 * @author randy.thomas@redhat.com, 2012-04-11
 */
@Named("dgptc")
@SessionScoped
public class DGPerfTestController implements Serializable {

	/**
	 * Serialization
	 */
	private static final long serialVersionUID = -8339176171754706030L;

	/**
	 * Injected DataGridEJB client
	 */
	@EJB
	private DataGridEJB dataGridEJB;

	/**
	 * Stores the response from the call to dataGridEJB.storeItem(...)
	 */
	private String message;

	/**
	 * Invoke dataGridEJB.storeItem(...) and store the message
     *
     * @param name A value to be used for this test
	 */
	public void setName(String name) {
		message = dataGridEJB.storeItem(name, "");
	}

    /**
     * Get the message
     *
     * @return message. The greeting message.
     */
	public String getMessage() {
		return message;
	}

}

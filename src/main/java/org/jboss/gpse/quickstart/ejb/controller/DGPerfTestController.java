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
 * The response is obtained by invoking getMessage().
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
	 * Injected Stateful DataGridEJB client
	 */
	@EJB
	private DataGridEJB dataGridEJB;

	/**
	 * Stores the response from the call to dataGridEJB.storeItem(...)
	 */
	private String message;
	
	/**
	 * Store the number of entries to make into the cache store
	 */
	private int count;
	
	/**
	 * Stores the size of the entries to be inserted from the cache sore on write operations
	 */
	private int size;
	
	/**
	 * Invokes the write operation on the dataGridEJG with count for settin the number of 
	 * entries and size to indicate the size of the entries.
	 * Returning void causes JSF navigation to remain on the same page
	 */
	public void writeEntries()
	{
		message += "<\br>" + dataGridEJB.write(count, size);
	}
	
	/**
	 * Invokes the read operation on the dataGridEJB with count for the maximum number of 
	 * entries to read.
	 * Returning void causes JSF navigation to remain on the same page
	 */
	public void readEntries()
	{
		message += "<\br>" + dataGridEJB.read(count);
	}
	
	public void clearMessages()
	{
		message = "";
	}

    /**
     * Get the message
     *
     * @return message. The greeting message.
     */
	public String getMessage() {
		return message;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}

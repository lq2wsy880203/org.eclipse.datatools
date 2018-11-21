/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.zseries.internal.ui;

import org.eclipse.datatools.connectivity.ui.wizards.ExtensibleNewConnectionProfileWizard;

public class NewZSeriesConnectionProfileWizard extends
		ExtensibleNewConnectionProfileWizard {
	
	public NewZSeriesConnectionProfileWizard() {
		super(new ZSeriesDBProfileDetailsWizardPage("org.eclipse.datatools.enablement.ibm.db2.zseries.internal.ui.ZSeriesDBProfileDetailsWizardPage"));
	}
}
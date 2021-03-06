/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.ase.ddl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.modelbase.sql.expressions.QueryExpression;
import org.eclipse.datatools.modelbase.sql.expressions.QueryExpressionDefault;
import org.eclipse.datatools.modelbase.sql.expressions.SQLExpressionsPackage;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;

public class SybaseASEDdlParser {

    public SybaseASEDdlParser()
    {
    }
    
	public SybaseASEDdlParser(DatabaseDefinition def){
		this.def = def;
	}

	public void parseView (ViewTable view,String viewText){
    	DataModelElementFactory factory = this.def.getDataModelElementFactory();

    	String body = viewText;
    	Pattern pattern = Pattern.compile(".*[\\s]+?AS[\\s]+?([(\\s]*SELECT.*)",Pattern.CASE_INSENSITIVE|Pattern.DOTALL); //$NON-NLS-1$
    	Matcher matcher = pattern.matcher(viewText);
    	if (matcher.matches()) {
    		body = matcher.group(1);
    		pattern = Pattern.compile("(.*)[ \t]+?WITH[ \t]+?.*",Pattern.CASE_INSENSITIVE|Pattern.DOTALL); //$NON-NLS-1$
    		matcher = pattern.matcher(body);
    		if (matcher.matches()) {
    			body = matcher.group(1).trim();
    		}
    	}

    	QueryExpression queryExpression = (QueryExpression) factory.create(SQLExpressionsPackage.eINSTANCE.getQueryExpressionDefault());
		((QueryExpressionDefault)queryExpression).setSQL(body);
		view.setQueryExpression(queryExpression);
    }
    
    public String parseDefaultRuleStatement(String deftStr)
    {
        String body = deftStr;
        Pattern pattern = Pattern.compile(".*[\\s]+?AS[\\s]+?([(\\s]*.*)",Pattern.CASE_INSENSITIVE|Pattern.DOTALL); //$NON-NLS-1$
        Matcher matcher = pattern.matcher(deftStr);
        if (matcher.matches()) {
            body = matcher.group(1);
        }
        return body.trim(); 
    }
    
    public String parseInlineDefault(String deftStr)
    {
        String body = deftStr;
        Pattern pattern = Pattern.compile(".*DEFAULT[\\s]+?([(\\s]*.*)",Pattern.CASE_INSENSITIVE|Pattern.DOTALL); //$NON-NLS-1$
        Matcher matcher = pattern.matcher(deftStr);
        if (matcher.matches()) {
            body = matcher.group(1);
        }
        return body.trim();
    }
    
	private DatabaseDefinition def;
    
    public static void main(String[] args)
    {
        System.out.println(new SybaseASEDdlParser(null).parseInlineDefault("  default 999"));
    }
}

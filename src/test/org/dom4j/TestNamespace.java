/*
 * Copyright 2001 (C) MetaStuff, Ltd. All Rights Reserved.
 * 
 * This software is open source. 
 * See the bottom of this file for the licence.
 * 
 * $Id$
 */

package org.dom4j;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import junit.framework.*;
import junit.textui.TestRunner;

import org.dom4j.io.SAXReader;

/** A test harness to test the use of Namespaces.
  *
  * @author <a href="mailto:james.strachan@metastuff.com">James Strachan</a>
  * @version $Revision$
  */
public class TestNamespace extends AbstractTestCase {

    /** Input XML file to read */
    protected static String INPUT_XML_FILE = "xml/namespaces.xml";
    
    /** Namespace to use in tests */
    protected static Namespace XSL_NAMESPACE = Namespace.get( 
        "xsl", "http://www.w3.org/1999/XSL/Transform" 
    );
    
    protected static QName XSL_TEMPLATE = QName.get( "template", XSL_NAMESPACE );
    
    
    public static void main( String[] args ) {
        TestRunner.run( suite() );
    }
    
    public static Test suite() {
        return new TestSuite( TestNamespace.class );
    }
    
    public TestNamespace(String name) {
        super(name);
    }

    // Test case(s)
    //-------------------------------------------------------------------------                    
    public void debugShowNamespaces() throws Exception {
        Element root = getRootElement();
        
        for ( Iterator iter = root.elementIterator(); iter.hasNext(); ) {
            Element element = (Element) iter.next();
            
            log( "Found element:    " + element );
            log( "Namespace:        " + element.getNamespace() );
            log( "Namespace prefix: " + element.getNamespacePrefix() );
            log( "Namespace URI:    " + element.getNamespaceURI() );
        }
    }
        
    public void testGetElement() throws Exception {
        Element root = getRootElement();
        
        
        Element firstTemplate = root.element( XSL_TEMPLATE );
        assert( "Root element contains at least one <xsl:template/> element", firstTemplate != null );
        
        log( "Found element: " + firstTemplate );
    }
        
    public void testGetElements() throws Exception {
        Element root = getRootElement();
        
        List list = root.elements( XSL_TEMPLATE );
        assert( "Root element contains at least one <xsl:template/> element", list.size() > 0 );
        
        log( "Found elements: " + list );
    }
        
    public void testElementIterator() throws Exception {
        Element root = getRootElement();
        Iterator iter = root.elementIterator( XSL_TEMPLATE );
        assert( "Root element contains at least one <xsl:template/> element", iter.hasNext() );

        do {
            Element element = (Element) iter.next();
            log( "Found element: " + element );
        }
        while ( iter.hasNext() );
    }
        
    // Implementation methods
    //-------------------------------------------------------------------------                    
    protected void setUp() throws Exception {
        SAXReader reader = new SAXReader();
        document = reader.read( new File( INPUT_XML_FILE ) );
    }

    /** @return the root element of the document */
    protected Element getRootElement() {
        Element root = document.getRootElement();
        assert( "Document has root element", root != null );
        return root;
    }
}




/*
 * Redistribution and use of this software and associated documentation
 * ("Software"), with or without modification, are permitted provided
 * that the following conditions are met:
 *
 * 1. Redistributions of source code must retain copyright
 *    statements and notices.  Redistributions must also contain a
 *    copy of this document.
 *
 * 2. Redistributions in binary form must reproduce the
 *    above copyright notice, this list of conditions and the
 *    following disclaimer in the documentation and/or other
 *    materials provided with the distribution.
 *
 * 3. The name "DOM4J" must not be used to endorse or promote
 *    products derived from this Software without prior written
 *    permission of MetaStuff, Ltd.  For written permission,
 *    please contact dom4j-info@metastuff.com.
 *
 * 4. Products derived from this Software may not be called "DOM4J"
 *    nor may "DOM4J" appear in their names without prior written
 *    permission of MetaStuff, Ltd. DOM4J is a registered
 *    trademark of MetaStuff, Ltd.
 *
 * 5. Due credit should be given to the DOM4J Project
 *    (http://dom4j.org/).
 *
 * THIS SOFTWARE IS PROVIDED BY METASTUFF, LTD. AND CONTRIBUTORS
 * ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL
 * METASTUFF, LTD. OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Copyright 2001 (C) MetaStuff, Ltd. All Rights Reserved.
 *
 * $Id$
 */

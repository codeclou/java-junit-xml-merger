/*
 * MIT License
 *
 * Copyright (c) 2017 Bernhard Grünewaldt
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.codeclou.java.junit.xml.merger.model;

import io.codeclou.java.junit.xml.merger.GetterSetterValidator;
import io.codeclou.java.junit.xml.merger.JunitXmlParser;
import org.junit.Test;
import org.w3c.dom.Document;

import static org.junit.Assert.assertEquals;

public class TestSuitesTest {
    @Test
    public void testPojoGetterSetter() {
        GetterSetterValidator.validateAccessors(TestSuites.class);
    }

    @Test
    public void testToXml() throws Exception {
        JunitXmlParser jxml = new JunitXmlParser();
        TestSuite suite1 = jxml.transformTestSuite(XmlHelper.xmlFromString("<testsuite name='foo' time='4.20' errors='1' tests='3' failures='5' skipped='2'></testsuite>".replaceAll("'", "\"")));
        TestSuite suite2 = jxml.transformTestSuite(XmlHelper.xmlFromString("<testsuite name='bar' time='21.01' errors='2' tests='4' failures='3' skipped='6'></testsuite>".replaceAll("'", "\"")));
        TestSuites suites = new TestSuites();
        suites.setName("foobar23");
        suites.getTestSuites().add(suite1);
        suites.getTestSuites().add(suite2);
        assertEquals(suites.getFailures(), new Long(8L));
        assertEquals(suites.getTests(), new Long(7L));
        assertEquals(suites.getName(), "foobar23");
        assertEquals(suites.getTime(), new Double(25.21));

        Document d = suites.toXml();
        assertEquals(d.getFirstChild().getAttributes().getNamedItem("failures").getNodeValue(), "8");
    }
}

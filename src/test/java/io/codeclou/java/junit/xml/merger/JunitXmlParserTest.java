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
package io.codeclou.java.junit.xml.merger;

import io.codeclou.java.junit.xml.merger.model.TestSuite;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import java.io.File;
import java.util.Collection;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;

public class JunitXmlParserTest {

    private File getTestFile(String filename) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(filename).getFile());
    }

    @Test
    public void testParseSuites() throws Exception {
        JunitXmlParser parser = new JunitXmlParser();
        Collection<TestSuite> c = parser.parseTestSuites(getTestFile("testsuites.xml"));
        assertFalse(c.isEmpty());
    }

    @Test
    public void testParse() throws Exception {
        JunitXmlParser parser = new JunitXmlParser();
        TestSuite t = parser.parseTestSuite(getTestFile("sort-model-test.xml"));
        assertEquals(Long.valueOf(0L), t.getErrors());
        assertEquals(Long.valueOf(0L), t.getFailures());
        assertEquals(Long.valueOf(0L), t.getSkipped());
        assertEquals(Long.valueOf(3L), t.getTests());
        assertTrue(t.getTime() > 0.028);
        assertEquals("ut.io.codeclou.customfield.editor.model.rest.SortModelTest", t.getName());
        assertNotNull(t.getXml());
    }

    @Test
    public void testParseNotSoWellFormed() throws Exception {
        JunitXmlParser parser = new JunitXmlParser();
        TestSuite t = parser.parseTestSuite(getTestFile("my-not-so-well-formed-component-test.xml"));
        assertEquals(Long.valueOf(0L), t.getErrors());
        assertEquals(Long.valueOf(0L), t.getFailures());
        assertEquals(Long.valueOf(0L), t.getSkipped());
        assertEquals(Long.valueOf(0L), t.getTests());
        assertTrue(t.getTime() >= 0.0);
        assertEquals("i.am.empty.but.sometimes.that.happens", t.getName());
        assertNotNull(t.getXml());
    }

    @Test
    public void testRunInvalidInput1() throws Exception {
        String[] args = {};
        JunitXmlParser parser = new JunitXmlParser();
        parser.run(args);
        Boolean hasCmdLineParameterErrors = (Boolean) Whitebox.getInternalState(parser, "hasCmdLineParameterErrors");
        assertTrue(hasCmdLineParameterErrors);
    }

    @Test
    public void testRunInvalidInput2() throws Exception {
        String[] args = {"-i=foo"};
        JunitXmlParser parser = new JunitXmlParser();
        parser.run(args);
        Boolean hasCmdLineParameterErrors = (Boolean) Whitebox.getInternalState(parser, "hasCmdLineParameterErrors");
        assertTrue(hasCmdLineParameterErrors);
    }

    @Test
    public void testRunInvalidInput3() throws Exception {
        String[] args = {"-i=foo", "-o=bar.xml"};
        JunitXmlParser parser = new JunitXmlParser();
        parser.run(args);
        Boolean hasCmdLineParameterErrors = (Boolean) Whitebox.getInternalState(parser, "hasCmdLineParameterErrors");
        assertTrue(hasCmdLineParameterErrors);
    }

    @Test
    public void testRunValidInputWithInvalidFolders() throws Exception {
        String[] args = {"-i=foo", "-o=?x/bar.xml", "-s=foo"};
        JunitXmlParser parser = new JunitXmlParser();
        parser.run(args);
        Boolean hasFileNotFoundErrors = (Boolean) Whitebox.getInternalState(parser, "hasFileNotFoundErrors");
        assertTrue(hasFileNotFoundErrors);
    }

    @Test
    public void testRunValidInputWithValidFolders() throws Exception {
        String[] args = {"-i=src/test/resources/", "-o=output.xml", "-s=foo bar"};
        JunitXmlParser parser = new JunitXmlParser();
        parser.run(args);
        Boolean hasCmdLineParameterErrors = (Boolean) Whitebox.getInternalState(parser, "hasCmdLineParameterErrors");
        assertFalse(hasCmdLineParameterErrors);
        Boolean hasFileNotFoundErrors = (Boolean) Whitebox.getInternalState(parser, "hasFileNotFoundErrors");
        assertFalse(hasFileNotFoundErrors);
    }

    @Test
    public void testRunValidInputWithEmptyInputFolder() throws Exception {
        File emptyDir = new File("src/test/resources/empty/");
        emptyDir.mkdir();
        String[] args = {"-i=src/test/resources/empty/", "-o=output.xml", "-s=foo bar"};
        JunitXmlParser parser = new JunitXmlParser();
        parser.run(args);
        Boolean hasCmdLineParameterErrors = (Boolean) Whitebox.getInternalState(parser, "hasCmdLineParameterErrors");
        assertFalse(hasCmdLineParameterErrors);
        Boolean hasFileNotFoundErrors = (Boolean) Whitebox.getInternalState(parser, "hasFileNotFoundErrors");
        assertFalse(hasFileNotFoundErrors);
        emptyDir.delete();
    }
}

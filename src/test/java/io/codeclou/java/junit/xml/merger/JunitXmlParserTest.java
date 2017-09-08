/*
 * MIT License
 *
 * Copyright (c) 2017 codeclou.io
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

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class JunitXmlParserTest {

    private File getTestFile(String filename) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(filename).getFile());
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
}

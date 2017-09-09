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

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import java.util.List;

public class TestSuites {

    private String name;

    private List<TestSuite> testSuites = new ArrayList<>();

    //
    // SUM GETTERS
    //
    public Long getTests() {
        return testSuites.stream().mapToLong(t -> t.getTests()).sum();
    }
    public Long getFailures() {
        return testSuites.stream().mapToLong(t -> t.getFailures()).sum();
    }
    public Double getTime() {
        return testSuites.stream().mapToDouble(t -> t.getTime()).sum();
    }

    //
    // NORMAL SETTERS/GETTERS
    //
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<TestSuite> getTestSuites() {
        return testSuites;
    }

    //
    // XML
    //
    public Document toXml() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        Element rootEl = document.createElement("testsuites");
        document.appendChild(rootEl);
        rootEl.setAttribute("time", this.getTime().toString());
        rootEl.setAttribute("tests", this.getTests().toString());
        rootEl.setAttribute("failures", this.getFailures().toString());
        rootEl.setAttribute("name", this.getName());
        for (TestSuite t : this.testSuites) {
            rootEl.appendChild(document.importNode(t.getXml(), true));
        }
        return document;
    }

}

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

import org.w3c.dom.Node;

public class TestSuite {

    private Long tests;
    private Long failures;
    private Long errors;
    private Long skipped;
    private String name;
    private Double time;

    private Node xml;

    public Long getTests() {
        return tests;
    }

    public void setTests(Long tests) {
        this.tests = tests;
    }

    public Long getFailures() {
        return failures;
    }

    public void setFailures(Long failures) {
        this.failures = failures;
    }

    public Long getErrors() {
        return errors;
    }

    public void setErrors(Long errors) {
        this.errors = errors;
    }

    public Long getSkipped() {
        return skipped;
    }

    public void setSkipped(Long skipped) {
        this.skipped = skipped;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public Node getXml() {
        return xml;
    }

    public void setXml(Node xml) {
        this.xml = xml;
    }
}

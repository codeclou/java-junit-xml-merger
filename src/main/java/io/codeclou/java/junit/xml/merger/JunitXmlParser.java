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
import org.apache.commons.cli.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class JunitXmlParser {

    private CommandLineParser parser = new DefaultParser();
    private Options options = new Options();

    protected TestSuite parseTestSuite(File filename) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(filename);
        return transform(document.getFirstChild());
    }

    public TestSuite transform(Node testSuite) {
        TestSuite t = new TestSuite();
        t.setTests(Long.valueOf(testSuite.getAttributes().getNamedItem("tests").getNodeValue()));
        t.setErrors(Long.valueOf(testSuite.getAttributes().getNamedItem("errors").getNodeValue()));
        t.setFailures(Long.valueOf(testSuite.getAttributes().getNamedItem("failures").getNodeValue()));
        t.setSkipped(Long.valueOf(testSuite.getAttributes().getNamedItem("skipped").getNodeValue()));
        t.setName(testSuite.getAttributes().getNamedItem("name").getNodeValue());
        t.setTime(Double.valueOf(testSuite.getAttributes().getNamedItem("time").getNodeValue()));
        t.setXml(testSuite);
        return t;
    }

    protected void run(String[] args) throws ParseException {
        Option option = new Option("i", "input", true, "input files");
        option.setArgs(1000); // allow multiple
        options.addOption(option);
        options.addOption("o", "output", true, "output file");
        CommandLine cmd = this.parser.parse( options, args);

        System.out.println("Java Junit Xml Merger");
        if(cmd.hasOption("input")) {
            System.out.println("input ok");
            for (Option o : cmd.getOptions()) {
                System.out.println(o.getOpt() + " - " + o.getValue());
            }
        } else {
            System.out.println("input fail");
        }
    }
}

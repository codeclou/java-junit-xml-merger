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
import io.codeclou.java.junit.xml.merger.model.TestSuites;
import org.apache.commons.cli.*;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class JunitXmlParser {

    private CommandLineParser parser = new DefaultParser();
    private Options options = new Options();
    private Boolean hasCmdLineParameterErrors = false;
    private Boolean hasFileNotFoundErrors = false;

    protected TestSuite parseTestSuite(File filename) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document document = builder.parse(filename);
        return transform(document.getFirstChild());
    }

    public TestSuite transform(Node testSuite) {
        TestSuite t = new TestSuite();
        NamedNodeMap attrs = testSuite.getAttributes();
        t.setTests(attrs.getNamedItem("tests") != null ? Long.valueOf(attrs.getNamedItem("tests").getNodeValue()) : 0L);
        t.setErrors(attrs.getNamedItem("errors") != null ? Long.valueOf(testSuite.getAttributes().getNamedItem("errors").getNodeValue()) : 0L);
        t.setFailures(attrs.getNamedItem("failures") != null ? Long.valueOf(testSuite.getAttributes().getNamedItem("failures").getNodeValue()) : 0L);
        t.setSkipped(attrs.getNamedItem("skipped") != null ? Long.valueOf(testSuite.getAttributes().getNamedItem("skipped").getNodeValue()) : 0L);
        t.setName(testSuite.getAttributes().getNamedItem("name").getNodeValue());
        t.setTime(attrs.getNamedItem("time") != null ? Double.valueOf(testSuite.getAttributes().getNamedItem("time").getNodeValue()) : 0.0);
        t.setXml(testSuite);
        return t;
    }

    protected void run(String[] args) throws Exception {
        Option option = new Option("i", "inputDir", true, "input dir that contains xml files");
        options.addOption(option);
        options.addOption("o", "output", true, "output xml file");
        options.addOption("s", "suiteName", true, "suite name");
        CommandLine cmd = this.parser.parse(options, args);
        System.out.println("\033[32;1;2m+-------------------------+\033[0m");
        System.out.println("\033[32;1;2m|  Java Junit Xml Merger  |\033[0m");
        System.out.println("\033[32;1;2m+-------------------------+\033[0m");
        if (!cmd.hasOption("inputDir")) {
            System.out.println("\033[31;1mError >> Please specify inputDir with -i\033[0m");
            hasCmdLineParameterErrors = true;
        }
        if (!cmd.hasOption("output")) {
            System.out.println("\033[31;1mError >> Please specify output with -o\033[0m");
            hasCmdLineParameterErrors = true;
        }
        if (!cmd.hasOption("suiteName")) {
            System.out.println("\033[31;1mError >> Please specify suiteName with -s\033[0m");
            hasCmdLineParameterErrors = true;
        }
        if (!hasCmdLineParameterErrors) {
            System.out.println("\033[32;1;2mSuccess >> All input parameters ok\033[0m");
            File outputFile = new File(cmd.getOptionValue("output"));
            File inputFileDir = new File(cmd.getOptionValue("inputDir"));
            try {
                // "touch"/"overwrite" file
                new FileOutputStream(outputFile).close();
            } catch (IOException e) {
                hasFileNotFoundErrors = true;
                System.out.println("\033[31;1mError >> Outputfile not writeable\033[0m");
                System.out.println(outputFile.getAbsolutePath());
            }
            if (!inputFileDir.isDirectory()) {
                hasFileNotFoundErrors = true;
                System.out.println("\033[31;1mError >> Input dir not readable\033[0m");
                System.out.println(inputFileDir.getAbsolutePath());
            }
            if (!hasFileNotFoundErrors) {
                System.out.println("\033[32;1;2mSuccess >> All files and folders ok\033[0m");
                TestSuites suites = new TestSuites();
                suites.setName(cmd.getOptionValue("suiteName"));
                File[] filesList = inputFileDir.listFiles();
                for (File f : filesList) {
                    if (f.getAbsoluteFile().toString().endsWith(".xml")) {
                        System.out.println("\033[32;1;2mInfo >> adding " + f.getName() +  " to TestSuites\033[0m");
                        try {
                            suites.getTestSuites().add(parseTestSuite(f));
                        } catch (Exception e) {
                            System.out.println("\033[31;1mError >> the file " + f.getName() + " cannot be read: ignored\033[0m");
                            e.printStackTrace();
                        }
                    }
                }
                Document xml = suites.toXml();
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                Result output = new StreamResult(outputFile);
                Source input = new DOMSource(xml);
                transformer.transform(input, output);
            }
        }
    }
}

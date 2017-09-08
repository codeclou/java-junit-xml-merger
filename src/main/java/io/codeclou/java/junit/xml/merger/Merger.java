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

import org.apache.commons.cli.*;

public class Merger {
    private static CommandLineParser parser = new DefaultParser();
    private static Options options = new Options();


    public static void main(String [] args) throws ParseException {
        Option option = new Option("i", "input", true, "input files");
        option.setArgs(1000); // allow multiple
        options.addOption(option);
        options.addOption("o", "output", true, "output file");
        CommandLine cmd = parser.parse( options, args);

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

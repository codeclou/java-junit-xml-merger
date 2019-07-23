# Java JUnit XML Merger

> Merges multiple JUnit XML files into a single testsuites XML file. Simply combine your test results from different test runners.

[![](https://codeclou.github.io/java-junit-xml-merger/img/github-product-logo-java-merger.png)](https://github.com/codeclou/java-junit-xml-merger)

[![](https://codeclou.github.io/doc/badges/generated/test-coverage-100.svg?v2)](https://codeclou.github.io/java-junit-xml-merger/test-coverage/1.0.1/)

&nbsp;

----
&nbsp;

### Is this for me?

You have multiple XML files that look like this:

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<testsuite tests="23" failures="0" name="foo.BarUnitTest" time="4.20" errors="0" skipped="0">
  <!-- foo -->
</testsuite>
```

And you want a single file that looks like this:

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<testsuites name="My Suite" time="6.60" tests="26" failures="0">
  <testsuite tests="23" failures="0" name="foo.BarUnitTest" time="4.20" errors="0" skipped="0">
    <!-- foo -->
  </testsuite>
  <testsuite tests="3" failures="0" name="bar.FooUnitTest" time="2.40" errors="0" skipped="0">
    <!-- bar -->
  </testsuite>
</testsuites>
```

then this is for you

----
&nbsp;

### Usage

With folder `src/test/resources/` containing multiple `*.xml` files in junit-xml format.
A combined result will be written to `output.xml`. The Suite name will be `My Suite`.

```
# INSTALL
curl -L -o junit-xml-merger.jar \
     https://github.com/codeclou/java-junit-xml-merger/releases/download/1.0.1/junit-xml-merger.jar

# RUN
java -jar junit-xml-merger.jar \
     -i=src/test/resources/ \
     -o=output.xml \
     -s="My Suite"
```

-----
&nbsp;

### Demo

<p align="center"><img src"=width="80%" src="https://codeclou.github.io/java-junit-xml-merger/img/demo.gif" /></p>
  
```
git clone https://github.com/codeclou/java-junit-xml-merger.git src
cd src

curl -L -o junit-xml-merger.jar \
     https://github.com/codeclou/java-junit-xml-merger/releases/download/1.0.1/junit-xml-merger.jar

java -jar junit-xml-merger.jar \
     -i=src/test/resources/ \
     -o=output.xml \
     -s="My Suite"

# Show result
xmllint --format output.xml | pygmentize
```


----
&nbsp;

### License

[MIT](https://github.com/codeclou/java-junit-xml-merger/blob/master/LICENSE) © [Bernhard Grünewaldt](https://github.com/clouless)

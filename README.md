# java-junit-xml-merger

![](https://codeclou.github.io/doc/badges/generated/test-coverage-100.svg?v2)

Merges multiple Junit XML files into a single testsuites XML file.

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
     https://github.com/codeclou/java-junit-xml-merger/releases/download/1.0.0/junit-xml-merger.jar

# RUN
java -jar junit-xml-merger.jar \
     -i=src/test/resources/ \
     -o=output.xml \
     -s=My Suite
```

-----
&nbsp;

### License

[MIT](https://github.com/cloutainer/java-junit-xml-merger/blob/master/LICENSE) © [Bernhard Grünewaldt](https://github.com/clouless)

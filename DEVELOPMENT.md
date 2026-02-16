# Development

### Build

```
mvn package -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true
java -jar target/java-junit-xml-merger.jar
```

### Testcoverage

We use jacoco and have enforced 100% coverage

```bash
mvn clean 
mvn test 
mvn verify
mvn jacoco:report
```

Now look into `target/site/jacoco/` and open `index.html` in the browser.
Should look like so:

<img width="1043" height="216" alt="image" src="https://github.com/user-attachments/assets/36b98e8a-5a60-4e51-83e9-43a717047845" />

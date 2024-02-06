# Hashcat Concurrency
## To lunch this application:
1. Download repository: `https://github.com/TeaCollector/hashcatconcurrency.git`
2. Build project: `gradle build` (Gradle 8.6 and Java 21)
3. Enter in terminal: `java -jar build/libs/HashcatConcurrency-1.0.jar -a MD5 -h fcea920f7412b5da7be0cf42b8c93759 -l 7`
4. You can change value of flag 'a', 'h' and 'l'; for example: `java -jar build/libs/HashcatConcurrency-1.0.jar -a SHA1 -h aa97067fed4747836d0926fcd16cbe6e4d546159 -l 8`
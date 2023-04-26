# Demonstration that NOTE messages are not logged

This is a minimal project to reproduce a bug with the maven-compiler-plugin.

Specifically that NOTE level messages from annotation processors are not logged
when `<fork>true</fork>` is used.

## To reproduce

Just run `mvn clean compile` and observe the output

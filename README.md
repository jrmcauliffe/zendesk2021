# Requirements

This project requires the Java 8 JDK to be installed as well as sbt (a scala build tool). These are platform dependant, but the basics for installing them can be found at https://www.scala-lang.org/download/2.13.3.html 

To build the command line application, clone the repository then run the command

`sbt clean assembly`

at the project root directory. This will build a jar file which can be run as a command line application.

`java -jar ./target/scala-2.13/zendesk2021-assembly-1.0.jar`

You can also specify another path for the data directory with the `--datapath` flag

`java -jar ./target/scala-2.13/zendesk2021-assembly-1.0.jar --datapath ./newpath`

The datapath defaults to the data included in the repo in the `./data` directory

# Assumptions / Notes

I considered using Spark as a library but thought this might have been against the spirit of "Please don't use a database or full text search product..".
This would have provided a pretty simple search api as well as a cleaner way to deal with the schema.

I have also made the assumption that the schema is represented by the sample data (fields in every record are 'not null'
and must be included otherwise parsing will fail). This is by no means the only option, especially for something that's a
search product, but fail fast does have some advantages.


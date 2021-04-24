


Requirments

This project requires the Java 8 JDK to be installed as well as sbt (a scala build tool). These are platform dependant, but the basics for installing them can be found at https://www.scala-lang.org/download/2.13.3.html 

To build the command line application, clone the repository then run the command

`sbt clean assembly`

at the project root directory. This will build a jar file which can be run as a command line application.

`java -jar ./target/scala-2.13/zendesk2021-assembly-1.0.jar organization._id 103`
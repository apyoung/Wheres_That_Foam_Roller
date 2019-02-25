These instructions have been tested on Windows hosts.

Open cmd.exe and navigate to the project root folder.
For example:
cd C:\WTF

Make sure there is a classes folder in the root folder, if not, run:
mkdir classes

Compile the program with this command:
javac -d classes -cp classes;src/lib/forms_rt.jar src/gui/*.java src/helper/*.java src/*.java

Output folder is classes
Classpath includes the "forms_rt.jar" which is used by the intellij form designer.

Run the program with this command:
java -cp src/lib/mysql-connector-java-5.1.45-bin.jar;src/lib/forms_rt.jar;classes WTF

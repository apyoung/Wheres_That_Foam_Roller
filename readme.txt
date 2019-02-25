Open the command prompt/terminal and navigate to the project root folder.
For example on a Windows machine:
cd C:\WTF

Make sure there is a classes folder in the root folder, if not, run:
mkdir classes

To compile the program on Windows, run this command:
javac -d classes -cp classes;src/lib/* src/gui/*.java src/helper/*.java src/*.java

To compile the program on Mac/Linux, run this command:
javac -d classes -cp classes:src/lib/* src/gui/*.java src/helper/*.java src/*.java

Output folder is classes
Classpath includes the "forms_rt.jar" which is used by the intellij form designer.

To run the program on Windows, run this command:
java -cp src/lib/*;classes WTF

To run the program on Mac/Linux, run this command:
java -cp src/lib/*:classes WTF

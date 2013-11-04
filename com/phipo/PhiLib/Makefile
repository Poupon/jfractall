all::

clean::
	/usr/bin/rm *.class  *.jar

jar::
	/usr/bin/rm  PhiLib.jar;jar cfm PhiLib.jar manifeste *.class; cd /home/team02/phipo/DEV/java/src; jar uf PhiLib/PhiLib.jar phipo/PhiLib/Interface/*.class phipo/PhiLib/Lang/*.class

%.class: %.java
	javac  -classpath ../.." -deprecation -g $*.java
 

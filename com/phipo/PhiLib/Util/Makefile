all::  PPDependsObjectDestroy.class  PPIniFile.class  PPObjectDestroy.class  PPToken.class PPTrace.class

clean::
	/usr/bin/rm *.class  *.jar

jar::
	cd /home/team02/phipo/DEV/java/src; /usr/bin/rm  /home/team02/phipo/DEV/java/src/phipo/PhiLib/Util/Util.jar;jar cfm /home/team02/phipo/DEV/java/src/phipo/PhiLib/Util/Util.jar phipo/Util/Util.mf phipo/Util/*.class; cd /home/team02/phipo/DEV/java/src; jar uf /home/team02/phipo/DEV/java/src/phipo/PhiLib/Util/Util.jar phipo/PhiLib/Interface/*.class phipo/PhiLib/Lang/*.class phipo/PhiLib/Util/*.class

%.class: %.java
	javac  -classpath "../../.." -deprecation -g $*.java
 

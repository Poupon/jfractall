all::  PFrameChild.class  PPAbout.class  PPAppli.class  PPCanvas.class  PPDoubleField.class  PPField.class  PPFileFilter.class  PPFloatField.class  PPHelpChild.class  PPHtml.class  PPIntField.class  PPManager.class  PPObjectDispatch.class  PPObjectDispatchClient.class  PPObjectDispatcher.class  PPTerm.class  PPTextStream.class  PPTraceTerm.class  PParam.class  PPrint.class

clean::
	/usr/bin/rm *.class  Interface.jar

jar::
	cd /home/team02/phipo/DEV/java/src; /usr/bin/rm  /home/team02/phipo/DEV/java/src/phipo/PhiLib/Interface/Interface.jar;jar cfm /home/team02/phipo/DEV/java/src/phipo/PhiLib/Interface/Interface.jar  phipo/Interface/Interface.mf phipo/Interface/*.class ; cd /home/team02/phipo/DEV/java/src; jar uf /home/team02/phipo/DEV/java/src/phipo/PhiLib/Interface/Interface.jar phipo/PhiLib/Interface/*.class phipo/PhiLib/Lang/*.class phipo/PhiLib/Util/*.class phipo/PhiLib/Ors/*.class phipo/PhiLib/Sql/*.class 

%.class: %.java
	javac  -classpath "../../.." -deprecation -g $*.java
 

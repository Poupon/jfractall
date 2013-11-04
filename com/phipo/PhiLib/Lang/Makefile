all::  PPLangToken.class  PPLangTokenizer.class  RPVM.class  RPVMCmd.class  RPVMCmdBase.class  RPVMException.class  RPVMManager.class  RPVMManagerBase.class

clean::
	/usr/bin/rm *.class  *.jar

jar::
	cd /home/team02/phipo/DEV/java/src; /usr/bin/rm  /home/team02/phipo/DEV/java/src/phipo/PhiLib/Lang/Lang.jar;jar cfm /home/team02/phipo/DEV/java/src/phipo/PhiLib/Lang/Lang.jar phipo/Lang/Lang.mf phipo/Lang/*.class; cd /home/team02/phipo/DEV/java/src; jar uf /home/team02/phipo/DEV/java/src/phipo/PhiLib/Lang/Lang.jar phipo/PhiLib/Interface/*.class phipo/PhiLib/Lang/*.class phipo/PhiLib/Util/*.class

%.class: %.java
	javac  -classpath "../../.." -deprecation -g $*.java
 

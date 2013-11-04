all::  SqlLogin.class  SqlServer.class SqlConnex.class  SqlTerm.class

clean::
	/usr/bin/rm *.class  *.jar

jar::
	cd /home/team02/phipo/DEV/java/src; /usr/bin/rm  /home/team02/phipo/DEV/java/src/phipo/PhiLib/Sql/Sql.jar;jar cfm /home/team02/phipo/DEV/java/src/phipo/PhiLib/Sql/Sql.jar phipo/Sql/Sql.mf phipo/Sql/*.class; cd /home/team02/phipo/DEV/java/src; jar uf /home/team02/phipo/DEV/java/src/phipo/PhiLib/Sql/Sql.jar phipo/PhiLib/Interface/*.class phipo/PhiLib/Lang/*.class phipo/PhiLib/Util/*.class

%.class: %.java
	javac  -classpath "/home/team02/phipo/DEV/java/src" -deprecation -g $*.java
 

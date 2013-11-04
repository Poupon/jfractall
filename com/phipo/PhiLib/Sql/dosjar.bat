
cd   /home/team02/phipo/DEV/java/src; 
del  /home/team02/phipo/DEV/java/src/phipo/Sql/Sql.jar
jar cfm /home/team02/phipo/DEV/java/src/phipo/Sql/Sql.jar phipo/Sql/Sql.mf phipo/Sql/*.class; cd /home/team02/phipo/DEV/java/src; jar uf /home/team02/phipo/DEV/java/src/phipo/Sql/Sql.jar phipo/PhiLib/Interface/*.class phipo/PhiLib/Lang/*.class phipo/PhiLib/Util/*.class

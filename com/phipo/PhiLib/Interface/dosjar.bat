
cd   /home/team02/phipo/DEV/java/src; 
del  /home/team02/phipo/DEV/java/src/phipo/Interface/Interface.jar
jar cfm /home/team02/phipo/DEV/java/src/phipo/Interface/Interface.jar phipo/Interface/Interface.mf phipo/Interface/*.class; cd /home/team02/phipo/DEV/java/src; jar uf /home/team02/phipo/DEV/java/src/phipo/Interface/Interface.jar phipo/PhiLib/Interface/*.class phipo/PhiLib/Lang/*.class phipo/PhiLib/Util/*.class

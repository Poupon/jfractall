all::  CanvasFractal.class  CanvasPalette.class  ConfigDiag.class  DialogPalette.class  FractalConfig.class  FractalMng.class  FractalProcess.class  FrameFractal.class  JFractAll.class  TColor.class

clean::
	rm *.class  *.jar

jar::
	cd ../..;rm  phipo/JFractAll/JFractAll.jar;jar cvfm phipo/JFractAll/JFractAll.jar phipo/JFractAll/JFractAll.mf phipo/JFractAll/*.class  phipo/JFractAll/JFractAll.jar phipo/PhiLib/Interface/*.class 

%.class: %.java
	javac  -classpath ../.. -deprecation -g $*.java
 

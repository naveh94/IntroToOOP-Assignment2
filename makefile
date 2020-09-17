# 312275746
# Naveh Marchoom

compile: bin
	javac -cp src -d bin -cp biuoop-1.4.jar:. src/*.java

run2:
	java -cp biuoop-1.4.jar:bin AbstractArtDrawing

run3.2:
	java -cp biuoop-1.4.jar:bin BouncingBallAnimation
	
run3.3:
	java -cp biuoop-1.4.jar:bin MultipleBouncingBallsAnimation 12 2 3 4 2 9
	
run3.4:
	java -cp biuoop-1.4.jar:bin MultipleFramesBouncingBallsAnimation 12 2 3 4 2 9

bin:
	mkdir bin
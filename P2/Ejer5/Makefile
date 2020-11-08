JAVAC = /home/pablojj/openjdk/jdk-14.0.2/bin/javac
default: 
	$(JAVAC) C.java
	$(JAVAC) Juego.java
	$(JAVAC) Cliente.java
	$(JAVAC) ServidorConcurrente.java
	$(JAVAC) Procesador.java
	java ServidorConcurrente
	java Cliente

clean:
	rm *.class
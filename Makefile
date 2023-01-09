JCC = javac

JFLAGS = -g

JVM = java

.SUFFIXES: .java .class

.java.class:
	$(JCC) $(JFLAGS) $*.java

TARGET = S_40202625_detector

FILE1 = 
FILE2 = 

all: $(TARGET)

clean:
	$(RM) $(TARGET)

run: 
	javac $(TARGET).java
	java $(TARGET) $(FILE1) $(FILE2)

testPlagiarism: $(TARGET)
	@echo "Testing plagiarism test cases in ../data/plagiarismXX"
	@for file in ../data/plagiarism*; do echo "Testing $$file"; $(JVM) $(TARGET) $$file/1.txt $$file/2.txt;done

testNonPlagiarism: $(TARGET)
	@echo "Testing non-plagiarism test cases in ../data/okayXX"
	@for file in ../data/okay*; do echo "Testing $$file"; $(JVM) $(TARGET) $$file/1.txt $$file/2.txt;done

test: testPlagiarism testNonPlagiarism
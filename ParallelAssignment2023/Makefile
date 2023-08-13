JAVAC = /usr/bin/javac
JAVA = /usr/bin/java
.SUFFIXES: .java .class
SRCDIR = src/MonteCarloMini
BINDIR = bin

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) -sourcepath $(SRCDIR) $<

CLASSES= TerrainArea.class SearchParallel.class MonteCarloMinimizationParallel.class Search.class MonteCarloMinimization.class
CLASS_FILES = $(CLASSES:%.class=$(BINDIR)/%.class)
JAVA_FILES = $(CLASSES:%.class=$(SRCDIR)/%.java)

default: $(CLASS_FILES)

clean:
	rm $(BINDIR)/MonteCarloMini/*.class


run:
	$(JAVA) -cp $(BINDIR) MonteCarloMini.MonteCarloMinimizationParallel $(ARGS)
	$(JAVA) -cp $(BINDIR) MonteCarloMini.MonteCarloMinimization $(ARGS)

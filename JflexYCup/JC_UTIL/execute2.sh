#! /bin/bash 
echo "INICIANDO COMPILACION JFLEX "
java -jar ../libe/jflex-full-1.8.2.jar LexerUtil.jflex
echo "INICIANDO COMPILACION JAVA CUP"
java -jar ../libe/java-cup-11b.jar -parser ParserUtil -symbols symU ParserUtil.cup
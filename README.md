# Shift_JavaTask
Инструкция по запуску:
1. git clone https://github.com/MatusNatalia/Shift_JavaTask.git
2. cd Shift_JavaTask
3. mvn compile
4. mvn exec:java -Dexec.mainClass="org.shift.Main" -Dexec.args="аргументы"
 
 Пример:  
 mvn exec:java -Dexec.mainClass="org.shift.Main" -Dexec.args="-i -a out.txt in1.txt in2.txt"

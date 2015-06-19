mkdir -p out
javac -sourcepath ./src -d out src/shop/Store.java
java -classpath ./out shop.Store

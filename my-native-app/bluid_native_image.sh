export JAVA_HOME=/...
export MVN_HOME=/...
export PATH=${JAVA_HOME}/bin:${MVN_HOME}/bin:${PATH}
mvn spring-boot:build-image > resBuild.txt 2>&1
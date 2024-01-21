FROM quay.io/wildfly/wildfly
ADD target/microprofile-config.war /opt/jboss/wildfly/standalone/deployments/
ADD docker/standalone.conf /opt/jboss/wildfly/bin/standalone.conf
ADD docker/standalone.xml /opt/jboss/wildfly/standalone/configuration/standalone.xml
Add docker/module/module.xml /opt/jboss/wildfly/modules/system/layers/base/org/postgresql/main/module.xml
Add docker/module/postgresql-42.7.0.jar /opt/jboss/wildfly/modules/system/layers/base/org/postgresql/main/postgresql-42.7.0.jar
ENV DEBUG_MODE=true
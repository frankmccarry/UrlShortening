package org.wildfly.quickstarts.service;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/v1/rest")
public class JaxRsApplication extends Application {
}

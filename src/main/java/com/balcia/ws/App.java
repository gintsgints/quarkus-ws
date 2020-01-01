package com.balcia.ws;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeIn;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.security.SecuritySchemes;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
@OpenAPIDefinition(info = @Info(title="Balcia demo API", version = "1.0.0"))
@SecuritySchemes(value = {
        @SecurityScheme(securitySchemeName = "api_key",
            type = SecuritySchemeType.APIKEY,
            apiKeyName = "api_key",
            in = SecuritySchemeIn.HEADER),
        @SecurityScheme(securitySchemeName = "openIdConnectUrl",
                type = SecuritySchemeType.OPENIDCONNECT,
                openIdConnectUrl = "http://localhost:8280/auth/realms/quarkus/.well-known/openid-configuration")}
)
public class App extends Application {
}

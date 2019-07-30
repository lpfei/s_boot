package com.example.core.config;

import com.example.core.properties.AppProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.reflect.Type;
import java.time.temporal.Temporal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.example.core.consts.AppConst.HALO_VERSION;

/**
 * Swagger configuration.
 *
 * @author johnniang
 */
@EnableSwagger2
@Configuration
@Slf4j
public class SwaggerConfiguration {

    private final AppProperties appProperties;

    public SwaggerConfiguration(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    private final List<ResponseMessage> globalResponses = Arrays.asList(
            new ResponseMessageBuilder().code(200).message("Success").build(),
            new ResponseMessageBuilder().code(400).message("Bad request").build(),
            new ResponseMessageBuilder().code(401).message("Unauthorized").build(),
            new ResponseMessageBuilder().code(403).message("Forbidden").build(),
            new ResponseMessageBuilder().code(404).message("Not found").build(),
            new ResponseMessageBuilder().code(500).message("Internal server error").build());

    @Bean
    public Docket defaultApi() {
        if (appProperties.isDocDisabled()) {
            log.debug("Doc has been disabled");
        }
        return buildApiDocket("com.example.module.api",
                "com.example.module.api",
                "/**")
//                .securitySchemes(contentApiKeys())
//                .securityContexts(contentSecurityContext())
                .enable(!appProperties.isDocDisabled());
    }

    @Bean
    public Docket adminApi() {
        if (appProperties.isDocDisabled()) {
            log.debug("Doc has been disabled");
        }

        return buildApiDocket("com.example.module.api.admin",
                "com.example.module.api.admin",
                "/api/admin/**")
//                .securitySchemes(adminApiKeys())
//                .securityContexts(adminSecurityContext())
                .enable(!appProperties.isDocDisabled());
    }

    /*@Bean
    SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
                .clientId("halo-app-client-id")
                .clientSecret("halo-app-client-secret")
                .realm("halo-app-realm")
                .appName("halo-app")
                .scopeSeparator(",")
                .additionalQueryStringParams(null)
                .useBasicAuthenticationWithAccessCodeGrant(false)
                .build();
    }*/

    private Docket buildApiDocket(@NonNull String groupName, @NonNull String basePackage, @NonNull String antPattern) {
        Assert.hasText(groupName, "Group name must not be blank");
        Assert.hasText(basePackage, "Base package must not be blank");
        Assert.hasText(antPattern, "Ant pattern must not be blank");

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.ant(antPattern))
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, globalResponses)
                .globalResponseMessage(RequestMethod.POST, globalResponses)
                .globalResponseMessage(RequestMethod.DELETE, globalResponses)
                .globalResponseMessage(RequestMethod.PUT, globalResponses)
                .directModelSubstitute(Temporal.class, String.class);
    }


    private List<SecurityContext> adminSecurityContext() {
        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("/api/admin/.*"))
                        .build()
        );
    }


    private List<SecurityContext> contentSecurityContext() {
        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(contentApiAuth())
                        .forPaths(PathSelectors.regex("/api/content/.*"))
                        .build()
        );
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope[] authorizationScopes = {new AuthorizationScope("Admin api", "Access admin api")};
        return Arrays.asList(new SecurityReference("Token from header", authorizationScopes),
                new SecurityReference("Token from query", authorizationScopes));
    }

    private List<SecurityReference> contentApiAuth() {
        AuthorizationScope[] authorizationScopes = {new AuthorizationScope("content api", "Access content api")};
        return Arrays.asList(new SecurityReference("Access key from header", authorizationScopes),
                new SecurityReference("Access key from query", authorizationScopes));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API Documentation")
                .description("Documentation for API")
                .version(HALO_VERSION)
//                .termsOfServiceUrl("https://github.com/halo-dev")
//                .contact(new Contact("RYAN0UP", "https://ryanc.cc/", "i#ryanc.cc"))
                .build();
    }

    /*@Bean
    public AlternateTypeRuleConvention customizeConvention(TypeResolver resolver) {
        return new AlternateTypeRuleConvention() {
            @Override
            public int getOrder() {
                return Ordered.LOWEST_PRECEDENCE;
            }

            @Override
            public List<AlternateTypeRule> rules() {
                return Arrays.asList(
                        newRule(User.class, emptyMixin(User.class)),
                        newRule(UserDetail.class, emptyMixin(UserDetail.class)),
                        newRule(resolver.resolve(Pageable.class), resolver.resolve(pageableMixin())),
                        newRule(resolver.resolve(Sort.class), resolver.resolve(sortMixin())));
            }
        };
    }*/

    /**
     * For controller parameter(like eg: HttpServletRequest, ModelView ...).
     *
     * @param clazz controller parameter class type must not be null
     * @return empty type
     */
    private Type emptyMixin(Class<?> clazz) {
        Assert.notNull(clazz, "class type must not be null");

        return new AlternateTypeBuilder()
                .fullyQualifiedClassName(String.format("%s.generated.%s", clazz.getPackage().getName(), clazz.getSimpleName()))
                .withProperties(Collections.emptyList())
                .build();
    }

    private Type sortMixin() {
        return new AlternateTypeBuilder()
                .fullyQualifiedClassName(String.format("%s.generated.%s", Sort.class.getPackage().getName(), Sort.class.getSimpleName()))
                .withProperties(Collections.singletonList(property(String[].class, "sort")))
                .build();
    }

    private Type pageableMixin() {
        return new AlternateTypeBuilder()
                .fullyQualifiedClassName(String.format("%s.generated.%s", Pageable.class.getPackage().getName(), Pageable.class.getSimpleName()))
                .withProperties(Arrays.asList(property(Integer.class, "page"), property(Integer.class, "size"), property(String[].class, "sort")))
                .build();
    }

    private AlternateTypePropertyBuilder property(Class<?> type, String name) {
        return new AlternateTypePropertyBuilder()
                .withName(name)
                .withType(type)
                .withCanRead(true)
                .withCanWrite(true);
    }

}

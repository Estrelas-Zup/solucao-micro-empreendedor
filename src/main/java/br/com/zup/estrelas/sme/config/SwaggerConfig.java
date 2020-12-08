package br.com.zup.estrelas.sme.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import br.com.zup.estrelas.sme.entity.Usuario;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("br.com.zup"))
				.build().apiInfo(metaData()).ignoredParameterTypes(Usuario.class).globalOperationParameters(
						Arrays.asList(new ParameterBuilder().name("Authorization").description("Header para token JWT")
								.modelRef(new ModelRef("String")).parameterType("header").required(false).build()));
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder().title("Sisger Universe REST API").description(
				"Sistema automatizado, que calcula estrategicamente a otimização do controle de estoque e vendas.\n"
						+ "Saiba mais através de nosso [GitHub](https://github.com/Estrelas-Zup/solucao-micro-empreendedor).")
				.version("1.0.0").build();
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}

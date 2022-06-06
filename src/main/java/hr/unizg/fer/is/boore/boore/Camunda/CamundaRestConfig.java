package hr.unizg.fer.is.boore.boore.Camunda;

import org.camunda.bpm.spring.boot.starter.rest.CamundaJerseyResourceConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/engine-rest")
@CrossOrigin(origins = "http://localhost:3000")
public class CamundaRestConfig extends CamundaJerseyResourceConfig {


}

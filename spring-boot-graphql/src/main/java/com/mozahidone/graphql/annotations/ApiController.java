package com.mozahidone.graphql.annotations;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Target({java.lang.annotation.ElementType.TYPE})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
@RestController
@CrossOrigin(origins = "*", methods = {GET, POST, PUT,PATCH, DELETE, OPTIONS})
public @interface ApiController {
}

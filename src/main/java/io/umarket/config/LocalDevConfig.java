            package io.umarket.config;
import java.io.File;
/*    */ import java.io.IOException;
/*    */ import org.springframework.context.annotation.Configuration;
/*    */ import org.springframework.context.annotation.Profile;
/*    */ import org.springframework.core.io.ClassPathResource;
/*    */ import org.thymeleaf.TemplateEngine;
/*    */ import org.thymeleaf.templateresolver.FileTemplateResolver;
/*    */ import org.thymeleaf.templateresolver.ITemplateResolver;
/*    */


/*    */ @Configuration
/*    */ @Profile({"local"})
/*    */ public class LocalDevConfig
/*    */ {
/*    */   public LocalDevConfig(TemplateEngine templateEngine) throws IOException {
/* 20 */     ClassPathResource applicationProperties = new ClassPathResource("application.properties");
/* 21 */     if (applicationProperties.isFile()) {
/* 22 */       File sourceRoot = applicationProperties.getFile().getParentFile();
/* 23 */       while ((sourceRoot.listFiles((dir, name) -> name.equals("mvnw"))).length != 1) {
/* 24 */         sourceRoot = sourceRoot.getParentFile();
/*    */       }
/* 26 */       FileTemplateResolver fileTemplateResolver = new FileTemplateResolver();
/* 27 */       fileTemplateResolver.setPrefix(sourceRoot.getPath() + "/src/main/resources/templates/");
/* 28 */       fileTemplateResolver.setSuffix(".html");
/* 29 */       fileTemplateResolver.setCacheable(false);
/* 30 */       fileTemplateResolver.setCharacterEncoding("UTF-8");
/* 31 */       fileTemplateResolver.setCheckExistence(true);
/* 32 */       templateEngine.setTemplateResolver((ITemplateResolver)fileTemplateResolver);
/*    */     } 
/*    */   }
/*    */ }

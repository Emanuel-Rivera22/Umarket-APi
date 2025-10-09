package io.umarket.util; 
/*    */ import jakarta.servlet.http.HttpServletRequest;
/*    */ import org.springframework.context.MessageSource;
/*    */ import org.springframework.stereotype.Component;
/*    */ import org.springframework.web.context.request.RequestContextHolder;
/*    */ import org.springframework.web.context.request.ServletRequestAttributes;
/*    */ import org.springframework.web.servlet.LocaleResolver;
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class WebUtils
/*    */ {
/*    */   public static final String MSG_SUCCESS = "MSG_SUCCESS";
/*    */   public static final String MSG_INFO = "MSG_INFO";
/*    */   public static final String MSG_ERROR = "MSG_ERROR";
/*    */   private static MessageSource messageSource;
/*    */   private static LocaleResolver localeResolver;
/*    */   
/*    */   public WebUtils(MessageSource messageSource, LocaleResolver localeResolver) {

/*    */   }
/*    */   
/*    */   public static HttpServletRequest getRequest() {
/* 26 */     ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
/* 27 */     return attrs != null ? attrs.getRequest() : null;
/*    */   }
/*    */   
/*    */   public static String getMessage(String code, Object... args) {
/* 30 */     return messageSource.getMessage(code, args, code, localeResolver.resolveLocale(getRequest()));
/*    */   }
/*    */ }


/* Location:              C:\Users\johnm\Downloads\Umarket-0.0.1-SNAPSHOT.jar!\BOOT-INF\classes\i\\umarke\\umarke\\util\WebUtils.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
package io.umarket.util;
/*    */ import jakarta.servlet.http.HttpServletRequest;
/*    */ import org.springframework.web.bind.annotation.ControllerAdvice;
/*    */ import org.springframework.web.bind.annotation.ModelAttribute;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @ControllerAdvice
/*    */ public class WebAdvice
/*    */ {
/*    */   @ModelAttribute("isDevserver")
/*    */   public Boolean getIsDevserver(HttpServletRequest request) {
/* 16 */     return Boolean.valueOf("1".equals(request.getHeader("X-Devserver")));
/*    */   }
/*    */ }


/* Location:              C:\Users\johnm\Downloads\Umarket-0.0.1-SNAPSHOT.jar!\BOOT-INF\classes\i\\umarke\\umarke\\util\WebAdvice.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
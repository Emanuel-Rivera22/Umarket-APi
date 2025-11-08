package io.umarket.config;
/*    */ import java.beans.PropertyEditor;
/*    */ import org.springframework.beans.propertyeditors.StringTrimmerEditor;
/*    */ import org.springframework.web.bind.WebDataBinder;
/*    */ import org.springframework.web.bind.annotation.ControllerAdvice;
/*    */ import org.springframework.web.bind.annotation.InitBinder;
/*    */ 
/*    */ @ControllerAdvice
/*    */ public class ControllerConfig
/*    */ {
/*    */   @InitBinder
/*    */   public void initBinder(WebDataBinder binder) {
/* 14 */     binder.registerCustomEditor(String.class, (PropertyEditor)new StringTrimmerEditor(true));
/*    */   }
/*    */ }



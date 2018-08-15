package cn.ives.aspect;

import cn.ives.aspect.annotation.Permissions;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

@Aspect
@Configuration
public class TestAop {

//   @Pointcut("execution(* cn.ives.controller.HelloWorld.getApiVersion*())")
//   public void excudeService() {
//      System.out.println("excudeService");
//   }

   @Before("execution(* cn.ives.controller.HelloWorld.getApiVersion*())")
   public void twiceAsOld() {
      System.out.println("切面before执行了-----------");
   }

   @Before("@annotation(cn.ives.aspect.annotation.Permissions)")
   public void testPermissions(JoinPoint joinPoint) throws Throwable {
      Signature signature = joinPoint.getSignature();
      if(signature instanceof MethodSignature) {
         MethodSignature methodSignature = (MethodSignature) signature;
         Method method = methodSignature.getMethod();
         Permissions annotation = method.getAnnotation(Permissions.class);

         if(annotation != null) {
            String value = annotation.value();
            System.out.println(value);
         }
      }
   }

//   @Around("excudeService()")
//   public Object twiceAsOld(ProceedingJoinPoint proceedingJoinPoint) {
//      System.out.println("切面执行了。。。。");
//
//      try {
//         return proceedingJoinPoint.proceed();
//      } catch (Throwable e) {
//         e.printStackTrace ();
//      }
//      return null;
//   }
}

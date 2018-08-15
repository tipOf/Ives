package cn.ives.controller;

import cn.ives.aspect.annotation.Permissions;
import cn.ives.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/version")
public class HelloWorld {
//   @Autowired
//   private DemoService demoService;

   @GetMapping
   public String getApiVersion() {
      StringBuilder re = new StringBuilder();

//      demoService.getData().forEach(record -> {
//         re.append(record.getUserId() + record.getNickName() + record.getTel());
//      });
      re.append("1.0.0");
      System.out.println(re.toString());
      return re.toString();
   }
}

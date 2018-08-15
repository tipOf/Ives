package cn.ives.service;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Repository;


//@Repository
public class DemoService {
//   @Autowired
//   private JdbcTemplate jdbcTemplate;
//
//   public List<TpUserInfo> getData() {
//      String sql = "select user_id, tel, nickname FROM ives.tp_user";
//      List<TpUserInfo> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TpUserInfo.class));
//      return userList;
//   }

   public void sendMail() {
      MailAccount account = new MailAccount();
      MailUtil.send("liyongli@togeek.cn", "李勇力，恭喜你成为...", "李勇力，我就试试", false);
   }

   public static void main(String[] args) {
   }
}

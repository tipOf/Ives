package cn.ives.domain;

import javax.persistence.Entity;
import java.util.List;

public class User {
   private String id;
   private String name;
   private List<Role> roles;

   public List<Role> getRoles() {
      return roles;
   }

   public void setRoles(List<Role> roles) {
      this.roles = roles;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getPwd() {
      return pwd;
   }

   public void setPwd(String pwd) {
      this.pwd = pwd;
   }

   private String pwd;
}

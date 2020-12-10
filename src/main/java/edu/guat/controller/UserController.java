package edu.guat.controller;

import com.github.pagehelper.PageInfo;
import edu.guat.po.User;
import edu.guat.service.UserService;
import edu.guat.utils.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCrypt bCrypt;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(User user, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        User dbuser = userService.findOne(user);
        if (dbuser == null) {
            model.addAttribute("loginError", "账号或密码错误！");
            return "login";
        }
        // 将前端传过来的密码和数据库中对应的密文对比
        if (bCrypt.matches(user.getPassword(), dbuser.getPassword())) {
            session.setAttribute("user", dbuser);
            if (dbuser.getStatus() == 1) {
                return "redirect:findAll";
            } else {
                //使用此种方式代替字符串拼接避免中文乱码问题
                redirectAttributes.addAttribute("userid", dbuser.getId());
//                return "redirect:/deposit/mydeposit?username=" + dbuser.getUsername();
                return "redirect:/deposit/mydeposit";
            }
        } else {
            model.addAttribute("loginError", "账号或密码错误！");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @GetMapping("/register")
    public String toRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(User user, Model model) {
        try {
            //对密码进行加密
            user.setPassword(bCrypt.encode(user.getPassword()));
            if (userService.add(user)) {
                model.addAttribute("registerOk", "注册成功！");
                return "login";
            } else {
                model.addAttribute("registerError", "注册失败！");
                return "register";
            }
        } catch (Exception e) {
            model.addAttribute("exits", "该账号已存在，请重新输入！");
            return "register";
        }
    }

    @RequestMapping("/findAll")
    public String findAll(Model model,
                          Integer id,
                          @RequestParam(value = "username", defaultValue = "") String username,
                          @RequestParam(value = "address", defaultValue = "") String address,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        PageInfo<User> pageInfo = new PageInfo<>(userService.findAll(id,username,address, pageNum, pageSize));
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("username", username);
        model.addAttribute("id", id);
        model.addAttribute("address", address);
        return "main";
    }

    @GetMapping("/update")
    @ResponseBody
    public User findUser(Integer id) {
        return userService.findById(id);
    }

    @PostMapping("/update")
    @ResponseBody
    public String update(User user) {
        try {
            if (userService.update(user) > 0) {
                return "ok";
            } else {
                return "error";
            }
        }catch (Exception e){
            //e.printStackTrace();
            return "exits";
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String deleteUser(Integer id) {
        try {
            if (userService.delete(id) > 0) {
                return "ok";
            } else {
                return "error";
            }
        }catch (Exception e){
            return "depositing";
        }
    }

    @RequestMapping("/modifyPassword")
    @ResponseBody
    public String modifyPassword(@RequestParam("account") String account,
                                 @RequestParam("oldpwd") String oldpwd,
                                 @RequestParam("newpwd") String newpwd) {
        User user = new User();
        user.setAccount(account);
        //通过账号从数据库中找到对应的用户
        User dbuser = userService.findOne(user);
        if (dbuser == null) {   //判断是否存在该用户
            return "error";
        } else if (bCrypt.matches(oldpwd, dbuser.getPassword())) { //判断旧密码是否正确
            user.setUsername(dbuser.getUsername());     //通过用户名进行修改
            user.setPassword(bCrypt.encode(newpwd));    //设置新密码
            if (userService.updatePassword(user) > 0) {
                return "ok";
            } else {
                return "error";
            }
        } else {
            return "error";
        }
    }

    @GetMapping("/resetpwd")
    public String toResetpwd() {
        return "resetpwd";
    }

    @PostMapping("/resetpwd")
    @ResponseBody
    public String doResetpwd(User user) {
        User u = userService.findForUpdatePwd(user);
        if (u == null) {
            return "nosuchone";
        } else {
            user.setPassword(bCrypt.encode(user.getPassword()));
            if (userService.updatePassword(user) > 0) {
                return "ok";
            } else {
                return "error";
            }
        }
    }

}

package com.cesaba.siriusmobliemain.Controller;


import com.alibaba.fastjson.JSONObject;
import com.cesaba.siriusmobliemain.annotation.LogAnnotation;
import com.cesaba.siriusmobliemain.dto.JwtUser;
import com.cesaba.siriusmobliemain.dto.VerifyCode;
import com.cesaba.siriusmobliemain.dto.VerifyString;
import com.cesaba.siriusmobliemain.entity.Role;
import com.cesaba.siriusmobliemain.entity.User;
import com.cesaba.siriusmobliemain.filter.TokenFilter;
import com.cesaba.siriusmobliemain.mapper.UserMapper;
import com.cesaba.siriusmobliemain.service.MailService;
import com.cesaba.siriusmobliemain.service.UserService;
import com.cesaba.siriusmobliemain.service.VerifyService;
import com.cesaba.siriusmobliemain.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;



@Api(tags = "用户")
@RestController
@RequestMapping("/user")
@EnableSwagger2
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private VerifyService verifyService;

    @Autowired
    private MailService mailService;

    @Resource
    private TemplateEngine templateEngine;

    private static final Logger log = LoggerFactory.getLogger("adminLogger");



    @LogAnnotation
    @ApiOperation(value = "添加用户")
    @PreAuthorize("hasAuthority('sys:user:add')")
    @RequestMapping(method = RequestMethod.POST, value = "/insert")
    public void insert(User user){
        userService.insertUser(user);
    }


    @LogAnnotation
    @ApiOperation(value = "删除用户")
    @PreAuthorize("hasAuthority('sys:user:del')")
    @RequestMapping(method = RequestMethod.GET, value = "/delete/{id}")
    public void delete(@PathVariable("id") String id){
        userService.deleteUserById(id);
    }


    @LogAnnotation
    @ApiOperation(value = "update user")
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public void update(User user){
        userService.updateUserById(user);
        String id = user.getId();
        log.debug("更新了用户{}信息",id);
    }

    @LogAnnotation
    @RequestMapping(method = RequestMethod.POST, value = "/update/{id}")
    public void updateDept(@PathVariable("id")String id, @Param("dept")String dept){userService.updateDept(id, dept);}

    /*@RequestMapping(method = RequestMethod.GET, value = "/select/{id}")
    public User select(@PathVariable("id") String id){
        return userService.selectById(id);
    }*/

    @LogAnnotation
    @RequestMapping(method = RequestMethod.POST, value = "/setPassword/{id}")
    public void setPassword(@PathVariable("id")String id, @Param("newPassword")String newPassword){userService.setPassword(id, newPassword);}

    @RequestMapping(method = RequestMethod.GET, value = "/select/{deptname}")   //在控制访问层里面对返回的List进行操作
    public String selectUserByDept(@PathVariable("deptname") String deptname){
        List list = userService.selectUserByDept(deptname);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("data",list);
        return jsonObject.toString();
    }

    @RequestMapping(method = RequestMethod.GET, value = "select/{pageNum}/{pageSize}")
    public List<User> selectAll(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
        return userService.selectAll(pageNum,pageSize);
    }

    @GetMapping("/current")
    @ApiOperation(value = "当前登录用户")
    //@PreAuthorize("hasAuthority('sys:user:query')")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public User currentUser() {
        //System.out.println("????????????????????");
        return UserUtil.getLoginUser();
    }

    @GetMapping("/ip")
    @ApiOperation(value = "当前IP地址")
    public boolean getUserCurrentIp(HttpServletRequest request, @Param("ip") String ip){
        System.out.println(ip);
        String currentUserId = UserUtil.getLoginUser().getId();
        System.out.println(currentUserId);
        String token = TokenFilter.getToken(request);
        System.out.println(token);
        return userService.saveUserIp(ip, token);
    }



    @LogAnnotation
    @PutMapping(params = "headImgUrl")
    @ApiOperation(value = "修改头像")
    public void updateHeadImgUrl(String headImgUrl) {
        User user = UserUtil.getLoginUser();
        JwtUser jwtUser = new JwtUser();
        BeanUtils.copyProperties(user, jwtUser);
        jwtUser.setHeadImgUrl(headImgUrl);
        userService.updateUser(jwtUser);
    }

    /*@GetMapping("/verify")
    @ApiOperation(value = "验证码")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.setContentType("image/JPEG");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        HttpSession session = request.getSession();
        VerifyCode s = new VerifyCode(60);
        session.setAttribute("code", s.getImage());
        s.write(response.getOutputStream());
        System.out.println(s.getCodeBody());
    }*/

    @LogAnnotation
    @GetMapping("/email")
    @ApiOperation(value = "生成邮箱验证码并备份缓存")
    public String emailVerify() throws Exception {
        String id = UserUtil.getLoginUser().getId();
        String username = UserUtil.getLoginUser().getUsername();
        String addr = UserUtil.getLoginUser().getEmail();
        String ipAddr = UserUtil.getLoginUser().getIPaddress();
        String str = new VerifyString().getStr();
        verifyService.mail_verify(id, str);
        Context context = new Context();
        context.setVariable("username", username);
        context.setVariable("userid", id);
        context.setVariable("userIp", ipAddr);
        context.setVariable("str", str);
        String emailContent = templateEngine.process("emailTemplate", context);
        mailService.sendMail(addr,"邮箱验证码 From 数字媒体实验室", emailContent);
        return addr;
    }

    @LogAnnotation
    @PostMapping("/email/{str}")
    @ApiOperation(value = "对返回验证码进行确认")
    public boolean identify(@PathVariable("str") String str){
        String id = UserUtil.getLoginUser().getId();
        System.out.println(verifyService.identify(id, str));
        return verifyService.identify(id, str);
    }








    /*@Autowired
    UserMapper userMapper;

    @GetMapping("/user/insert")
    public User insertUser(User user){
        userMapper.insertUserById(user);
        return user;
    }

    /*@GetMapping("user/delete")
    public User de*/

    /*private static final byte[] DES_KEY = { 21, 1, -110, 82, -32, -85, -128, -65 };  //EDSI阿米
    @SuppressWarnings("restriction")
    public static String encryptBasedDes(String data) {
        String encryptedData = null;
        try {
            //可信任的随机数源
            SecureRandom sr = new SecureRandom();
            DESKeySpec deskey = new DESKeySpec(DES_KEY);
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(deskey);
            // 加密对象
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key, sr);
            // 加密，并把字节数组编码成字符串
            encryptedData = new sun.misc.BASE64Encoder().encode(cipher.doFinal(data.getBytes()));
        } catch (Exception e) {
            // log.error("加密错误，错误信息：", e);
            throw new RuntimeException("加密错误，错误信息：", e);
        }
        return encryptedData;
    }
    @SuppressWarnings("restriction")
    public static String decryptBasedDes(String cryptData) {
        String decryptedData = null;
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            DESKeySpec deskey = new DESKeySpec(DES_KEY);
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(deskey);
            // 解密对象
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key, sr);
            // 把字符串进行解码，解码为为字节数组，并解密
            decryptedData = new String(cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(cryptData)));
        } catch (Exception e) {
            throw new RuntimeException("解密错误，错误信息：", e);
        }
        return decryptedData;
    }*/



}

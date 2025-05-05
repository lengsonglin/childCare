package com.cqut.childcare.customer.service.impl;

import com.cqut.childcare.common.constant.MinioBucketConstant;
import com.cqut.childcare.common.constant.RedisKey;
import com.cqut.childcare.common.domain.vo.ApiResult;
import com.cqut.childcare.common.exception.AppRuntimeException;
import com.cqut.childcare.common.exception.CommonErrorEnum;
import com.cqut.childcare.common.exception.RelationErrorEnum;
import com.cqut.childcare.common.utils.JsonUtils;
import com.cqut.childcare.common.utils.JwtUtils;
import com.cqut.childcare.common.utils.RedisUtils;
import com.cqut.childcare.customer.dao.CustomerDao;
import com.cqut.childcare.customer.domain.dto.CustomerLoginAboutDto;
import com.cqut.childcare.customer.domain.dto.CustomerLoginDto;
import com.cqut.childcare.customer.domain.dto.ModifyCInfoDto;
import com.cqut.childcare.customer.domain.entity.Customer;
import com.cqut.childcare.customer.domain.enums.CustomerTypeEnum;
import com.cqut.childcare.customer.domain.enums.LoginAboutTypeEnum;
import com.cqut.childcare.customer.domain.vo.CustomerBaseInfo;
import com.cqut.childcare.customer.domain.vo.CustomerInfoVo;
import com.cqut.childcare.customer.domain.vo.LoginVo;
import com.cqut.childcare.customer.service.CustomerInfoService;
import com.cqut.childcare.minIo.service.OssService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author Faiz
 * @ClassName CustomerInfoServiceImpl
 * @Version 1.0
 */
@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private OssService ossService;

    private static final Integer TOKEN_EXPIRE_DAYS = 5;
    //token续期时间
    private static final Integer TOKEN_RENEWAL_DAYS = 2;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomerDao customerDao;
    @Override
    public void loginAbout(CustomerLoginAboutDto customerLoginAboutDto) {
        String telPhone = customerLoginAboutDto.getTelPhone();
        String password = customerLoginAboutDto.getPassword();
        String captcha = customerLoginAboutDto.getCaptcha();
        Integer type = customerLoginAboutDto.getType();

        String captchaRedisValue = redisTemplate.opsForValue().get(RedisKey.getKey(RedisKey.CAPTCHA_KEY,telPhone));

        if (!captcha.equals(captchaRedisValue)){
            throw new AppRuntimeException(CommonErrorEnum.INVALID_CAPTCHA);
        }
        Customer customer = customerDao.getByTelPhone(telPhone);
        if (customer!=null){
            throw new AppRuntimeException(CommonErrorEnum.CUSTOMER_PHONE_IS_REGISTER);
        }
        customer = new Customer();
        customer.setTelPhone(telPhone);
        customer.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        customer.setAvatar("");       //初始用默认头像
        customer.setRegisterTime(new Date());

        if(Objects.equals(type, LoginAboutTypeEnum.REGISTER.getType())){
            customer.setType(CustomerTypeEnum.FAMILY.getType());
            customerDao.save(customer);
        }else if(Objects.equals(type, LoginAboutTypeEnum.MODIFY_PASSWORD.getType())){
            boolean flag = customerDao.modifyPasswordByTelphone(password,telPhone);
            if (!flag) {
                throw new AppRuntimeException(CommonErrorEnum.LOGIN_ERROR);
            }
        }
        redisTemplate.delete(RedisKey.getKey(RedisKey.CAPTCHA_KEY,telPhone));
    }

    @Override
    public ApiResult<LoginVo> login(CustomerLoginDto customerLoginDto) {
        String password = customerLoginDto.getPassword();
        String telPhone = customerLoginDto.getTelPhone();

        Customer customer = customerDao.getByTelPhone(telPhone);

        if (Objects.isNull(customer)) {
            return ApiResult.fail(CommonErrorEnum.LOGIN_ERROR);
        }
        //密码验证
        String md5InputPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        if(!md5InputPassword.equals(customer.getPassword())) {
            return ApiResult.fail(CommonErrorEnum.LOGIN_ERROR);
        }
        // 重新生成token
        String token = jwtUtils.createToken(customer.getId());

        //把 token 刷新进redis
        redisTemplate.opsForValue().set(RedisKey.getKey(RedisKey.CUSTOMER_TOKEN,customer.getId()),token,TOKEN_EXPIRE_DAYS, TimeUnit.DAYS);
        //加载用户信息到redis
        String key = RedisKey.getKey(RedisKey.CUSTOMER_INFO, customer.getId());
        redisTemplate.opsForValue().set(key,JsonUtils.toStr(customer));

        CustomerInfoVo customerInfoVo = new CustomerInfoVo();
        BeanUtils.copyProperties(customer,customerInfoVo);
        LoginVo data = new LoginVo(token,customerInfoVo);
        return ApiResult.success(data);
    }

    @Override
    public CustomerBaseInfo getCustomerInfo(Long cId) {
        Customer customer = new Customer();
        //从redis 获取用户信息
        String customerInfoOfJSON = redisTemplate.opsForValue().get(RedisKey.getKey(RedisKey.CUSTOMER_INFO, cId));

        if (StringUtils.isEmpty(customerInfoOfJSON)) {
            //从数据库获取用户信息
            customer = customerDao.getByCid(cId);
            String key = RedisKey.getKey(RedisKey.CUSTOMER_INFO, cId);
            redisTemplate.opsForValue().set(key,JsonUtils.toStr(customer));
            if(Objects.isNull(customer)){
                return new CustomerBaseInfo();
            }
        } else {
            customer = JsonUtils.toObj(customerInfoOfJSON,Customer.class);
        }
        CustomerBaseInfo customerBaseInfo = new CustomerBaseInfo();
        BeanUtils.copyProperties(customer,customerBaseInfo);
        return customerBaseInfo;
    }

    /**
     *  校验token是不是有效
     * @param token
     * @return
     */
    @Override
    public boolean verify(String token) {
        Long cid = jwtUtils.getCidOrNull(token);
        if (Objects.isNull(cid)) {
            return false;
        }
        String key = RedisKey.getKey(RedisKey.CUSTOMER_TOKEN, cid);
        String realToken = redisTemplate.opsForValue().get(key);
        return Objects.equals(token, realToken);//有可能token失效了，需要校验是不是和最新token一致
    }

    @Override
    public Long getValidCid(String token) {
        boolean verify = verify(token);
        return verify ? jwtUtils.getCidOrNull(token) : null;
    }

    @Async
    @Override
    public void renewalTokenIfNecessary(Long uid) {
        String key = RedisKey.getKey(RedisKey.CUSTOMER_TOKEN, uid);
        long expireDays = RedisUtils.getExpire(key, TimeUnit.DAYS);
        if (expireDays == -2) {//不存在的key
            return;
        }
        if (expireDays < TOKEN_RENEWAL_DAYS) {//小于2天的token帮忙续期
            RedisUtils.expire(key, TOKEN_EXPIRE_DAYS, TimeUnit.DAYS);
        }
    }

    @Override
    public CustomerInfoVo modifyCustomerInfo(Long cid, ModifyCInfoDto modifyCInfoDto) {
        String avatarUrl = "";
          //判断头像是否为空
        if(Objects.nonNull(modifyCInfoDto.getAvatarFile())){
            avatarUrl = ossService.uploadFile(modifyCInfoDto.getAvatarFile(), MinioBucketConstant.CUSTOMER_AVATAR_BUCKET);
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(modifyCInfoDto,customer);
        if(StringUtils.hasText(avatarUrl)){
            customer.setAvatar(avatarUrl);
        }
        customer.setId(cid);
        customerDao.modifyCustomerInfo(customer);
        //删除redis 缓存
        redisTemplate.delete(RedisKey.getKey(RedisKey.CUSTOMER_INFO,cid));
        Customer temp = customerDao.getByCid(cid);
        CustomerInfoVo customerInfoVo = new CustomerInfoVo();
        BeanUtils.copyProperties(temp,customerInfoVo);
        return customerInfoVo;
    }

    @Override
    public ApiResult getFileUrl(String filePath) {
        String avatarUrl = "";
        try {
            if(StringUtils.hasText(filePath)){
                avatarUrl  = ossService.findObjectAcrossBuckets(filePath);
            }
        } catch (Exception e) {
            throw new AppRuntimeException(CommonErrorEnum.GET_FILE_FAILED);
        }
        Map<String,String> data = new HashMap<>();
        data.put("avatarUrl",avatarUrl);
        return ApiResult.success(data);
    }

    @Override
    public ApiResult getCustomerInfoByTelPhone(String telPhone) {
        Customer customer = customerDao.getCustomerInfoByTelPhone(telPhone);
        if(Objects.nonNull(customer)){
            CustomerBaseInfo customerBaseInfo = new CustomerBaseInfo();
            BeanUtils.copyProperties(customer,customerBaseInfo);
            return ApiResult.success(customerBaseInfo);
        }
        return ApiResult.fail(RelationErrorEnum.INVALID_TELPHONE);
    }

    @Override
    public ResponseEntity<InputStreamResource> getFile(String filePath)  {

        try {
            return ossService.getFileAcrossBuckets(filePath);
        } catch (Exception e) {
            throw new AppRuntimeException(CommonErrorEnum.GET_FILE_FAILED);
        }
    }

}

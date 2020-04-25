package com.bwie.service;

import cn.hutool.crypto.SecureUtil;
import com.bwie.dao.TUserMapper;
import com.bwie.model.TUser;
import com.bwie.model.TUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @描述：
 * @作者：zhangyuyang
 * @日期：2020/4/22 9:08
 */
@Service
public class UserService {
    @Autowired
    private TUserMapper tUserMapper ;

    @Transactional
    public int registerToUser(TUser tUser) {
        return  tUserMapper.insert(tUser);
    }

    public String checkPhone(String phone) {
        //根据手机号去查询是否有当前用户，如果有，则返回消息为手机号已存在，请重新输入
        //如果没有，则提示用户手机号可用
        TUserExample tUserExample = new TUserExample();
        //select * from t_user where phone = #{phone}
        tUserExample.createCriteria().andPhoneEqualTo(phone);
        List<TUser> tUsers = tUserMapper.selectByExample(tUserExample);
        //如果返回的集合中的数据大于0 ，则当前数据肯定已经存在了
        if(null!=tUsers && tUsers.size()>0){
            //已存在
            return "0";
        }else{
            return "1";
        }

    }

    public TUser checkUserNameAndPassword(String loginName, String password) {
        TUserExample tUserExample = new TUserExample();
        //select * from t_user where phone = #{phone}
        //对密码进行加密的对比
        String md5Password = SecureUtil.md5(password);//密码md5 加密



        tUserExample.createCriteria().andLoginNameEqualTo(loginName).andPasswordEqualTo(md5Password);
        List<TUser> tUsers = tUserMapper.selectByExample(tUserExample);
        if(null!=tUsers && tUsers.size()>0){
            return  tUsers.get(0);
        }
        return null;
    }
}

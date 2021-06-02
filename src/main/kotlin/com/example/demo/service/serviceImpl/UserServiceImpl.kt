package com.example.demo.service.serviceImpl

import com.example.demo.bean.MessageBean
import com.example.demo.bean.SignBean
import com.example.demo.bean.SignDataVo
import com.example.demo.bean.UserBean
import com.example.demo.dao.UserDao
import com.example.demo.service.UserService
import org.springframework.beans.BeanUtils
import org.springframework.stereotype.Service
import org.springframework.util.DigestUtils
import sun.security.provider.MD5
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*
import javax.annotation.Resource
import kotlin.collections.ArrayList
import kotlin.math.hypot

@Service("UserService")
class UserServiceImpl:UserService{

    @Resource
    lateinit var userDao: UserDao

    override fun getUserById(userid:Int): UserBean? {
        return userDao.getUserById(userid)
    }

    override fun addUser(userpassword: String, username: String):Int? {
        var toAddUser:UserBean= UserBean()
        toAddUser.userpassword=userpassword
        toAddUser.username=username
        userDao.addUser(toAddUser)
        return toAddUser.userid
    }

    override fun setUserColor(userid: Int, userColor: String) {
        userDao.setUserColor(userid,userColor)
    }

    override fun setUserDirection(userid: Int, direction: Boolean) {
        userDao.setUserDirection(userid,direction)
    }

    override fun setOtherId(userid: Int, otherid: Int) {
        userDao.setOtherId(userid,otherid)
    }

    override fun sign(userid: Int, type: Int, event: String, message: String, progress: Int) {
        var calendar:Calendar= Calendar.getInstance(Locale.CHINA)
        var signDate=SimpleDateFormat("yyyy-MM-dd").format(calendar.time)
        var baseBuilder:StringBuilder= StringBuilder()
        baseBuilder.append(userid)
        baseBuilder.append(type)
        baseBuilder.append(event)
        baseBuilder.append(message)
        baseBuilder.append(progress)
        val verifyData:String=DigestUtils.md5DigestAsHex(baseBuilder.toString().toByteArray())
        var putMessage:MessageBean= MessageBean()
        putMessage.type=type
        putMessage.event=event
        putMessage.message=message
        putMessage.progress=progress
        putMessage.verifyData=verifyData
        userDao.addMessage(putMessage)
        val messageId:Int?=putMessage.signMessageId
        if(messageId==null||messageId==0){
            return
        }else{
            var putSign:SignBean= SignBean()
            putSign.signDate=signDate
            putSign.signMessage=messageId
            putSign.userid=userid
            if(userDao.getSignByDate(signDate,userid)!=null){
                userDao.updateSign(putSign)
            }else {
                userDao.sign(putSign)
            }
        }
    }

    override fun getSignDate(userid: Int): List<SignDataVo> {
        var signDataList:ArrayList<SignDataVo> = ArrayList()
        var signList:List<SignBean> =userDao.getSignByUserId(userid)
        for(sign:SignBean in signList){
            println(sign.signid)
            println(sign.signMessage)
            if(sign.signMessage==null){
                break
            }else {
                var signData:SignDataVo= SignDataVo()
                BeanUtils.copyProperties(sign,signData)
                BeanUtils.copyProperties(userDao.getSignMessageById(sign.signMessage!!),signData)
                signDataList.add(signData)
            }
        }
        return signDataList
    }

    override fun test(): Any {
        return userDao.getSignByUserId(1001)
    }
}
package com.example.demo.dao

import com.example.demo.bean.MessageBean
import com.example.demo.bean.SignBean
import com.example.demo.bean.SignDataVo
import com.example.demo.bean.UserBean
import org.apache.ibatis.annotations.Mapper

@Mapper
interface UserDao {

    fun getUserById(userid:Int): UserBean
    fun addUser(user:UserBean)
    fun setUserColor(userid: Int, userColor: String)
    fun setUserDirection(userid:Int,direction:Boolean)
    fun setOtherId(userid:Int,otherid:Int)
    fun addMessage(put: MessageBean)
    fun sign(put:SignBean)
    fun getSignByDate(date:String,userid:Int):SignBean?
    fun updateSign(put:SignBean)
    fun getSignByUserId(userid: Int):List<SignBean>
    fun getSignMessageById(messageId:Int):MessageBean
}
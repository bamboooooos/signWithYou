package com.example.demo.service

import com.example.demo.ResultModel
import com.example.demo.bean.SignDataVo
import com.example.demo.bean.UserBean
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
interface UserService {
    fun getUserById(userid:Int):UserBean?
    fun addUser(userpassword:String,username:String):Int?
    fun setUserColor(userid:Int,userColor:String)
    fun setUserDirection(userid:Int,direction:Boolean)
    fun setOtherId(userid:Int,otherid:Int)
    fun sign(userid:Int,type:Int,event:String,message:String,progress:Int)
    fun getSignDate(userid: Int):List<SignDataVo>
    fun test():Any
}
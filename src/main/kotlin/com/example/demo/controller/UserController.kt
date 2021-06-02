package com.example.demo.controller

import com.example.demo.ResultModel
import com.example.demo.bean.SignDataVo
import com.example.demo.bean.UserBean
import com.example.demo.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource

@RestController
class UserController {

    @Resource
    lateinit var userService:UserService


    @GetMapping("hello")
    fun hello():Any{
        return "hello"
    }

    @RequestMapping("/index")
    fun index():String{
        return "index"
    }

    @GetMapping("getUserById")
    fun getUserById(userid:Int):ResultModel{
        println("get:getUserById{userid:$userid}")
        var result:ResultModel= ResultModel()
        val resultData=userService.getUserById(userid)
        val isResultEmpty:Boolean=(resultData?.userid == null ||resultData.userid==0)
        if(isResultEmpty){
            result.code=404
            result.message="未找到该用户"
            result.data=null
        }else{
            result.code=0
            result.message=""
            result.data=resultData
        }
        println("get:getUserById=>result:"+!isResultEmpty)
        return result
    }
    @GetMapping("addUser")
    fun addUser(userpassword:String,username:String):ResultModel{
        println("get:addUser{}")
        var result:ResultModel= ResultModel()
        val resultId=userService.addUser(userpassword,username)
        val isResultEmpty:Boolean=(resultId == null ||resultId==0)
        if (isResultEmpty) {
            result.code = 404
            result.message = "添加失败"
            result.data = null
        }else{
            result.code=0
            result.message=""
            result.data=resultId
        }
        println("get:getUserById=>result:"+!isResultEmpty)
        return result
    }

    @GetMapping("setUserColor")
    fun setUserColor(userid:Int,userColor:String):ResultModel{
        println("get:setUserColor{userid:$userid,userColor:$userColor}")
        var result:ResultModel= ResultModel()
        userService.setUserColor(userid,userColor)
        result.code=0
        result.message=""
        result.data="execute"
        println("get:getUserById=>result:execute")
        return result
    }

    @GetMapping("setUserDirection")
    fun setUserDirection(userid:Int,direction:Boolean):ResultModel{
        println("get:setUserDirection{userid:$userid,direction:$direction}")
        var result:ResultModel= ResultModel()
        userService.setUserDirection(userid,direction)
        result.code=0
        result.message=""
        result.data="execute"
        println("get:setUserDirection=>result:execute")
        return result
    }

    @GetMapping("setOtherId")
    fun setOtherId(userid:Int,otherid:Int):ResultModel{
        println("get:setOtherId{userid:$userid,otherid:$otherid}")
        var result:ResultModel= ResultModel()
        userService.setOtherId(userid,otherid)
        result.code=0
        result.message=""
        result.data="execute"
        println("get:setOtherId=>result:execute")
        return result
    }
    @GetMapping("sign")
    fun sign(userid:Int,type:Int,event:String,message:String,progress:Int):ResultModel{
        println("get:sign{userid:$userid,type:$type,event:$event,message:$message,progress:$progress}")
        var result:ResultModel= ResultModel()
        userService.sign(userid,type,event,message,progress)
        result.code=0
        result.message=""
        result.data="execute"
        println("get:sign=>result:execute")
        return result
    }
    @GetMapping("getSignDate")
    fun getSignDate(userid: Int):ResultModel{
        println("get:getSignDate{userid:$userid}")
        var result:ResultModel= ResultModel()
        var resultData:List<SignDataVo> =userService.getSignDate(userid)
        val isResultEmpty:Boolean=(resultData == null || resultData.isEmpty())
        if(isResultEmpty){
            result.code = 404
            result.message = "未找到数据"
            result.data = null
        }else {
            result.code = 0
            result.message = ""
            result.data = resultData
        }
        println("get:getSignDate=>result:"+!isResultEmpty)
        return result
    }

    @GetMapping("test")
    fun test():Any{
        return userService.test()
    }
}
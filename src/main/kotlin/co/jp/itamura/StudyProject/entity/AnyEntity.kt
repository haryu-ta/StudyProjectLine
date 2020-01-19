package co.jp.itamura.StudyProject.entity

//data class AnyEntity(val id : Int,val json : String)

open class AnyEntity(val id : Int,val json : String){
    
    override fun toString() : String{
        return "AnyEntity=(id='${id}',json='${json}')"
    }

}
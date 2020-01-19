package co.jp.itamura.StudyProject.entity

class LineEntity : AnyEntity{

    var name : String = ""

    // 新たな項目を追加で定義する場合はセカンドコンストラクタ定義
    constructor(id : Int,json : String,name : String ):super(id,json){
        this.name = name
    }

    constructor(id : Int,json : String) : super(id,json){
        this.name = ""
    }

    override fun toString(): String {
        return "LineEntity=(id='${id}',json='${json}',name='${name}')"
    }
}

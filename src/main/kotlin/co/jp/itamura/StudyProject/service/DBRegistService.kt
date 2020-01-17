package co.jp.itamura.StudyProject.service

interface DBRegistService {
    fun registPrefucture(no:Int) : Unit

    fun regist(kbn : String) : Unit
}
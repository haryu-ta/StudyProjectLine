package co.jp.itamura.StudyProject.service.impl

import co.jp.itamura.StrudyProject.service.DBReadService
import co.jp.itamura.StudyProject.constans.*
import co.jp.itamura.StudyProject.controller.APIController
import co.jp.itamura.StudyProject.dao.*
import co.jp.itamura.StudyProject.entity.AnyEntity
import co.jp.itamura.StudyProject.entity.LineEntity
import co.jp.itamura.StudyProject.entity.PrefuctureEntity
import co.jp.itamura.StudyProject.entity.SarchConditionEntity
import co.jp.itamura.StudyProject.service.DBRegistService
import org.apache.logging.log4j.LogManager
import org.omg.CORBA.Object
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.FileReader

@Service
class DBReadServiceImpl : DBReadService {

    @Autowired
    lateinit private var prefuctureDao : PrefuctureDao

    @Autowired
    lateinit private var lineDao : LineDao

    @Autowired
    lateinit private var stationDao : StationDao

    @Autowired
    lateinit private var stationJoinDao: StationJoinDao

    @Autowired
    lateinit private var groupDao: GroupDao

    companion object {
        private val logger = LogManager.getLogger(DBReadServiceImpl::class.java)
    }

    /**
     * 指定された情報を返却する.
     * @return json文字列
     */
    override fun readInfo(kbn: String, no: String): String? {

        var dao : AllDao

        // INSERTを行う共通関数
        val func : (AllDao, SarchConditionEntity) -> AnyEntity = {
            dao, entity ->  dao.select(entity)
        }

        when(kbn){
            "p" -> {
                dao = prefuctureDao
            }
            "l" -> {
                dao = lineDao
            }
            "s" -> {
                dao = stationDao
            }
            "g" -> {
                dao = groupDao
            }
            "j" ->{
                dao = stationJoinDao
            }
            else -> {
                logger.info("============ 処理対象なし ============")
                return ""
            }
        }

       val result : AnyEntity? = func(dao,SarchConditionEntity(no))

        return result?.json
    }

}
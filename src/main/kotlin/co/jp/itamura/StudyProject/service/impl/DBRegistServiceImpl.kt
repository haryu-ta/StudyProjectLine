package co.jp.itamura.StudyProject.service.impl

import co.jp.itamura.StudyProject.constans.*
import co.jp.itamura.StudyProject.controller.APIController
import co.jp.itamura.StudyProject.dao.*
import co.jp.itamura.StudyProject.entity.AnyEntity
import co.jp.itamura.StudyProject.entity.LineEntity
import co.jp.itamura.StudyProject.entity.PrefuctureEntity
import co.jp.itamura.StudyProject.service.DBRegistService
import org.apache.logging.log4j.LogManager
import org.omg.CORBA.Object
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.FileReader
import jdk.nashorn.internal.codegen.CompilerConstants.className
import kotlin.reflect.KType


@Service
class DBRegistServiceImpl : DBRegistService {

    @Autowired
    lateinit private var resourceLoader : ResourceLoader

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
        private val logger = LogManager.getLogger(DBRegistServiceImpl::class.java)
    }

    override fun registPrefucture(no: Int) {

        // DBに格納する値を取得
        var line : String = ""
        val resource: Resource = //resourceLoader.getResource("classpath:file/${no}.json")
                resourceLoader.getResource("file:${P_FILEPATH}${no}.json")
        val bf : BufferedReader = BufferedReader(FileReader(resource.file))
        bf.use{
            line = it.readLine()
        }

        // Entityに詰める
        val entity = PrefuctureEntity(no, line)

        // Daoを呼び出す
        prefuctureDao.upsertPrefucture(entity)

    }

    override fun regist(kbn : String) {

        var filepath : String = ""
        var dao : AllDao
        //val clazz : Class<*>

        // INSERTを行う共通関数
        val func : (AllDao,AnyEntity) -> Unit = {
            dao, entity ->  dao.upsert(entity)
        }

        when(kbn){
            "p" -> {
                filepath = P_FILEPATH
                dao = prefuctureDao
                //clazz = Class.forName("co.jp.itamura.StudyProject.entity.PrefuctureEntity")
            }
            "l" -> {
                filepath = L_FILEPATH
                dao = lineDao
                //clazz = Class.forName("co.jp.itamura.StudyProject.entity.LineEntity")
            }
            "s" -> {
                filepath = S_FILEPATH
                dao = stationDao
            }
            "g" -> {
                filepath = G_FILEPATH
                dao = groupDao
            }
            "j" ->{
                filepath = J_FILEPATH
                dao = stationJoinDao
            }
            else -> {
                logger.info("============ 処理対象なし ============")
                return
            }
        }

        val resource: Resource = resourceLoader.getResource("file:${filepath}")

        //val clazz = Class.forName(className)
        //val service = clazz.newInstance() as Service

        // 指定ディレクトリにファイルが存在しない場合には処理を抜ける
        if( resource.file.listFiles() != null  ) {

            // 指定ディレクトリは以下のjsonファイル数分ループ処理
            for (f in resource.file.listFiles().filter { it.isFile && it.name.endsWith(".json") }) {

                var line: String = ""
                var no: Int = f.name.replace(".json", "").toInt()

                val bf: BufferedReader = BufferedReader(FileReader(f))
                bf.use {
                    line = it.readLine()
                }

                logger.info("${no} -> ${line}")
                func(dao,AnyEntity(no,line))
                //func(dao,clazz.getConstructor(Int::class.java,String::class.java).newInstance(no,line) as LineEntity)

            }
        }
    }

}
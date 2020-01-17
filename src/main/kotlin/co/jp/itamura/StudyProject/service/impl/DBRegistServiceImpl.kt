package co.jp.itamura.StudyProject.service.impl

import co.jp.itamura.StudyProject.constans.*
import co.jp.itamura.StudyProject.controller.APIController
import co.jp.itamura.StudyProject.dao.PrefuctureDao
import co.jp.itamura.StudyProject.entity.AnyEntity
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

@Service
class DBRegistServiceImpl : DBRegistService {

    @Autowired
    lateinit private var resourceLoader : ResourceLoader

    @Autowired
    lateinit private var prefuctureDao : PrefuctureDao

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

        when(kbn){
            "p" -> filepath = P_FILEPATH
            "l" -> filepath = L_FILEPATH
            "s" -> filepath = S_FILEPATH
            "g" -> filepath = G_FILEPATH
            "j" -> filepath = J_FILEPATH
            else -> {
                logger.info("============ 処理対象なし ============")
                return
            }
        }

        var func = { prefuctureDao.upsertPrefucture(PrefuctureEntity(1,"")) }

        val resource: Resource = resourceLoader.getResource("file:${filepath}")

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
                //AnyEntity(no,line)

            }
        }
    }

}
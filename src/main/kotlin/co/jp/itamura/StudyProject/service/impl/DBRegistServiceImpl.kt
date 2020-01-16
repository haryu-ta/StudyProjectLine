package co.jp.itamura.StudyProject.service.impl

import co.jp.itamura.StudyProject.constans.FILEPATH
import co.jp.itamura.StudyProject.dao.PrefuctureDao
import co.jp.itamura.StudyProject.entity.PrefuctureEntity
import co.jp.itamura.StudyProject.service.DBRegistService
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

    override fun regist(no: Int) {

        // DBに格納する値を取得
        var line : String = ""
        val resource: Resource = //resourceLoader.getResource("classpath:file/${no}.json")
                resourceLoader.getResource("file:${FILEPATH}${no}.json")
        val bf : BufferedReader = BufferedReader(FileReader(resource.file))
        bf.use{
            line = it.readLine()
        }

        // Entityに詰める
        val entity = PrefuctureEntity(no, line)

        // Daoを呼び出す
        prefuctureDao.upsertPrefucture(entity)

    }

}
package co.jp.itamura.StudyProject.service.impl

import co.jp.itamura.StudyProject.controller.APIController
import co.jp.itamura.StudyProject.dto.Line
import co.jp.itamura.StudyProject.dto.Lines
import co.jp.itamura.StrudyProject.service.FileReadService
import co.jp.itamura.StudyProject.constans.P_FILEPATH
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.FileReader


@Service
class FileReadServiceImpl : FileReadService {

    @Autowired
    lateinit private var resourceLoader: ResourceLoader

    companion object {
        private val logger = LogManager.getLogger(FileReadServiceImpl::class.java)
    }

    /**
     * 指定フォルダに配置されたJSONファイルの中身を返却.
     *
     */
    override fun readFile(no : Int): String {
        //val lineList : MutableList<Line> = mutableListOf()
        //lineList.add(Line(1234,"板村"))
        //lineList.add(Line(2345,"金村"))
        //return Lines(lineList)

        var line : String = ""
        // ファイルの取得
        val resource: Resource = //resourceLoader.getResource("classpath:file/${no}.json")
                resourceLoader.getResource("file:${P_FILEPATH}${no}.json")
        val bf : BufferedReader = BufferedReader(FileReader(resource.file))
        bf.use{
            line = it.readLine()
        }

        return line

    }

}
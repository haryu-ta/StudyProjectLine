package co.jp.itamura.StrudyProject.service

import co.jp.itamura.StudyProject.dto.Lines

interface FileReadService {

    fun readFile(no : Int) : String

}


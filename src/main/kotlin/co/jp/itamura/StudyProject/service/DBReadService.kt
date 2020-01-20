package co.jp.itamura.StrudyProject.service

import co.jp.itamura.StudyProject.dto.Lines

interface DBReadService {

    fun readInfo(kbn : String,no : String) : String?

}


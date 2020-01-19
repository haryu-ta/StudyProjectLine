package co.jp.itamura.StudyProject.dao

import co.jp.itamura.StudyProject.entity.PrefuctureEntity
import org.apache.ibatis.annotations.Mapper

@Mapper
interface PrefuctureDao : AllDao {

    fun upsertPrefucture(entity : PrefuctureEntity)

}
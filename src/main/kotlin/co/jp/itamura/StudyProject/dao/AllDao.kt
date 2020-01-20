package co.jp.itamura.StudyProject.dao

import co.jp.itamura.StudyProject.entity.AnyEntity
import co.jp.itamura.StudyProject.entity.SarchConditionEntity

interface AllDao {

    fun upsert(entity : AnyEntity) : Unit

    fun select(entity : SarchConditionEntity) : AnyEntity
    
}
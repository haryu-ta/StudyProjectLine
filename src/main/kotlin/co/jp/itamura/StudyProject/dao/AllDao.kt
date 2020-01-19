package co.jp.itamura.StudyProject.dao

import co.jp.itamura.StudyProject.entity.AnyEntity

interface AllDao {

    fun upsert(entity : AnyEntity) : Unit
    
}
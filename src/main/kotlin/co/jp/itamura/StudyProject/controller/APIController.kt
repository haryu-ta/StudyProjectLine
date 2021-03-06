package co.jp.itamura.StudyProject.controller

import co.jp.itamura.StrudyProject.service.DBReadService
import co.jp.itamura.StudyProject.dto.Lines
import co.jp.itamura.StudyProject.service.DBRegistService
import co.jp.itamura.StrudyProject.service.FileReadService
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
class APIController() {

    //@Autowired
    //lateinit private var service : FileReadService

    @Autowired
    lateinit private var readService : DBReadService

    @Autowired
    lateinit private var regService : DBRegistService

    companion object {
        private val logger = LogManager.getLogger(APIController::class.java)
    }

    /**
     * 各情報をDBから取得
     *
     * @param kbn  p:都道府県 l:路線 s:駅 g:グループ j:隣駅
     * @param infono 情報ID
     * URL : /api/x/xx
     */
    @GetMapping(value= ["/api/{kbn}/{infono}"])
    @ResponseStatus(HttpStatus.OK)
    fun getInfo(@PathVariable("kbn") kbn : String,@PathVariable("infono") infono : String) : String? {

        return readService.readInfo(kbn,infono)

    }

    /**
     * 各情報をDBに格納する
     *
     * @param kbn  p:都道府県 l:路線 s:駅 g:グループ j:隣駅
     * URL : /api/x
     */
    @PutMapping(value= ["/api/{kbn}"])
    @ResponseStatus(HttpStatus.CREATED)
    fun registLine(@PathVariable("kbn") kbn : String) : Unit {

        return regService.regist(kbn)

    }

    /**
     * 都道府県の路線情報を取得する
     * URL: /api/p?api=x
     */
    /*
    @GetMapping(value = ["/api/p"])
    fun getData(@RequestParam("api",required = false) no : String?) : String {

        logger.info("api=${no}");
        var nos : Int
        try{
            nos = no?.toInt() ?: 0
        }catch(e : NumberFormatException){
            nos = 0
        }
        if( nos >= 1 && nos <= 47) {
            return service.readFile(nos)
        }else{
            return "" //Lines(mutableListOf())
        }
    }
    */

    /**
     * 都道府県の路線情報を取得する.
     * URL: /api2/p/x
     */
    /*
    @GetMapping(value= ["/api2/p/{prefucture_code}"])
    fun getData2(@PathVariable("prefucture_code") prefucture_code : String) : String {
        logger.info("api=${prefucture_code}");
        var nos : Int
        try{
            nos = prefucture_code?.toInt() ?: 0
        }catch(e : NumberFormatException){
            nos = 0
        }
        if( nos >= 1 && nos <= 47) {
            return service.readFile(nos)
        }else{
            return ""
        }
    }
    */

//    /**
//     * 都道府県の路線情報をDBに格納する
//     */
//    @PutMapping(value= ["/api/p/{prefucture_code}"])
//    @ResponseStatus(HttpStatus.CREATED)
//    fun registPrefucture(@PathVariable("prefucture_code") prefucture_code : String) : Unit {
//        var nos : Int
//        try{
//            nos = prefucture_code?.toInt() ?: 0
//        }catch(e : NumberFormatException){
//            nos = 0
//        }
//        if( nos >= 1 && nos <= 47) {
//            return regService.registPrefucture(nos)
//        }
//
//        return
//    }

}
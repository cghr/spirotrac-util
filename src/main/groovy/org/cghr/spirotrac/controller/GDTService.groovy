package org.cghr.spirotrac.controller

import com.google.gson.Gson
import org.cghr.spirotrac.util.GDTCreator
import org.cghr.spirotrac.util.SpirotracUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * Created by ravitej on 27/10/14.
 */
@RestController
@RequestMapping("/launchSpirotrac")
class GDTService {

    @Autowired
    GDTCreator gdtCreator
    @Autowired
    SpirotracUtil spirotracUtil

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
    String createGDT(@RequestBody String data) {

        Map participant = new Gson().fromJson(data, HashMap)
        gdtCreator.createGDTFile(participant)
        spirotracUtil.launch()

    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    String getGDTData() {

        gdtCreator.getOutputGDTData()

    }


}

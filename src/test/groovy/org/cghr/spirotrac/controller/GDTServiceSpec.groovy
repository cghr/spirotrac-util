package org.cghr.spirotrac.controller

import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.support.GenericGroovyXmlContextLoader
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * Created by ravitej on 27/10/14.
 */
@ContextConfiguration(value = "classpath:appContext.groovy", loader = GenericGroovyXmlContextLoader)
class GDTServiceSpec extends Specification {

    @Autowired
    GDTService gdtService
    MockMvc mockMvc
    String dirPath = File.createTempDir().absolutePath + "/"


    def setupSpec() {

    }

    def setup() {

        gdtService.gdtCreator.gdtDirectoryPath = dirPath
        mockMvc = MockMvcBuilders.standaloneSetup(gdtService).build()

    }

    def "should generate a GDT file from a given request data"() {
        given:
        Map data = [pid: '5000045', dob: '01-01-1989', gender: 'Male', height: 176, weight: 85]
        String json = new Gson().toJson(data)

        when:
        mockMvc.perform(post("/launchSpirotrac").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())



        then:
        new File(dirPath).listFiles().size() == 1
        new File(dirPath + "/" + "Lufuedv1.046").text == new File("testResources/expected/gdtGenerated.expected").text

    }


}
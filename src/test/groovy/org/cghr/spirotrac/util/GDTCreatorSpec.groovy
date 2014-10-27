package org.cghr.spirotrac.util

import com.github.jknack.handlebars.Handlebars
import spock.lang.Specification


/**
 * Created by ravitej on 27/10/14.
 */
class GDTCreatorSpec extends Specification {

    GDTCreator gdtCreator
    String filename = "Lufuedv1.046"

    String gdtDirectoryPath = File.createTempDir().absolutePath + "/"
    Handlebars handlebars = new Handlebars()


    def setup() {

        gdtCreator = new GDTCreator(gdtDirectoryPath, "templates/gdtTemplate", handlebars,15,filename)
    }

    def "should create a gdt file with given data"() {
        given:

        Map data = [pid: '5000045', dob: '01011989', gender: '01', height: '176', weight: '085']

        when:
        gdtCreator.createGDTFile(data)

        then:
        new File(gdtDirectoryPath).listFiles().size() == 1
        new File(gdtDirectoryPath+"/"+filename).text== new File("testResources/expected/gdtGenerated.expected").text


    }

}
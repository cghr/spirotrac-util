package org.cghr.spirotrac.util

import com.github.jknack.handlebars.Handlebars
import spock.lang.Specification

/**
 * Created by ravitej on 27/10/14.
 */
class GDTCreatorSpec extends Specification {

    GDTCreator gdtCreator
    String filename = "Lufuedv1.046"
    String outputFilename = "EDV1LUFU.046"

    Map participant = [pid: '5000045', dob: '01-01-1989', gender: 'Male', height: 176, weight: 85]

    String gdtDirectoryPath = File.createTempDir().absolutePath + "/"
    Handlebars handlebars = new Handlebars()


    def setup() {

        gdtCreator = new GDTCreator(gdtDirectoryPath, "templates/gdtTemplate", handlebars, 15, filename, outputFilename)
    }

    def "should create a gdt file with given data"() {

        when:
        gdtCreator.createGDTFile(participant)

        then:
        new File(gdtDirectoryPath).listFiles().size() == 1
        new File(gdtDirectoryPath + "/" + filename).text == new File("testResources/expected/gdtGenerated.expected").text


    }

    def "should get the data of output file create by spirotrac "() {

        given:
        String output = new File('testResources/output/' + outputFilename).text
        new File(gdtDirectoryPath + outputFilename).setText(output)



        expect:
        gdtCreator.getOutputGDTData() == output


    }

    def "should transform the participant data to the required GDT format"() {

        expect:
        gdtCreator.transformData(participant) == [pid: '5000045', dob: '01011989', gender: '01', height: '176', weight: '085']


    }

}
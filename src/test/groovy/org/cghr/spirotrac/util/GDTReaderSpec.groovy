package org.cghr.spirotrac.util

import spock.lang.Specification


/**
 * Created by ravitej on 6/11/14.
 */
class GDTReaderSpec extends Specification {

    String path = "testResources/output/EDV1LUFU.046"
    GDTReader gdtReader

    def setup() {

        Map labels = [actual: "0138420", predicted: "0138460", type: "0188470"]
        List testParams = ["FVC", "FEV1", "FEV6", "PEF"]

        gdtReader = new GDTReader(path, labels, testParams)

    }

    def "should get the result from the generated file"() {
        given:
        String expectedResult = '{"FVC_type":"Best Test","FVC_actual":"3.61","FVC_predicted":"4.81","FEV1_type":"Best Test","FEV1_actual":"3.10","FEV1_predicted":"3.78","FEV6_type":"Best Test","FEV6_actual":"3.61","FEV6_predicted":"4.81","PEF_type":"Best Test","PEF_actual":"8.78","PEF_predicted":"9.02"}'

        expect:
        gdtReader.getGDTData() == expectedResult
    }

}
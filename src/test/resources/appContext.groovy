import com.github.jknack.handlebars.Handlebars
import org.cghr.spirotrac.util.GDTCreator
import org.cghr.spirotrac.util.GDTReader
import org.cghr.spirotrac.util.SpirotracUtil

beans {

    xmlns([context: 'http://www.springframework.org/schema/context'])
    xmlns([mvc: 'http://www.springframework.org/schema/mvc'])


    context.'component-scan'('base-package': 'org.cghr.spirotrac.controller')


    mvc.'annotation-driven'()

    handlebars(Handlebars)
    spirotracUtil(SpirotracUtil, "")
    gdtCreator(GDTCreator, "", "templates/gdtTemplate", handlebars, 15, "Lufuedv1.046","EDV1LUFU.046")

    Map labels = [actual: "0138420", predicted: "0138460", type: "0188470"]
    List testParams = ["FVC", "FEV1", "FEV6", "PEF"]
    gdtReader(GDTReader,"C:/ProgramData/Vitalograph/Spirotrac V/EDV1LUFU.046",labels,testParams)



}
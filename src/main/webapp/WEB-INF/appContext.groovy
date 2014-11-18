import com.github.jknack.handlebars.Handlebars
import org.cghr.spirotrac.util.GDTCreator
import org.cghr.spirotrac.util.SpirotracUtil

beans {

    xmlns([context: 'http://www.springframework.org/schema/context'])
    xmlns([mvc: 'http://www.springframework.org/schema/mvc'])


    context.'component-scan'('base-package': 'org.cghr.spirotrac.controller')


    mvc.'annotation-driven'()

    handlebars(Handlebars)
    spirotracUtil(SpirotracUtil, "C:\\Program Files (x86)\\Vitalograph\\Spirotrac V\\Spirotrc.exe /edv")
    gdtCreator(GDTCreator, "C:/ProgramData/Vitalograph/Spirotrac V/", "templates/gdtTemplate", handlebars, 15, "Lufuedv1.046", "EDV1LUFU.046")


}

package controller;

import modelado.Conectar;
import modelado.Libros;
import modelado.LibrosValidar;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("add.htm")
public class AddController {
    LibrosValidar librosValidar;
    private JdbcTemplate jdbcTemplate;
    
    public AddController() 
    {
        this.librosValidar=new LibrosValidar();
        Conectar con=new Conectar();
        this.jdbcTemplate=new JdbcTemplate(con.conectar() );
    }
    @RequestMapping(method=RequestMethod.GET) 
    public ModelAndView form()
    {
        ModelAndView mav=new ModelAndView();
        mav.setViewName("add");
        mav.addObject("libros",new Libros());
        return mav;
    }
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView form
        (
                @ModelAttribute("libros") Libros u,
                BindingResult result,
                SessionStatus status
        )
    {
        this.librosValidar.validate(u, result);
        if(result.hasErrors())
        {
            ModelAndView mav=new ModelAndView();
            mav.setViewName("add");
            mav.addObject("libros",new Libros());
            return mav;
        }else
        {
        this.jdbcTemplate.update
        (
        "insert into libros (nombre,editorial,no_pag ) values (?,?,?)",
         u.getNombre(),u.getEditorial(),u.getNo_pag()
        );
         return new ModelAndView("redirect:/home.htm");
        }
       
    }
}


package controller;

import modelado.Conectar;
import modelado.Libros;
import modelado.LibrosValidar;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("edit.htm")
public class EditController 
{
    LibrosValidar librosValidar;
    private JdbcTemplate jdbcTemplate;
     
    
    public EditController() 
    {
        this.librosValidar=new LibrosValidar();
        Conectar con=new Conectar();
        this.jdbcTemplate=new JdbcTemplate(con.conectar() );
    }
    @RequestMapping(method=RequestMethod.GET) 
    public ModelAndView form(HttpServletRequest request)
    {
        ModelAndView mav=new ModelAndView();
        int idLibro=Integer.parseInt(request.getParameter("idLibro"));
        Libros datos=this.selectLibro(idLibro);
        mav.setViewName("edit");
        mav.addObject("libros",new Libros(idLibro,datos.getNo_pag(),datos.getNombre(),datos.getEditorial()));
        return mav;
    }
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView form
        (
                @ModelAttribute("libros") Libros u,
                BindingResult result,
                SessionStatus status,
                HttpServletRequest request
        )
    {
        this.librosValidar.validate(u, result);
        if(result.hasErrors())
        {
             ModelAndView mav=new ModelAndView();
            int idLibro=Integer.parseInt(request.getParameter("idLibro"));
            Libros datos=this.selectLibro(idLibro);
            mav.setViewName("edit");
            mav.addObject("libros",new Libros(idLibro,datos.getNo_pag(),datos.getNombre(),datos.getEditorial()));
            return mav;
        }else
        {
            int idLibro=Integer.parseInt(request.getParameter("idLibro"));
        this.jdbcTemplate.update(
                    "update libros "
                + "set nombre=?,"
                + " editorial=?,"
                + "no_pag=? "
                + "where "
                + "idLibro=? ",
         u.getNombre(),u.getEditorial(),u.getNo_pag(),idLibro);
         return new ModelAndView("redirect:/home.htm");
        }
       
    }
    public Libros selectLibro(int idLibro) 
    {
        final Libros libro = new Libros();
        String quer = "SELECT * FROM libros WHERE idLibro='" + idLibro+"'";
        return (Libros) jdbcTemplate.query
        (
                quer, new ResultSetExtractor<Libros>() 
            {
                @Override
                public Libros extractData(ResultSet rs) throws SQLException, DataAccessException {
                    if (rs.next()) {
                        libro.setNombre(rs.getString("nombre"));
                        libro.setEditorial(rs.getString("editorial"));
                        libro.setNo_pag(rs.getInt("no_pag"));
                    }
                    return libro;
                }


            }
        );
    }
}



package controller;

import modelado.Conectar;
import javax.servlet.http.HttpServletRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


public class DeleteController {
    private JdbcTemplate jdbcTemplate;
    public DeleteController()
    {
        Conectar con=new Conectar();
        this.jdbcTemplate=new JdbcTemplate(con.conectar() );
    }
    
    @RequestMapping("delete.htm")
    public ModelAndView home(HttpServletRequest request) 
    {
        int idLibro=Integer.parseInt(request.getParameter("idLibro"));
        this.jdbcTemplate.update(
                    "delete from libros "
                + "where "
                + "idLibro=? ",
        idLibro);
        return new ModelAndView("redirect:/home.htm");
    }
}

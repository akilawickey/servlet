import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/onlineUpdater"})
public class onlineUpdater extends HttpServlet {
    servlet update = new servlet();
    
 public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
        response.setContentType("text/event-stream");
        PrintWriter out = response.getWriter();
	//String name = request.getParameter("img");
        int r = update.hitCount;
        out.write("data:"+Integer.toString(r)+"\n\n");
        out.flush();
        
 }
}

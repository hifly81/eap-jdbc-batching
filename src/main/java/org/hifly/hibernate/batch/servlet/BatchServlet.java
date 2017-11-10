package org.hifly.hibernate.batch.servlet;

import org.hifly.hibernate.batch.model.Member;
import org.hifly.hibernate.batch.service.MemberRegistration;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(
        name = "BatchServlet",
        urlPatterns = "/batchServlet"
)
public class BatchServlet extends HttpServlet {

    @Inject
    MemberRegistration memberRegistration;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Database Batch</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Database Batch</h1>");

            memberRegistration.bulkInsert(generateRegistrations());

            out.println("<br><br>Check server.log for output");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            Logger.getLogger(BatchServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private List<Member> generateRegistrations() {
        List<Member> list = new ArrayList<>();
        for(int i = 0; i <= 50; i++) {
            Member memberRegistration = new Member();
            memberRegistration.setId(new Random().nextLong());
            memberRegistration.setAddress("Via Roma");
            memberRegistration.setEmail("john.smith@mailinator.com");
            memberRegistration.setName("gio");
            memberRegistration.setPhoneNumber("2125551212");
            list.add(memberRegistration);
        }
        return list;
    }
}

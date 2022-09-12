package MyPack;

import java.io.*;
import java.sql.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class ControllerServlet extends HttpServlet {

    // This method only calls processRequest()

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    // This method only calls processRequest()
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        // retrieving value of action parameter
        String userAction = request.getParameter("action");

        // if request comes to move to addperson.jsp from hyperlink
        if (userAction.equals("addproduct")) {
            response.sendRedirect("addproduct.jsp");
        }
        // if request comes to move to searchperson.jsp from hyperlink
        else if (userAction.equals("searchproduct")) {
            response.sendRedirect("searchproduct.jsp");
        }

        else if (userAction.equals("deleteproduct")) {
            response.sendRedirect("deleteproduct.jsp");
        }


        if (userAction.equals("signup")) {
            signup(request, response);
        }

        else if (userAction.equals("login")) {
            login(request, response);
        }
        // if “save�? button clicked on addperson.jsp to add new record
        else if (userAction.equals("save"))
        { addProduct(request,response); }

        else if (userAction.equals("delete"))
        { deleteProduct(request,response); }



        else if (userAction.equals("viewproducts"))
        { viewAllProducts(request, response); }

        else if (userAction.equals("viewusers"))
        { viewAllUsers(request, response); }


        else if (userAction.equals("search"))
        { viewSearchProducts(request, response); }

    } // end processRequest()

    private void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try

        {
            PersonDAO pDAO = new PersonDAO();

            PersonInfo person = new PersonInfo();

            String pName = request.getParameter("name");
            person.setName(pName);

            String add = request.getParameter("address");
            person.setAddress(add);

            String pNo = request.getParameter("phoneNum");
            person.setPhoneNum(pNo);

            String pass = request.getParameter("password");
            person.setPassword(pass);

            String utype = request.getParameter("usertype");
            person.setUserType(utype);

            pDAO.addPerson(person);
            HttpSession session= request.getSession(true);
            session.setAttribute("person",person);
            // session.setAttribute("fromSignUp",true);
            // session.setAttribute("fromLogin",false);
            session.setAttribute("header","succesful signup, " + pName);
            response.sendRedirect("home.jsp");
        } catch (SQLException sqlex) {

            // setting SQLException instance
            request.setAttribute("javax.servlet.jsp.JspException", sqlex);
            RequestDispatcher rd = request.getRequestDispatcher("addbookerror.jsp");
            rd.forward(request, response);
        }

        catch (ClassNotFoundException cnfe) {

            // setting ClassNotFoundException instance
            request.setAttribute("javax.servlet.jsp.JspException", cnfe);
            RequestDispatcher rd = request.getRequestDispatcher("addbokerror.jsp");
            rd.forward(request, response);
        } catch (Exception cnfe) {

            super.log(cnfe.getMessage());
            System.out.println(cnfe.toString());

            // setting ClassNotFoundException instance
            // request.setAttribute("javax.servlet.jsp.JspException" , cnfe);
            // request.setAttribute("exception" , cnfe.getMessage());
            // request.setAttribute("hi" , "hello there");
            // RequestDispatcher rd = request.getRequestDispatcher("addbokerror.jsp");
            // rd.forward(request,response);
        }

    }// end addperson()


    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try
        {
            PersonDAO pDAO = new PersonDAO();

            String pName = request.getParameter("name");
            String pass = request.getParameter("password");

            PersonInfo person = pDAO.login(pName,pass);

            if (person!=null){
                HttpSession session= request.getSession(true);
                session.setAttribute("person",person);
                // System.out.println("setting fromLogin attribute"); 
                // session.setAttribute("fromLogin",true);
                // session.setAttribute("fromSignUp",false);
                session.setAttribute("header","succesful login, " + pName);
                // System.out.println("setting fromLogin attribute finished"); 
            }

            System.out.println("redirecting home.jsp"); 

            response.sendRedirect("home.jsp");
            System.out.println("redirecting finished"); 

        } catch (SQLException sqlex) {
            System.out.println(sqlex.toString());
            // setting SQLException instance
            request.setAttribute("javax.servlet.jsp.JspException", sqlex);
            RequestDispatcher rd = request.getRequestDispatcher("addbookerror.jsp");
            rd.forward(request, response);
        }

        catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe.toString());
            // setting ClassNotFoundException instance
            request.setAttribute("javax.servlet.jsp.JspException", cnfe);
            RequestDispatcher rd = request.getRequestDispatcher("addbokerror.jsp");
            rd.forward(request, response);
        } catch (Exception cnfe) {

            super.log(cnfe.getMessage());
            System.out.println(cnfe.toString());

            // setting ClassNotFoundException instance
            // request.setAttribute("javax.servlet.jsp.JspException" , cnfe);
            // request.setAttribute("exception" , cnfe.getMessage());
            // request.setAttribute("hi" , "hello there");
            // RequestDispatcher rd = request.getRequestDispatcher("addbokerror.jsp");
            // rd.forward(request,response);
        }

    }// end addperson()


    private void viewAllProducts(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {
    try {

    ProductDao pDAO = new ProductDao();

    // String pName = request.getParameter("name");

    ArrayList productList = pDAO.getProductsList();
    request.setAttribute("list", productList);

    RequestDispatcher rd = request.getRequestDispatcher("showproducts.jsp");
    rd.forward(request, response);
    }catch (SQLException sqlex){

    // setting SQLException instance
    request.setAttribute("javax.servlet.jsp.JspException" , sqlex);
    RequestDispatcher rd = request.getRequestDispatcher("addbookerror.jsp");
    rd.forward(request,response);

    }catch (ClassNotFoundException cnfe){

    // setting ClassNotFoundException instance
    request.setAttribute("javax.servlet.jsp.JspException" , cnfe);
    RequestDispatcher rd = request.getRequestDispatcher("addbookerror.jsp");
    rd.forward(request, response);

    }
    }// end searchPerson()

    private void addProduct(HttpServletRequest request, HttpServletResponse
    response)
    throws ServletException, IOException
    { try

    {
    ProductDao pDAO = new ProductDao();

    Product product = new Product();

    String pName = request.getParameter("name");
    product.setName(pName);

    String color = request.getParameter("color");
    product.setColor(color);

    String comp = request.getParameter("company");
    product.setCompany(comp);

    String price = request.getParameter("price");
    product.setPrice(price);

    pDAO.addProduct(product);

    HttpSession session= request.getSession(false);
    session.setAttribute("header","product added succesfully");

    response.sendRedirect("home.jsp");
    }catch (SQLException sqlex){

    // setting SQLException instance
    request.setAttribute("javax.servlet.jsp.JspException" , sqlex);
    RequestDispatcher rd = request.getRequestDispatcher("addbookerror.jsp");
    rd.forward(request, response); }

    catch (ClassNotFoundException cnfe){

    // setting ClassNotFoundException instance
    request.setAttribute("javax.servlet.jsp.JspException" , cnfe);
    RequestDispatcher rd = request.getRequestDispatcher("addbokerror.jsp");
    rd.forward(request,response);
    }

    }// end addperson()


    private void deleteProduct(HttpServletRequest request, HttpServletResponse
    response)
    throws ServletException, IOException
    { try

    {
    ProductDao pDAO = new ProductDao();

    String pName = request.getParameter("name");
    pDAO.deleteProduct(pName);

    HttpSession session= request.getSession(false);
    session.setAttribute("header","product deleted succesfully");

    response.sendRedirect("home.jsp");
    }catch (SQLException sqlex){

    // setting SQLException instance
    request.setAttribute("javax.servlet.jsp.JspException" , sqlex);
    RequestDispatcher rd = request.getRequestDispatcher("addbookerror.jsp");
    rd.forward(request, response); }

    catch (ClassNotFoundException cnfe){

    // setting ClassNotFoundException instance
    request.setAttribute("javax.servlet.jsp.JspException" , cnfe);
    RequestDispatcher rd = request.getRequestDispatcher("addbokerror.jsp");
    rd.forward(request,response);
    }

    }// end addperson()


    private void viewSearchProducts(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {
    try {
    System.out.println("entered viewSearchProducts");    
    ProductDao pDAO = new ProductDao();

    // String pName = request.getParameter("name");

    ArrayList productList = pDAO.getSearchProductsList(request.getParameter("name"));
    request.setAttribute("list", productList);

    System.out.println("forwarding request");    

    RequestDispatcher rd = request.getRequestDispatcher("showproducts.jsp");
    rd.forward(request, response);
    }catch (SQLException sqlex){

    System.out.println(sqlex);    


    // setting SQLException instance
    request.setAttribute("javax.servlet.jsp.JspException" , sqlex);
    RequestDispatcher rd = request.getRequestDispatcher("addbookerror.jsp");
    rd.forward(request,response);

    }catch (ClassNotFoundException cnfe){
        System.out.println(cnfe);    
    // setting ClassNotFoundException instance
    request.setAttribute("javax.servlet.jsp.JspException" , cnfe);
    RequestDispatcher rd = request.getRequestDispatcher("addbookerror.jsp");
    rd.forward(request, response);

    }    
    }// end searchPerson()


    private void viewAllUsers(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {
    try {

    PersonDAO pDAO = new PersonDAO();

    // String pName = request.getParameter("name");

    ArrayList usersList = pDAO.getUsersList();
    request.setAttribute("userslist", usersList);

    RequestDispatcher rd = request.getRequestDispatcher("showusers.jsp");
    rd.forward(request, response);
    }catch (SQLException sqlex){

    // setting SQLException instance
    request.setAttribute("javax.servlet.jsp.JspException" , sqlex);
    RequestDispatcher rd = request.getRequestDispatcher("addbookerror.jsp");
    rd.forward(request,response);

    }catch (ClassNotFoundException cnfe){

    // setting ClassNotFoundException instance
    request.setAttribute("javax.servlet.jsp.JspException" , cnfe);
    RequestDispatcher rd = request.getRequestDispatcher("addbookerror.jsp");
    rd.forward(request, response);

    }
    }// end searchPerson()


} // end ControllerServlet
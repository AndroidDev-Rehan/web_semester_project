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

        else if (userAction.equals("blockuser")) {
            response.sendRedirect("blockuser.jsp");
        }

        else if (userAction.equals("editproduct")) {
            response.sendRedirect("editproduct.jsp");
        }



        else if (userAction.equals("signup")) {
            signup(request, response);
        }

        else if (userAction.equals("login")) {
            login(request, response);
        }

        else if (userAction.equals("block")) {
            blockUser(request, response);
        }

        else if (userAction.equals("unblock")) {
            unblockUser(request, response);
        }

        else if (userAction.equals("edit")) {
            editProduct(request, response);
        }

        else if (userAction.equals("Edit and Save")) {
            editAndSaveProduct(request, response);
        }

        else if (userAction.equals("logout")) {
            logout(request, response);
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

            if(pDAO.userExists(pName)){
                response.sendRedirect("login.jsp?userexists=true");
                return;
            }

            System.out.println("user doesn't exist already");


            String add = request.getParameter("address");
            person.setAddress(add);

            String pNo = request.getParameter("phoneNum");
            person.setPhoneNum(pNo);

            String pass = request.getParameter("password");
            person.setPassword(pass);

            // String utype = request.getParameter("usertype");
            person.setUserType("regular");

            System.out.println("usertype of person in controller:");
            System.out.println(person.getUserType());

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
                if (person.getStatus().equals("blocked")){
                    response.sendRedirect("login.jsp?blocked=true");
                    return;
                }

                HttpSession session= request.getSession(true);
                session.setAttribute("person",person);
                session.setAttribute("header","succesful login, " + pName);
                System.out.println("redirecting home.jsp"); 
                response.sendRedirect("home.jsp");
                System.out.println("redirecting finished"); 
                return;

                // System.out.println("setting fromLogin attribute finished"); 
            }


            // response.sendRedirect("home.jsp");

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

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try
        {

            HttpSession session= request.getSession(false);
            if (session==null){
                response.sendRedirect("login.jsp");                
                return;
            }

            session.invalidate();


            System.out.println("redirecting home.jsp"); 

            response.sendRedirect("login.jsp");
            System.out.println("redirecting finished"); 

        } 
 catch (Exception cnfe) {

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

    private void blockUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try

        {
            PersonDAO pDAO = new PersonDAO();

            String pName = request.getParameter("name");

            pDAO.blockUser(pName);
            HttpSession session= request.getSession(false);
            session.setAttribute("header","succesfully blocked " + pName);
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

    private void unblockUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try

        {
            PersonDAO pDAO = new PersonDAO();

            String pName = request.getParameter("name");

            pDAO.unblockUser(pName);
            HttpSession session= request.getSession(false);
            session.setAttribute("header","succesfully unblocked " + pName);
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

    if(pDAO.productExists(pName)){
        HttpSession session= request.getSession(false);
        session.setAttribute("header","product with same name exists already");
        response.sendRedirect("home.jsp");
        return;
    }


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

    private void editProduct(HttpServletRequest request, HttpServletResponse
    response)
    throws ServletException, IOException
    { try

    {
    ProductDao pDAO = new ProductDao();

    String pName = request.getParameter("name");
    HttpSession session= request.getSession(false);
    if(pDAO.productExists(pName)==false){        
        session.setAttribute("header","No Product with name " + pName + " exists");
        response.sendRedirect("home.jsp");
        return;
    }

    session.setAttribute("productname",pName);
    response.sendRedirect("editproductform.jsp");
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

    private void editAndSaveProduct(HttpServletRequest request, HttpServletResponse
    response)
    throws ServletException, IOException
    { try

    {
    ProductDao pDAO = new ProductDao();
    Product product = new Product();

    System.out.println("into controller edit and save");

    HttpSession session= request.getSession(false);
    String name = (String)session.getAttribute("productname");

    product.setName(name);

    String color = request.getParameter("color");
    product.setColor(color);

    String comp = request.getParameter("company");
    product.setCompany(comp);

    String price = request.getParameter("price");
    product.setPrice(price);

    System.out.println("calling update product from controller");

    pDAO.updateProduct(product);

    System.out.println("redirecting to home");

    session.setAttribute("header","product updated succesfully");
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
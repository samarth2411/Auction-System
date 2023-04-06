package controllers;

import com.google.inject.Inject;
import dao.EmployeeDao;
import dto.UserDto;
import models.AppUser;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.params.PathParam;
import ninja.session.Session;


public class EmpController {
    @Inject
    EmployeeDao userDao;




    public Result CreateUser(UserDto userDto, Session session){
        try{
            AppUser appUser = userDao.AddUser(userDto);
            System.out.println("New User "+userDto.getName()+" added");
            session.clear();
            session.put("email",userDto.getEmail());
            return Results.json().render(appUser);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return Results.badRequest().json().render("Bad Request");
    }

    public Result countAllUsers() throws Exception {
        try{
            System.out.println("Counted All the Users");
            Long count = userDao.countAllUsers();
            if(count==null){
                return Results.badRequest().json().render("Bad Request");
            }
            return Results.json().render(count);

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return Results.badRequest().json().render("Bad Request");

    }

    public Result validateUser(@PathParam("email") String email , @PathParam("password") String password , Session session){
        try {
            AppUser result = userDao.validate(email,password);
            session.clear();
            if(email==null && password==null){
                return Results.badRequest().json().render("Bad Request");
            }
            session.put("email",email);

            return Results.json().render(result);

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return Results.badRequest().json().render("Bad Request");

    }

    public Result getUser(Context context) throws Exception {
        try{
            if(context.getSession()!=null){
                String email = context.getSession().get("email");
                AppUser user = userDao.getUser(email);
                return Results.json().render(user);
            }
            else{
                return Results.unauthorized().json().render("Unauthorized");
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return Results.badRequest().json().render("Bad Request");

    }

    public Result getUserEmail(@PathParam("email") String email){
        try{
            AppUser user = userDao.getUserEmail(email);
            return Results.json().render(user);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return Results.badRequest().json().render("Bad Request");


    }

    public Result logout(Session session,Context context){
        try{
            System.out.println("User Logged Out");
            session.clear();
            boolean logout = true;
            return Results.json().render(logout);

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return Results.badRequest().json().render("Bad Request");

    }





//    public Result deleteProduct(@Param("id") Long id){
//        userDao.deleteProduct(id);
//        return Results.json().render("Product with id "+ id +" Deleted");
//    }

//    public Result getEmployee(@Param("name") String name){
//        // userDao.getEmployeeByNameWithNamedQuery(name);
//        System.out.println("Employee with "+name+ " Retreived");
//        return Results.json().render(userDao.getEmployeeByNameWithNamedQuery(name));
//    }
//
//    public Result getAllEmployee(){
//        // userDao.getEmployeeByNameWithNamedQuery(name);
//        System.out.println("All Employees Retrieved");
//        return Results.json().render(userDao.getEmployee());
//    }

//    public Result deleteEmployee(@Param("name") String name){
//
//    }


    public Result indexOptions(){
        // System.out.println("hello");
        return Results.json().addHeader("Content-Type", "application/json").
                addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").
                addHeader("Access-Control-Allow-Credentials", "true").
                addHeader("Access-Control-Allow-Origin", "http://localhost:4200").
                addHeader("Access-Control-Allow-Headers", "content-type").
                render("cors accepted...");

    }






}

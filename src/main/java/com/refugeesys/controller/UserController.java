package com.refugeesys.controller;

import com.refugeesys.model.Refugee;
import com.refugeesys.model.User;
import com.refugeesys.service.RefugeeService;
import com.refugeesys.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Comparator;
import java.util.List;
@Controller
public class UserController {
    @Autowired
    private RefugeeService refugeeService;
    @Autowired
    private UserService userService;
    // Display login page
    @GetMapping("/")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new User());
        return mav;
    }

    // Handle login request
    @PostMapping("/login")
    public String handleLogin(@ModelAttribute("user") User user, HttpServletRequest request) {
        if (user.getUsername() == null || user.getUsername().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty()) {
            // Username or password is empty
            request.getSession().setAttribute("error", "Username and password are required");
            return "redirect:/denaid";
        }

        User oauthUser = userService.login(user.getUsername(), user.getPassword());
        if (oauthUser == null) {
            // Authentication failed (username or password incorrect)
            request.getSession().setAttribute("error", "Invalid username or password");
            return "redirect:/denaid";
        } else {
            // Successful login, redirect to dashboard
            return "redirect:/dashboard";
        }
    }
    // Handle logout request
    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Invalidate the session
        request.getSession().invalidate();
        return "redirect:/"; // Redirect to the login page or another landing page
    }
    // Display access denied page
    @GetMapping("/denaid")
    public String denied(Model model) {
        // You can retrieve error message from session and add it to model
        Object error = model.asMap().get("error");
        if (error != null) {
            model.addAttribute("error", error.toString());
        }
        return "denaid"; // Assuming "denaid.html" is your denied access view
    }
    // Display dashboard page
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Refugee> listRefugees = refugeeService.getAllRefugees(null);
        int refugeeCount = listRefugees.size();
        model.addAttribute("refugeeCount", refugeeCount);
        return "dashboard"; // Assuming "dashboard.html" is your dashboard view
    }
    // Display user registration form
    @GetMapping("/register")
    public String registerUser(Model model) {
        model.addAttribute("user", new User());
        return "RegisterUser"; // Assuming "RegisterUser.html" is your registration form view
    }
    // Display list of all users, optionally filtered by keyword
    @GetMapping("/all")
    public String getAllUsers(Model model, @RequestParam(name = "keyword", required = false) String keyword) {
        List<User> listUsers = userService.getAllUsers(keyword);
        listUsers.sort(Comparator.comparing(User::getId));
        model.addAttribute("users", listUsers);
        model.addAttribute("keyword", keyword);
        return "UserList"; // Assuming "UserList.html" is your user list view
    }
    // Retrieve user by ID and return as JSON
    @GetMapping("/{id}")
    @ResponseBody
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }
    // Handle user registration form submission
    @PostMapping("/save")
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/all";
    }
    // Handle user deletion
    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/all";
    }
    // Display form for editing an existing user
    @RequestMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "EditUser"; // Assuming "EditUser.html" is your edit user form view
    }
    // Handle edit user form submission
    @PostMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/all";
    }
}
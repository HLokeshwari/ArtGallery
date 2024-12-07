package klu.controller;

import java.io.File;


import java.io.IOException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import klu.model.ArtFair;
import klu.model.Artreg;
import klu.model.Consumer;
import klu.model.ConsumerManager;
import klu.model.Shipping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;






@RestController
@RequestMapping("/")//consumer
public class ConsumerController 
{
	@Autowired
	ConsumerManager CM;
	
//	@Autowired
//	private JavaMailSender mailSender;
	
	@PostMapping("/register")
    public String register(HttpServletRequest request,HttpServletResponse response)throws IOException {


        Consumer C = new Consumer(); // Create a new Consumer object

        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String contactno = request.getParameter("contactno");
        String address = request.getParameter("address");
        String dob = request.getParameter("dob");
        String artPreference = request.getParameter("artPreference");
        String newsletter = request.getParameter("newsletter");

        Random random = new Random();
        Long userid = (long) (random.nextInt(9000) + 1000);
        C.setUserid(userid);
        C.setEmail(email);
        C.setName(name);
        C.setPassword(password);
        C.setGender(gender);
        C.setContactno(contactno);
        C.setAddress(address);
        C.setDateOfBirth(dob);
        C.setArtPreference(artPreference);
        C.setNewsletter(newsletter);
        C.setUserRole("buyer");

        String result =  CM.insertUser(C); 
        if ("redirectLogin".equals(result)) {
            response.sendRedirect("login");  //here instead of this keep another file and then in that login link
            return null; 
        }
        else if ("alredyexist".equals(result)) 
        {
            response.sendRedirect("login?=exist");  
            return null;
        }
        else {
            return result;
        }    }
	
	
	
	
	@PostMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");

	    Consumer C = CM.loginUser(email, password);
	    if (C != null) {
	        HttpSession session = request.getSession();
	        session.setAttribute("cms", C);
	       // sendLoginSuccessEmail(C.getEmail(),C.getName());

	        if ("buyer".equalsIgnoreCase(C.getUserRole())) {
	            response.sendRedirect("buyerhome");
	        } else if ("seller".equalsIgnoreCase(C.getUserRole())) {
	            response.sendRedirect("sellerhome");
	        } else if ("admin".equalsIgnoreCase(C.getUserRole())) {
	            response.sendRedirect("adminhome");
	        }
	        else if ("curator".equalsIgnoreCase(C.getUserRole())) {
	            response.sendRedirect("curatorhome");
	        }
	        
	        return null; 
	    } else {
	    	
	    	 response.sendRedirect("login?error=invalid");
	    	 return null;
	    	 
	    }
	}
	

//	private void sendLoginSuccessEmail(String userEmail,String userName) {
//	    SimpleMailMessage message = new SimpleMailMessage();
//	    message.setTo(userEmail);
//	    message.setSubject("Login Successful");
//	    String messageBody = String.format(
//	            "Dear %s,\n\n" +
//	            "You have successfully logged in to your account. If this was not you, please contact our support team immediately.\n\n" +
//	            "If you have any questions or need assistance, feel free to reach out to us at:\n" +
//	            "Email: support@artspectrum.com\n" +
//	            "Phone: (123) 456-7890\n\n" +
//	            "Warm regards,\n" +
//	            "The Art Spectrum Team",
//	            userName
//	            
//	        );
//	    message.setText(messageBody);
//
//	    try {
//	        mailSender.send(message);
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
//	}


	
	
  @PostMapping("/sellartreg")
  public String sellartreg(HttpServletRequest request,HttpServletResponse response,@RequestParam("imageurl") MultipartFile imageFile)throws IOException 
  {
      Artreg A = new Artreg();
      HttpSession session = request.getSession();
      
      Consumer seller = (Consumer) session.getAttribute("cms");
      
	  Random random = new Random();
	  int randomDigits = 100000 + random.nextInt(900000);
	  String artid = "ART" + randomDigits;
	 
	  String sellername = seller.getName();
	  String sellerid = seller.getUserid().toString();

      String arttitle = request.getParameter("arttitle");
      String artdescription = request.getParameter("artdescription");
      String artmedium = request.getParameter("artmedium");
      String artdimensions = request.getParameter("artdimensions");
      String artcost = request.getParameter("artcost");
      String datelisted = request.getParameter("datelisted");
      String availstatus = request.getParameter("availstatus");
      String appPath = request.getServletContext().getRealPath(""); // Get the real path of the web application
      String folder = appPath + "arts/";
      String imageFileName = sellerid + "_image" + randomDigits + ".jpg";
      String filePath = folder + imageFileName;
      File file = new File(filePath);
      imageFile.transferTo(file);
      String imageUrl = "arts/" + imageFileName;
      A.setImageurl(imageUrl);
      
      A.setArtid(artid);
      A.setSellername(sellername);
      A.setSellerid(sellerid);
      A.setArttitle(arttitle);
      A.setArtdescription(artdescription);
      A.setArtmedium(artmedium);
      A.setArtdimensions(artdimensions);
      A.setArtcost(artcost);
      A.setDatelisted(datelisted);
      A.setAvailstatus(availstatus);
      
    
      
     String result = CM.insertArt(A);
     if("artsuccess".equals(result)) 
     {
    	 response.sendRedirect("myarts");
    	 return null;
     }
     else
     {
    	 //response.sendRedirect("artfailure");
    	 return result;
     }
      
      
   
  }
  
  
  
  
  @PostMapping("/registerArtFair")
  public String registerArtFair(HttpServletRequest request, HttpServletResponse response, @RequestParam("timage") MultipartFile imageFile) throws IOException {
      ArtFair artFair = new ArtFair();
      HttpSession session = request.getSession();

      // Check if curator details are available in the session
      Consumer curator = (Consumer) session.getAttribute("cms");
      if (curator == null) {
          return "redirect:/sessionexpiry.html";  // Redirect to session expiry or login page
      }

      // Generate a unique ID for the art fair
      Random random = new Random();
      int randomDigits = 100000 + random.nextInt(900000);
      String tourid = "CUR" + randomDigits;

      // Set art fair details from request parameters
      artFair.setCuratorId(curator.getUserid().toString());
      artFair.setName(request.getParameter("name"));
      artFair.setTdescription(request.getParameter("tdescription"));
      artFair.setStartDate(request.getParameter("startDate"));
      artFair.setEndDate(request.getParameter("endDate"));
      artFair.setLocation(request.getParameter("location"));
      artFair.setCapacity(Integer.parseInt(request.getParameter("capacity")));
      artFair.setEntryFee(Double.parseDouble(request.getParameter("entryFee")));
      artFair.setTheme(request.getParameter("theme"));

      // Handle image upload
      String appPath = request.getServletContext().getRealPath(""); // Real path of the web application
      String folder = appPath + "tours/";

      // Create directory if it doesn't exist
      File dir = new File(folder);
      if (!dir.exists()) {
          dir.mkdirs();
      }

      String imageFileName = tourid + "_image" + randomDigits + ".jpg";
      String filePath = folder + imageFileName;
      File file = new File(filePath);

      // Save the uploaded image to the specified path
      imageFile.transferTo(file);

      // Set the relative path of the image in the database
      String timageUrl = "tours/" + imageFileName;
      artFair.setTimage(timageUrl);

      // Save art fair to database (assuming a service layer method for this)
      String result = CM.insertTour(artFair);
      if ("artsuccess".equals(result)) {
          response.sendRedirect("mytours");
          return null;  // End response here if redirecting
      } else {
          return result;  // Return the result string in case of failure
      }
  }


  
  
  
  
  @PostMapping("/shipping")
  public String shipping(HttpServletRequest request,HttpServletResponse response)throws IOException {
      Shipping S = new Shipping(); // Create a new Consumer object
      HttpSession session = request.getSession();
      
      Consumer buyer = (Consumer) session.getAttribute("cms");

      String orderid = URLEncoder.encode("#order-" + (100000 + new Random().nextInt(900000)) + "-" + (100000 + new Random().nextInt(900000)));
      String artid = request.getParameter("artid");
      String arttitle = request.getParameter("arttitle");
      String artmedium = request.getParameter("artmedium");
      String artdimensions = request.getParameter("artdimensions");
      String artcost =  request.getParameter("artcost");
      String shippingaddress = request.getParameter("shippingaddress");
      String buyerid =  buyer.getUserid().toString();
      String buyername = buyer.getName();
      String artseller = request.getParameter("artseller");
      String orderstatus = "Ordered";
      String artimage = request.getParameter("artimage");
      
      
      
      S.setOrderid(orderid);
      S.setArtid(artid);
      S.setArttitle(arttitle);
      S.setArtmedium(artmedium);
      S.setArtdimensions(artdimensions);
      S.setArtcost(artcost);
      S.setShippingaddress(shippingaddress);
      S.setBuyerid(buyerid);
      S.setBuyername(buyername);
      S.setArtseller(artseller);
      S.setOrderstatus(orderstatus);
      S.setArtimage(artimage);
      
      String result = CM.insertship(S);
      if("ordersuccess".equals(result))
      {
       // sendOrderConfirmationEmail(buyerEmail,buyername, orderid.replaceFirst("%23", "#"), arttitle, artcost, shippingaddress, orderstatus,artimage);

    	  response.sendRedirect("ordersuccess?artseller=" + Base64.getEncoder().encodeToString(artseller.getBytes("UTF-8")) +
                  "&artid=" + Base64.getEncoder().encodeToString(artid.getBytes("UTF-8")) +
                  "&orderid=" + Base64.getEncoder().encodeToString(orderid.getBytes("UTF-8")) +
                  "&shippingaddress=" + Base64.getEncoder().encodeToString(shippingaddress.getBytes("UTF-8")) +
                  "&artcost=" + Base64.getEncoder().encodeToString(artcost.getBytes("UTF-8")) +
                  "&orderstatus=" + Base64.getEncoder().encodeToString("Ordered".getBytes("UTF-8")) +
                  "&arttitle=" + Base64.getEncoder().encodeToString(arttitle.getBytes("UTF-8")) +
                  "&artdimensions=" + Base64.getEncoder().encodeToString(artdimensions.getBytes("UTF-8")) +
                  "&artimage=" + Base64.getEncoder().encodeToString(artimage.getBytes("UTF-8")));
return null;

      }
      else
      {
    	  return result;
      }
      
  }
   

//  private void sendOrderConfirmationEmail(String buyerEmail, String buyername, String orderid, String arttitle, String artcost, String shippingaddress, String orderstatus, String artimage) {
//	    try {
//	        MimeMessage mimeMessage = mailSender.createMimeMessage();
//	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//
//	        helper.setTo(buyerEmail);
//	        helper.setSubject("Order Confirmation: " + orderid);
//
//	        String messageBody = String.format(
//	                "<html>" +
//	                        "<body>" +
//	                        "<p>Dear %s,</p>" +
//	                        "<p>Thank you for your purchase! Below are the details of your order:</p>" +
//	                        "<p>Order ID: %s<br>" +
//	                        "Art Title: %s<br>" +
//	                        "Art Cost: %s<br>" +
//	                        "Shipping Address: %s<br>" +
//	                        "Order Status: %s</p>" +
//	                        "<p>Here is the image of the artwork:</p>" +
//	                        "<img src='cid:artImage' alt='Art Image' style='width:300px; height:200px;' />"+
//	                        "<p>We will notify you once your order is shipped.</p>" +
//	                        "<p>Best regards,<br>Art Gallery Team</p>" +
//	                        "</body>" +
//	                        "</html>",
//	                buyername, orderid, arttitle, artcost, shippingaddress, orderstatus
//	        );
//
//	        helper.setText(messageBody, true);
//	        FileSystemResource file = new FileSystemResource(new File("src/main/webapp/" + artimage));
//	        helper.addInline("artImage", file); // Embed the image in the email body with 'cid:artImage'
//	        mailSender.send(mimeMessage);
//
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
//	}
//  
//  
  @PostMapping("cancelorder")
  public String cancelorder(HttpServletRequest request,HttpServletResponse response)throws IOException
  {
    String orderid = request.getParameter("orderid").replace("#", "%23");
    String orderstatus = request.getParameter("orderstatus");

   
    
    String result = CM.cancelOrder(orderid,orderstatus);
    if("cancelorder".equals(result)) 
    {
   	 response.sendRedirect("myorders");
   	 return null;
    }
    else
    {
   	 return result;
    }
     
  }
  
  @PostMapping("updatemyart")
  public String updatemyart(HttpServletRequest request,HttpServletResponse response)throws IOException 
  {
   Artreg A = new Artreg();
   String artId = request.getParameter("artId");
   String artTitle = request.getParameter("artTitle");
   String artPrice = request.getParameter("artPrice");
   String artDimensions = request.getParameter("artDimensions");
   String artMedium = request.getParameter("artMedium");
   String artDescription = request.getParameter("artDescription");
   String artStatus = request.getParameter("artStatus");
   
   
   A.setArtid(artId);
   A.setArttitle(artTitle);
   A.setArtcost(artPrice);
   A.setArtdimensions(artDimensions);
   A.setArtmedium(artMedium);
   A.setArtdescription(artDescription);
   A.setAvailstatus(artStatus);
   
   String result = CM.updateMyArt(A);
   if("updateartsuccess".equals(result))
   {
	   response.sendRedirect("myarts");
	   return null;
   }
   else
   {
	   return result;
   }
  }
  
  
  
  @PostMapping("updateallart")
  public String updateallart(HttpServletRequest request,HttpServletResponse response)throws IOException 
  {
   Artreg A = new Artreg();
   String artId = request.getParameter("artId");
   String artTitle = request.getParameter("title");
   String artPrice = request.getParameter("cost");
   String artDimensions = request.getParameter("dimensions");
   String artMedium = request.getParameter("medium");
   String artDescription = request.getParameter("description");
   String artStatus = request.getParameter("availStatus");
   String sellerid = request.getParameter("sellerId");
   String sellername = request.getParameter("sellerName");

   A.setArtid(artId);
   A.setArttitle(artTitle);
   A.setArtcost(artPrice);
   A.setArtdimensions(artDimensions);
   A.setArtmedium(artMedium);
   A.setArtdescription(artDescription);
   A.setAvailstatus(artStatus);
   A.setSellerid(sellerid);
   A.setSellername(sellername);
   
 
   
   String result = CM.updateAllArt(A);
   if("updateallart".equals(result))
   {
	   response.sendRedirect("viewarts");
	   return null;
   }
   else
   {
	   return result;
   }
  }
  
  
  
  
  
  
  @PostMapping("updatealltours")
  public String updatealltours(HttpServletRequest request, HttpServletResponse response) throws IOException {
      ArtFair artFair = new ArtFair();

   // Parse parameters from request
      String tourId = request.getParameter("tourid");
      String name = request.getParameter("name");
      String tdescription = request.getParameter("tdescription");
      String startDate = request.getParameter("startdate");
      String endDate = request.getParameter("enddate");
      String location = request.getParameter("location");
      String capacity = request.getParameter("capacity");
      String entryFee = request.getParameter("entryfee");
      String theme = request.getParameter("theme");

      // Set values in ArtFair object
      if (tourId != null && !tourId.isEmpty()) {
          artFair.setTourid(Long.parseLong(tourId));
      }
      artFair.setName(name);
      artFair.setTdescription(tdescription);
      artFair.setStartDate(startDate);
      artFair.setEndDate(endDate);
      artFair.setLocation(location);

      if (capacity != null && !capacity.isEmpty()) {
          artFair.setCapacity(Integer.parseInt(capacity));
      }

      if (entryFee != null && !entryFee.isEmpty()) {
          artFair.setEntryFee(Double.parseDouble(entryFee));
      }
      artFair.setTheme(theme);

      // Invoke consumer manager method
      String result = CM.updateAllTours(artFair);
      if ("updatealltours".equals(result)) {
          response.sendRedirect("mytours"); // Redirect on success
          return null;
      } else {
          return result; // Return error message
      }
  }

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  @PostMapping("updateorder")
  public String updateorder(HttpServletRequest request,HttpServletResponse response)throws IOException
  {
    String orderid = request.getParameter("orderid").replace("#", "%23");
    String orderstatus = request.getParameter("orderstatus");

   
    
    String result = CM.updateOrder(orderid,orderstatus);
    if("updatedorder".equals(result)) 
    {
   	 response.sendRedirect("vieworders");
   	 return null;
    }
    else
    {
   	 return result;
    }
     
  }
  
  
  @PostMapping("updateallorder")
  public String updateallorder(HttpServletRequest request,HttpServletResponse response)throws IOException 
  {
   Shipping S = new Shipping();
   
   String orderId = request.getParameter("orderId").replaceFirst("#", "%23");
   String artCost = request.getParameter("artCost");
   String artDimensions=request.getParameter("artDimensions");
   String artId = request.getParameter("artId");
   String artMedium = request.getParameter("artMedium");
   String artSeller=request.getParameter("artSeller");
   String artTitle = request.getParameter("artTitle");
   String buyerId = request.getParameter("buyerId");
   String buyerName = request.getParameter("buyerName");
   String orderStatus = request.getParameter("orderStatus");
   String shippingAddress = request.getParameter("shippingAddress");
   
   S.setOrderid(orderId);
   S.setArtcost(artCost);
   S.setArtdimensions(artDimensions);
   S.setArtid(artId);
   S.setArtmedium(artMedium);
   S.setArtseller(artSeller);
   S.setArttitle(artTitle);
   S.setBuyerid(buyerId);
   S.setBuyername(buyerName);
   S.setOrderstatus(orderStatus);
   S.setShippingaddress(shippingAddress);
   
   String result = CM.updateAllOrder(S);
   if("updateallorder".equals(result))
   {
	   response.sendRedirect("viewallorders");
	   return null;
   }
   else
   {
	   return result;
   }
  }
  
  
  @PostMapping("deletemyart")
  public String deletemyart(HttpServletRequest request,HttpServletResponse response)throws IOException
  {
	    String artId = request.getParameter("artId");

	    String result = CM.deleteArtById(artId);
	    
	    if("deletedart".equals(result))
	    {
	    	response.sendRedirect("myarts");
	    	return null;
	    }
	    else
	    {
	    	return result;
	    }

  }
  
  
  
  @PostMapping("/updatecustomers")
  public String updatecustomers(HttpServletRequest request,HttpServletResponse response)throws IOException {
	    
	  String useridParam = request.getParameter("userId");  
	  Long userid = Long.valueOf(useridParam);
	  String name = request.getParameter("name");
      String gender = request.getParameter("gender");
      String contactno = request.getParameter("contactno");
      String address = request.getParameter("address");
      String dob = request.getParameter("dob");
      String artPreference = request.getParameter("artPreference");
      String newsletter = request.getParameter("newsletter");



      String result =  CM.updateUser(userid,name,gender,contactno,address,dob,artPreference,newsletter); 
      if ("updateduser".equals(result)) {
          response.sendRedirect("viewcustomers?message=User updated successfully");
          return null; 
      } else {
          return result;
      }    }
  
  
  
  
  @PostMapping("deletecustomer")
  public String deletecustomer(HttpServletRequest request,HttpServletResponse response)throws IOException
  {
	    String userId = request.getParameter("userId");

	    String result = CM.deleteCustomerById(userId);
	    
	    if("deletedconsumer".equals(result))
	    {
	    	response.sendRedirect("viewcustomers");
	    	return null;
	    }
	    else
	    {
	    	return result;
	    }

  }
  
  
  @PostMapping("deleteallart")
  public String deleteallart(HttpServletRequest request,HttpServletResponse response)throws IOException
  {
	    String artId = request.getParameter("artId");

	    String result = CM.deleteallArtById(artId);
	    
	    if("deletedallart".equals(result))
	    {
	    	response.sendRedirect("viewarts");
	    	return null;
	    }
	    else
	    {
	    	return result;
	    }

  }
  
  
  
  
  @PostMapping("deletealltours")
  public String deletealltours(HttpServletRequest request, HttpServletResponse response) throws IOException {
      try {
          Long tourId = Long.parseLong(request.getParameter("tourId")); // Parse tourId to Long

          String result = CM.deleteAllToursById(tourId); // Call the service method with Long type

          if ("deletedalltours".equals(result)) { // Check success string
              response.sendRedirect("mytours"); // Redirect to the tours page
              return null;
          } else {
              return result;
          }
      } catch (NumberFormatException e) {
          return "Invalid tour ID format"; // Handle invalid ID format
      }
  }


  
  
  
  
  
  
  
  
  
  
  @PostMapping("deleteallorder")
  public String deleteallorder(HttpServletRequest request,HttpServletResponse response)throws IOException
  {
	    String orderId = request.getParameter("orderId").replace("#", "%23");

	    String result = CM.deleteallOrderById(orderId);
	    
	    if("deletedallorder".equals(result))
	    {
	    	response.sendRedirect("viewallorders");
	    	return null;
	    }
	    else
	    {
	    	return result;
	    }

  }
  
 
  @PostMapping("changepwd")
  public String changepwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
      HttpSession session = request.getSession();
      Consumer buyer = (Consumer) session.getAttribute("cms");
      
      String currentpwd = request.getParameter("currentpwd");
      String newpwd = request.getParameter("newpwd");
      
      if (buyer.getPassword().equals(currentpwd)) {
    	    if (currentpwd.equals(newpwd)) {
    	        response.sendRedirect("changepwd?message=sa456464"); 
    	        return null;
    	    } else {
    	        String result = CM.changePassword(buyer.getUserid(), newpwd);

    	        if ("changed".equals(result)) {
    	        	buyer.setPassword(newpwd);
    	        	session.setAttribute("cms", buyer);
    	            response.sendRedirect("changepwd?message=w3dhsur34s");
    	            return null;
    	        } else {
    	            return result;
    	        }
    	    }
    	} else {
    	    response.sendRedirect("changepwd?message=failure");
    	    return null;
    	}
  }

  @PostMapping("changespwd")
  public String changespwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
      HttpSession session = request.getSession();
      Consumer seller = (Consumer) session.getAttribute("cms");
      
      String currentpwd = request.getParameter("currentpwd");
      String newpwd = request.getParameter("newpwd");
      
      if (seller.getPassword().equals(currentpwd)) {
    	    if (currentpwd.equals(newpwd)) {
    	        response.sendRedirect("changespwd?message=sa456464"); 
    	        return null;
    	    } else {
    	        String result = CM.changePassword(seller.getUserid(), newpwd);

    	        if ("changed".equals(result)) {
    	             seller.setPassword(newpwd);
    	        	session.setAttribute("cms", seller);
    	            response.sendRedirect("changespwd?message=w3dhsur34s");
    	            return null;
    	        } else {
    	            return result;
    	        }
    	    }
    	} else {
    	    response.sendRedirect("changespwd?message=failure");
    	    return null;
    	}
  }
  
  @PostMapping("changeapwd")
  public String changeapwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
      HttpSession session = request.getSession();
      Consumer admin = (Consumer) session.getAttribute("cms");
      
      String currentpwd = request.getParameter("currentpwd");
      String newpwd = request.getParameter("newpwd");
      
      if (admin.getPassword().equals(currentpwd)) {
    	    if (currentpwd.equals(newpwd)) {
    	        response.sendRedirect("changeapwd?message=sa456464"); 
    	        return null;
    	    } else {
    	        String result = CM.changePassword(admin.getUserid(), newpwd);

    	        if ("changed".equals(result)) {
    	             admin.setPassword(newpwd);
    	        	session.setAttribute("cms", admin);
    	            response.sendRedirect("changeapwd?message=w3dhsur34s");
    	            return null;
    	        } else {
    	            return result;
    	        }
    	    }
    	} else {
    	    response.sendRedirect("changeapwd?message=failure");
    	    return null;
    	}
  }
  
  
  @PostMapping("changecpwd")
  public String changecpwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
      HttpSession session = request.getSession();
      Consumer curator = (Consumer) session.getAttribute("cms");
      
      String currentpwd = request.getParameter("currentpwd");
      String newpwd = request.getParameter("newpwd");
      
      if (curator.getPassword().equals(currentpwd)) {
    	    if (currentpwd.equals(newpwd)) {
    	        response.sendRedirect("changecpwd?message=sa456464"); 
    	        return null;
    	    } else {
    	        String result = CM.changePassword(curator.getUserid(), newpwd);

    	        if ("changed".equals(result)) {
    	             curator.setPassword(newpwd);
    	        	session.setAttribute("cms", curator);
    	            response.sendRedirect("changecpwd?message=w3dhsur34s");
    	            return null;
    	        } else {
    	            return result;
    	        }
    	    }
    	} else {
    	    response.sendRedirect("changecpwd?message=failure");
    	    return null;
    	}
  }
  
  
  
  
 
 
  
  
}
	
	
	



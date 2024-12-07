package klu.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import klu.repository.ArtregRepository;
import klu.repository.ArttourRepository;
import klu.repository.ConsumerRepository;
import klu.repository.ShippingRepository;


@Service
public class ConsumerManager {

	@Autowired
	ConsumerRepository CR;
	
	@Autowired
	ArtregRepository AR;
	
	@Autowired
	ArttourRepository TR;
	
	@Autowired
	ShippingRepository SR;
	

	
	public String insertUser(Consumer C) 
	{
		try
		{
			if(CR.validateEmail(C.email)>0)
			return "alredyexist";
			CR.save(C);
			return "redirectLogin";
		}
		catch (Exception e)
		{
			return e.getMessage();
		}
	}

	public Consumer loginUser(String cemail,String pwd)
	{
		try
		{
			Consumer C = CR.loginvalidate(cemail, pwd);
			return C;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	public String insertArt(Artreg A) 
	{
		try
		{

			AR.save(A);
			return "artsuccess";
		}
		catch (Exception e)
		{
			return e.getMessage();
		}
		
	}
	
	
	public String insertTour(ArtFair artfair) 
	{
		try
		{

			TR.save(artfair);
			return "artsuccess";
		}
		catch (Exception e)
		{
			return e.getMessage();
		}
		
	}
	
	public String insertship(Shipping S) 
	{
		try
		{

			SR.save(S);
			return "ordersuccess";
		}
		catch (Exception e)
		{
			return e.getMessage();
		}
		
	}
	
	public List<Artreg> getAllArts() 
	{
		return AR.findAll();
	}
	
	
	public List<Artreg> getMyArts(String sellerid) 
	{
		return AR.findBySellerid(sellerid);
	}
	
	
	public List<ArtFair> getMyTours(String curatorId)
	{
	    return TR.findByCuratorId(curatorId);
	}
	
	
	
	
	

	public List<Shipping> getmyorders(String buyerid)
	{
		return SR.findByBuyerid(buyerid);
	}

	public String cancelOrder(String orderid,String orderstatus) 
	{
		try
		{
		int rowsAffected = SR.cancelorder(orderid,orderstatus);
		 if (rowsAffected > 0) {
	            return "cancelorder"; 
	        }
		 else
		 {
	            return "No order found with that ID."; 

		 }
		}
		catch (Exception e) 
		{
			return e.getMessage();
		}
	}

	public List<Shipping> getvieworders(String artseller) {
		
		return SR.findByArtseller(artseller);
	}

	public String updateMyArt(Artreg A) 
	{
		try
		{
			int rowsAffected = AR.updatemyart(A.getArtid(),A.getArttitle(),A.getArtcost(),A.getArtdimensions(),A.getArtmedium(),A.getArtdescription(),A.getAvailstatus());
			if(rowsAffected>0)
			{
				return "updateartsuccess";
			}
			else
			{
				return "No Art ID exist";
			}
					
		}
		catch (Exception e) 
		{
			return e.getMessage();
		}
		
	}

	public String deleteArtById(String artId) 
	{
		try
		{
			int arteffected = AR.deleteArtById(artId);
			if(arteffected>0)
			{
				return "deletedart";
			}
			else
			{
				return "No Art Deleted";
			}
			
		}
		catch (Exception e) 
		{
			return e.getMessage();
		}
	}

	public String updateOrder(String orderid, String orderstatus) 
	{
		try
		{
		int rowsAffected = SR.updateorder(orderid,orderstatus);
		 if (rowsAffected > 0) {
	            return "updatedorder"; 
	        }
		 else
		 {
	            return "No order found with that ID."; 

		 }
		}
		catch (Exception e) 
		{
			return e.getMessage();
		}
	}

	public List<Consumer> getviewcustomers() 
	{
	return CR.findAll();
	}

	

	public String updateUser(Long userid,String name, String gender, String contactno, String address, String dob,
			String artPreference, String newsletter) {
		try
		{
		int rowsAffected = CR.updateCustomer(userid,name,gender,contactno,address,dob,artPreference,newsletter);
		 if (rowsAffected > 0) {
	            return "updateduser"; 
	        }
		 else
		 {
	            return "No user found with that ID."; 

		 }
		}
		catch (Exception e) 
		{
			return e.getMessage();
		}
	}

	public String deleteCustomerById(String userId)
	{
		try
		{
			int consumereffected = CR.deleteCustomerById(userId);
			if(consumereffected>0)
			{
				return "deletedconsumer";
			}
			else
			{
				return "No consumer Deleted";
			}
			
		}
		catch (Exception e) 
		{
			return e.getMessage();
		}
	}

	public List<Shipping> getallorders() 
	{
		return SR.findAll();
	}

	public String deleteallArtById(String artId) 
	{
		try
		{
			int allarteffected = AR.deleteArtById(artId);
			if(allarteffected>0)
			{
				return "deletedallart";
			}
			else
			{
				return "No all art Deleted";
			}
			
		}
		catch (Exception e) 
		{
			return e.getMessage();
		}
	}

	public String deleteallOrderById(String orderId) 
	{
		try
		{
			int allordereffected = SR.deleteOrderById(orderId);
			if(allordereffected>0)
			{
				return "deletedallorder";
			}
			else
			{
				return "No all art Deleted";
			}
			
		}
		catch (Exception e) 
		{
			return e.getMessage();
		}
	}
	
	
	public String deleteAllToursById(Long tourId) {
	    try {
	        int allToursAffected = TR.deleteTourById(tourId); // Use the correct service/repository reference
	        if (allToursAffected > 0) {
	            return "deletedalltours"; // Return success message
	        } else {
	            return "No tours deleted"; // Return failure message
	        }
	    } catch (Exception e) {
	        return e.getMessage(); // Return exception message in case of error
	    }
	}

	
	
	
	

	public String changePassword(Long userid, String newpwd)
	{
		try
		{
			int changepwdeffected = CR.changepass(userid,newpwd);
			if(changepwdeffected>0)
			{
				return "changed";
			}
			else
			{
				return "password failure";
			}
			
		}
		catch (Exception e) 
		{
			return e.getMessage();
		}	}

	public String updateAllArt(Artreg A) {
		try
		{
			int rowsAffected = AR.updateallart(A.getArtid(),A.getArttitle(),A.getArtcost(),A.getArtdimensions(),A.getArtmedium(),A.getArtdescription(),A.getAvailstatus(),A.getSellerid(),A.getSellername());
			if(rowsAffected>0)
			{
				return "updateallart";
			}
			else
			{
				return "No Art ID exist";
			}
					
		}
		catch (Exception e) 
		{
			return e.getMessage();
		}
	}
	
	
	public String updateAllTours(ArtFair artFair) { 
	    try {
	        int rowsAffected = TR.updateAllTours(
	            artFair.getTourid(),
	            artFair.getName(),
	            artFair.getTdescription(),
	            artFair.getStartDate(),
	            artFair.getEndDate(),
	            artFair.getLocation(),
	            artFair.getCapacity(),
	            artFair.getEntryFee(),
	            artFair.getTheme()
	        );
	        
	        if (rowsAffected > 0) {
	            return "updatealltours"; // Success response
	        } else {
	            return "No Tour ID exists"; // When no rows are updated
	        }
	    } catch (Exception e) {
	        return "Error: " + e.getMessage(); // Improved error handling
	    }
	}


	
	
	
	
	

	

	public String updateAllOrder(Shipping S)
	{
		try 
		{
		int rowsAffected = SR.updateallorder(S.getOrderid(),S.getArtcost(),S.getArtdimensions(),S.getArtid(),S.getArtmedium(),S.getArtseller(),S.getArttitle(),S.getBuyerid(),S.getBuyername(),S.getOrderstatus(),S.getShippingaddress());
		if(rowsAffected>0)
		{
			return "updateallorder";
		}
		else
		{
			return "No Order ID exist";
		}
				
	}
	catch(Exception e) 
	{
		return e.getMessage();
	}
	

	}

	public List<ArtFair> getAllTours() 
	{
		return TR.findAll();
	}

	

}

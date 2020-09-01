package org.ecards.controllers.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.ecards.configuration.properties.Roles;
import org.ecards.entities.Card;
import org.ecards.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@Autowired
	private CardService cardService; 
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value = "/")
	public ModelAndView card(Authentication authentication,
			@RequestParam(required = false, name = "pageNum")  Integer pageNum,
			@RequestParam(required = false, name = "pageSize") Integer pageSize) {
		
		if(authentication == null) {
			ModelAndView modelAndView = new ModelAndView("redirect:/login");
		    return modelAndView;
		}
		
		ModelAndView modelAndView = new ModelAndView("Index");	
		modelAndView.addObject("createdcard", new Card());
		modelAndView.addObject("editedcard", new Card());
		modelAndView.addObject("deletededcard", new Card());
		modelAndView.addObject("isAdmin", authentication.getAuthorities().iterator().next().getAuthority().equals(Roles.ROLE_ADMIN.value));
		
		String userName = authentication.getName();
		
		if(pageNum == null)
			pageNum  = 0;
		
		if(pageSize == null)
			pageSize = 5;

		
		if(authentication.getAuthorities().iterator().hasNext()) {
			if(authentication.getAuthorities().iterator().next().getAuthority().equals(Roles.ROLE_USER.value)) {
				modelAndView.addObject("numOfPages", cardService.getCountOfCardPagesPerUser(userName, pageSize));
				modelAndView.addObject("pageCards" , cardService.getPageCardsPerUser(userName,pageNum, pageSize));
			}else {
				modelAndView.addObject("numOfPages", cardService.getCountOfAllCardPages(pageSize));
				modelAndView.addObject("pageCards", cardService.getAllPageCards(pageNum, pageSize));
			}
		}
		
		
		return modelAndView;
	}
	
	@PostMapping(value = "/cards")
	public ModelAndView cards(Authentication authentication, HttpServletRequest request) {
		
		if(authentication == null) {
			ModelAndView modelAndView = new ModelAndView("redirect:/login");
		    return modelAndView;
		}
		
		String userName   = authentication.getName();
		String cardNum = request.getParameter("cardNum");

		if(cardNum.isBlank()) {
			ModelAndView modelAndView = new ModelAndView("redirect:/");
		    return modelAndView;
		} 
		
		ModelAndView modelAndView = new ModelAndView("Index");	
		modelAndView.addObject("createdcard", new Card());
		modelAndView.addObject("editedcard", new Card());
		modelAndView.addObject("deletededcard", new Card());
		modelAndView.addObject("searchNum", cardNum);
		modelAndView.addObject("isAdmin", authentication.getAuthorities().iterator().next().getAuthority().equals(Roles.ROLE_ADMIN.value));
		
		
		
		if(authentication.getAuthorities().iterator().hasNext()) {
			if(authentication.getAuthorities().iterator().next().getAuthority().equals(Roles.ROLE_USER.value)) {
				modelAndView.addObject("numOfPages", 0);
				modelAndView.addObject("pageCards" , cardService.getPageCardsPerUserPerNum(userName, cardNum));
			}else {
				modelAndView.addObject("numOfPages", 0);
				modelAndView.addObject("pageCards" , cardService.getAllPageCardsPerNum(cardNum));
			}
		}
		
		return modelAndView;
		
	}
	
	@PostMapping("/createCard")
	public ModelAndView createCard(Authentication authentication, @ModelAttribute("createdcard") @Valid Card createdcard, BindingResult result) {
		
		if(authentication == null) {
			ModelAndView modelAndView = new ModelAndView("redirect:/login");
		    return modelAndView;
		}
		
		ModelAndView modelAndView =  new ModelAndView("redirect:/");
		String userName = authentication.getName();
		createdcard.setUserName(userName);
		
		if(result.hasErrors()) {
			System.err.println(result.getAllErrors().toString());
			logger.error("Card Creation Error : "+result.getAllErrors().toString());
			modelAndView = new ModelAndView("redirect:/");
		}else {
			if(authentication.getAuthorities().iterator().hasNext()) {
				if(authentication.getAuthorities().iterator().next().getAuthority().equals(Roles.ROLE_USER.value)) {	
					cardService.createCard(createdcard);
					modelAndView = new ModelAndView("redirect:/");
			    }else {
					System.err.println("User is not authorized to do the action create");
					logger.error("Card Creation Error : " + "User is not authorized to do the action create");		
			    }
			}else {
				System.err.println("User is not authorized to do the action create");
				logger.error("Card Creation Error : " + "User is not authorized to do the action create");
			}
		}
		
		return modelAndView;
	}
	
	@PostMapping("/editCard")
	public ModelAndView editCard(Authentication authentication, @ModelAttribute("editedcard") @Valid Card editedcard, BindingResult result) {
		
		if(authentication == null) {
			ModelAndView modelAndView = new ModelAndView("redirect:/login");
		    return modelAndView;
		}
		
		ModelAndView modelAndView =  new ModelAndView("redirect:/");
		
		if(result.hasErrors()) {
			System.err.println(result.getAllErrors().toString());
			logger.error("Card Creation Error : "+result.getAllErrors().toString());
			modelAndView = new ModelAndView("redirect:/");
		}else {
			if(authentication.getAuthorities().iterator().hasNext()) {
				if(authentication.getAuthorities().iterator().next().getAuthority().equals(Roles.ROLE_USER.value)) {	
					cardService.updateCard(editedcard);
					modelAndView = new ModelAndView("redirect:/");
			    }else {
					System.err.println("User is not authorized to do the action edit");
					logger.error("Card Creation Error : " + "User is not authorized to do the action edit");		
			    }
			}else {
				System.err.println("User is not authorized to do the action edit");
				logger.error("Card Creation Error : " + "User is not authorized to do the action edit");
			}
		}
		
		return modelAndView;
	}
	
	@PostMapping("/deleteCard")
	public ModelAndView deleteCard(Authentication authentication, @ModelAttribute("deletedCard") Card deletedCard) {
		
		if(authentication == null) {
			ModelAndView modelAndView = new ModelAndView("redirect:/login");
		    return modelAndView;
		}
		
		ModelAndView modelAndView =  new ModelAndView("redirect:/");
		
		if(authentication.getAuthorities().iterator().hasNext()) {
			if(authentication.getAuthorities().iterator().next().getAuthority().equals(Roles.ROLE_USER.value)) {	
				cardService.deleteCard(deletedCard);
				modelAndView = new ModelAndView("redirect:/");
		    }else {
				System.err.println("User is not authorized to do the action edit");
				logger.error("Card Creation Error : " + "User is not authorized to do the action edit");		
		    }
		}else {
			System.err.println("User is not authorized to do the action edit");
			logger.error("Card Creation Error : " + "User is not authorized to do the action edit");
		}
		
		
		return modelAndView;
	}
	
	
}

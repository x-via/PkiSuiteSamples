package com.lacunasoftware.suite.sample.controller;

import com.lacunasoftware.restpki.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.lacunasoftware.suite.sample.util.*;

import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthenticationRestPkiController {

	/**
	 * This action initiates an authentication with REST PKI and renders the authentication page.
	 */
	@RequestMapping(value = "/authentication-restpki", method = {RequestMethod.GET})
	public String get(Model model, HttpServletResponse response) throws RestException {

		// Get an instance of the Authentication class.
		Authentication auth = new Authentication(Util.getRestPkiClient());

		// Call the startWithWebPki() method, which initiates the authentication. This yields the
		// "token", a 22-character case-sensitive URL-safe string, which represents this
		// authentication process. We'll use this value to call the signWithRestPki() method on the
		// Web PKI component (see file signature-form.js) and also to call the completeWithWebPki()
		// method on the post() method below (this should not be mistaken with the API access
		// token). We have encapsulated the security context choice on Util.java.
		String token = auth.startWithWebPki(Util.getSecurityContextId());

		// The token acquired above can only be used for a single authentication attempt. In order
		// to retry the signature it is necessary to get a new token. This can be a problem if the
		// user uses the back button of the browser, since the browser might show a cached page
		// that we rendered previously, with a now stale token. To prevent this from happening, we
		// call the method Util.setNoCacheHeaders(), which sets HTTP headers to prevent caching of
		// the page.
		Util.setNoCacheHeaders(response);

		// Render the authentication page (templates/index.html).
		model.addAttribute("token", token);
		return "authentication-restpki/index";
	}

	/**
	 * This action receives the form submission from the view. We'll call REST PKI to validate the
	 * authentication.
	 */
	@RequestMapping(value = "/authentication-restpki", method = {RequestMethod.POST})
	public String post(
		@RequestParam(value = "token") String token,
		Model model
	) throws RestException {

		// Get an instance of the Authentication class.
		Authentication auth = new Authentication(Util.getRestPkiClient());

		// Call the completeWithWebPki() method with the token (rendered in a hidden input field,
		// see file templates/index.html). This method finalizes the authentication
		// process, yielding a ValidationResults object which denotes whether the authentication
		// was successful or not.
		ValidationResults vr = auth.completeWithWebPki(token);

		// Check the authentication result.
		if (!vr.isValid()) {

			// If the authentication was not successful, we render a page showing what went wrong.

			// The toString() method of the ValidationResults object can be used to obtain the
			// checks performed, but the string contains tabs and new line characters for
			// formatting, which we'll convert to <br>'s and &nbsp;'s.
			String vrHtml = vr.toString()
				.replaceAll("\n", "<br>")
				.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");

			// Render the authentication failed page (templates/authentication-failed.html).
			model.addAttribute("vrHtml", vrHtml);
			return "authentication-restpki/failed";
		}

		// At this point, you have assurance that the certificate is valid according to the
		// SecurityContext passed on the first step (see method get()) and that the user is indeed
		// the certificate's subject. Now, you'd typically query your database for a user that
		// matches one of the certificate's fields, such as userCert.getEmailAddress() or
		// userCert.getPkiBrazil().getCpf() (the actual field to be used as key depends on your
		// application's business logic) and set the user as authenticated with whatever web
		// security framework your application uses. For demonstration purposes, we'll just render
		// the user's certificate information.
		PKCertificate userCert = auth.getPKCertificate();

		// Render the authentication succeeded page (templates/authentication-success.html).
		model.addAttribute("userCert", userCert);
		return "authentication-restpki/success";
	}
}
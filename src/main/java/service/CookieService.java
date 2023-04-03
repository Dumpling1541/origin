package service;

import javax.servlet.http.Cookie;

public class CookieService {
	public static Cookie findByName(Cookie[] cookies, String name) {
		if(cookies == null) {
			return null;
		}
		Cookie found = null;
		for(Cookie c : cookies) {
			if(c.getName().equals(name)) {
				found = c;
				break;
			}
		}
		return found;
	}
}

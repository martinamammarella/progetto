package com.example.FINDWORK.controller;
 
	import com.example.FINDWORK.service.*;
	import org.json.simple.*;
	import com.example.FINDWORK.model.*;
	import com.example.FINDWORK.utils.*;
	import  java.util.*;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.bind.annotation.RestController;
	@RestController
	public class controller{
	public 	String fcity[]= new String[2];
	serviceAppImplements s= new serviceAppImplements();
	@PostMapping(value="/filtri")
		  public JSONArray getFiltro(@RequestBody JSONObject body){
			  JSONObject o = new JSONObject(body);
			  JSONArray city=((JSONArray)o.get("city"));
			  String citys[]= new String[city.size()];
			  JSONArray linguaggi=((JSONArray)o.get("linguaggi"));
			  String lin[]= new String[linguaggi.size()];
			  String remote=((String)o.get("remote"));
			  String oreLavoro=((String)o.get("oreLavoro"));
			  JSONArray array= new JSONArray();
			  int c=0;
			  for(Object ob:city) {
				  JSONObject ob2= new JSONObject();
				  citys[c]=(String)ob2.get("name");
			  }
			  for(Object ob:linguaggi) {
				  JSONObject ob2= new JSONObject();
				  citys[c]=(String)ob2.get("lin");
			  }
			  array=s.getFiltro(citys, lin, remote, oreLavoro);
			 
			  return array;
		  }
	@PostMapping(value="/statistiche")
				public ResponseEntity<Object> getStats(@RequestBody JSONObject body){
					JSONObject o= new JSONObject(body);
					  String city[]=((String[])o.get("city"));
					  String linguaggi[]=((String[])o.get("linguaggi"));
					  String sito=((String)o.get("sito"));
					  String data=((String)o.get("oreLavoro"));
					  return new ResponseEntity <> (s.getStats(city, linguaggi, sito, data),HttpStatus.OK);
				}
	@GetMapping(value="/offerte")
	 public ArrayList<Lavori> getOfferte(){
	 return	s.getOfferte();
			}
	 
	@GetMapping(value="/filtriprova")
	public ArrayList<Lavori> getfiltri(@RequestParam String parametro ) {
		return s.analisifiltro(parametro, s.getOfferte());
	 		  
	}
	}



